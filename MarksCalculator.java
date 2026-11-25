import java.io.*;
import java.util.Scanner;

class MarksCalculator {
    private String studentName;
    private int[] marks = new int[5];
    private int total;
    private double percentage;
    private String grade;
    private boolean hasData = false;
    private final String FILE_NAME = "student_record.txt";

    // Accepts student name and marks for 5 subjects with validation
    public void inputMarks(Scanner sc) {
        System.out.print("Enter Student Name: ");
        sc.nextLine(); // Consume leftover newline
        studentName = sc.nextLine();

        for (int i = 0; i < 5; i++) {
            while (true) {
                System.out.print("Enter marks for Subject " + (i + 1) + " (0-100): ");
                if (sc.hasNextInt()) {
                    int m = sc.nextInt();
                    if (m >= 0 && m <= 100) {
                        marks[i] = m;
                        break;
                    } else {
                        System.out.println("Invalid marks. Enter a value between 0 and 100.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    sc.next(); // Clear invalid input
                }
            }
        }
        calculateTotal();
        calculatePercentage();
        calculateGrade();
        hasData = true;
        System.out.println("Marks recorded successfully!");
    }

    // Calculates total marks using a loop
    public int calculateTotal() {
        total = 0;
        for (int m : marks) {
            total += m;
        }
        return total;
    }

    // Calculates percentage
    public double calculatePercentage() {
        percentage = (total / 500.0) * 100.0;
        percentage = Math.round(percentage * 100.0) / 100.0;
        return percentage;
    }

    // Assigns grade according to criteria
    public String calculateGrade() {
        if (percentage >= 90) {
            grade = "A+";
        } else if (percentage >= 75) {
            grade = "A";
        } else if (percentage >= 60) {
            grade = "B";
        } else if (percentage >= 40) {
            grade = "C (Pass)";
        } else {
            grade = "F";
        }
        return grade;
    }

    // Generates a Result object
    public Result getResult() {
        if (!hasData) {
            return new Result(null, null, 0, 0.0, null);
        }
        return new Result(studentName, marks, total, percentage, grade);
    }

    // Finds highest and lowest scoring subjects
    public void findHighestLowest() {
        if (!hasData) {
            System.out.println("No data available. Enter marks first.");
            return;
        }
        int highest = marks[0], lowest = marks[0];
        int highSub = 1, lowSub = 1;

        for (int i = 1; i < marks.length; i++) {
            if (marks[i] > highest) {
                highest = marks[i];
                highSub = i + 1;
            }
            if (marks[i] < lowest) {
                lowest = marks[i];
                lowSub = i + 1;
            }
        }
        System.out.println("Highest: Subject " + highSub + " (" + highest + ") | Lowest: Subject " + lowSub + " (" + lowest + ")");
    }

    // Sorts marks in descending order without altering original subject order
    public void sortMarks() {
        if (!hasData) {
            System.out.println("No data available. Enter marks first.");
            return;
        }
        int[] sorted = marks.clone();
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - i - 1; j++) {
                if (sorted[j] < sorted[j + 1]) {
                    int temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        System.out.print("Marks (High to Low): ");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + (i < sorted.length - 1 ? ", " : ""));
        }
        System.out.println();
    }

    // Searches for a particular mark using Linear Search
    public void searchMark(Scanner sc) {
        if (!hasData) {
            System.out.println("No data available. Enter marks first.");
            return;
        }
        System.out.print("Enter mark to search: ");
        int target = sc.nextInt();
        boolean found = false;

        for (int i = 0; i < marks.length; i++) {
            if (marks[i] == target) {
                System.out.println("Mark " + target + " found in Subject " + (i + 1));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Mark " + target + " not found in any subject.");
        }
    }

    // Saves student record to a file on exit
    public void saveData() {
        if (!hasData) return;
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(studentName);
            for (int i = 0; i < marks.length; i++) {
                writer.print(marks[i] + (i < marks.length - 1 ? "," : ""));
            }
            writer.println();
            System.out.println("Data saved successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Loads previously stored record at program start
    public void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String name = reader.readLine();
            String marksLine = reader.readLine();
            if (name != null && marksLine != null) {
                String[] parts = marksLine.split(",");
                if (parts.length == 5) {
                    this.studentName = name;
                    for (int i = 0; i < 5; i++) {
                        this.marks[i] = Integer.parseInt(parts[i].trim());
                    }
                    calculateTotal();
                    calculatePercentage();
                    calculateGrade();
                    hasData = true;
                    System.out.println("Previous record loaded for student: " + studentName);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load previous data.");
        }
    }
}