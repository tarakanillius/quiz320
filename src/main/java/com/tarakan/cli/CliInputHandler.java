package com.tarakan.cli;

import java.util.Scanner;

public class CliInputHandler {
    private final Scanner scanner;

    public CliInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
