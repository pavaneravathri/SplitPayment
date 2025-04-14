package banking;

import java.util.*;

class User {
    String username;
    String password;
    double balance;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
    }
}

class Transaction {
    String type;
    double amount;
    Date date;

    public Transaction(String type, double amount, Date date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return type + " - Amount: " + amount + " - Date: " + date;
    }
}

public class WalletSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, User> users = new HashMap<>();
    private static final List<Transaction> transactions = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Try again.");
        } else {
            users.put(username, new User(username, password));
            System.out.println("Registration successful!");
        }
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            currentUser = users.get(username);
            System.out.println("Login successful!");
            userMenu();
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n1. Add Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Available Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addMoney();
                case 2 -> withdrawMoney();
                case 3 -> checkBalance();
                case 4 -> viewTransactions();
                case 5 -> {
                    currentUser = null;
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addMoney() {
        System.out.print("Enter amount to add: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentUser.balance += amount;
            transactions.add(new Transaction("Deposit", amount, new Date()));
            System.out.println("Amount added successfully!");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && currentUser.balance >= amount) {
            currentUser.balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount, new Date()));
            System.out.println("Amount withdrawn successfully!");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    private static void viewTransactions() {
        System.out.println("\nTransaction History:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
    private static void checkBalance(){
        System.out.println("Current Balance: " + currentUser.balance);
    }
}
