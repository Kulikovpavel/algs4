import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 16.02.13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
public class Brute {
    public static void main(String[] args){
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
        for (int i1=0;i1<N;i1++){
            for (int i2=i1+1;i2<N;i2++){
                for (int i3=i2+1;i3<N;i3++){
                    for (int i4=i3+1;i4<N;i4++){
                       // if (i1 == i2 || i1 == i3 || i1 == i4 || i2==i3||i2==i4||i3==i4) continue;

                        if (a[i1].slopeTo(a[i2]) == a[i1].slopeTo(a[i3]) &&
                            a[i1].slopeTo(a[i2]) == a[i1].slopeTo(a[i4]) ){

                            Point[] b = new Point[4];
                            b[0] = a[i1];
                            b[1] = a[i2];
                            b[2] = a[i3];
                            b[3] = a[i4];


                            Arrays.sort(b);
//                            b[0].drawTo(b[3]);
                            StdOut.println(b[0]+ " -> " + b[1]+ " -> "+ b[2]+ " -> " +b[3]);



                        }

                    }
                }
            }

        }
        System.out.println(s.elapsedTime());
    }
}
