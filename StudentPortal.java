import java.util.*;
enum SubjectStatus { PASSED, FAILED }
class Student {
    String name;
    int prn;
    String[] subjects = {"OOP", "Java", "DSA", "Physics", "Calculus"};
    int[][] marks = new int[5][2];
    int[] attendancePerSubject = new int[5];
    SubjectStatus[] status = new SubjectStatus[5];
    float sgpa, previousSgpa, cgpa, aggregateAttendance;
    String failedSubjects;

    Student() {
        name = "Unknown";
        prn = 0;
        failedSubjects = "None";
        for (int i = 0; i < 5; i++) {
            marks[i][0] = marks[i][1] = 50;
            status[i] = SubjectStatus.PASSED;
            attendancePerSubject[i] = 80;
        }
        aggregateAttendance = 80;
        evaluate();
        previousSgpa = 7.0f;
        cgpa = (sgpa + previousSgpa) / 2;
    }

    Student(String n, int p) {
        name = n;
        prn = p;
        failedSubjects = "";
        Random rand = new Random();
        int totalAttendance = 0;
        for (int i = 0; i < 5; i++) {
            marks[i][0] = rand.nextInt(61) + 30;
            marks[i][1] = rand.nextInt(61) + 30;
            attendancePerSubject[i] = (i == 2) ? rand.nextInt(10) + 65 : rand.nextInt(21) + 75;
            totalAttendance += attendancePerSubject[i];
        }
        aggregateAttendance = totalAttendance / 5.0f;
        evaluate();
        previousSgpa = rand.nextFloat() * 4 + 5;
        cgpa = (sgpa + previousSgpa) / 2;
    }

    Student(Student s) {
        name = s.name;
        prn = s.prn;
        subjects = s.subjects.clone();
        for (int i = 0; i < 5; i++) {
            marks[i][0] = s.marks[i][0];
            marks[i][1] = s.marks[i][1];
            status[i] = s.status[i];
            attendancePerSubject[i] = s.attendancePerSubject[i];
        }
        failedSubjects = s.failedSubjects;
        sgpa = s.sgpa;
        previousSgpa = s.previousSgpa;
        cgpa = s.cgpa;
        aggregateAttendance = s.aggregateAttendance;
    }

    void evaluate() {
        float totalMarks = 0;
        failedSubjects = "";
        for (int i = 0; i < 5; i++) {
            float avg = (marks[i][0] + marks[i][1]) / 2.0f;
            totalMarks += marks[i][0] + marks[i][1];
            if (avg < 40) {
                status[i] = SubjectStatus.FAILED;
                failedSubjects += subjects[i] + ", ";
            } else status[i] = SubjectStatus.PASSED;
        }
        sgpa = totalMarks / 100f;
        if (failedSubjects.equals("")) failedSubjects = "None";
        else failedSubjects = failedSubjects.substring(0, failedSubjects.length() - 2);
    }

    void displayDetails() {
        System.out.println("\n--- Student Portal ---");
        System.out.println("School: School of Engineering and Technology");
        System.out.println("Branch: Computer Science");
        System.out.println("Name: " + name + " | PRN: " + prn);
        for (int i = 0; i < 5; i++) {
            String attn = (attendancePerSubject[i] < 75) ? " (Low Attendance ❌)" : "";
            System.out.println(subjects[i] + ": " + marks[i][0] + "," + marks[i][1] +
                               " → " + status[i] + " | Attendance=" + attendancePerSubject[i] + "%" + attn);
        }
        System.out.printf("\nAggregate Attendance: %.2f%% | Prev SGPA: %.2f | SGPA: %.2f | CGPA: %.2f\n",
                          aggregateAttendance, previousSgpa, sgpa, cgpa);
        System.out.println("Failed Subjects: " + failedSubjects);
    }
}

public class StudentPortal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            // Print menu each time
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Student Portal");
            System.out.println("2. Logout");
            System.out.print("Enter your choice (1 or 2): ");
            int ch = sc.nextInt(); sc.nextLine();

            if (ch == 1) {
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter PRN: ");
                int prn = sc.nextInt();
                Student s = new Student(name, prn);
                Student copy = new Student(s);
                copy.displayDetails();
            } else if (ch == 2) {
                System.out.println("Logged out. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}
