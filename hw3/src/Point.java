import java.util.Comparator;

import static java.lang.Math.abs;

/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 15.02.13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();       // YOUR DEFINITION HERE

    private class BySlope implements Comparator<Point>
    {
        public int compare(Point p1, Point p2){
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);

            if (slope1<slope2) return -1;
            else if (slope1 == slope2) return 0;
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
            if(that.y==this.y) return Double.NEGATIVE_INFINITY;
            else return Double.POSITIVE_INFINITY;
        }
        if (that.y == this.y) return 0.0;
        double result = (double)(this.y - that.y) / (this.x - that.x);
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