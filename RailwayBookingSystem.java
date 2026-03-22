

import java.util.Random;
import java.util.Scanner;
abstract class Reservation {
    protected String name, gender, seatNo, departure, destination, trainName, timeSlot, coachType, mealPref, journeyDate;
    protected Integer age;
    protected String phoneNumber;
    protected Double price;

    public Reservation(String name, String gender, Integer age, String phoneNumber, String trainName, String seatNo,
                       String departure, String destination, String timeSlot, String coachType, String mealPref, String journeyDate, Double price) {
        this.name = name; this.gender = gender; this.age = age; this.phoneNumber = phoneNumber; this.trainName = trainName;
        this.seatNo = seatNo; this.departure = departure; this.destination = destination; this.timeSlot = timeSlot;
        this.coachType = coachType; this.mealPref = mealPref; this.journeyDate = journeyDate; this.price = price;
    }

    public abstract void bookTicket();
    public void showDetails() {
        System.out.println("Passenger Name  : " + name);
        System.out.println("Gender          : " + gender);
        System.out.println("Age             : " + age);
        System.out.println("Phone Number    : " + phoneNumber);
        System.out.println("Departure       : " + departure);
        System.out.println("Destination     : " + destination);
        System.out.println("Train Name      : " + trainName);
        System.out.println("Coach Type      : " + coachType);
        System.out.println("Journey Date    : " + journeyDate);
        System.out.println("Time Slot       : " + timeSlot);
        System.out.println("Seat Number     : " + seatNo);
        System.out.println("Meal Preference : " + mealPref);
    }
}

class GeneralReservation extends Reservation {
    public GeneralReservation(String n,String g,Integer a,String ph,String t,String s,String dep,String dest,String time,String c,String meal,String jd,Double p){
        super(n,g,a,ph,t,s,dep,dest,time,c,meal,jd,p);
    }
    @Override public void bookTicket() {
        System.out.println("\nBooking General Ticket..."); showDetails();
        System.out.println("Ticket Price    : ₹" + price); System.out.println("Booking Successful!");
    }
}

class ConcessionReservation extends Reservation {
    private String concession;
    public ConcessionReservation(String n,String g,Integer a,String ph,String t,String s,String dep,String dest,String time,String c,String meal,String jd,Double p,String conc){
        super(n,g,a,ph,t,s,dep,dest,time,c,meal,jd,p); this.concession = conc;
    }
    @Override public void bookTicket() {
        System.out.println("\nBooking Concession Ticket..."); showDetails();
        System.out.println("Original Price  : ₹" + price);
        System.out.println("Concession Type : " + concession);
        System.out.println("Discounted Price: ₹" + price*0.75);
        System.out.println("Booking Successful!");
    }
}

class User {
    private String username,password;
    public User(String u,String p){ username=u; password=p;}
    public boolean login(String u,String p){ return username.equals(u) && password.equals(p);}
}

public class RailwayBookingSystem {
    static Random rand = new Random();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Railway Booking System ===");
        System.out.print("Register username: "); String username = sc.nextLine();
        System.out.print("Set password: "); String password = sc.nextLine();
        User user = new User(username,password);

        boolean loggedIn=false;
        for(int i=0;i<3;i++){
            System.out.print("Username: "); String u=sc.nextLine();
            System.out.print("Password: "); String p=sc.nextLine();
            if(user.login(u,p)){ loggedIn=true; System.out.println("Login successful!\n"); break;}
            else System.out.println("Invalid credentials. Try again.");
        }
        if(!loggedIn){ System.out.println("Too many failed attempts. Exiting."); return;}

        System.out.print("Enter departure: "); String departure = sc.nextLine();
        System.out.print("Enter destination: "); String destination = sc.nextLine();
        String[] trains = {"Express Line","Superfast Express","Jan Shatabdi"};
        System.out.println("\nAvailable trains:");
        for(int i=0;i<trains.length;i++) System.out.println((i+1)+". "+trains[i]);
        System.out.print("Select train number: "); int tChoice=Integer.parseInt(sc.nextLine());
        String trainName = trains[tChoice-1];

        System.out.print("Enter journey date (dd-mm-yyyy): "); String journeyDate=sc.nextLine();
        String[] timeSlots = {"05:00 AM","08:00 AM","11:00 AM","02:00 PM","05:00 PM"};
        System.out.println("Available time slots:");
        for(int i=0;i<timeSlots.length;i++) System.out.println((i+1)+". "+timeSlots[i]);
        System.out.print("Select time slot number: "); int timeChoice=Integer.parseInt(sc.nextLine());
        String timeSlot = timeSlots[timeChoice-1];

        System.out.print("Choose coach type:\n1.AC Sleeper\n2.AC Two Tier\n3.AC Three Tier\nEnter: "); int coachChoice=Integer.parseInt(sc.nextLine());
        String coachType = coachChoice==1?"AC Sleeper":coachChoice==2?"AC Two Tier":"AC Three Tier";

        Reservation[] passengers = new Reservation[10]; int pCount=0;
        boolean addMore=true;
        while(addMore){
            System.out.print("\nEnter passenger name: "); String name=sc.nextLine();
            System.out.print("Gender: "); String gender=sc.nextLine();
            System.out.print("Age: "); Integer age = Integer.valueOf(sc.nextLine());
            System.out.print("Mobile number: "); String phone=sc.nextLine();
            System.out.print("Meal preference (Rice & Daal) yes/no: "); String mealPref = sc.nextLine().equalsIgnoreCase("yes")?"Rice & Daal":"No meal";
            System.out.print("Ticket type (1-General, 2-Concession): "); int tt=Integer.parseInt(sc.nextLine());
            String concession=""; if(tt==2){ System.out.print("Concession (Student/Senior/Ladies): "); concession=sc.nextLine();}
            String seatNo = (char)('A'+rand.nextInt(4))+""+(1+rand.nextInt(10));
            Double price=1000.0;
            if(tt==1) passengers[pCount]=new GeneralReservation(name,gender,age,phone,trainName,seatNo,departure,destination,timeSlot,coachType,mealPref,journeyDate,price);
            else passengers[pCount]=new ConcessionReservation(name,gender,age,phone,trainName,seatNo,departure,destination,timeSlot,coachType,mealPref,journeyDate,price,concession);
            pCount++;
            System.out.print("Add another passenger? (yes/no): "); addMore = sc.nextLine().equalsIgnoreCase("yes");
        }

        System.out.print("Return journey? (yes/no): "); boolean returnJ=sc.nextLine().equalsIgnoreCase("yes");
        String returnDate="", returnTime="";
        if(returnJ){
            System.out.print("Enter return date: "); returnDate=sc.nextLine();
            System.out.println("Return time slots:"); for(int i=0;i<timeSlots.length;i++) System.out.println((i+1)+". "+timeSlots[i]);
            System.out.print("Select return time slot number: "); returnTime=timeSlots[Integer.parseInt(sc.nextLine())-1];
        }

        System.out.print("Payment method (1-UPI,2-Credit,3-Debit): "); int payChoice=Integer.parseInt(sc.nextLine());
        String payment = payChoice==1?"UPI":payChoice==2?"Credit Card":"Debit Card";

        Reservation[] returnPassengers = new Reservation[pCount];
        for(int i=0;i<pCount;i++){
            passengers[i].bookTicket();
            System.out.println("Payment Method: "+payment);
            System.out.println("Ticket confirmation sent via SMS to " + passengers[i].phoneNumber + " for Seat: " + passengers[i].seatNo);
            if(returnJ){
                String returnSeat = (char)('A'+rand.nextInt(4))+""+(1+rand.nextInt(10));
                returnPassengers[i] = passengers[i] instanceof ConcessionReservation?
                        new ConcessionReservation(passengers[i].name,passengers[i].gender,passengers[i].age,passengers[i].phoneNumber,trainName,returnSeat,destination,departure,returnTime,coachType,passengers[i].mealPref,returnDate,1000.0,"Concession"):
                        new GeneralReservation(passengers[i].name,passengers[i].gender,passengers[i].age,passengers[i].phoneNumber,trainName,returnSeat,destination,departure,returnTime,coachType,passengers[i].mealPref,returnDate,1000.0);
                returnPassengers[i].bookTicket();
                System.out.println("Payment Method: "+payment);
                System.out.println("Return journey confirmation sent via SMS to " + passengers[i].phoneNumber + " for Seat: " + returnSeat);
            }
        }

        System.out.println("\n======= ALL TICKET DETAILS =======");
        System.out.printf("%-15s %-6s %-3s %-12s %-10s %-12s %-12s %-10s %-10s %-10s %-5s\n","Name","Gen","Age","Phone","Depart","Dest","Train","Coach","Date","Time","Seat");
        for(int i=0;i<pCount;i++){
            Reservation r = passengers[i];
            System.out.printf("%-15s %-6s %-3d %-12s %-10s %-12s %-12s %-10s %-10s %-10s %-5s\n",
                    r.name,r.gender,r.age,r.phoneNumber,r.departure,r.destination,r.trainName,r.coachType,r.journeyDate,r.timeSlot,r.seatNo);
            if(returnJ){
                Reservation rr = returnPassengers[i];
                System.out.printf("%-15s %-6s %-3d %-12s %-10s %-12s %-12s %-10s %-10s %-10s %-5s\n",
                        rr.name,rr.gender,rr.age,rr.phoneNumber,rr.departure,rr.destination,rr.trainName,rr.coachType,rr.journeyDate,rr.timeSlot,rr.seatNo);
            }
        }
        System.out.println("==================================");
        System.out.println("\nThank you for using Railway Booking System!");
        sc.close();
    }
}
