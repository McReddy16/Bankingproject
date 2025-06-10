//This class will handle all banking operations (create account, login, deposit, withdraw, view balance).
import java.sql.*;
import java.util.Scanner;

public class BankOperations {
    Connection conn = DBConnection.getConnection();
    Scanner sc = new Scanner(System.in);

    public void createAccount() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Initial Deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();  // clear newline

        try {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO accounts (username, password, balance) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setDouble(3, amount);
            ps.executeUpdate();
            System.out.println("Account created successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public boolean login() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM accounts WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful! Welcome, " + username);
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public void deposit() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Amount to Deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE username = ?");
            ps.setDouble(1, amount);
            ps.setString(2, username);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Deposit successful!");
            else
                System.out.println("Username not found.");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void withdraw() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Amount to Withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        try {
            PreparedStatement check = conn.prepareStatement(
                "SELECT balance FROM accounts WHERE username = ?");
            check.setString(1, username);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance >= amount) {
                    PreparedStatement ps = conn.prepareStatement(
                        "UPDATE accounts SET balance = balance - ? WHERE username = ?");
                    ps.setDouble(1, amount);
                    ps.setString(2, username);
                    ps.executeUpdate();
                    System.out.println("Withdrawal successful!");
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Username not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void viewBalance() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT balance FROM accounts WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Your Balance: ₹" + rs.getDouble("balance"));
            } else {
                System.out.println("Username not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
