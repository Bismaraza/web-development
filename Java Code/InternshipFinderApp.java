// ========== Base Class: User (Encapsulation applied) ==========
class User {
    private String username;
    private String password;
    private String role; // "student" or "employer"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters (Encapsulation)
    public String getUsername() { return username; }
    public String getRole() { return role; }

    public boolean login(String inputPassword) throws Exception {
        if (this.password.equals(inputPassword)) {
            return true;
        } else {
            throw new Exception("Invalid login for user: " + username);
        }
    }
}

// ========== Inheritance: Student extends User ==========
class Student extends User {
    private String skills;
    private String education;

    public Student(String username, String password, String skills, String education) {
        super(username, password, "student");
        this.skills = skills;
        this.education = education;
    }

    // FR02: Update profile
    public void updateProfile(String newSkills, String newEducation) {
        this.skills = newSkills;
        this.education = newEducation;
        System.out.println(getUsername() + " updated profile.");
    }

    // FR07: Apply for internship
    public void applyForInternship(String internshipTitle) {
        System.out.println(getUsername() + " applied for: " + internshipTitle);
    }
}

// ========== Inheritance: Employer extends User ==========
class Employer extends User {
    private String companyName;

    public Employer(String username, String password, String companyName) {
        super(username, password, "employer");
        this.companyName = companyName;
    }

    // FR10: Post internship
    public void postInternship(String internshipTitle) {
        System.out.println(companyName + " posted internship: " + internshipTitle);
    }
}

// ========== Main Application ==========
public class InternshipFinderApp {
    public static void main(String[] args) {
        try {
            // Creating Student & Employer
            Student student = new Student("ali123", "pass123", "Java, SQL", "BSCS");
            Employer employer = new Employer("techcorp", "secure456", "TechCorp Pvt Ltd");

            // FR01: Secure login with Exception Handling
            if (student.login("pass123")) {
                System.out.println(student.getUsername() + " logged in successfully!");
                student.updateProfile("Java, Python", "BSCS (Final Year)");
                student.applyForInternship("Software Intern");
            }

            if (employer.login("wrongpass")) { // Wrong password -> throws exception
                employer.postInternship("Web Developer Intern");
            }

        } catch (Exception e) {
            // Exception Handling Example
            System.out.println("⚠ Error: " + e.getMessage());
        }
    }
}
