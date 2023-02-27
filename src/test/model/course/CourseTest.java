package model.course;

import model.account.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    Course course1;
    Course course2;
    Student student1;
    Student student2;
    Student student3;
    Student student4;
    Student student5;

    @BeforeEach
    void runBefore() {
        course1 = new Course("CPSC", 210, 203);
        course2 = new Course("AB", 1, 9999);
        student1 = new Student("abc", "Eric", "Shuai");
        student2 = new Student("Boba", "Eric", "Shui");
        student3 = new Student("aaaah", "Eric", "C");
        student4 = new Student("iLoveJaVa1", "Hey", "Boy");
        student5 = new Student("hiyaaaa", "Hey", "Boy");
    }

    @Test
    void constructorTest() {
        assertEquals("CPSC", course1.getCourseName());
        assertEquals("AB", course2.getCourseName());
        assertEquals(210, course1.getCourseNum());
        assertEquals(1, course2.getCourseNum());
        assertEquals(203, course1.getCourseSection());
        assertEquals(9999, course2.getCourseSection());
        assertEquals(0, course1.getStudentList().size());
        assertEquals(0, course2.getStudentList().size());
    }

    @Test
    void addStudentTest() {
        // add a student
        assertTrue(course1.addStudent(student1));
        assertEquals(1, course1.getStudentList().size());
        assertTrue(course1.getStudentList().contains(student1));

        // try adding them again
        assertFalse(course1.addStudent(student1));
        assertEquals(1, course1.getStudentList().size());
        assertTrue(course1.getStudentList().contains(student1));

        // add a new student
        assertTrue(course1.addStudent(student2));
        assertEquals(2, course1.getStudentList().size());
        assertTrue(course1.getStudentList().contains(student2));

        // try a new course
        assertTrue(course2.addStudent(student2));
        assertEquals(1, course2.getStudentList().size());
        assertTrue(course2.getStudentList().contains(student2));
    }

    @Test
    void removeStudentTest() {
        // First add students
        course1.addStudent(student1);
        course1.addStudent(student2);

        // Try removing a student that doesn't exist
        assertFalse(course1.removeStudent(student3));

        // Remove a student
        assertTrue(course1.removeStudent(student1));
        assertEquals(1, course1.getStudentList().size());
        assertFalse(course1.getStudentList().contains(student1));
        assertTrue(course1.getStudentList().contains(student2));

        // Try removing the same student
        assertFalse(course1.removeStudent(student1));
        assertEquals(1, course1.getStudentList().size());
        assertFalse(course1.getStudentList().contains(student1));
        assertTrue(course1.getStudentList().contains(student2));
    }

    @Test
    void getStudentListSortedTest() {
        // First test a sort with no students
        assertEquals(new ArrayList<String>(), course1.getStudentListSorted());

        // Then add all students (non-alphabetical order)
        course1.addStudent(student4);
        course1.addStudent(student1);
        course1.addStudent(student3);
        course1.addStudent(student5);
        course1.addStudent(student2);

        // Create the supposed list, and check for equality
        ArrayList<String> resultList = new ArrayList<>();
        resultList.add("Eric C");
        resultList.add("Eric Shuai");
        resultList.add("Eric Shui");
        resultList.add("Hey Boy");
        resultList.add("Hey Boy");
        assertEquals(resultList, course1.getStudentListSorted());
    }
    /*
getstudentlistsorted: add a bunch of students, check for sort. Check same first name, first/last, a bit off, empty list
 */

    @Test
    void courseToKeyTest() {
        assertEquals("CPSC-210-203", course1.courseToKey());
        assertEquals("AB-1-9999", course2.courseToKey());
    }

    @Test
    void courseLongTest() {
        assertEquals("CPSC 210, Section 203", course1.courseLong());
        assertEquals("AB 1, Section 9999", course2.courseLong());
    }
}
