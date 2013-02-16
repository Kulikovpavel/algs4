import sun.reflect.generics.tree.ByteSignature;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 15.02.13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */

public class Point implements Comparable<Point> {

    // compare points by slope
    public static final Comparator<Point> SLOPE_ORDER = new BySlope();       // YOUR DEFINITION HERE

    private static class BySlope implements Comparator<Point>
    {
        public int compare(Point p1, Point p2){
            if (p1.slopeTo(p2)<p2.slopeTo(p1)) return -1;
            else if (p1.slopeTo(p2) == p2.slopeTo(p1)) return 0;
            else return 1;
        }
    }

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x){
            if(that.y>=this.y) return Double.POSITIVE_INFINITY;
            else return Double.NEGATIVE_INFINITY;
        }

        double result = (double)(that.y - this.y) / (that.x - this.x);
//        System.out.println(result);
        return result;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if ((this.y < that.y) || (this.y == that.y && this.x < that.x))  {return -1;}
        else if (this.y == that.y && this.x == that.x) {return 0;}
        else return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}