class User {
    protected String username;

    public User(String username) {
        this.username = username;
    }

    // Method to be overridden
    public void updateProfile() {
        System.out.println(username + " updated profile (generic user).");
    }
}

class Student extends User {
    private String skills;
    private String education;

    public Student(String username, String skills, String education) {
        super(username);
        this.skills = skills;
        this.education = education;
    }

    @Override
    public void updateProfile() {
        System.out.println(username + " updated Student Profile with Skills: " + skills + ", Education: " + education);
    }
}

class Employer extends User {
    private String companyName;

    public Employer(String username, String companyName) {
        super(username);
        this.companyName = companyName;
    }

    @Override
    public void updateProfile() {
        System.out.println(username + " updated Employer Profile with Company: " + companyName);
    }
}

public class ProfileOverriding {
    public static void main(String[] args) {
        User student = new Student("Ali", "Java, SQL", "BSCS");
        User employer = new Employer("TechCorp", "TechCorp Pvt Ltd");

        // Runtime Polymorphism: same method behaves differently
        student.updateProfile();
        employer.updateProfile();
    }
}
