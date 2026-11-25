import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MarksCalculator calculator = new MarksCalculator();

        // Load data from file at start
        calculator.loadData();

        int choice;
        do {
            System.out.println("\n=== MARKS & GRADE CALCULATOR ===");
            System.out.println("1. Enter Student Marks");
            System.out.println("2. Display Result");
            System.out.println("3. Find Highest & Lowest Scoring Subject");
            System.out.println("4. Sort Marks (High to Low)");
            System.out.println("5. Search for a Mark");
            System.out.println("6. Save & Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid choice. Enter a number (1-6): ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    calculator.inputMarks(sc);
                    calculator.getResult().displayResult();
                    break;
                case 2:
                    calculator.getResult().displayResult();
                    break;
                case 3:
                    calculator.findHighestLowest();
                    break;
                case 4:
                    calculator.sortMarks();
                    break;
                case 5:
                    calculator.searchMark(sc);
                    break;
                case 6:
                    // Save data on exit
                    calculator.saveData();
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid option! Please choose between 1 and 6.");
            }
        } while (choice != 6);

        sc.close();
    }
}