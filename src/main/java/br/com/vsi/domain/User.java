package br.com.vsi.domain;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;

    public User(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Check if user entity id is equals
     * @param obj Input object
     * @return Returns true if is equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User objAsUser = (User) obj;

        return this.getId().equals(objAsUser.getId());
    }

    /**
     * Generate hash code using user uuid
     * @return User entity hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id.toString());
    }
}
