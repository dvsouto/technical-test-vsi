package br.com.vsi.presentation.console;

import br.com.vsi.domain.User;

import java.util.HashSet;

/**
 * Explain how you would use a design pattern to decouple your code from a third-party
 * library that might be replaced in the future. Describe the advantages and limitations of
 * your chosen approach, and provide a small code snippet illustrating its application.
 */
public class OverrideHashCodeConsolePrint {
    public OverrideHashCodeConsolePrint(){}

    public void execute() {
        System.out.println("-- Override Hash code: ------------------\n");

        HashSet<User> users = new HashSet<>();

        User user = new User("John Doe", "john@example.com");
        User equalsUuid = new User("Joao da Silva", "joao@example.com");
        User diffUser = new User("Maria dos Santos", "maria@example.com");

        equalsUuid.setId(user.getId());

        users.add(user);
        users.add(equalsUuid);
        users.add(diffUser);

        System.out.println("-- Loop of Hash Map");
        for(User itUser : users) {
            System.out.println("Current user:\nID = " + itUser.getId() + "\nName:" + itUser.getName() + "\nE-mail:" + itUser.getEmail() + "\nHash Code:" + itUser.hashCode() + "\n");
        }

        System.out.println("-- End of Hash Map\n");

        System.out.println("Hash map size:" + users.size());
    }
}