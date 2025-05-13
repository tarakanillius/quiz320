package com.tarakan.cli;

import java.util.Scanner;
/**
 * Handles CLI user input with typed parsing and validation.
 */
public class CliInputHandler {
    /**
     * Scanner for user input.
     */
    private final Scanner scanner;
    /**
     * Creates an input handler using the provided Scanner.
     *
     * @param scanner the Scanner for user input
     */
    public CliInputHandler(Scanner scanner) {
        if (scanner == null) throw new IllegalArgumentException("Scanner cannot be null");

        this.scanner = scanner;
    }
    /**
     * Reads an integer from the user input.
     *
     * @return the integer input, or {@code null} if invalid
     */
    public Integer getIntInput() {
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    /**
     * Reads a string from the user input.
     *
     * @return the trimmed string input
     */
    public String getStringInput() {
        var temp = scanner.nextLine().trim();

        if (temp.isEmpty() || temp.isBlank()) throw new IllegalArgumentException("String cannot be empty");

        return temp;
    }
    /**
     * Reads a boolean from the user input.
     *
     * @param trueValue the string that should be interpreted as true (case-insensitive)
     * @return true if input equals trueValue, false otherwise
     */
    public boolean getBooleanInput(String trueValue) {
        if (trueValue == null) throw new IllegalArgumentException("trueValue cannot be null");

        return scanner.nextLine().trim().equalsIgnoreCase(trueValue);
    }
    /**
     * Reads a non-empty string from the user input.
     * <p>
     *     If the input is empty or blank, the user is prompted to enter a non-empty string.
     * </p>
     * @param prompt the prompt to display to the user
     * @return the non-empty string input
     */
    public String promptNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input;

            try {
                input = getStringInput();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Value cannot be empty.");
                continue;
            }

            return input;
        }
    }
    /**
     * Reads a positive integer from the user input.
     * <p>
     *     If the input is not a positive integer, the user is prompted to enter a positive integer.
     * </p>
     * @param prompt the prompt to display to the user
     * @return the positive integer input
     */
    public int promptPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            Integer input = getIntInput();

            if (input == null || input < 0) {
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }

            return input;
        }

    }
    /**
     * Reads an integer within a specified range from the user input.
     * <p>
     *     If the input is not within the specified range, the user is prompted to enter an integer within the range.
     * </p>
     * @param prompt the prompt to display to the user
     * @param minInclusive the minimum inclusive value
     * @param maxInclusive the maximum inclusive value
     * @return the integer input within the specified range
     */
    public int promptBoundedInt(String prompt, int minInclusive, int maxInclusive) {
        while (true) {
            System.out.print(prompt);
            Integer input = getIntInput();

            if (input == null || input < minInclusive || input > maxInclusive) {
                System.out.println("Invalid input. Please enter a number between " + minInclusive + " and " + maxInclusive + ".");
                continue;
            }

            return input;
        }
    }
    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}