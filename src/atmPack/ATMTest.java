package atmPack;

import org.junit.Test;
import static org.junit.Assert.*;

public class ATMTest {

    /**
     * Your assignment is to write many test cases  *****
     * I have provided some examples to help you get started
     * <p>
     * All the lines are commented out until you get the basic
     * ATM class working, then uncomment them
     */


    @Test
    public void testConstructor() {
        ATM s1 = new ATM(6, 5, 4);

        assertEquals(6, s1.getHundreds());
        assertEquals(5, s1.getFifties());
        assertEquals(4, s1.getTwenties());

        ATM s2 = new ATM();
        assertEquals(0, s2.getHundreds());
        assertEquals(0, s2.getFifties());
        assertEquals(0, s2.getTwenties());

        ATM s3 = new ATM(s1);
        assertEquals(6, s3.getHundreds());
        assertEquals(5, s3.getFifties());
        assertEquals(4, s3.getTwenties());
    }

    @Test
    public void testTakeOut1() {
        ATM s1 = new ATM(3, 3, 2);
        s1.takeOut(1, 1, 1);
        assertEquals(2, s1.getHundreds());
        assertEquals(2, s1.getFifties());
        assertEquals(1, s1.getTwenties());
    }

    @Test
    public void testTakeOut2ADD() {
        ATM s1 = new ATM(5, 3, 3);
        ATM s2 = s1.takeOut(120);

        assertEquals(4, s1.getHundreds());
        assertEquals(3, s1.getFifties());
        assertEquals(2, s1.getTwenties());

        assertEquals(1, s2.getHundreds());
        assertEquals(0, s2.getFifties());
        assertEquals(1, s2.getTwenties());
    }

    @Test
    public void testPutIn() {
        ATM s1 = new ATM();
        s1.putIn(2, 3, 4);
        assertEquals(2, s1.getHundreds());
        assertEquals(3, s1.getFifties());
        assertEquals(4, s1.getTwenties());
    }

    @Test
    public void testPutInTakeOut() {
        ATM s1 = new ATM();
        s1.putIn(3, 3, 2);
        s1.takeOut(1, 1, 1);
        assertEquals(2, s1.getHundreds());
        assertEquals(2, s1.getFifties());
        assertEquals(1, s1.getTwenties());
    }

    @Test
    public void testEqual() {
        ATM s1 = new ATM(2, 5, 4);
        ATM s2 = new ATM(6, 5, 4);
        ATM s3 = new ATM(2, 5, 4);

        assertFalse(s1.equals(s2));
        assertTrue(s1.equals(s3));
    }

    @Test
    public void testCompareTo() {
        ATM s1 = new ATM(2, 5, 4);
        ATM s2 = new ATM(6, 5, 4);
        ATM s3 = new ATM(2, 3, 4);
        ATM s4 = new ATM(2, 5, 4);

        assertTrue(s2.compareTo(s1) > 0);
        assertTrue(s3.compareTo(s1) < 0);
        assertTrue(s1.compareTo(s4) == 0);
    }

    @Test
    public void testLoadSave() {
        ATM s1 = new ATM(6, 5, 4);
        ATM s2 = new ATM(6, 5, 4);

        s1.save("file1");
        s1 = new ATM();  // resets to zero

        s1.load("file1");
        assertTrue(s1.equals(s2));

    }


    @Test
    public void testMutate() {
        ATM s1 = new ATM(6, 5, 4);
        ATM.suspend(true);
        s1.takeOut(120);
        assertEquals(6, s1.getHundreds());
        assertEquals(5, s1.getFifties());
        assertEquals(4, s1.getTwenties());
        ATM.suspend(false);
    }

    // IMPORTANT: only one test per exception!!!
    // testing negative number for nickels, takeOut
    @Test
            (expected = IllegalArgumentException.class)
    public void testTakeOutNegTwenties() {
        ATM s1 = new ATM(2, 2, 2);
        s1.takeOut(1, 1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegHunderies1() {
        new ATM(-300, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegHunderies2() {
        new ATM(300, -10, 0);
    }

    // testing negative number for quarters, putIn
    @Test(expected = IllegalArgumentException.class)
    public void testPutInNeghunderies() {
        ATM s = new ATM(2, 3, 4);
        s.putIn(-30, 2, 30);
    }

    public void testEquals() {
        ATM s1 = new ATM(2, 3, 4);
        ATM s2 = new ATM(2, 3, 4);
        assertEquals(s1, s2);
    }
}