package com.nhnacademy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SimpleMB {
    // request 만드는 용
    public static byte[] makeReadHoldingRegistersRequest(int address, int quantity) {
        byte[] frame = new byte[5];
        ByteBuffer b = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);

        // PDU의 function code
        frame[0] = 0x03;

        // PDU의 data
        b.putInt(address);
        frame[1] = b.get(2);
        frame[2] = b.get(3);

        b.clear();
        b.putInt(quantity);
        frame[3] = b.get(2);
        frame[4] = b.get(3);

        return frame;
    }

    // response 만드는 용
    public static byte[] makeReadHoldingRegistersResponse(int address, int[] registers){
        byte [] frame = new byte[1+ 1+ registers.length*2];    // Get 같은 경우 정해져 잇다

        frame[0] = 0x03;

        // register가 big endian 구조라 다시 뒤집어 줘야 한다
        frame[1] = (byte) (registers.length*2);

        for (int i = 0; i < registers.length; i++) {
            frame[2 + i * 2] = (byte) ((registers[i] >> 8) & 0xFF);
            frame[2 + i * 2 + 1] = (byte) (registers[i] & 0xFF);
        }

        return frame;
    }

    public static byte[] makeReadInputRegistersResponse(int address, int[] registers) {
        byte [] frame = new byte[1+ 1+ registers.length*2];
        System.out.println(registers.length);
        frame[0] = 0x04;    // Function code

        frame[1] = (byte)(registers.length*2);  // Byte count = 2 * N(갯수)

        // Input Registers = n * 2 bytes -> data를 2byte로 바꿔서 넣어줘야한다
        for (int i = 0; i < registers.length; i++) {
            frame[2+i*2] = (byte) ((registers[i] >> 8) & 0xFF); 
            //?? 여기 왜 0으로 만들어줘야하는거야? 만약 수가 frame 앞도 써야하는거면 어뜩할라고? ex. 304넣으면 어뜩할라고?
            // 1 0011 0000 >> 8 -> 0000 0001이 되서 frame[3],frame[4] 봤을때 비로소 원래 넣고 싶어하던 수가 된다

            frame[2+i*2+1] = (byte) (registers[i] & 0xFF);
        }

        return frame;
    }

    public static byte[] makeWriteSingleRegisterResponse(int address, int register) {
        byte [] frame = new byte[1 + 2 + 2];
        ByteBuffer b = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);

        frame[0] = 0x06;    // Function code

        b.putInt(address);
        frame[1] = b.get(2);     // aaddress
        frame[2] = b.get(3);

        b.clear();
        b.putInt(register);
        frame[3] = b.get(2);
        frame[4] = b.get(3);

        return frame;
    }

    public static byte[] makeWriteMutiRegistersResponse(int address, int quantity) {
        byte [] frame = new byte[1 + 2 + 2 ];
        ByteBuffer b = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);

        frame[0] = 0x10;

        b.putInt(address);
        frame[1] = b.get(2);
        frame[2] = b.get(3);

        b.clear();
        b.putInt(quantity);
        frame[3] = b.get(2);
        frame[4] = b.get(3);

        return frame;

    }

    public static byte[] addMBAP(int transactionId, int unitId, byte[] pdu) {
        byte [] adu = new byte[7+pdu.length];   // header가 7byte에 나머지는 pdu 길이이다
        ByteBuffer b = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);

        b.putInt(transactionId);

        adu[0] = b.get(2);
        adu[1] = b.get(3);
        adu[2] = 0;
        adu[3] = 0;
        adu[4] = 0;             // 상의는 길이가 0
        adu[5] = (byte)(pdu.length + 1);    // 여기서 음수 양수 문제가 생길 수 있다
        adu[6] = (byte)unitId;
        System.arraycopy(pdu, 0, adu, 7, pdu.length);
        return adu;

    }
}
