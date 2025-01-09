package br.com.vsi.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User defaultUser;

    @BeforeEach
    void setUp() {
        defaultUser = new User("John Doe", "john.doe@example.com");
    }

    @Test
    void shouldBeEqualsUser() {
        User equalsUser = new User("Other John Doe", "other.john.doe@example.com");
        equalsUser.setId(defaultUser.getId());

        assertEquals(defaultUser, equalsUser);
    }

    @Test
    void shouldBeDiffrentUser() {
        User diffUser = new User("John Doe", "john.doe@example.com");

        assertNotEquals(defaultUser, diffUser);
    }

    @Test
    void shouldBeVerifyEqualHashCode() {
        HashSet<User> users = new HashSet<>();

        User equalsUser = new User("Other John Doe", "other.john.doe@example.com");
        equalsUser.setId(defaultUser.getId());

        User diffUser = new User("John Doe", "john.doe@example.com");

        users.add(defaultUser);
        users.add(equalsUser);
        users.add(diffUser);

        assertEquals(2, users.size());
    }
}
