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

        In in = new In(args[0]);      // input file
        int N = in.readInt();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        Point[] a =  new Point[N];
        int i = 0;
        while (!in.isEmpty()) {

            Point p = new Point(in.readInt(),in.readInt());
            a[i] = p;
            i++;
            p.draw();
        }

        Stopwatch s= new Stopwatch();

        Point results[][]= new Point[100000][3];
        Point[] reserve = Arrays.copyOf(a,N);
        Arrays.sort(reserve);
        int results_count = 0;
        for (i=0;i<N;i++){
            Point comparePoint = reserve[i];
            Arrays.sort(a,comparePoint.SLOPE_ORDER);
            for ( int j=1; (j<N) ;j++){
                double slope =comparePoint.slopeTo(a[j]);
                int count = 2;

                while(j+count<N){
                    if (slope == comparePoint.slopeTo(a[j+count])){
                        count++;
                    }
                    else {
                        break;
                    }
                }
                if (count>2){
                    Point[] b = Arrays.copyOfRange(a,j,j+count);

                    b = Arrays.copyOf(b,b.length+1);
                    b[b.length-1] = b[0];
                    b[0] = comparePoint;

                    Arrays.sort(b);

                    Point[] current_result = new Point[2];
                    current_result[0] = b[0];
                    current_result[1] = b[b.length-1];
                    boolean allok = true;
                    for (int ind=0;ind<results_count;ind++){
                        if (results[ind]==null ||(results[ind][0] == current_result[0] && results[ind][1] == current_result[1])){
                            allok = false;
                            break;
                        }
                    };
                    if (allok){
                        results[results_count] = current_result;
                        results_count++;

                        int ind = 0;
                        for (Point x :b){
                            StdOut.print(x);
                            if (ind<b.length-1) StdOut.print(" -> ");
                            ind++;
                        }
                        StdOut.println();
                        b[0].drawTo(b[b.length-1]);

                    }
                    if (N>1000 && results_count>N/2) {
                        StdDraw.show(1);
                        return;
                    }
                }
                else count = 1;
                j += count-1;
            }
        }
        System.out.println(s.elapsedTime());
        StdDraw.show(1);
    }
}
