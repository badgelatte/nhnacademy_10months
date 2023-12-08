package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ModbusServer extends Thread{
    public static void main(String[] args) {
        int[] holdingRegisters = new int[100];

        // injection
        // 0,1,2,3,4,5,6,7,8,9 값을 modbus에 보낸다
        for (int i = 0; i < holdingRegisters.length; i++) {
            holdingRegisters[i] = i;
        }

        byte unitId = 1;
        try (ServerSocket serverSocket = new ServerSocket(11502)) {   // 서버 11502port로 열기
            // while(!Thread.currentThread().isInterrupted())
            try(Socket socket = serverSocket.accept()){               // 연결하려는 서버 허락하기
                BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
                BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                while(socket.isConnected()){        // socket이 연결되어 있다면
                    byte[] inputBuffer = new byte[1024];
                
                    // socket을 통해 request를 받는다
                    int receivedLength = inputStream.read(inputBuffer,0,inputBuffer.length);    
                    // inputStream을 0~inputBuffer 크기까지 읽어서 inputBuffer에 넣는다
                    // read의 return 값은 버퍼로 읽은 total bytes 수
                    System.out.println("inputBufffer : " + Arrays.toString(inputBuffer)+"/5555");
                    System.out.println("reL : " + receivedLength);  // 12
                    if(receivedLength > 0){         // 받은 값들이 있을 경우
                        // request = [0, 2, 0, 0, 0, 6, 1, 3, 0, 0, 0, 10]
                        System.out.println(Arrays.toString(Arrays.copyOfRange(inputBuffer, 0, receivedLength)));    // 받은 값들 확인하기

                        if((receivedLength > 7) && (6 + inputBuffer[5]) == receivedLength){   
                            // 길이 먼저 체크 -> 헤더 길이 최소 7/
                            // -> 6 = header길이에서 하나뺀 거(Unit Identifier 제외한 길이) , inputBuffer[5] = 들어올 data 길이
                            
                            if (unitId == inputBuffer[6]) { // unitId 체크
                                int transactionId = inputBuffer[0] << 8 | inputBuffer[1];   // 00 | 03 => 03으로 transactionId = 00000000 00000011
                                // inputBuffer[0] << 8 해서 8 bit 자리를 왼쪽으로 밀어버린다 그러고 inputBuffer[1]이랑 합친다
                                System.out.println("transactionId : " + transactionId);
                                int functionCode = inputBuffer[7];      // 헤더 파싱 해주는게 젤 좋다
                                System.out.println("functionCode : " + functionCode);

                                switch (functionCode) {
                                    case 3: // 3을 FC_Read_Holding_Registers로 설정해주면 좋다
                                        int address = (inputBuffer[8] << 8) | inputBuffer[9];       // address = 2bytes이기 때문에 이렇게 만든다
                                        System.out.println("address : " + address);
                                        int quantity = (inputBuffer[10] << 8) | inputBuffer[11];
                                        System.out.println("quantity : " + quantity);

                                        // address + quantity = PDU의 Data
                                        // address + quantity: 시작 주소부터 갯수만큼 주겠다
                                        if (address + quantity < holdingRegisters.length) {
                                            System.out.println("Address : " + address +", Quantity: " + quantity);

                                            outputStream.write(SimpleMB.addMBAP(transactionId, unitId, 
                                                    SimpleMB.makeReadHoldingRegistersResponse(address, 
                                                    Arrays.copyOfRange(holdingRegisters, 0, quantity))));
                                            outputStream.flush();
                                        }
                                        break;
                                    
                                    case 4:
                                        address = (inputBuffer[8] << 8) | inputBuffer[9];       // address = 2bytes이기 때문에 이렇게 만든다
                                        // 범위가 0x007D라고 inputBuffer[10]에 있는 걸 버리는 게 맞을까?
                                        quantity =(inputBuffer[10] << 8) | inputBuffer[11];    // 125(=0x007D)까지 입력가능

                                        if (address + quantity < holdingRegisters.length) {
                                            System.out.println("Address : " + address +", Quantity: " + quantity);

                                            outputStream.write(SimpleMB.addMBAP(transactionId, unitId, 
                                                    SimpleMB.makeReadInputRegistersResponse(address, 
                                                    Arrays.copyOfRange(holdingRegisters, 0, quantity))));
                                            outputStream.flush();
                                        }
                                        break;

                                    case 6:
                                        address = (inputBuffer[8] << 8) | inputBuffer[9];       // address = 2bytes이기 때문에 이렇게 만든다
                                        quantity = (inputBuffer[10] << 8) | inputBuffer[11];    // 125(=0x007D)까지 입력가능
                                        // address+ quantity = PDU의 data로 holdingRegister가 들어가는 곳이다
                                        if (address + quantity < holdingRegisters.length) {
                                            System.out.println("Address : " + address +", Quantity: " + quantity);

                                            outputStream.write(SimpleMB.addMBAP(transactionId, unitId, 
                                                    SimpleMB.makeWriteSingleRegisterResponse(address, quantity)));
                                            outputStream.flush();
                                        }
                                        break;

                                    case 16:
                                        address = (inputBuffer[8] << 8) | inputBuffer[9];
                                        quantity = (inputBuffer[10] << 8) | inputBuffer[11];
                                        int byteCount = inputBuffer[12];
                                        
                                        // Registers Value
                                        byte[] value = Arrays.copyOfRange(inputBuffer, 13, inputBuffer.length-1);  

                                        if(address + quantity < holdingRegisters.length) {
                                            System.out.println("AdressL " + address + ", Quantity: " + quantity);

                                            outputStream.write(SimpleMB.addMBAP(transactionId, unitId, SimpleMB.makeWriteMutiRegistersResponse(address, quantity)));
                                            outputStream.flush();
                                        }
                                    default:
                                }
                            } else{
                                System.err.println("수신 패킷 길이가 잘못되었습니다.");
                            }
                        }
                    } else if(receivedLength < 0) {
                        break;
                    }
                }
            } catch(IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}