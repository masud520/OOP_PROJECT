import java.util.*;
import java.text.SimpleDateFormat;

public class ExaminationSystem {
    // Constants
    private static final int MIN_STUDENTS = 10;
    private static final int MIN_SUBJECTS = 5;
    
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        
        // 1. Read student details
        readStudentDetails(students);
        
        // 2. Read subject scores for each student
        readSubjectScores(students);
        
        // 3. Calculate averages and grades
        calculateResults(students);
        
        // 4. Display report cards
        displayReportCards(students);
    }
    
    // Method to read student details
    private static void readStudentDetails(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter details for " + MIN_STUDENTS + " students:");
        
        for (int i = 0; i < MIN_STUDENTS; i++) {
            System.out.println("\nStudent " + (i+1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("ID Number: ");
            String id = scanner.nextLine();
            
            students.add(new Student(name, id));
        }
    }
    
    // Method to read subject scores
    private static void readSubjectScores(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        String[] subjects = {"Math", "English", "Science", "", "Programming"};
        
        for (Student student : students) {
            System.out.println("\nEnter scores for " + student.getName() + ":");
            for (String subject : subjects) {
                System.out.print(subject + " score: ");
                double score = scanner.nextDouble();
                student.addSubjectScore(subject, score);
            }
            scanner.nextLine(); // consume newline
        }
    }
    
    // Method to calculate results
    private static void calculateResults(List<Student> students) {
        for (Student student : students) {
            student.calculateAverage();
            student.determineGrade();
            student.generateRecommendation();
        }
    }
    
    // Method to display report cards
    private static void displayReportCards(List<Student> students) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());
        
        for (Student student : students) {
            System.out.println("\n\n=== REPORT CARD ===");
            System.out.println("Date: " + currentDate);
            System.out.println("Student Name: " + student.getName());
            System.out.println("ID Number: " + student.getId());
            System.out.println("\nSubject Scores:");
            
            for (Map.Entry<String, Double> entry : student.getSubjectScores().entrySet()) {
                System.out.printf("%-15s: %.1f%n", entry.getKey(), entry.getValue());
            }
            
            System.out.printf("%nAverage Score: %.1f%n", student.getAverageScore());
            System.out.println("Grade: " + student.getGrade());
            System.out.println("Recommendation: " + student.getRecommendation());
            System.out.println("==================");
        }
    }
}

class Student {
    private String name;
    private String id;
    private Map<String, Double> subjectScores;
    private double averageScore;
    private String grade;
    private String recommendation;
    
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.subjectScores = new HashMap<>();
    }
    
    public void addSubjectScore(String subject, double score) {
        subjectScores.put(subject, score);
    }
    
    public void calculateAverage() {
        double sum = 0;
        for (double score : subjectScores.values()) {
            sum += score;
        }
        this.averageScore = sum / subjectScores.size();
    }
    
    public void determineGrade() {
        // Strathmore University grading system (adjust as needed)
        if (averageScore >= 75) this.grade = "A";
        else if (averageScore >= 60) this.grade = "B";
        else if (averageScore >= 50) this.grade = "C";
        else if (averageScore >= 40) this.grade = "D";
        else this.grade = "F";
    }
    
    public void generateRecommendation() {
        if (averageScore >= 70) this.recommendation = "Excellent performance";
        else if (averageScore >= 60) this.recommendation = "Good performance";
        else if (averageScore >= 50) this.recommendation = "Average performance";
        else if (averageScore >= 40) this.recommendation = "Below average - needs improvement";
        else this.recommendation = "Poor performance - consider remedial classes";
    }
    
    // Getters
    public String getName() { return name; }
    public String getId() { return id; }
    public Map<String, Double> getSubjectScores() { return subjectScores; }
    public double getAverageScore() { return averageScore; }
    public String getGrade() { return grade; }
    public String getRecommendation() { return recommendation; }
}