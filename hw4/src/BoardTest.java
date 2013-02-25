import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 24.02.13
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
public class BoardTest {
    @org.junit.Test
    public void testManhattan() throws Exception {
        Board b = new Board(new int[][]{new int[]{0,1,3},new int[]{4,2,5},new int[]{7,8,6}});
        System.out.println(b);
        assertEquals(4, b.manhattan());

//        assertEquals(0,b.manDist(2,1,1));

    }
}
