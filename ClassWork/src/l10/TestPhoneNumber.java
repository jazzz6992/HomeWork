package l10;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestPhoneNumber extends Assert {
    @Rule
    public ExpectedException e = ExpectedException.none();

    @Test
    public void testPhoneNumber() {
        boolean result = Main.checkPhoneMumber("810375297777777");
        assertTrue(result);
    }

    @Test
    public void testPhoneNumber1() {
        boolean result = Main.checkPhoneMumber("+375297777777");
        assertTrue(result);
    }

    @Test
    public void testPhoneNumber2() {
        boolean result = Main.checkPhoneMumber("375297777777");
        assertFalse(result);
    }

    @Test
    public void testPhoneNumber3() {
        boolean result = Main.checkPhoneMumber("810375287777777");
        assertFalse(result);
    }

    @Test
    public void testPhoneNumber4() {
        e.expect(NullPointerException.class);
        Main.checkPhoneMumber(null);
    }
}
