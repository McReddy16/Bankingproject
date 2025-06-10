//This is your main program with menu options.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankOperations bank = new BankOperations();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Oracle Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View Balance");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    bank.login();
                    break;
                case 3:
                    bank.deposit();
                    break;
                case 4:
                    bank.withdraw();
                    break;
                case 5:
                    bank.viewBalance();
                    break;
                case 6:
                    System.out.println("Thank you for using Banking System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
