package main.writeSingleRegister;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SimpleMB_WriteSingleRegister {
    public static byte[] makeWriteSingleRegister(int address, int value){
        byte [] frame = new byte[5];    // Get 같은 경우 정해져 잇다

        // allocate(?) = 메모리 ?개를 던져줌, 메모리는 주로 1byte를 준다
        ByteBuffer b = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);  // big endian을 위해 만듦 -> 젤 큰게 마지막에 있다
        b.putInt(address);  // address = 1~6535까지만 들어간다 = 2 byte로 이루어져있다
        
        // PDU의 FunctionCode
        frame[0] = 0x06;                // FunctionCode

        // PDU의 data
        frame[1] = b.get(2);
        frame[2] = b.get(3);

        b.clear();
        b.putInt(value);
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
