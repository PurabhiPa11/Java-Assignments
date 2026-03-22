import java.sql.*;

public class Assignment {
    public static void main(String[] args) {
        // Use try-with-resources for automatic resource management
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish connection and resources
            try (
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital1", "root", "1234"
                );
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM patients")
            ) {
                System.out.println("Connection successful!");

                while (rs.next()) {
                    System.out.println(
                        rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getString("branch")
                    );
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}