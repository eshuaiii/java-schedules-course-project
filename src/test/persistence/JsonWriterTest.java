package persistence;

import model.account.Account;
import model.account.AllAccounts;
import model.account.Student;
import model.course.AllCourses;
import model.course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    AllAccounts aa;
    AllCourses ac;

    @BeforeEach
    void runBefore() {
        aa = new AllAccounts();
        ac = new AllCourses();
    }
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccountList.json");
            writer.open();
            writer.write(aa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccountList.json");
            List<Object> result = reader.read();

            aa = (AllAccounts) result.get(0);
            ac = (AllCourses) result.get(1);
            assertEquals(0, aa.getAccountList().size());
            assertEquals(0, ac.getCourseList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Student student1 = aa.signup("ericshuai", "Eric", "S", "abc");
            Student student2 = aa.signup("bob123", "Bob", "Joe", "BobJoe123");

            Course course1 = new Course("CPSC", 210, 203);
            Course course2 = new Course("CPSC", 121, 202);

            student1.addCourse(course1);
            student1.addCourse(course2);
            student2.addCourse(course2);


            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccountList.json");
            writer.open();
            writer.write(aa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccountList.json");
            List<Object> result = reader.read();
            aa = (AllAccounts) result.get(0);
            ac = (AllCourses) result.get(1);

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
            fail("Exception should not have been thrown");
        }
    }
}