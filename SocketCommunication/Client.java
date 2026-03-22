
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send 3 messages
            for (int i = 0; i < 3; i++) {
                out.println("Hello Server, this is message " + (i+1));
                String response = in.readLine();
                System.out.println(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
