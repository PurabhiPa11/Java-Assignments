import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started. Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Exchange a few messages
            for (int i = 0; i < 3; i++) {
                String msg = in.readLine();
                System.out.println("Client: " + msg);
                out.println("Server reply " + (i+1) + ": Got your message -> " + msg);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
