package com.nhnacademy.test;

import com.nhnacademy.node.TCPServer;
import com.nhnacademy.node.TcpEcho;
import com.nhnacademy.wire.BufferedWire;
import com.nhnacademy.wire.Wire;

public class TestTcpEchoServer {
    public static void main(String[] args) {
        TCPServer server = new TCPServer("server");
        TcpEcho echo = new TcpEcho("echo");

        Wire wire1 = new BufferedWire();
        Wire wire2 = new BufferedWire();

        server.connectOutputWire(0, wire1);
        echo.connectInputWire(0, wire1);
        echo.connectOutputWire(0, wire2);
        server.connectInputWire(0, wire2);

        echo.start();
        server.start();
    }
}
