package com.nhnacademy;

import java.util.Scanner;

public class TerminalInNode extends InputNode {
    Scanner scanner;

    public TerminalInNode() {
        this(1);
    }

    public TerminalInNode(int count) {
        super(count);
    }

    void perform() {
        String line = scanner.nextLine();
        StringMessage message = new StringMessage(line);
        output(message);
    }
}
