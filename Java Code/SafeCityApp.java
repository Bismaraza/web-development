import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SafeCityApp {

    // === User Class ===
    static class User {
        String name, email, phone, passwordHash;

        User(String name, String email, String phone, String password) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.passwordHash = hashPassword(password);
        }

        boolean checkPassword(String password) {
            return passwordHash.equals(hashPassword(password));
        }

        private static String hashPassword(String password) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashed = md.digest(password.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : hashed) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // === Data Store ===
    static Map<String, User> emailUsers = new HashMap<>();
    static Map<String, User> phoneUsers = new HashMap<>();

    // === Validation Regex ===
    static final String EMAIL_REGEX = ".+@.+\\..+";
    static final String PHONE_REGEX = "\\d{10,13}";

    // === Register ===
    static void register(Scanner sc) {
        try {
            System.out.print("Enter name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter email: ");
            String email = sc.nextLine().trim();
            if (!email.matches(EMAIL_REGEX)) {
                throw new Exception("Invalid email format.");
            }

            System.out.print("Enter phone: ");
            String phone = sc.nextLine().trim();
            if (!phone.matches(PHONE_REGEX)) {
                throw new Exception("Invalid phone number.");
            }

            System.out.print("Enter password: ");
            String pass = sc.nextLine();

            if (emailUsers.containsKey(email) || phoneUsers.containsKey(phone)) {
                throw new Exception("User already exists with this email or phone.");
            }

            User newUser = new User(name, email, phone, pass);
            emailUsers.put(email, newUser);
            phoneUsers.put(phone, newUser);

            System.out.println("✅ Registration successful.");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // === Login ===
    static User login(Scanner sc) {
        try {
            System.out.println("Login using: 1. Email  2. Phone");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter email: ");
                String email = sc.nextLine().trim();
                if (!email.matches(EMAIL_REGEX)) throw new Exception("Invalid email format.");

                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                User u = emailUsers.get(email);
                if (u != null && u.checkPassword(pass)) {
                    System.out.println("✅ Login successful.");
                    return u;
                }
            } else if (choice.equals("2")) {
                System.out.print("Enter phone: ");
                String phone = sc.nextLine().trim();
                if (!phone.matches(PHONE_REGEX)) throw new Exception("Invalid phone format.");

                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                User u = phoneUsers.get(phone);
                if (u != null && u.checkPassword(pass)) {
                    System.out.println("✅ Login successful.");
                    return u;
                }
            }
            System.out.println("❌ Login failed.");
            return null;
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            return null;
        }
    }

    // === Profile Update ===
    static void updateProfile(Scanner sc, User user) {
        System.out.print("Enter new name (leave blank to keep old): ");
        String n = sc.nextLine().trim();
        if (!n.isEmpty()) user.name = n;

        System.out.print("Enter new phone (leave blank to keep old): ");
        String p = sc.nextLine().trim();
        if (!p.isEmpty()) {
            if (!p.matches(PHONE_REGEX)) {
                System.out.println("❌ Invalid phone number. Skipped.");
            } else {
                phoneUsers.remove(user.phone);
                user.phone = p;
                phoneUsers.put(p, user);
            }
        }

        System.out.println("✅ Profile updated: " + user.name + ", " + user.phone);
    }

    // === Main Menu ===
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== SafeCityApp Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice (1-3): ");

            String opt = sc.nextLine();

            if (opt.equals("1")) {
                register(sc);
            } else if (opt.equals("2")) {
                User user = login(sc);
                if (user != null) {
                    updateProfile(sc, user);
                }
            } else if (opt.equals("3")) {
                System.out.println("👋 Goodbye!");
                break;
            } else {
                System.out.println("❌ Invalid option.");
            }
        }

        sc.close();
    }
}
