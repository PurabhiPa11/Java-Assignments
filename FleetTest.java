
import java.util.*;
interface Vehicle {
    void start();
    void stop();
    int getSpeed();
    String getFuelType();
}

interface Maintenance {
    void performMaintenance();
}

class Car implements Vehicle, Maintenance {
    private int speed;
    public void start() { speed = 60; System.out.println("Car started."); }
    public void stop() { speed = 0; System.out.println("Car stopped."); }
    public int getSpeed() { return speed; }
    public String getFuelType() { return "Petrol"; }
    public void performMaintenance() { System.out.println("Car maintenance: Oil change, Brake check"); }
}

class Bus implements Vehicle, Maintenance {
    private int speed;
    public void start() { speed = 40; System.out.println("Bus started."); }
    public void stop() { speed = 0; System.out.println("Bus stopped."); }
    public int getSpeed() { return speed; }
    public String getFuelType() { return "Diesel"; }
    public void performMaintenance() { System.out.println("Bus maintenance: Engine inspection, Tire pressure check"); }
}

class Motorcycle implements Vehicle {
    private int speed;
    public void start() { speed = 80; System.out.println("Motorcycle started."); }
    public void stop() { speed = 0; System.out.println("Motorcycle stopped."); }
    public int getSpeed() { return speed; }
    public String getFuelType() { return "Petrol"; }
}

public class FleetTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vehicle[] fleet = { new Car(), new Bus(), new Motorcycle() };
        String[] names = { "Car", "Bus", "Motorcycle" };

        while(true) {
            System.out.println("\n=== Fleet Menu ===");
            for(int i=0;i<fleet.length;i++) System.out.println((i+1)+". "+names[i]);
            System.out.println((fleet.length+1)+". Exit");
            System.out.print("Choose a vehicle to operate: ");
            int choice = sc.nextInt();
            if(choice == fleet.length+1) break;

            if(choice >=1 && choice <= fleet.length) {
                Vehicle v = fleet[choice-1];
                v.start();
                System.out.println("Speed: " + v.getSpeed() + " km/h");
                System.out.println("Fuel Type: " + v.getFuelType());
                v.stop();

                if(v instanceof Maintenance) ((Maintenance)v).performMaintenance();
                else System.out.println(names[choice-1] + " does not require scheduled maintenance.");
            } else {
                System.out.println("Invalid choice!");
            }
        }
        System.out.println("Exiting Fleet Management. Goodbye!");
        sc.close();
    }
}
