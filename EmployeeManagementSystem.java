

import java.util.*;
class Employee {
    int empId;
    String name, department, position;
    int totalWorkingDays = 26, attendance;
    double basicSalary;

    void inputDetails(Scanner sc) {
        Random rand = new Random();
        System.out.print("Enter Employee ID: ");
        empId = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Department: ");
        department = sc.nextLine();
        System.out.print("Enter Position: ");
        position = sc.nextLine();

        attendance = rand.nextInt(totalWorkingDays + 1);
        basicSalary = 25000 + rand.nextInt(15001);
    }

    void displayDetails() {
        System.out.println("\n--- Employee Details ---");
        System.out.println("ID: " + empId + " | Name: " + name);
        System.out.println("Department: " + department + " | Position: " + position);
        System.out.println("Basic Salary: ₹" + basicSalary);
        System.out.println("Attendance: " + attendance + "/" + totalWorkingDays);
    }
}

class Salary extends Employee {
    double hra, da, award, bonus, pf, pt, hrFund, total;
    boolean isOutstanding;

    void calculateAll() {
        hra = 0.20 * basicSalary;
        da = 0.10 * basicSalary;
        isOutstanding = attendance >= 20;
        award = isOutstanding ? 5000 : 0;
        pf = 0.12 * basicSalary;
        pt = 200;
        hrFund = 0.03 * basicSalary;
        total = basicSalary + hra + da + award + bonus + hrFund - pf - pt;
    }

    @Override
    void displayDetails() {  
        super.displayDetails();
        System.out.println("Outstanding: " + isOutstanding);
    }

    void displaySalary() {
        calculateAll();
        System.out.println("\n--- Salary Breakdown ---");
        System.out.printf("%-20s : ₹%.2f%n", "Basic Salary", basicSalary);
        System.out.printf("%-20s : ₹%.2f%n", "HRA", hra);
        System.out.printf("%-20s : ₹%.2f%n", "DA", da);
        System.out.printf("%-20s : ₹%.2f%n", "Award", award);
        System.out.printf("%-20s : ₹%.2f%n", "Bonus", bonus);
        System.out.printf("%-20s : ₹%.2f%n", "HR Fund", hrFund);
        System.out.printf("%-20s : ₹%.2f%n", "PF", pf);
        System.out.printf("%-20s : ₹%.2f%n", "PT", pt);
        System.out.println("-------------------------------");
        System.out.printf("%-20s : ₹%.2f%n", "Total Salary", total);
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Salary emp = new Salary();
        boolean employeeAdded = false;
        boolean isHRAuthenticated = false;
        final String HR_USER = "admin", HR_PASS = "admin123";

        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Employee Interface");
            System.out.println("2. HR Interface");
            System.out.println("3. Logout");
            System.out.print("Choose (1-3): ");
            int choice = sc.nextInt(); sc.nextLine();

            if (choice == 1) {
                System.out.println("\n--- Employee Interface ---");
                System.out.println("1. Add/Update Details");
                System.out.println("2. View Details");
                System.out.println("3. View Salary Breakdown");
                System.out.println("4. Back");
                int empChoice = sc.nextInt(); sc.nextLine();
                if (empChoice == 1) {
                    emp.inputDetails(sc);
                    employeeAdded = true;
                    emp.bonus = 0;
                    System.out.println("Employee details updated!");
                } else if (empChoice == 2 && employeeAdded) {
                    emp.displayDetails();
                } else if (empChoice == 3 && employeeAdded) {
                    emp.displaySalary();
                } else if (empChoice == 4) continue;
                else System.out.println("Invalid or no details!");
            }

            else if (choice == 2) {
                if (!employeeAdded) {
                    System.out.println("Add employee first!");
                    continue;
                }
                if (!isHRAuthenticated) {
                    System.out.print("Enter HR Username: ");
                    String u = sc.nextLine();
                    System.out.print("Enter HR Password: ");
                    String p = sc.nextLine();
                    if (!u.equals(HR_USER) || !p.equals(HR_PASS)) {
                        System.out.println("Access Denied!");
                        continue;
                    }
                    isHRAuthenticated = true;
                    System.out.println("HR Login Successful!");
                }

                System.out.println("\n--- HR Interface ---");
                System.out.println("1. Add Bonus");
                System.out.println("2. View Salary Breakdown");
                System.out.println("3. Back");
                int hrChoice = sc.nextInt(); sc.nextLine();
                if (hrChoice == 1) {
                    System.out.print("Enter bonus amount: ₹");
                    emp.bonus = sc.nextDouble();
                    System.out.println("Bonus added!");
                } else if (hrChoice == 2) {
                    emp.displaySalary();
                } else if (hrChoice == 3) continue;
            }

            else if (choice == 3) {
                System.out.println("Logging out...");
                break;
            }
        }
        sc.close();
    }
}
