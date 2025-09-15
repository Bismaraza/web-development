// Exception Handling Example
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String inputPassword) throws Exception {
        if (this.password.equals(inputPassword)) {
            return true;
        } else {
            throw new Exception("Invalid login for user: " + username);
        }
    }

    public String getUsername() {
        return username;
    }
}

public class ExceptionHandlingDemo {
    public static void main(String[] args) {
        User student = new User("Ali123", "mypassword");

        try {
            if (student.login("wrongpass")) {
                System.out.println(student.getUsername() + " logged in successfully!");
            }
        } catch (Exception e) {
            System.out.println("⚠ Error: " + e.getMessage());
        }
    }
}
