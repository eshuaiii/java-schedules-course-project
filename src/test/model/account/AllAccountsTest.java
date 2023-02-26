package model.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllAccountsTest {

    AllAccounts accountList;

    @BeforeEach
    void runBefore() {
        accountList = new AllAccounts();
    }

    @Test
    void constructorTest() {
        assertEquals(0, accountList.getAccountList().size());
    }

    @Test
    void signupTest() {
        Student student1 = accountList.signup("abc", "Eric", "S", "abcdefg");
        Account account1 = accountList.getAccountList().get("abc");
        assertEquals("abc", account1.getUsername());
        assertEquals("Eric", account1.getFirstName());
        assertEquals("S", account1.getLastName());
        assertEquals("abcdefg", account1.getPassword());
        assertEquals(student1, account1.getStudent());
        assertEquals(1, accountList.getAccountList().size());

        Student student2 = accountList.signup("iLoveJaVa1", "Hey", "Boy", "pplp2!pplo");
        Account account2 = accountList.getAccountList().get("iLoveJaVa1");
        assertEquals("iLoveJaVa1", account2.getUsername());
        assertEquals("Hey", account2.getFirstName());
        assertEquals("Boy", account2.getLastName());
        assertEquals("pplp2!pplo", account2.getPassword());
        assertEquals(student2, account2.getStudent());
        assertEquals(2, accountList.getAccountList().size());
    }

    @Test
    void loginTest() {
        //Login: test right combination, wrong password, username doesn't exist
        Student student1 = accountList.signup("abc", "Eric", "S", "abcdefg");

        // login successful
        assertEquals(student1, accountList.login("abc", "abcdefg"));

        // wrong password
        assertNull(accountList.login("abc", "aaaaaaaaaaaaa"));

        // user does not exist
        assertNull(accountList.login("aaaaaaaaaa", "abcdefg"));
    }

    @Test
    void searchStudentTest() {
        Student student1 = accountList.signup("abc", "Eric", "S", "abcdefg");
        Student student2 = accountList.signup("Boba", "Jim", "Joe", "hnnNNnnggg1");
        Student student3 = accountList.signup("iLoveJaVa1", "Hey", "Boy", "pplp2!pplo");

        // successfully finds
        assertEquals(student1, accountList.searchStudent("abc"));
        assertEquals(student2, accountList.searchStudent("Boba"));
        assertEquals(student3, accountList.searchStudent("iLoveJaVa1"));

        // fails to find student
        assertNull(accountList.searchStudent("hello"));
    }
}