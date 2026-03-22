

import java.util.*;

class TicketCounter {
    private int availableTickets;
    private Queue<String> seats;
    private String movieName;
    private String theatreName;
    private String[] timeSlots;

    public TicketCounter(String movie, String theatre, String[] timeSlots, int totalTickets) {
        this.movieName = movie;
        this.theatreName = theatre;
        this.timeSlots = timeSlots;
        this.availableTickets = totalTickets;
        seats = new LinkedList<>();
        for (int i = 1; i <= totalTickets; i++) seats.add("Seat-" + i);
    }

    public synchronized void bookTicket(String user, int numTickets, String mobile, String language, String seatType, String time, boolean is3D) {
        try {
            double price = (seatType.equals("Silver") ? 150 : seatType.equals("Gold") ? 250 : 400);
            if (is3D) price += 50; 
            double total = price * numTickets;

            System.out.println("\n" + user + " is booking " + numTickets + " " + seatType + (is3D ? " (3D)" : "") +
                    " ticket(s) for " + movieName + " at " + theatreName + " (" + language + ") [" + time + "]");
            if (numTickets <= 0) throw new IllegalArgumentException("Invalid number of tickets!");
            Thread.sleep(500);

            if (availableTickets >= numTickets) {
                System.out.print("Booking successful | Seats: ");
                for (int i = 0; i < numTickets; i++) System.out.print(seats.poll() + " ");
                System.out.println("\nTickets remaining: " + (availableTickets -= numTickets));
                System.out.println("Total amount paid: Rs. " + total);
                System.out.println("SMS sent to " + mobile + ": Your ticket(s) are confirmed. Enjoy the show!\n");
            } else {
                System.out.println("Sorry " + user + ", only " + availableTickets + " ticket(s) left.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String getMovieName() { return movieName; }
    public String getTheatreName() { return theatreName; }
    public String[] getTimeSlots() { return timeSlots; }
}

class UserRunnable implements Runnable {
    private TicketCounter counter;
    private String user; private int tickets; private String mobile, language, seatType, time;
    private boolean is3D;

    public UserRunnable(TicketCounter c, String u, int t, String m, String lang, String s, String time, boolean is3D) {
        counter = c; user = u; tickets = t; mobile = m; language = lang; seatType = s; this.time = time; this.is3D = is3D;
    }

    public void run() { counter.bookTicket(user, tickets, mobile, language, seatType, time, is3D); }
}

public class MovieBooking3D {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Welcome to Movie Ticket Booking Portal");
        System.out.print("Enter your name: "); String user = sc.nextLine();

        String[] movies = {"Avengers", "Spider-Man", "Joker", "Inception", "Interstellar", "The Batman", "Thor", "Doctor Strange"};
        String[] theatres = {"PVR", "INOX", "Cinepolis"};
        String[] times = {"10:00 AM", "12:30 PM", "2:00 PM", "4:30 PM", "6:00 PM", "8:30 PM"};

        System.out.println("\nAvailable Movies:");
        for (int i=0;i<movies.length;i++) System.out.println((i+1)+". "+movies[i]);
        System.out.print("Choose movie: "); int mChoice = sc.nextInt()-1;

        System.out.println("\nAvailable Theatres:");
        for (int i=0;i<theatres.length;i++) System.out.println((i+1)+". "+theatres[i]);
        System.out.print("Choose theatre: "); int tChoice = sc.nextInt()-1;

        System.out.println("\nAvailable Time Slots:");
        for (int i=0;i<times.length;i++) System.out.println((i+1)+". "+times[i]);
        System.out.print("Choose time slot: "); int timeChoice = sc.nextInt()-1; sc.nextLine();

        System.out.print("Select language (English/Hindi): "); String lang = sc.nextLine();
        System.out.print("Number of tickets: "); int numTickets = sc.nextInt(); sc.nextLine();

        System.out.println("Seat types:\n1. Silver\n2. Gold\n3. Sofa");
        System.out.print("Choose seat type: "); int sChoice = sc.nextInt(); sc.nextLine();
        String seatType = sChoice==1?"Silver":sChoice==2?"Gold":"Sofa";

        System.out.print("Do you want 3D movie? (yes/no): "); boolean is3D = sc.nextLine().trim().equalsIgnoreCase("yes");

        System.out.print("Enter your mobile number: "); String mobile = sc.nextLine();

        TicketCounter counter = new TicketCounter(movies[mChoice], theatres[tChoice], times, 20);
        Thread bookingThread = new Thread(new UserRunnable(counter, user, numTickets, mobile, lang, seatType, times[timeChoice], is3D));
        bookingThread.start();
        bookingThread.join();

        System.out.println("Booking completed. Thank you!");
    }
}
