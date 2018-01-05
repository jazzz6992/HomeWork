package l10;

import org.junit.Assert;
import org.junit.Test;

public class TestCarNumber extends Assert {

    @Test
    public void testCarNumber() {
        boolean result = Main.checkCarNumber("2050 AO-2");
        assertTrue(result);
    }

    @Test
    public void testCarNumber2() {
        boolean result = Main.checkCarNumber("1110 AO-2");
        assertTrue(result);
    }

    @Test
    public void testCarNumber3() {
        boolean result = Main.checkCarNumber("1110 AO-22");
        assertFalse(result);
    }
}
