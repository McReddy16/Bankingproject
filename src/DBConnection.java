
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE",   // Your Oracle DB URL
                "system", "manager"            // Your username & password
            );
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            return null;
        }
    }
}


