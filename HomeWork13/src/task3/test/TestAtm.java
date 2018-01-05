package task3.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import task3.atm.Atm;
import task3.atm.AtmWithBlackJackAndHookers;
import task3.atm.Denomination;

import java.util.ArrayList;
import java.util.List;

public class TestAtm extends Assert {
    Atm atm;

    @Before
    public void before() {
        List<Denomination> denominations = new ArrayList<>();
        denominations.add(new Denomination(100, 100));
        denominations.add(new Denomination(50, 100));
        denominations.add(new Denomination(20, 100));
        atm = new AtmWithBlackJackAndHookers(denominations, true, "VTB", "ATM-Produce co");
    }

    @Test
    public void testWithdraw() {
        assertTrue(atm.withdraw(770));
    }

    @Test
    public void testWithdraw1() {
        assertFalse(atm.withdraw(555));
    }

    @Test
    public void testWithdraw2() {
        assertFalse(atm.withdraw(17100));
    }

    @Test
    public void testWithdraw3() {
        assertFalse(atm.withdraw(-20));
    }

    @Test
    public void testWithdraw4() {
        assertTrue(atm.withdraw(0));
    }

    @Test
    public void testWithdraw5() {
        assertTrue(atm.withdraw(120));
    }

    @Test
    public void testDeposit() {
        assertTrue(atm.deposit(20));
    }
    @Test
    public void testDeposit1() {
        assertTrue(atm.deposit(0));
    }
    @Test
    public void testDeposit2() {
        assertFalse(atm.deposit(-20));
    }
    @Test
    public void testDeposit3() {
        assertTrue(atm.deposit(120));
    }
    @Test
    public void testDeposit4() {
        assertFalse(atm.deposit(5));
    }
    @Test
    public void testDeposit5() {
        assertTrue(atm.deposit(770));
    }

    @After
    public void after() {
        atm = null;
    }
}
