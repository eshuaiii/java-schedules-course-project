package persistence;

import model.account.Account;
import model.course.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

// modelled based on the JsonSerializationDemo file, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkAccount(Account account, String username, String firstName, String lastName, String password) {
        assertEquals(username, account.getUsername());
        assertEquals(firstName, account.getFirstName());
        assertEquals(lastName, account.getLastName());
        assertEquals(password, account.getPassword());
        assertEquals(firstName, account.getStudent().getFirstName());
        assertEquals(lastName, account.getStudent().getLastName());
        assertEquals(username, account.getStudent().getUsername());
    }
    protected void checkCourse(Course course, String courseName, Integer courseNum, Integer courseSection) {
        assertEquals(courseName, course.getCourseName());
        assertEquals(courseNum, course.getCourseNum());
        assertEquals(courseSection, course.getCourseSection());
    }
}
