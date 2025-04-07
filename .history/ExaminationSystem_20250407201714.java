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
        String[] subjects = {"Math", "English", "Science", "History", "Programming"};
        
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
    
    