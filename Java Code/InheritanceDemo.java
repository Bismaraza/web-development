// Inheritance Example
class User {
    protected String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

// Student class inheriting User
class Student extends User {
    public Student(String username) {
        super(username);
    }

    public void applyInternship(String title) {
        System.out.println(getUsername() + " applied for internship: " + title);
    }
}

// Employer class inheriting User
class Employer extends User {
    public Employer(String username) {
        super(username);
    }

    public void postInternship(String title) {
        System.out.println(getUsername() + " posted internship: " + title);
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Student student = new Student("Ali");
        Employer employer = new Employer("TechCorp");

        student.applyInternship("Java Intern");
        employer.postInternship("Web Developer Intern");
    }
}
