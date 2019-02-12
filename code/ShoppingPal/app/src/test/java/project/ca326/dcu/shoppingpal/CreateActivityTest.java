package project.ca326.dcu.shoppingpal;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CreateActivityTest {

    ArrayList<String> items;

    public String Add(String item, ArrayList<String> items) {
        if (items.contains(item.toLowerCase().trim())) {
            return null;
        }
        else if (item == null || item.trim().equals("")) {
            return null;
        } else {
            return item.toLowerCase().replaceAll("\\s+", "");
        }
    }

    @Test
    public void addTest () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs", "wheat"));
        String input = Add("Strawberry ", items);
        String expected = "strawberry";
        assertEquals(input, expected);
    }

    @Test
    public void addTest2 () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs","raspberry", "wheat"));
        String input = Add("Raspberry ", items);
        String expected = null;
        assertEquals(input, expected);
    }

    @Test
    public void addTest3 () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs","raspberry", "wheat"));
        String input = Add(" ", items);
        String expected = null;
        assertEquals(input, expected);
    }

    @Test
    public void addTest4 () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs", "wheat","pork", "eggs", "wheat","pork", "eggs", "wheat"));
        String input = Add("Strawberry ", items);
        String expected = "strawberry";
        assertEquals(input, expected);
    }

    @Test
    public void addTest5 () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs", "wheat"));
        String input = Add("Barley ", items);
        String expected = "barley";
        assertEquals(input, expected);
    }

    @Test
    public void addTest6 () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs", "wheat"));
        String input = Add("wheat ", items);
        String expected = null;
        assertEquals(input, expected);
    }

    @Test
    public void addTest7 () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs", "wheat"));
        String input = Add("barley ", items);
        String expected = "barley";
        assertEquals(input, expected);
    }

    public String Save(String key, ArrayList<String> items) {
        if (!key.equals("") && !items.isEmpty()) {
            return "success"; //push item into database
        }
        else {
            return null;
        }
    }

    @Test
    public void saveTest () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs", "wheat"));
        String input = Save("Berries", items);
        String expected = "success";
        assertEquals(input, expected);
    }
    @Test
    public void saveTest2 () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs", "wheat"));
        String input = Save("", items);
        String expected = null;
        assertEquals(input, expected);
    }

    @Test
    public void saveTest3 () throws Exception {
        items = new ArrayList<String>();
        String input = Save("", items);
        String expected = null;
        assertEquals(input, expected);
    }

    @Test
    public void saveTest4 () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs", "wheat", "barley", "strawberry", "blueberry"));
        String input = Save("Tom", items);
        String expected = "success";
        assertEquals(input, expected);
    }

    @Test
    public void saveTest5 () throws Exception {
        items = new ArrayList<String>(Arrays.asList("pork", "eggs", "wheat", "strawberry", "blueberry"));
        String input = Save("annie", items);
        String expected = "success";
        assertEquals(input, expected);
    }
}