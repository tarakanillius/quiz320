package com.tarakan.cli;

import java.util.Scanner;
/**
 * Class for handling CLI input.
 * <p>
 * This class provides methods for handling various types of input in the CLI.
 * It uses a Scanner to read input from the user.
 * It provides methods for getting integer, string, and boolean inputs.
 * It also provides a method for closing the Scanner.
 * </p>
 */
public class CliInputHandler {
    /**
     * Scanner for reading input from the user.
     */
    private final Scanner scanner;
    /**
     * Constructor for CliInputHandler.
     * <p>
     * This constructor initializes the CliInputHandler with the provided Scanner.
     * </p>
     * @param scanner Scanner for reading input from the user.
     */
    public CliInputHandler(Scanner scanner) {this.scanner = scanner;}
    /**
     * Method to get an integer input from the user.
     * @return The integer input from the user.
     */
    public int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    /**
     * Method to get a string input from the user.
     * @return The string input from the user.
     */
    public String getStringInput() {
        return scanner.nextLine();
    }
    /**
     * Method to get a boolean input from the user.
     * @param trueValue The string value that represents true.
     * @return The boolean input from the user.
     */
    public boolean getBooleanInput(String trueValue) {
        return scanner.nextLine().equalsIgnoreCase(trueValue);
    }
    /**
     * Method to close the Scanner.
     */
    public void close() {
        scanner.close();
    }
}
