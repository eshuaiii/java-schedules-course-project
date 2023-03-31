package model.account;

import model.course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    Student student1;
    Student student2;
    Student student3;
    Course course1;
    Course course2;

    @BeforeEach
    void runBefore() {
        student1 = new Student("abc", "Eric", "S");
        student2 = new Student("Boba", "Jim", "Joe");
        student3 = new Student("iLoveJaVa1", "Hey", "Boy");
        course1 = new Course("CPSC", 210, 203);
        course2 = new Course("AB", 1, 9999);
    }

    @Test
    void constructorTest() {
        assertEquals("abc", student1.getUsername());
        assertEquals("Boba", student2.getUsername());
        assertEquals("Eric", student1.getFirstName());
        assertEquals("Jim", student2.getFirstName());
        assertEquals("S", student1.getLastName());
        assertEquals("Joe", student2.getLastName());
        assertEquals(0, student1.getCourseList().size());
        assertEquals(0, student2.getCourseList().size());
    }

    @Test
    void addCourseTest() {
        // add one course
        student1.addCourse(course1);
        assertEquals(course1, student1.getCourseList().get("CPSC-210-203"));
        assertEquals(1, student1.getCourseList().size());
        assertTrue(course1.getStudentList().contains(student1));
        assertEquals(1, course1.getStudentList().size());

        // add the same course to see if anything changes
        student1.addCourse(course1);
        assertEquals(course1, student1.getCourseList().get("CPSC-210-203"));
        assertEquals(1, student1.getCourseList().size());
        assertTrue(course1.getStudentList().contains(student1));
        assertEquals(1, course1.getStudentList().size());

        // add a new course
        student1.addCourse(course2);
        assertEquals(course2, student1.getCourseList().get("AB-1-9999"));
        assertEquals(2, student1.getCourseList().size());
        assertTrue(course2.getStudentList().contains(student1));
        assertEquals(1, course2.getStudentList().size());

        // add a new student to a course
        student2.addCourse(course1);
        assertEquals(course1, student2.getCourseList().get("CPSC-210-203"));
        assertEquals(1, student2.getCourseList().size());
        assertTrue(course1.getStudentList().contains(student2));
        assertEquals(2, course1.getStudentList().size());
    }

    @Test
    void removeCourseTest() {
        // First add all required courses:
        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course1);
        student3.addCourse(course2);

        // remove a course from student1
        student1.removeCourse(course2);
        assertNull(student1.getCourseList().get("AB-1-9999"));
        assertEquals(1, student1.getCourseList().size());
        assertFalse(course2.getStudentList().contains(student1));
        assertEquals(1, course2.getStudentList().size());

        // try removing again
        student1.removeCourse(course2);
        assertNull(student1.getCourseList().get("AB-1-9999"));
        assertEquals(1, student1.getCourseList().size());
        assertFalse(course2.getStudentList().contains(student1));
        assertEquals(1, course2.getStudentList().size());

        // remove another course from student1
        student1.removeCourse(course1);
        assertNull(student1.getCourseList().get("CPSC-210-203"));
        assertEquals(0, student1.getCourseList().size());
        assertFalse(course1.getStudentList().contains(student1));
        assertEquals(1, course1.getStudentList().size());

        // remove a course that doesn't exist in a student
        student3.removeCourse(course1);
        assertNull(student3.getCourseList().get("CPSC-210-203"));
        assertEquals(course2, student3.getCourseList().get("AB-1-9999"));
        assertEquals(1, student3.getCourseList().size());
        assertEquals(1, course2.getStudentList().size());
    }

    @Test
    void getSharedCourses() {
        // First add all required courses:
        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course1);

        // comparing with a student that has no courses
        assertEquals(new ArrayList<Course>(), student2.getSharedCourses(student3));
        assertEquals(new ArrayList<Course>(), student3.getSharedCourses(student2));

        // comparing with a student that has no shared courses
        student3.addCourse(course2);
        assertEquals(new ArrayList<Course>(), student2.getSharedCourses(student3));
        assertEquals(new ArrayList<Course>(), student3.getSharedCourses(student2));

        // has one shared course
        ArrayList<Course> courseList1 = new ArrayList<>();
        ArrayList<Course> courseList2 = new ArrayList<>();

        courseList1.add(course1);
        courseList2.add(course2);

        assertEquals(courseList1, student1.getSharedCourses(student2));
        assertEquals(courseList1, student2.getSharedCourses(student1));
        assertEquals(courseList2, student1.getSharedCourses(student3));
        assertEquals(courseList2, student3.getSharedCourses(student1));

        // has multiple shared courses
        student2.addCourse(course2);
        ArrayList<Course> courseList3 = new ArrayList<>();
        courseList3.add(course2);
        courseList3.add(course1);
        assertEquals(courseList3, student1.getSharedCourses(student2));
        assertEquals(courseList3, student2.getSharedCourses(student1));


    }

    @Test
    void getFullNameTest() {
        assertEquals("Eric S", student1.getFullName());
        assertEquals("Jim Joe", student2.getFullName());
        assertEquals("Hey Boy", student3.getFullName());
    }

    @Test
    void getCoursesTest() {
        // First add all required courses:
        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course1);
        student3.addCourse(course2);

        List<Course> courseList1 = student1.getCourses();
        List<Course> courseList2 = student2.getCourses();
        List<Course> courseList3 = student3.getCourses();

        // Then run:
        assertEquals(2, courseList1.size());
        assertTrue(courseList1.contains(course1));
        assertTrue(courseList1.contains(course2));

        assertEquals(1, courseList2.size());
        assertTrue(courseList2.contains(course1));

        assertEquals(1, courseList3.size());
        assertTrue(courseList3.contains(course2));
    }

    @Test
    void getCourseNamesGUITest() {
        Map<String, String> result = student1.getCourseNamesGUI();
        assertEquals(0, result.size());

        // First add all required courses:
        student1.addCourse(course1);
        student1.addCourse(course2);
        result = student1.getCourseNamesGUI();

        // Check for existence:
        assertEquals(2, result.size());
        assertEquals(course1.courseToKey(), result.get(course1.courseLong()));
        assertEquals(course2.courseToKey(), result.get(course2.courseLong()));
    }

    @Test
    void getSharedCoursesGUI() {
        // First add all required courses:
        student1.addCourse(course1);
        student1.addCourse(course2);
        // Try one with no shared courses:
        Map<String, String> result = student1.getSharedCoursesGUI(student2);
        assertNull(result);

        // add a course
        student2.addCourse(course1);
        result = student1.getSharedCoursesGUI(student2);
        assertEquals(1, result.size());
        assertEquals(course1.courseToKey(), result.get(course1.courseLong()));
    }
}