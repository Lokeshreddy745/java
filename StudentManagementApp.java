
import java.util.Scanner;

public class StudentManagementApp {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        int choice;

        //for menu
        do {
            System.out.println("//-----Student Management Menu -----//");
            System.out.println("1) Create Student ");
            System.out.println("2) Remove Student ");
            System.out.println("3) Display All Students ");
            System.out.println("4) Display One Student ");
            System.out.println("5) Average Marks of Students ");
            System.out.println("6) Exit ");

            System.out.println("Enter Your Choice");
            choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter ID :");
                    int id = s.nextInt();
                    manager.duplicate(id);
                    // s.next();
                    System.out.println("Enter Name :");
                    // s.next();
                    String name = s.next();
                    // s.next();
                    System.out.println("Enter Age :");
                    // s.next();
                    int age = s.nextInt();
                    // s.next();
                    System.out.println("Enter Course Name :");
                    // s.next();
                    String course = s.next();
                    System.out.println("Enter Student Marks :");
                    int marks = s.nextInt();
                    Student student = new Student(id, name, age, course, marks);
                    manager.addStudent(student);
                    break;

                case 2:
                    System.out.println("Enter Student ID to remove");
                    int removeId = s.nextInt();
                    manager.removeStudent(removeId);
                    break;

                case 3:
                    manager.displayAllStudents();
                    break;

                case 4:
                    System.out.println("Enter Student ID to display :");
                    int displayId = s.nextInt();
                    manager.displayOneStudent(displayId);
                    break;

                case 6:
                    System.out.println("Exiting Good BYE !");
                    break;
                case 5:
                    System.out.println("Average Marks of Students");
                    manager.avgMarks();
                    break;

                default:
                    System.out.println("Please Enter Valid Choice !!");
            }
        } while (choice != 6);

        s.close();

    }
}

abstract class Person {

    protected int id;
    protected String name;
    protected int age;

    //Constructor
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public abstract void displayDetails();
}

final class Student extends Person {

    private String course;
    int marks;
    private static int StudentCount = 0;

    public Student(int id, String name, int age, String course, int marks) {
        super(id, name, age);
        this.course = course;
        this.marks = marks;
        StudentCount++;
    }

    @Override
    public void displayDetails() {
        System.out.println("Id : " + id);
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
        System.out.println("Course : " + course);
        System.out.println("Marks : " + marks);
    }

    public static int getStudentCount() {
        return StudentCount;
    }

    public static void decrementCount() {
        StudentCount--;
    }
}

class StudentManager {

    private Student[] studentArray = new Student[100];
    private int size = 0;

    public void duplicate(int id) {
        if (findIndexById(id) != -1) {
            System.out.println("Student with ID " + id + " already exists");
            System.exit(0);
        }

    }

    public void addStudent(Student student) {
        if (findIndexById(student.id) != -1) {
            System.out.println("Student with ID " + student.id + " already exists");
            return;
        }
        if (size >= studentArray.length) {
            System.out.println("Can not add the students cause array is full !!");
            return;
        }
        studentArray[size] = student;
        size++;
        System.out.println("Student Added Successfully.");
    }

    public void removeStudent(int id) {
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("No Student found with ID :" + id);
            return;
        }

        //Shift elements from ending
        for (int i = index; i < size - 1; i++) {
            studentArray[i] = studentArray[i + 1];
        }

        studentArray[size - 1] = null;
        size--;
        Student.decrementCount();
        System.out.println("Student removed Successfully.");
    }

    public void avgMarks() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += studentArray[i].marks;
        }
        int average = sum / Student.getStudentCount();
        System.out.println(average);
    }

    //Helper method to find Student index using ID
    private int findIndexById(int id) {
        for (int i = 0; i < size; i++) {
            if (studentArray[i].id == id) {
                return i;
            }
        }
        return -1;
    }

    //To Display One Student Details
    public void displayOneStudent(int id) {
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("No Student found with ID : " + id);
        } else {
            studentArray[index].displayDetails();
        }
    }

    //To Display All Student Details
    public void displayAllStudents() {
        if (size == 0) {
            System.out.println("There are no Students to display.");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println("---------------------------");
            studentArray[i].displayDetails();
        }

        System.out.println("------------------------");
        System.out.println("Total no.of Students :" + Student.getStudentCount());
    }

}
