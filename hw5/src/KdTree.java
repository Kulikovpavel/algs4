public class KdTree {
    private Node root;

    private int N;

    private static class Node {
        public Point2D p;      // the point
        public RectHV rect;    // the axis-aligned rectangle corresponding to this node
        public Node lb;        // the left/bottom subtree
        public Node rt;        // the right/top subtree
        public boolean xLevel;

        public Node(Point2D p, RectHV rect,Node lb,Node rt,boolean xLevel){
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
            this.xLevel = xLevel;
        }
    }
    public KdTree(){
        N = 0;

    }
    public void insert(Point2D p){

        if (contains(p)) return;
        if (N==0) put(p);
        else put(root,p,true,null,0);

    }                   // add the point p to the set (if it is not already in the set)
    private Node put(Point2D p){
        root = new Node(p,new RectHV(0,0,1,1), null, null,true);
        N = 1;
        return root;

    }
    private Node put(Node x,Point2D newPoint,boolean xLevel, Node prev,int cmpPrev) {
        if (x == null){
            N++;
            RectHV rect;
            if (!xLevel) {
                if (cmpPrev<0) rect = new RectHV(prev.rect.xmin(),prev.rect.ymin(),prev.p.x(),prev.rect.ymax());
                else rect = new RectHV(prev.p.x(),prev.rect.ymin(),prev.rect.xmax(),prev.rect.ymax());
            }
            else{
                if (cmpPrev<0) rect = new RectHV(prev.rect.xmin(),prev.rect.ymin(),prev.rect.xmax(),prev.p.y());
                else rect = new RectHV(prev.rect.xmin(),prev.p.y(),prev.rect.xmax(),prev.rect.ymax());
            }
            return new Node(newPoint, rect, null, null, xLevel);
        }

        int cmp;
        if (xLevel) cmp = Point2D.X_ORDER.compare(newPoint,x.p);
        else cmp = Point2D.Y_ORDER.compare( newPoint,x.p);
        if  (cmp < 0) x.lb  = put(x.lb,  newPoint, !xLevel, x, cmp);
        else x.rt = put(x.rt, newPoint, !xLevel, x, cmp);
        return x;
    }
    public boolean isEmpty(){
        return N == 0;
    }                        // is the set empty?
    public int size(){
        return N;
    }                               // number of points in the set
    private Iterable<Point2D> iterable(){
        Queue<Point2D> queue = new Queue<Point2D>();

        addQueue(root, queue);
        return queue;

    }
    private void addQueue(Node n, Queue queue){
        queue.enqueue(n.p);
        if (n.lb!=null){
            addQueue(n.lb,queue);
        }
        if (n.rt!=null){
            addQueue(n.rt,queue);
        }

    }

    public boolean contains(Point2D p){
        Node current = root;
        boolean xLevel = true;
        while(current != null){
            if (current.p.equals(p)) return true;

            int cmp;
            if (xLevel) cmp = Point2D.X_ORDER.compare(p, current.p);
            else cmp = Point2D.Y_ORDER.compare(p, current.p);

            if (cmp>=0) current = current.rt;
            else current = current.lb;
            xLevel = !xLevel;
        }
        return false;
    }              // does the set contain the point p?
    private Node get(Point2D p){
        Node current = root;
        boolean xLevel = true;
        while(current != null){
            if (current.p.equals(p)) return current;

            int cmp;
            if (xLevel) cmp = Point2D.X_ORDER.compare(p, current.p);
            else cmp = Point2D.Y_ORDER.compare(p, current.p);

            if (cmp>=0) current = current.rt;
            else current = current.lb;
            xLevel = !xLevel;
        }
        return null;
    }
    public void draw(){
        for(Point2D x: iterable()){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            x.draw();
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.001);
            Node node = get(x);
            node.rect.draw();
        }
    }                             // draw all of the points to standard draw
    private Iterable<Point2D> rangeBrute(RectHV rect){// all points in the set that are inside the rectangle

        Queue<Point2D> queue = new Queue<Point2D>();
        for (Point2D x: iterable()){
            if (x.x()>=rect.xmin() && x.x()<=rect.xmax() &&  x.y()>=rect.ymin() && x.y()<=rect.ymax()){
                queue.enqueue(x);
            }
        }
        return queue;
    }
    private void rangeRecursive(Node x, RectHV rect,Queue queue){
        if (x==null) return;
        if (rect.contains(x.p)) queue.enqueue(x.p);
        if (x.lb !=null && rect.intersects(x.lb.rect)) rangeRecursive(x.lb, rect,queue);
        if (x.rt !=null && rect.intersects(x.rt.rect)) rangeRecursive(x.rt, rect,queue);
        return;
    }
    public Iterable<Point2D> range(RectHV rect){// all points in the set that are inside the rectangle
        Queue<Point2D> queue = new Queue<Point2D>();
        rangeRecursive(root,rect,queue);
        return queue;
    }
    private Point2D nearestBrute(Point2D p){
        if (isEmpty()) return null;
        double min = 1000000000;
        Point2D minPoint = null;
        for (Point2D x: iterable()){
            double current =  p.distanceTo(x);

            if (current<min){
                min = current;
                minPoint = x;
            }

        }
        return minPoint;
    }
    private Node nearestNode(Point2D p, Node node, double dist) {
        if (node.rect.distanceSquaredTo(p) > dist)
            return node;
        Node min = node;
        Node mins;
        Node minm;
        Node main;
        Node second;

        if (node.lb!=null && (node.xLevel&&p.x()<node.p.x() || !node.xLevel && p.y()<node.p.y())) {
            main = node.lb;
            second = node.rt;
        }
        else{
            main = node.rt;
            second = node.lb;
        }

        if (main != null && main.rect.distanceSquaredTo(p) < dist) {
            minm = nearestNode(p, main, dist);
            if (minm.p.distanceSquaredTo(p)<dist){
                dist = minm.p.distanceSquaredTo(p);
                min = minm;
            }

        }
        if (second != null && second.rect.distanceSquaredTo(p) < dist){
            mins = nearestNode(p, second, dist);
            if (mins.p.distanceSquaredTo(p)<dist) min = mins;
        }
        return min;
    }

    public Point2D nearest(Point2D p){ // a nearest neighbor in the set to p; null if set is empty
        if (isEmpty()) return null;
        Point2D r = nearestNode(p, root, 1000000000).p;
        return r;

    }
}