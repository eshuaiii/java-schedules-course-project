package model.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account1;
    Account account2;

    @BeforeEach
    void runBefore() {
        account1 = new Account("abc", "Eric", "S", "abcdefg");
        account2 = new Account("Boba", "Jim", "Joe", "hnnNNnnggg1");
    }

    @Test
    void constructorTest() {
        assertEquals("abc", account1.getUsername());
        assertEquals("Boba", account2.getUsername());
        assertEquals("Eric", account1.getFirstName());
        assertEquals("Jim", account2.getFirstName());
        assertEquals("S", account1.getLastName());
        assertEquals("Joe", account2.getLastName());
        assertEquals("abcdefg", account1.getPassword());
        assertEquals("hnnNNnnggg1", account2.getPassword());

        assertEquals("abc", account1.getStudent().getUsername());
        assertEquals("Boba", account2.getStudent().getUsername());
        assertEquals("Eric", account1.getStudent().getFirstName());
        assertEquals("Jim", account2.getStudent().getFirstName());
        assertEquals("S", account1.getStudent().getLastName());
        assertEquals("Joe", account2.getStudent().getLastName());
    }

    @Test
    void loginTest() {
        // successful
        Student account1Test1 = account1.login("abcdefg");
        assertEquals("abc", account1Test1.getUsername());
        assertEquals("Eric", account1Test1.getFirstName());
        assertEquals("S", account1Test1.getLastName());

        Student account2Test1 = account2.login("hnnNNnnggg1");
        assertEquals("Boba", account2Test1.getUsername());
        assertEquals("Jim", account2Test1.getFirstName());
        assertEquals("Joe", account2Test1.getLastName());

        // not successful
        Student account1Test2 = account2.login("aaaaaaaaaaaaa");
        assertNull(account1Test2);
        Student account2Test2 = account2.login("");
        assertNull(account2Test2);
    }

}