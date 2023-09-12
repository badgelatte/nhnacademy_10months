package com.nhnacademy.synchronization.exam05;

public class Data {
    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    public synchronized String receive() {  // data 받는 경우
        while (transfer) {  // 조건: transfer == true라면 -> 받을 수 있는 상태를 말한다.
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        transfer = true;

        String returnPacket = packet;
        notifyAll();
        return returnPacket;        // 가지고 있는 packet을 보낸다
    }

    public synchronized void send(String packet) {  // data 보내는 경우
        while (!transfer) { // 조건: transfer가 false라면 -> 받을 수 없는 상태라면
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        transfer = false;

        this.packet = packet;   // packet 소유
        notifyAll();

        
    }
}