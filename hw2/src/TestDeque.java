/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 12.02.13
 * Time: 0:55
 * To change this template use File | Settings | File Templates.
 */
//package hw2;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

/**
 * Tests for {@link Deque}.
 *
 * @author user@example.com (John Doe)
 */
@RunWith(JUnit4.class)
public class TestDeque {
//    @Before





    Deque deque;

    @Before
    public void setUp (){
        deque = new Deque<Integer>();
        deque.addFirst(22);
        deque.addFirst(77);
        deque.addFirst(99);
        deque.addLast(101);
        deque.addLast(102);

    }

    @Test
    public void basic() {


        assertEquals(99, deque.removeFirst());
        assertEquals(77, deque.removeFirst());
        assertEquals(102, deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(false, deque.isEmpty());
        assertEquals(22, deque.removeFirst());
        assertEquals(101, deque.removeLast());
        assertEquals(true, deque.isEmpty());
        deque.addLast(102);
        assertEquals(102, deque.removeLast());
        assertEquals(0, deque.size());
    }
    @Test
    public void addLast(){
        Deque deque1 = new Deque<Integer>();

        deque1.addLast(101);
        deque1.addLast(102);
        assertEquals(102,deque1.removeLast());
        assertEquals(1, deque1.size());

    }
    @Test
    public void removeLast(){
        Deque deque1 = new Deque<Integer>();

        deque1.addFirst(101);
        deque1.addFirst(102);
        assertEquals(101,deque1.removeLast());
        assertEquals(1, deque1.size());
        assertEquals(102,deque1.removeLast());
        assertEquals(0, deque1.size());
    }
    @Test
    public void iterator() {

//        for (Integer x : deque){
//            assertEquals(x,22);
//            break;
//        }
        deque.addFirst(101);
        deque.addFirst(102);
        deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();
        deque.removeLast();
        Iterator<Integer> i= deque.iterator();

        while (i.hasNext())
        {

            Integer value = i.next();
            System.out.println(value);
//            assertEquals(99, value.intValue());
//            break;

        }

    }

//    @Test
//    @Ignore
//    public void thisIsIgnored() {
//    }
}