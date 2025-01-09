package br.com.vsi.application;

import java.util.ArrayList;
import java.util.List;

public class Anagram {
    private List<String> anagramsList;

    /**
     * Create the anagram list
     * @param input The input list
     * @return The anagram list
     */
    public List<String> execute(List<String> input) {
        this.anagramsList = new ArrayList<>();

        // Perform validation
        this.validate(input);

        // Start the generation of anagram
        this.generateRecursive(input, new ArrayList<>(), new StringBuilder());

        return this.anagramsList;
    }

    // Validate the input and throws exception if invalid
    private void validate(List<String> input) throws IllegalArgumentException {
        // Check if is empty
        if (input.isEmpty()){
            throw new IllegalArgumentException("The list cannot be empty");
        }

        // Check if is letter
        for(String itData : input) {
            if (itData.length() > 1 || ! Character.isLetter(itData.charAt(0))) {
                throw new IllegalArgumentException("The value \"" + itData + "\" is not a letter");
            }
        }
    }

    /**
     * Recursive method to generate anagrams
     * @param input The input list
     * @param lettersUsed The state of letters which have been used in the current anagram
     * @param itAnagramBuffer The string builder of current anagram
     */
    private void generateRecursive(List<String> input, List<String> lettersUsed, StringBuilder itAnagramBuffer) {
        // If size of the buffer is equals the size of the input, then add it to the anagram list
        if (itAnagramBuffer.length() == input.size()) {
            this.anagramsList.add(itAnagramBuffer.toString());
        }

        // Iterate over each letter in the input
        for (String letter : input) {
            // Skip this letter if it has already been used in the current iteration
            if (lettersUsed.contains(letter)) {
                continue;
            }

            // Append the letter to string builder and mark it as used
            itAnagramBuffer.append(letter);
            lettersUsed.add(letter);

            // Call this method recursively with the updated state
            this.generateRecursive(input, lettersUsed, itAnagramBuffer);

            // Remove the last letter from the buffer and unmark it as used
            itAnagramBuffer.deleteCharAt(itAnagramBuffer.length() - 1);
            lettersUsed.remove(letter);
        }
    };
}
