package model;

/**
 * Abstract User class representing common properties for Admin and Employee.
 */
public abstract class User {
    protected String id;
    protected String password;
    protected String name;
    protected String email;

    public User(String id, String password, String name, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
}