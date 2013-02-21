import org.junit.Ignore;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 16.02.13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class PointTest {
    Point p0 = new Point(0,0);
    Point p01 = new Point(0,1);
    Point p0_1 = new Point(0,-1);
    Point p10 = new Point(1,0);
    Point p_10 = new Point(-1,0);
    Point p1 = new Point(1,1);
    Point p2 = new Point(2,2);
    Point p3 = new Point(3,3);
    Point p4 = new Point(4,4);
    Point p5 = new Point(5,5);
    Point p6 = new Point(6,6);

    @org.junit.Before
    public void setUp() throws Exception {



    }

    @org.junit.Test
    public void testSlopeTo() throws Exception {
        assertEquals(0,p_10.slopeTo(p0), 0.00000001);
        assertEquals(0,p0.slopeTo(p_10), 0.00000001);
        assertEquals(1,p0.slopeTo(p1), 0.00000001);
//        assertEquals(1,p0.slopeTo(p0), 0.00000001);
        assertEquals(Double.POSITIVE_INFINITY,p0.slopeTo(p0_1), 0.00000001);
        assertEquals(Double.POSITIVE_INFINITY,p0.slopeTo(p01), 0.00000001);

    }

    @org.junit.Test
    public void testCompareTo() throws Exception {
        assertEquals(-1,p0.compareTo(p6));
        assertEquals(1,p0.compareTo(p0_1));
        assertEquals(1,p1.compareTo(p0));
        assertEquals(1,p10.compareTo(p_10));
        assertEquals(1,p4.compareTo(p10));
        assertEquals(0,p6.compareTo(p6));


    }

    @org.junit.Test
    public void testForFast() throws Exception {
        Point p1 = new Point(21876, 29551);
        Point p2 = new Point (21559, 29604);

        assertEquals(true,p1.compareTo(p2)<0);
        assertEquals(true,p2.compareTo(p1)>0);
        assertEquals(-0.167192429022082,p1.slopeTo(p2),0.00000001 );



    }
}
