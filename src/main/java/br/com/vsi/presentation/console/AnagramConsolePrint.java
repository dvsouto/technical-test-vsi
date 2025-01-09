package br.com.vsi.presentation.console;

import br.com.vsi.application.Anagram;

import java.util.List;

/**
 * 1. Write a Java program to solve the following problem:
 * You are tasked with creating a utility function for a text-processing application. The
 * function must generate all possible anagrams from a given group of distinct letters. For
 * example, the input {a, b, c} should return the output: abc, acb, bac, bca, cab, cba.
 * Additional Requirements:
 * 1. The program should accept any group of distinct letters as input and produce the
 * correct result.
 * 2. Optimize for readability and clarity.
 * 3. Include basic validation (e.g., ensure the input is non-empty and contains only
 * letters).
 * 4. Write unit tests to validate your function with at least three different test cases,
 * including edge cases (e.g., input with a single letter or empty input).
 * 5. Document your code clearly, explaining the logic for generating anagrams.
 */
public class AnagramConsolePrint {
    public AnagramConsolePrint() {};

    public void execute() {
        List<String> input = List.of("A", "B", "C");

        System.out.println("-- Anagrams of A, B and C ------------\n");

        // Execute the generate anagram and print each one
        new Anagram().execute(input).forEach(System.out::println);
    }
}
