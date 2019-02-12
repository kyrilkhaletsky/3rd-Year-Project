package project.ca326.dcu.shoppingpal;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.Assert.*;

public class InformationActivityTest {

    public static String [] compareLists(String [] selected, String [] found){
        List<String> newlist = new ArrayList<String>();
        outerloop: for(String s1 : found){
            for (String s2 : selected){
                if(s1.contains(s2)){
                    newlist.add(s1);
                    continue outerloop;
                }
            }
        }
        return newlist.toArray(new String[newlist.size()]);
    }

    @Test
    public void compareTest () throws Exception {
        String [] selected = {"strawberry", "kiwi", "raspberry", "blueberry"};
        String [] found = {"strawberry"};
        String [] result = compareLists(selected, found);
        String [] expected = {"strawberry"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareTest2 () throws Exception {
        String [] selected = {"strawberry", "kiwi", "raspberry", "blueberry"};
        String [] found = {"pork"};
        String [] result = compareLists(selected, found);
        String [] expected = {};
        assertArrayEquals(result, expected);
    }
    @Test
    public void compareTest3 () throws Exception {
        String [] selected = {};
        String [] found = {"strawberry"};
        String [] result = compareLists(selected, found);
        String [] expected = {};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareTest4 () throws Exception {
        String [] selected = {"strawberry", "kiwi", "raspberry", "blueberry"};
        String [] found = {"kiwi"};
        String [] result = compareLists(selected, found);
        String [] expected = {"kiwi"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareTest5 () throws Exception {
        String [] selected = {"pork", "chicken", "beef", "lamb"};
        String [] found = {"pork"};
        String [] result = compareLists(selected, found);
        String [] expected = {"pork"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareTest6 () throws Exception {
        String [] selected = {"pork", "chicken", "beef", "lamb"};
        String [] found = {"pork", "chicken", "beef", "lamb"};
        String [] result = compareLists(selected, found);
        String [] expected = {"pork", "chicken", "beef", "lamb"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareTest7 () throws Exception {
        String [] selected = {"strawberry", "kiwi", "raspberry", "blueberry"};
        String [] found = {"strawberry", "kiwi", "raspberry", "blueberry"};
        String [] result = compareLists(selected, found);
        String [] expected = {"strawberry", "kiwi", "raspberry", "blueberry"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareTest8 () throws Exception {
        String [] selected = {"strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry"};
        String [] found = {"strawberry"};
        String [] result = compareLists(selected, found);
        String [] expected = {"strawberry"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareTest9 () throws Exception {
        String [] selected = {"strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry"};
        String [] found = {"strawberry", "kiwi", "raspberry", "blueberry"};
        String [] result = compareLists(selected, found);
        String [] expected = {"strawberry", "kiwi", "raspberry", "blueberry"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareTest10 () throws Exception {
        String [] selected = {"strawberry", "strawberry","strawberry","strawberry","strawberry","strawberry"};
        String [] found = {"strawberry"};
        String [] result = compareLists(selected, found);
        String [] expected = {"strawberry"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareEfficiencyTest () throws Exception {
        String [] selected = {"strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","test"};
        String [] found = {"strawberry","kiwi", "raspberry", "blueberry","test"};
        String [] result = compareLists(selected, found);
        String [] expected = {"strawberry","kiwi", "raspberry", "blueberry", "test"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void compareEfficiencyTest2 () throws Exception {
        String [] selected = {"strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "red blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","black kiwi", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry", "blueberry","strawberry", "kiwi", "raspberry",
                "blueberry","strawberry", "kiwi", "raspberry", "blueberry","test"};
        String [] found = {"strawberry","kiwi", "raspberry", "blueberry","test", "red blueberry", "black_kiwi"};
        String [] result = compareLists(selected, found);
        String [] expected = {"strawberry","kiwi", "raspberry", "blueberry","test", "red blueberry", "black_kiwi"};
        assertArrayEquals(result, expected);
    }

    public String [] tescoDataset(String str) {
        String[] foundItems;
        if (!str.contains("ingredients")) {
            return null;
        }
        else {
            //extract ingredients using java regex
            String ingredients = str.substring(str.indexOf("ingredients:") + 12, str.indexOf(":end"))
                    .replaceAll("[\n\r]", "")
                    .replace("\"", "")
                    .replaceAll("(?i)(?:contains|antioxidant|preservative)s?\\s*", "")
                    .replaceAll("\\<.*?\\>", "")
                    .replaceAll("\\((?:\\d|\\.)+%\\)", "");
            //split to a list
            List<String> list = new ArrayList<String>(Arrays.asList(ingredients.split("[:\\,\\.\\(\\)]+")));
            List<String> newlist = new ArrayList<String>();
            //trim each string and convert to lowercase if its not empty
            for (String s : list) {
                if (s.matches(".*\\w.*")) {
                    newlist.add(s.trim().toLowerCase());
                }
            }
            //remove duplicates using a linked hashset and add to foundItems
            newlist = new ArrayList<String>(new LinkedHashSet<String>(newlist));
            foundItems = new String[newlist.size()];
            return newlist.toArray(foundItems);
        }
    }

    @Test
    public void tescoDatasetTest () throws Exception {
        String input = "ingredients: strawberry, strawberry, contains, contains,contains,contains Antioxidant, (15%), , raspberry :end";
        String [] result = tescoDataset(input);
        String [] expected = {"strawberry", "raspberry"};
        assertArrayEquals(result, expected);
    }
    @Test
    public void tescoDatasetTest2 () throws Exception {
        String input = " strawberry, strawberry, contains, Antioxidant, (15%), , raspberry :end";
        String [] result = tescoDataset(input);
        String [] expected = null;
        assertArrayEquals(result, expected);
    }
    @Test
    public void tescoDatasetTest3 () throws Exception {
        String input = "ingredients: strawberry, strawberry, contains,\n, \\ , Antioxidant, (15%), , raspberry, blueberry :end";
        String [] result = tescoDataset(input);
        String [] expected = {"strawberry", "raspberry", "blueberry"};
        assertArrayEquals(result, expected);
    }
    @Test
    public void tescoDatasetTest4 () throws Exception {
        String input = "ingredients: contains,\n, Antioxidant, (15%) :end";
        String [] result = tescoDataset(input);
        String [] expected = {};
        assertArrayEquals(result, expected);
    }

    @Test
    public void tescoDatasetTest5 () throws Exception {
        String input = "ingredients: strawberry, strawberry, raspberry, contains, pork, pork, kiwi, Antioxidant, (15%), , raspberry :end";
        String [] result = tescoDataset(input);
        String [] expected = {"strawberry", "raspberry", "pork", "kiwi"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void tescoDatasetTest6 () throws Exception {
        String input = "ingredients: strawberry, strawberry, strawberry,strawberry,strawberry,strawberry :end";
        String [] result = tescoDataset(input);
        String [] expected = {"strawberry"};
        assertArrayEquals(result, expected);
    }
}