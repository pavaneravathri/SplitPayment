package banking;
import java.util.*;

    class Participant {
        String name;
        double balance;
        public Participant(String name) {
            this.name = name;
            this.balance = 0.0;
        }
    }
    public class SplitPayment {
        private static final Scanner scanner = new Scanner(System.in);
        private static final List<Participant> participants = new ArrayList<>();

        public static void main(String[] args) {
            while (true) {
                System.out.println("\n --- Split Payment ---");
                System.out.println("Press --> 1 to Add Participant");
                System.out.println("Press --> 2 to Add Expense");
                System.out.println("Press --> 3 to Show Balances");
                System.out.println("Press --> 4 to Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addParticipant();
                    case 2 -> addExpense();
                    case 3 -> showBalances();
                    case 4 -> {
                        System.out.println("Exiting the application Successfully.\n ---  Goodbye! --- ");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        }

        private static void addParticipant() {
            System.out.print("Enter participant name: ");
            String name = scanner.nextLine();
            participants.add(new Participant(name));
            System.out.println(name + " has been added to the group.");
        }

        private static void addExpense() {
            if (participants.isEmpty()) {
                System.out.println("No participants in the group.\n Press --> 1 to Add a Participant");
                return;
            }

            System.out.print("Enter expense description: ");
            String description = scanner.nextLine();
            System.out.print("Enter total expense amount: ");
            double amount = scanner.nextDouble();

            double splitAmount = amount / participants.size();
            System.out.println("Each participant owes: " + splitAmount);

            for (Participant p : participants) {
                p.balance -= splitAmount;
            }

            System.out.print("Who paid the expense? Enter participant name: ");
            scanner.nextLine(); // Consume newline
            String payerName = scanner.nextLine();

            boolean found = false;
            for (Participant p : participants) {
                if (p.name.equalsIgnoreCase(payerName)) {
                    p.balance += amount;
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Expense added successfully!");
            } else {
                System.out.println("Payer not found. Expense not recorded.");
            }
        }

        private static void showBalances() {
            if (participants.isEmpty()) {
                System.out.println("\n No participants in the group.\n Press --> 1 to Add a Participant");
                return;
            }
            System.out.println("\n--- Balances ---");
            for (Participant p : participants) {
                System.out.println(p.name + ": " + (p.balance >= 0 ? "Owes " : "Gets ") + Math.abs(p.balance));
            }
        }
    }

