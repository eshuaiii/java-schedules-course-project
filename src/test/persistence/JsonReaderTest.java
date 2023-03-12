package persistence;

import model.account.Account;
import model.account.AllAccounts;
import model.course.AllCourses;
import model.course.Course;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List<Object> result = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccountList.json");
        try {
            List<Object> result = reader.read();
            AllAccounts accountList = (AllAccounts) result.get(0);
            AllCourses courseList = (AllCourses) result.get(1);
            assertEquals(0, accountList.getAccountList().size());
            assertEquals(0, courseList.getCourseList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccountList.json");
        try {
            List<Object> result = reader.read();
            AllAccounts aa = (AllAccounts) result.get(0);
            AllCourses ac = (AllCourses) result.get(1);

            assertEquals(2, aa.getAccountList().size());
            assertEquals(2, ac.getCourseList().size());

            // check Account and Student details
            Account accountCheck1 = aa.getAccountList().get("ericshuai");
            assertNotNull(accountCheck1);
            checkAccount(accountCheck1, "ericshuai", "Eric", "S", "abc");
            Account accountCheck2 = aa.getAccountList().get("bob123");
            assertNotNull(accountCheck2);
            checkAccount(accountCheck2, "bob123", "Bob", "Joe", "BobJoe123");

            // check Course details
            Course courseCheck1 = ac.searchCourse("CPSC", 210, 203);
            assertNotNull(courseCheck1);
            checkCourse(courseCheck1, "CPSC", 210, 203);
            Course courseCheck2 = ac.searchCourse("CPSC", 121, 202);
            assertNotNull(courseCheck2);
            checkCourse(courseCheck2, "CPSC", 121, 202);

            // Check that all Students have right courses
            assertTrue(accountCheck1.getStudent().getCourseList().containsValue(courseCheck1));
            assertTrue(accountCheck1.getStudent().getCourseList().containsValue(courseCheck2));
            assertTrue(accountCheck2.getStudent().getCourseList().containsValue(courseCheck2));

            // and all Courses have their students
            assertTrue(courseCheck1.getStudentList().contains(accountCheck1.getStudent()));
            assertTrue(courseCheck2.getStudentList().contains(accountCheck1.getStudent()));
            assertTrue(courseCheck2.getStudentList().contains(accountCheck2.getStudent()));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}