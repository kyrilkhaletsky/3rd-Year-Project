package project.ca326.dcu.shoppingpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainActivityTest {

    public String checkEmail(String email) {
        if (email == null || email.isEmpty()){
            return null;
        }
        return email.trim();}
    @Test
    public void checkEmailTest () throws Exception {
        String input = checkEmail("");
        String expected = null;
        assertEquals(input, expected);
    }
    @Test
    public void checkEmailTest2 () throws Exception {
        String input = checkEmail("ben@hotmail.com ");
        String expected = "ben@hotmail.com";
        assertEquals(input, expected);
    }
    @Test
    public void checkEmailTest3 () throws Exception {
        String input = checkEmail(" D23abc6@mail.com ");
        String expected = "D23abc6@mail.com";
        assertEquals(input, expected);
    }
    @Test
    public void checkEmailTest4 () throws Exception {
        String input = checkEmail("kyril_18 ");
        String expected = "kyril_18";
        assertEquals(input, expected);
    }

    @Test
    public void checkEmailTest5 () throws Exception {
        String input = checkEmail("benkelly@hotmail.com ");
        String expected = "benkelly@hotmail.com";
        assertEquals(input, expected);
    }

    public String checkPassword(String password) {
        if(password == null || password.isEmpty()){
            return null;
        }
        return password.trim();
    }

    @Test
    public void checkPasswordTest () throws Exception {
        String input = checkPassword("");
        String expected = null;
        assertEquals(input, expected);
    }

    @Test
    public void checkPasswordTest2 () throws Exception {
        String input = checkPassword(" 123456 ");
        String expected = "123456";
        assertEquals(input, expected);
    }

    @Test
    public void checkPasswordTest3 () throws Exception {
        String input = checkPassword("thisisnotagoodpassword  ");
        String expected = "thisisnotagoodpassword";
        assertEquals(input, expected);
    }

    @Test
    public void checkPasswordTest4 () throws Exception {
        String input = checkPassword(" pa$$word ");
        String expected = "pa$$word";
        assertEquals(input, expected);
    }

    @Test
    public void checkPasswordTest5 () throws Exception {
        String input = checkPassword("789456123");
        String expected = "789456123";
        assertEquals(input, expected);
    }
}