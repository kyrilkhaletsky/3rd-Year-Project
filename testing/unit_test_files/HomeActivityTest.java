package project.ca326.dcu.shoppingpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class HomeActivityTest {

    public String barcodeResult(String barcode) {
        if(barcode.length() != 13 || !barcode.matches("[0-9]+")){
            return null;
        }
        return barcode;
    }

    @Test
    public void barcodeResultTest () throws Exception {
        String result = barcodeResult("1234567890123");
        String expected = ("1234567890123");
        assertEquals(expected, result);
    }

    @Test
    public void barcodeResultTest2 () throws Exception {
        String result = barcodeResult("9836510836270");
        String expected = "9836510836270";
        assertEquals(expected, result);
    }

    @Test
    public void barcodeResultTest3 () throws Exception {
        String result = barcodeResult("1234567801ABC");
        String expected = null;
        assertEquals(expected, result);
    }
    @Test
    public void barcodeResultTest4 () throws Exception {
        String result = barcodeResult("12 '56&801ABC");
        String expected = null;
        assertEquals(expected, result);
    }
    @Test
    public void barcodeResultTest5 () throws Exception {
        String result = barcodeResult("6729174526043");
        String expected = "6729174526043";
        assertEquals(expected, result);
    }

    @Test
    public void barcodeResultTest6 () throws Exception {
        String result = barcodeResult("4568531569784");
        String expected = "4568531569784";
        assertEquals(expected, result);
    }

    @Test
    public void barcodeResultTest7 () throws Exception {
        String result = barcodeResult("7898531569784");
        String expected = "7898531569784";
        assertEquals(expected, result);
    }

    @Test
    public void barcodeResultTest8 () throws Exception {
        String result = barcodeResult("7898531569456");
        String expected = "7898531569456";
        assertEquals(expected, result);
    }

    @Test
    public void barcodeResultTest9 () throws Exception {
        String result = barcodeResult("1234IOP801ABC");
        String expected = null;
        assertEquals(expected, result);
    }

    @Test
    public void barcodeResultTest10 () throws Exception {
        String result = barcodeResult("HJK4567801ABC");
        String expected = null;
        assertEquals(expected, result);
    }

}