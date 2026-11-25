class Result {
    private String studentName;
    private int[] marks;
    private int total;
    private double percentage;
    private String grade;

    public Result(String studentName, int[] marks, int total, double percentage, String grade) {
        this.studentName = studentName;
        this.marks = marks;
        this.total = total;
        this.percentage = percentage;
        this.grade = grade;
    }

    public void displayResult() {
        if (studentName == null || studentName.isEmpty()) {
            System.out.println("No student record found. Please enter marks first.");
            return;
        }
        System.out.println("\n--- STUDENT RESULT ---");
        System.out.print("Subject Marks: ");
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Sub" + (i + 1) + ": " + marks[i] + (i < marks.length - 1 ? ", " : ""));
        }
        System.out.println();
        System.out.println(studentName + " | Total: " + total + "/500 | " + percentage + "% | Grade " + grade);
    }
}