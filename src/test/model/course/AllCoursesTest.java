package model.course;

import model.account.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AllCoursesTest {

    AllCourses courseList;

    @BeforeEach
    void runBefore() {
        courseList = new AllCourses();
    }

    @Test
    void constructorTest() {
        assertEquals(0, courseList.getCourseList().size());
    }

    @Test
    void addCourseTest() {
        // add one
        courseList.addCourse("CPSC", 210, 203);
        assertEquals(1, courseList.getCourseList().size());
        assertTrue(courseList.getCourseList().containsKey("CPSC-210-203"));
        assertEquals("CPSC", courseList.getCourseList().get("CPSC-210-203").getCourseName());
        assertEquals(210, courseList.getCourseList().get("CPSC-210-203").getCourseNum());
        assertEquals(203, courseList.getCourseList().get("CPSC-210-203").getCourseSection());

        // add the same one
        courseList.addCourse("CPSC", 210, 203);
        assertEquals(1, courseList.getCourseList().size());
        assertTrue(courseList.getCourseList().containsKey("CPSC-210-203"));
        assertEquals("CPSC", courseList.getCourseList().get("CPSC-210-203").getCourseName());
        assertEquals(210, courseList.getCourseList().get("CPSC-210-203").getCourseNum());
        assertEquals(203, courseList.getCourseList().get("CPSC-210-203").getCourseSection());

        // add another one
        courseList.addCourse("AB", 1, 9999);
        assertEquals(2, courseList.getCourseList().size());
        assertTrue(courseList.getCourseList().containsKey("AB-1-9999"));
        assertEquals("AB", courseList.getCourseList().get("AB-1-9999").getCourseName());
        assertEquals(1, courseList.getCourseList().get("AB-1-9999").getCourseNum());
        assertEquals(9999, courseList.getCourseList().get("AB-1-9999").getCourseSection());

    }

    @Test
    void checkForEmptyCourseTest() {
        // test with empty course
        courseList.addCourse("CPSC", 210, 203);
        assertEquals(1, courseList.getCourseList().size());
        Course testCourse1 = courseList.getCourseList().get("CPSC-210-203");
        assertTrue(courseList.checkForEmptyCourse(testCourse1));
        assertEquals(0, courseList.getCourseList().size());

        // test with one student inside
        courseList.addCourse("AB", 1, 9999);
        assertEquals(1, courseList.getCourseList().size());
        Course testCourse2 = courseList.getCourseList().get("AB-1-9999");
        testCourse2.addStudent(new Student("abc", "Eric", "S"));
        assertFalse(courseList.checkForEmptyCourse(testCourse2));
        assertEquals(1, courseList.getCourseList().size());

        // do the same with a course with multiple students
        courseList.addCourse("MM", 333, 100);
        assertEquals(2, courseList.getCourseList().size());
        Course testCourse3 = courseList.getCourseList().get("MM-333-100");
        testCourse3.addStudent(new Student("abc", "Eric", "S"));
        testCourse3.addStudent(new Student("Boba", "Jim", "Joe"));
        assertFalse(courseList.checkForEmptyCourse(testCourse3));
        assertEquals(2, courseList.getCourseList().size());
    }

    @Test
    void searchCourseTest() {
        // cannot find course in empty
        assertNull(courseList.searchCourse("CPSC", 210, 203));

        // then add some courses
        courseList.addCourse("CPSC", 210, 203);
        courseList.addCourse("AB", 1, 9999);

        // find course
        Course testCourse1 = courseList.searchCourse("CPSC", 210, 203);
        assertEquals("CPSC", testCourse1.getCourseName());
        assertEquals(210, testCourse1.getCourseNum());
        assertEquals(203, testCourse1.getCourseSection());

        // cannot find course (small typo)
        assertNull(courseList.searchCourse("AB", 1, 999));

    }
}
