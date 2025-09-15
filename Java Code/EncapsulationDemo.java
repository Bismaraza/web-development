// Encapsulation Example
class User {
    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Encapsulation through getters/setters
    public String getUsername() { 
        return username; 
    }
    public void setUsername(String username) { 
        this.username = username; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}

public class EncapsulationDemo {
    public static void main(String[] args) {
        User user = new User("bisma", "bisma123");

        // Accessing through getter
        System.out.println("Username: " + user.getUsername());

        // Securely changing password
        user.setPassword("newpass123");

        // Verifying password
        System.out.println("Login success? " + user.checkPassword("newpass123"));
    }
}
