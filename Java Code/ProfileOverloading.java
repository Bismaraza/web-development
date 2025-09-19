class Student {
    private String skills;
    private String education;
    private String email;

    // Constructor
    public Student(String skills, String education, String email) {
        this.skills = skills;
        this.education = education;
        this.email = email;
    }

    // Overloaded Methods (Polymorphism - Compile-time)
    public void updateProfile(String skills) {
        this.skills = skills;
        System.out.println("Profile updated: Skills -> " + this.skills);
    }

    public void updateProfile(String skills, String education) {
        this.skills = skills;
        this.education = education;
        System.out.println("Profile updated: Skills -> " + this.skills + ", Education -> " + this.education);
    }

    public void updateProfile(String skills, String education, String email) {
        this.skills = skills;
        this.education = education;
        this.email = email;
        System.out.println("Profile updated: Skills -> " + this.skills +
                ", Education -> " + this.education + ", Email -> " + this.email);
    }
}

public class ProfileOverloading{
    public static void main(String[] args) {
        Student s = new Student("Java", "BSCS", "ali@email.com");

        // Different ways to update profile
        s.updateProfile("Java, SQL\n");
        s.updateProfile("Java, Python", "BSCS Final Year\n");
        s.updateProfile("Java, Spring", "BSCS Final Year", "bisma_new@email.com");
    }
}
