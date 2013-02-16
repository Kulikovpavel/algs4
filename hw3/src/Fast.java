import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 16.02.13
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class Fast {
    public static void main(String[] args) {
        try {
            System.setIn(new FileInputStream(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        int [] points = StdIn.readInts();

        int N = points[0];
        Point[] a =  new Point[N];
        for (int i=0; i<N;i++){
            Point p = new Point(points[i*2+1], points[i*2+2]);
            a[i] = p;
        }

        Stopwatch s= new Stopwatch();
        Arrays.sort(a,Point.SLOPE_ORDER);
//        for (Point x:a){
//            System.out.print(x);
//        }
//        System.out.println();
        for (int i=0;i<N-3;i++){

            System.out.println(a[i].slopeTo(a[i+1]));
            if (a[i].slopeTo(a[i+1]) != a[i].slopeTo(a[i+2])) continue;
            int count=3;
            while (count<N-i){
                if (a[i].slopeTo(a[i+1]) == a[i].slopeTo(a[i+count])) count++;
                else break;
            }
//            System.out.println(count);
            if (count>3){

                Point[] b = Arrays.copyOfRange(a,i,i+count);
                Arrays.sort(b);
                for (Point x :b){
                    StdOut.print(x +" -> ");
                }
                StdOut.println();
            }
            i += count-1;


        }
        System.out.println(s.elapsedTime());
    }
}
