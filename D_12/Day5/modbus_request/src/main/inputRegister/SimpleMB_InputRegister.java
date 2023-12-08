package main.inputRegister;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SimpleMB_InputRegister {
    public static byte[] makeReadInputRegisterRequeset(int address, int quantity){
        byte [] frame = new byte[5];    // Get 같은 경우 정해져 잇다

        // allocate(?) = 메모리 ?개를 던져줌, 메모리는 주로 1byte를 준다
        ByteBuffer b = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);  // big endian을 위해 만듦 -> 젤 큰게 마지막에 있다
        b.putInt(address);  // address = 1~6535까지만 들어간다 = 2 byte로 이루어져있다
        // -> address는 00~~~~로 이루어져 있다 why? address가 int 형태로 4 bytes이기 때문에 00을 붙여서 4bytes로 만들어준 것이다
        // BIG_ENDIAN은 예시로 설명한다
        // 2947이 주소값인 것을 b를 거치면 29/47로 나뉘어지는데 big endian은 수가 더 큰 값이 앞으로 가야한다
        // 근데 우리가 넣어지는 2번째와 3번째는 29, 47로 들어가는데 
        // 29 와 47 로 비교되는게 아니라 2900과 47로 비교되기 때문이다
        // quantity도 동일 => 범위가 보면 0x0000~0x007D까진데 얘 2byte 작성해야해서 0이더라도 작성해야한다

        // PDU의 FunctionCode
        frame[0] = 0x04;                // FunctionCode

        // PDU의 data
        frame[1] = b.get(2);            // 1,2는 address를 줘야하는데 big endian을 생각해야한다
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
