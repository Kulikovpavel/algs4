public class PointSET {
    private SET<Point2D> set;
    public PointSET(){
        set = new SET<Point2D>();

    }                               // construct an empty set of points
    public boolean isEmpty(){
        return set.isEmpty();
    }                        // is the set empty?
    public int size(){
        return set.size();
    }                               // number of points in the set
    public void insert(Point2D p){
     //   if (!contains(p)){
        set.add(p);
     //   }

    }                   // add the point p to the set (if it is not already in the set)
    public boolean contains(Point2D p){
        return set.contains(p);
    }              // does the set contain the point p?
    public void draw(){
        for(Point2D x: set){
            x.draw();
        }
    }                             // draw all of the points to standard draw
    public Iterable<Point2D> range(RectHV rect){
        Queue<Point2D> queue = new Queue<Point2D>();
        for (Point2D x: set){
            if (x.x()>=rect.xmin() && x.x()<=rect.xmax() &&  x.y()>=rect.ymin() && x.y()<=rect.ymax()){
                queue.enqueue(x);
            }
        }
        return queue;

    }     // all points in the set that are inside the rectangle
    public Point2D nearest(Point2D p){
        if (set.isEmpty()) return null;
        double min = 1000000000;
        Point2D minPoint = null;
        for (Point2D x: set){
            //if (x.equals(p)) continue;
            double current =  p.distanceTo(x);
            if (current<min){
                min = current;
                minPoint = x;
            }

        }
        return minPoint;
    }               // a nearest neighbor in the set to p; null if set is empty
}