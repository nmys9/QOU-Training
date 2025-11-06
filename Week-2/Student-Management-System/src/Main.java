import com.studentmanagement.SMS;

public class Main {
    public static void main(String[] args) {
        System.out.println("----- Welcome to Student Management System -----");

        SMS sms=new SMS();

        sms.addStudent(1,"noor");

        sms.addCourse(1,"Math");
        sms.addCourse(2,"sciences");
        sms.addCourse(3,"Arabic");

        sms.addEnrollment(1,2,90);
        sms.addEnrollment(1,1,99);
        sms.addEnrollment(1,3,80);


        sms.showStudents();

        sms.showCalcGPA(1);

        System.out.println("************q" +
                "qq`" +






    }
}