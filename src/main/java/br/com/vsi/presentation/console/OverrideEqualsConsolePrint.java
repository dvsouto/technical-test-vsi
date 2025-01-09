package br.com.vsi.presentation.console;

import br.com.vsi.domain.User;

/**
 * Explain how you would use a design pattern to decouple your code from a third-party
 * library that might be replaced in the future. Describe the advantages and limitations of
 * your chosen approach, and provide a small code snippet illustrating its application.
 */
public class OverrideEqualsConsolePrint {
    public OverrideEqualsConsolePrint() {}

    public void execute() {
        System.out.println("-- Override equals: ------------------\n");

        // Create user
        User user = new User("John Doe", "john.doe@example.com");

        // Create an equal user
        User userEquals = new User(user.getName(), user.getEmail());
        userEquals.setId(user.getId());

        // Create a different user
        User userDiff = new User("John Doe", "john.doe@example.com");

        // Print if user equals userEquals
        System.out.println("The user " + user.getName() + " with de id " + user.getId() + " is equals to user " + userEquals.getName() + " with id " + userEquals.getId() + ": " + user.equals(userEquals));

        // Print if user equals userDiff
        System.out.println("The user " + user.getName() + " with de id " + user.getId() + " is equals to user " + userDiff.getName() + " with id " + userDiff.getId() + ": " + user.equals(userEquals));
    }
}
