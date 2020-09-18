package atmPack;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test case class for ATM. 23 of the tests were provided by instructor.
 *
 *
 *
 */
public class ATMTest {

    @Test
    public void testConstructor() {
        ATM s1 = new ATM(6, 5, 4);

        assertEquals(s1.getHundreds(), 6);
        assertEquals(s1.getFifties(), 5);
        assertEquals(s1.getTwenties(), 4);

        ATM s2 = new ATM();
        assertEquals(s2.getHundreds(), 0);
        assertEquals(s2.getFifties(), 0);
        assertEquals(s2.getTwenties(), 0);

        ATM s3 = new ATM(s1);
        assertEquals(s3.getHundreds(), 6);
        assertEquals(s3.getFifties(), 5);
        assertEquals(s3.getTwenties(), 4);
    }

    //checks functionality of toString method
    @Test
    public void testToString() {
        ATM s1 = new ATM(2, 5, 4);

        s1.toString();
        assertEquals(s1.toString(), "2 hundred dollar bills, 5 fifty dollar bills, 4 twenty dollar bills");
    }

    @Test
    public void testTakeOut1() {
        ATM s1 = new ATM(3, 3, 2);
        s1.takeOut(1, 1, 1);
        assertEquals(s1.getHundreds(), 2);
        assertEquals(s1.getFifties(), 2);
        assertEquals(s1.getTwenties(), 1);
    }

    @Test
    public void testTakeOut2() {
        ATM s1 = new ATM(5, 3, 3);
        ATM s2 = s1.takeOut(120);

        assertEquals(s1.getHundreds(), 4);
        assertEquals(s1.getFifties(), 3);
        assertEquals(s1.getTwenties(), 2);

        assertEquals(s2.getHundreds(), 1);
        assertEquals(s2.getFifties(), 0);
        assertEquals(s2.getTwenties(), 1);
    }


    @Test
    public void testPutIn() {
        ATM s1 = new ATM();
        s1.putIn(2, 3, 4);
        assertEquals(s1.getHundreds(), 2);
        assertEquals(s1.getFifties(), 3);
        assertEquals(s1.getTwenties(), 4);
    }

    @Test
    public void testPutInTakeOut() {
        ATM s1 = new ATM();
        s1.putIn(3, 3, 2);
        s1.takeOut(1, 1, 1);
        assertEquals(s1.getHundreds(), 2);
        assertEquals(s1.getFifties(), 2);
        assertEquals(s1.getTwenties(), 1);
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

    // testing not able to make change
    @Test(expected = IllegalArgumentException.class)
    public void testTakeOutNull1() {
        ATM s1 = new ATM(3, 1, 2);
        ATM s2 = s1.takeOut(700);
        assertEquals(s2, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSaveLoadError () {
        ATM d = new ATM(1, 11, 2018);
        d.save("-!@#$%^&*()testit-.txt");
    }

    public ATM myTakeOut(int totalAmount, int hundreds, int fifties, int twenties) {
        if (totalAmount <= (hundreds * 100 + fifties * 50 + twenties * 20)) {
            for (int a = hundreds; a >= 0; a--) {
                for (int b = fifties; b >= 0; b--) {
                    for (int c = twenties; c >= 0; c--) {
                        if ((a * 100 + b * 50 + c * 20) == totalAmount) {
                            hundreds -= a;
                            fifties -= b;
                            twenties -= c;
                            return new ATM(a, b, c);
                        }
                    }
                }
            }
        }
        return null;
    }

    @Test (expected = IllegalArgumentException.class)
    public void testequals2() {
        ATM d1 = new ATM(2,21,2021);
        d1.equals("pizza");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testequals3() {
        ATM d1 = new ATM(2,29,2021);
        d1.equals(null);
    }

    @Test
    public void testTakeSuperTest() {
        int amount = 20;
        int numOfIll = 0;

        for (int h = 0; h < amount; h++) {
            System.out.println(h);
            for (int f = 0; f < amount; f++)
                for (int t = 0; t < amount; t++)
                    for (int m = 10; m < ((amount * 100) + (amount * 50) + (amount * 20) + 10); m += 10) {
                        try {
                            ATM a1 = new ATM(h, f, t);
                            ATM b1 = a1.takeOut(m);

                            ATM a2 = new ATM(h, f, t);
                            ATM b2 = myTakeOut(m, h, f, t);

                            //assertEquals (a1,a2);
                            assertEquals(b1, b2);
                        }
                        catch (IllegalArgumentException e) {
                            numOfIll++;
                        }
                    }
        }
        System.out.println(numOfIll);
        assertEquals(1589500, numOfIll);
    }

    @Test
    public void testTakeOut3() {
        ATM s1 = new ATM(0, 50, 3);
        ATM s2 = s1.takeOut(160);
        assertEquals(s1.getHundreds(), 0);
        assertEquals(s1.getFifties(), 48);
        assertEquals(s1.getTwenties(), 0);

        assertEquals(s2.getHundreds(), 0);
        assertEquals(s2.getFifties(), 2);
        assertEquals(s2.getTwenties(), 3);
    }

    @Test
    public void testTakeOut4() {
        ATM s1 = new ATM(5, 1, 4);
        ATM s2 = s1.takeOut(180);
        assertEquals(s1.getHundreds(), 4);
        assertEquals(s1.getFifties(), 1);
        assertEquals(s1.getTwenties(), 0);

        assertEquals(s2.getHundreds(), 1);
        assertEquals(s2.getFifties(), 0);
        assertEquals(s2.getTwenties(), 4);
    }

    @Test
    public void testTakeOut4a() {
        ATM s1 = new ATM(1, 1, 4);
        ATM s2 = s1.takeOut(130);
        assertEquals(s1.getHundreds(), 1);
        assertEquals(s1.getFifties(), 0);
        assertEquals(s1.getTwenties(), 0);

        assertEquals(s2.getHundreds(), 0);
        assertEquals(s2.getFifties(), 1);
        assertEquals(s2.getTwenties(), 4);
    }

    @Test
    public void testTakeOut4b() {
        ATM s1 = new ATM(5, 1, 4);
        ATM s2 = s1.takeOut(180);
        assertEquals(s1.getHundreds(), 4);
        assertEquals(s1.getFifties(), 1);
        assertEquals(s1.getTwenties(), 0);

        assertEquals(s2.getHundreds(), 1);
        assertEquals(s2.getFifties(), 0);
        assertEquals(s2.getTwenties(), 4);
    }

    @Test
    public void testTakeOut5() {
        ATM s1 = new ATM(0, 1, 4);
        ATM s2 = s1.takeOut(80);
        assertEquals(s1.getHundreds(), 0);
        assertEquals(s1.getFifties(), 1);
        assertEquals(s1.getTwenties(), 0);

        assertEquals(s2.getHundreds(), 0);
        assertEquals(s2.getFifties(), 0);
        assertEquals(s2.getTwenties(), 4);
    }

    @Test
    public void testTakeOut6() {
        ATM s1 = new ATM(0, 3, 3);
        ATM s2 = s1.takeOut(160);
        assertEquals(s1.getHundreds(), 0);
        assertEquals(s1.getFifties(), 1);
        assertEquals(s1.getTwenties(), 0);

        assertEquals(s2.getHundreds(), 0);
        assertEquals(s2.getFifties(), 2);
        assertEquals(s2.getTwenties(), 3);
    }

    @Test
    public void testMutate() {
        ATM s1 = new ATM(6, 5, 4);
        ATM.suspend(true);
        s1.takeOut(120);
        assertEquals(s1.getHundreds(), 6);
        assertEquals(s1.getFifties(), 5);
        assertEquals(s1.getTwenties(), 4);
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
    public void testConstructorNegHunderies() {
        new ATM(-300, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutInNeghunderies() {
        ATM s = new ATM(2, 3, 4);
        s.putIn(-30, 2, 30);
    }

    public void TestItALL() {
        for (int hundreds = 0; hundreds < 10; hundreds++)
            for (int fifties = 0; fifties < 10; fifties++)
                for (int twenties = 0; twenties < 10; twenties++)
                    for (int p = 0; p < 10; p++)
                        for (int amt = 20; amt < 500; amt += 10) {
                            ATM j1 = new ATM(hundreds, fifties, twenties);
                            ATM j2 = new ATM(hundreds, fifties, twenties);
                            ATM j3 = new ATM(hundreds, fifties, twenties);
                            ATM j4 = new ATM(hundreds, fifties, twenties);

                            try {
                                ATM j11 = j1.takeOut(amt);
                            } catch (Exception e) {
                            }
                        }
    }
}
