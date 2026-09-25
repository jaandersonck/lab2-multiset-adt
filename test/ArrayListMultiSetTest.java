import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListMultiSetTest {
    @Test
    public void testCountIsCorrect() {
        ArrayListMultiSet a = new ArrayListMultiSet();
        a.add(1);
        a.add(3);
        a.add(1);
        a.add(2);
        assertEquals(2, a.count(1));
    }

    @Test
    public void testSizeIsCorrect() {
        ArrayListMultiSet a = new ArrayListMultiSet();
        a.add(1);
        a.add(3);
        a.add(1);
        a.add(2);
        assertEquals(4, a.size());
    }

    @Test
    public void testContainsIsTrue() {
        ArrayListMultiSet a = new ArrayListMultiSet();
        a.add(1);
        a.add(3);
        a.add(1);
        a.add(2);
        assertTrue(a.contains(1));
    }

    @Test
    public void testContainsIsFalse() {
        ArrayListMultiSet a = new ArrayListMultiSet();
        a.add(1);
        a.add(3);
        a.add(1);
        a.add(2);
        assertFalse(a.contains(45));
    }

    @Test
    public void testIsEmptyIsTrue() {
        ArrayListMultiSet a = new ArrayListMultiSet();
        a.add(1);
        a.remove(1);
        assertTrue(a.isEmpty());
    }

    @Test
    public void testIsEmptyIsFalse() {
        ArrayListMultiSet a = new ArrayListMultiSet();
        a.add(1);
        assertFalse(a.isEmpty());
    }
}