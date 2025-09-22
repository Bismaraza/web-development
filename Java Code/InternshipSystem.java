import java.util.*;

// Enum for user roles
enum Role {
    STUDENT, COMPANY, ADMIN
}

// User class
class User {
    private String name;
    private String email;
    private String password;
    private Role role;
    private List<String> savedInternships = new ArrayList<>();

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public List<String> getSavedInternships() { return savedInternships; }

    public void updateProfile(String name) { this.name = name; }

    public void saveInternship(String internship) {
        if (!savedInternships.contains(internship)) savedInternships.add(internship);
    }
}

// Internship class
class Internship {
    private static int nextId = 1;
    private int id;
    private String title;
    private String location;
    private String field;
    private int duration;
    private int stipend;
    private String companyEmail;

    public Internship(String title, String location, String field, int duration, int stipend, String companyEmail) {
        this.id = nextId++;
        this.title = title;
        this.location = location;
        this.field = field;
        this.duration = duration;
        this.stipend = stipend;
        this.companyEmail = companyEmail;
    }

    public int getId() { return id; }
    public String getCompanyEmail() { return companyEmail; }

    @Override
    public String toString() {
        return "ID:" + id + ", " + title + ", " + location + ", Field:" + field +
               ", Duration:" + duration + " weeks, Stipend:" + stipend;
    }
}

// Application class
class Application {
    private int internshipId;
    private String studentEmail;

    public Application(int internshipId, String studentEmail) {
        this.internshipId = internshipId;
        this.studentEmail = studentEmail;
    }

    public int getInternshipId() { return internshipId; }
    public String getStudentEmail() { return studentEmail; }

    @Override
    public String toString() {
        return "Internship ID: " + internshipId + ", Student: " + studentEmail;
    }
}

// Main System class
public class InternshipSystem {
    private Scanner sc = new Scanner(System.in);
    private List<User> users = new ArrayList<>();
    private List<Internship> internships = new ArrayList<>();
    private List<Application> applications = new ArrayList<>();
    private User currentUser = null;

    public static void main(String[] args) {
        new InternshipSystem().run();
    }

    private void run() {
        while (true) {
            printMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1": registerUser(); break;
                case "2": loginUser(); break;
                case "3": updateProfile(); break;
                case "4": searchInternships(); break;
                case "5": postInternship(); break;
                case "6": logout(); break;
                case "7": forgetPassword(); break;
                case "8": exit(); break;
                case "9": applyForInternship(); break;
                case "10": viewApplications(); break;
                case "11": viewSavedInternships(); break;
                default: System.out.println("Invalid choice."); break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== Internship System =====");
        if (currentUser != null) {
            System.out.println("Logged in as: " + currentUser.getEmail() + " (" + currentUser.getRole() + ")");
        } else {
            System.out.println("Not logged in.");
        }
        System.out.println("1. Register User");
        System.out.println("2. Login User");
        System.out.println("3. Update Profile");
        System.out.println("4. Search Internships");
        System.out.println("5. Post Internship");
        System.out.println("6. Logout");
        System.out.println("7. Forget Password");
        System.out.println("8. Exit");
        if (currentUser != null && currentUser.getRole() == Role.STUDENT) {
            System.out.println("9. Apply for Internship");
            System.out.println("11. View Saved Internships");
        }
        if (currentUser != null && currentUser.getRole() == Role.COMPANY) {
            System.out.println("10. View Applications");
        }
        System.out.print("Enter choice: ");
    }

    private void registerUser() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        System.out.print("Enter role (STUDENT/COMPANY/ADMIN): ");
        String roleStr = sc.nextLine().toUpperCase();
        Role role = Role.valueOf(roleStr);
        users.add(new User(name, email, pass, role));
        System.out.println("Registration successful!");
    }

    private void loginUser() {
        if (currentUser != null) { System.out.println("Already logged in."); return; }
        System.out.print("Enter email: "); String email = sc.nextLine();
        System.out.print("Enter password: "); String pass = sc.nextLine();
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(pass)) {
                currentUser = u;
                System.out.println("Login successful!");
                return;
            }
        }
        System.out.println("Invalid email or password.");
    }

    private void logout() {
        if (currentUser != null) {
            System.out.println("Logging out " + currentUser.getEmail());
            currentUser = null;
        } else {
            System.out.println("No user logged in.");
        }
    }

    private void updateProfile() {
        if (currentUser == null) { System.out.println("Login first."); return; }
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        currentUser.updateProfile(name);
        System.out.println("Profile updated.");
    }

    private void postInternship() {
        if (currentUser == null || (currentUser.getRole() != Role.COMPANY && currentUser.getRole() != Role.ADMIN)) {
            System.out.println("Only COMPANY or ADMIN can post internships.");
            return;
        }
        System.out.print("Enter title: "); String title = sc.nextLine();
        System.out.print("Enter location: "); String loc = sc.nextLine();
        System.out.print("Enter field: "); String field = sc.nextLine();
        System.out.print("Enter duration (weeks): "); int dur = Integer.parseInt(sc.nextLine());
        System.out.print("Enter stipend: "); int stipend = Integer.parseInt(sc.nextLine());
        internships.add(new Internship(title, loc, field, dur, stipend, currentUser.getEmail()));
        System.out.println("Internship posted successfully!");
    }

    private void searchInternships() {
        if (internships.isEmpty()) { System.out.println("No internships available."); return; }
        System.out.println("Available internships:");
        for (Internship i : internships) System.out.println(i);
    }

    private void applyForInternship() {
        if (currentUser == null || currentUser.getRole() != Role.STUDENT) { System.out.println("Only students can apply."); return; }
        System.out.print("Enter internship ID to apply: "); int id = Integer.parseInt(sc.nextLine());
        Internship selected = null;
        for (Internship i : internships) if (i.getId() == id) selected = i;
        if (selected == null) { System.out.println("Internship not found."); return; }
        applications.add(new Application(id, currentUser.getEmail()));
        System.out.println("Applied successfully!");
    }

    private void viewApplications() {
        if (currentUser == null || currentUser.getRole() != Role.COMPANY) { System.out.println("Only companies can view applications."); return; }
        System.out.println("Applications:");
        for (Application a : applications) {
            for (Internship i : internships) {
                if (i.getId() == a.getInternshipId() && i.getCompanyEmail().equals(currentUser.getEmail()))
                    System.out.println(a);
            }
        }
    }

    private void viewSavedInternships() {
        if (currentUser == null || currentUser.getRole() != Role.STUDENT) { System.out.println("Only students can view saved internships."); return; }
        System.out.println("Saved Internships:");
        for (String s : currentUser.getSavedInternships()) System.out.println(s);
    }

    private void forgetPassword() {
        System.out.print("Enter your email: "); String email = sc.nextLine();
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                System.out.print("Enter new password: "); String newPass = sc.nextLine();
                u = new User(u.getName(), u.getEmail(), newPass, u.getRole());
                System.out.println("Password reset successful!");
                return;
            }
        }
        System.out.println("Email not found.");
    }

    private void exit() {
        System.out.println("Exiting system. Goodbye!");
        System.exit(0);
    }
}
