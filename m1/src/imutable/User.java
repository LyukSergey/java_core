package imutable;

public class User {

    private final String name;
    private String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public User(User other) {
        this.name = other.name;
        this.role = other.role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    // Цей сеттер робить клас змінним!
    public void setRole(String role) {
        this.role = role;
    }
}
