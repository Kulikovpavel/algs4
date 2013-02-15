import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 12.02.13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public class RandomizedQueueTest {
    RandomizedQueue rand;
    Integer[] testArray = {22,77,99,101,102};
    @Before
    public void setUp() throws Exception {
        rand = new RandomizedQueue<Integer>();


        for( int e:testArray){
            rand.enqueue(e);
        }
    }
    @Test
    public void basic(){
        assertEquals(false, rand.isEmpty());
        assertEquals(5, rand.size());
        assertEquals(true,Arrays.binarySearch(testArray, rand.sample())>-1);
        rand.dequeue();
//        System.out.println(rand.sample());
        assertEquals(4, rand.size());

    }

    @Test
    public void testIterator() throws Exception {
        Iterator<Integer> i= rand.iterator();
        Integer value = 0;
        while (i.hasNext())
        {
            value = i.next();
//            System.out.println(value);
            assertEquals(true,Arrays.binarySearch(testArray, value.intValue())>-1);
            break;

        }
        i = rand.iterator();
        assertNotEquals(value, i.next());  // 1/25 chance that failed
    }
}
