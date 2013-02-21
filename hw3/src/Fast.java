import java.util.Arrays;
import java.util.Comparator;
/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 16.02.13
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class Fast {
    /*
 * Copyright (c) 1998 - 2005 Versant Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Versant Corporation - initial API and implementation
 */



    /**
     * Quicksort implementation for sorting arrays. Unlike the merge sort in
     * java.utils.Collections this one does not create any objects. There are
     * two implementations, one for arrays of Comparable's and another that
     * uses a comparator.
     */




        /**
         * Sort the first size entries in a.
         */
        private static void quicksort(Object[] a, int size) {
            quicksort(a, 0, size - 1);
        }

        /**
         * Sort the entries in a between left and right inclusive.
         */
        private static void quicksort(Object[] a, int left, int right) {
            int size = right - left + 1;
            switch (size) {
                case 0:
                case 1:
                    break;
                case 2:
                    if (compare(a[left], a[right]) > 0) swap(a, left, right);
                    break;
                case 3:
                    if (compare(a[left], a[right - 1]) > 0) swap(a, left, right - 1);
                    if (compare(a[left], a[right]) > 0) swap(a, left, right);
                    if (compare(a[left + 1], a[right]) > 0) swap(a, left + 1, right);
                    break;
                default:
                    int median = median(a, left, right);
                    int partition = partition(a, left, right, median);
                    quicksort(a, left, partition - 1);
                    quicksort(a, partition + 1, right);
            }
        }

        private static int compare(Object a, Object b) {
            if (a == null) {
                return b == null ? 0 : -1;
            } else if (b == null) {
                return +1;
            } else {
                return ((Comparable)a).compareTo(b);
            }
        }

        private static void swap(Object[] a, int left, int right) {
            Object t = a[left];
            a[left] = a[right];
            a[right] = t;
        }

        private static int median(Object[] a, int left, int right) {
            int center = (left + right) / 2;
            if (compare(a[left], a[center]) > 0) swap(a, left, center);
            if (compare(a[left], a[right]) > 0) swap(a, left, right);
            if (compare(a[center], a[right]) > 0) swap(a, center, right);
            swap(a, center, right - 1);
            return right - 1;
        }

        private static int partition(Object[] a, int left, int right, int pivotIndex) {
            int leftIndex = left;
            int rightIndex = right - 1;
            while (true) {
                while (compare(a[++leftIndex], a[pivotIndex]) < 0);
                while (compare(a[--rightIndex], a[pivotIndex]) > 0);
                if (leftIndex >= rightIndex) {
                    break; // pointers cross so partition done
                } else {
                    swap(a, leftIndex, rightIndex);
                }
            }
            swap(a, leftIndex, right - 1);         // restore pivot
            return leftIndex;                 // return pivot location
        }

        /**
         * Sort the first size entries in a.
         */
        private static void quicksort(Object[] a, int size, Comparator c) {
            quicksort(a, 0, size - 1, c);
        }

        /**
         * Sort the entries in a between left and right inclusive.
         */
        private static void quicksort(Object[] a, int left, int right, Comparator c) {
            int size = right - left + 1;
            switch (size) {
                case 0:
                case 1:
                    break;
                case 2:
                    if (c.compare(a[left], a[right]) > 0) swap(a, left, right);
                    break;
                case 3:
                    if (c.compare(a[left], a[right - 1]) > 0) swap(a, left, right - 1);
                    if (c.compare(a[left], a[right]) > 0) swap(a, left, right);
                    if (c.compare(a[left + 1], a[right]) > 0) swap(a, left + 1, right);
                    break;
                default:
                    int median = median(a, left, right, c);
                    int partition = partition(a, left, right, median, c);
                    quicksort(a, left, partition - 1, c);
                    quicksort(a, partition + 1, right, c);
            }
        }

        private static int median(Object[] a, int left, int right, Comparator c) {
            int center = (left + right) / 2;
            if (c.compare(a[left], a[center]) > 0) swap(a, left, center);
            if (c.compare(a[left], a[right]) > 0) swap(a, left, right);
            if (c.compare(a[center], a[right]) > 0) swap(a, center, right);
            swap(a, center, right - 1);
            return right - 1;
        }

        private static int partition(Object[] a, int left, int right,
                                     int pivotIndex, Comparator c) {
            int leftIndex = left;
            int rightIndex = right - 1;
            while (true) {
                while (c.compare(a[++leftIndex], a[pivotIndex]) < 0);
                while (c.compare(a[--rightIndex], a[pivotIndex]) > 0);
                if (leftIndex >= rightIndex) {
                    break; // pointers cross so partition done
                } else {
                    swap(a, leftIndex, rightIndex);
                }
            }
            swap(a, leftIndex, right - 1);         // restore pivot
            return leftIndex;                 // return pivot location
        }


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

        //StdDraw.show(1);
        Stopwatch s= new Stopwatch();

        Point results[][]= new Point[100000][3];
        Point[] reserve = Arrays.copyOf(a,N);
        Arrays.sort(reserve);
        int results_count = 0;
//        Arrays.sort(a);
//        double [] slopes = new double[N];
        for (i=0;i<N;i++){

            Point comparePoint = reserve[i];
//            Arrays.fill(a,i,i,new Point(22222,22222));
//            a = Arrays.copyOf(reserve,N);
            Arrays.sort(a,comparePoint.SLOPE_ORDER);

//            quicksort(a,N,comparePoint.SLOPE_ORDER);
//            System.out.println(i);
//            System.out.println(a[i].slopeTo(a[i+1]));
//            if (a[i].slopeTo(a[i+1]) == a[i].slopeTo(a[i+2])) System.out.println("bingo");

            int count = 1;
//            System.out.println();
//            for ( int j=1; (j<N) ;j++){
//                slopes[j] = comparePoint.slopeTo(a[j]);
//            }
            for ( int j=1; (j<N) ;j++){
                double slope =comparePoint.slopeTo(a[j]);
                count = 2;

                while(j+count<N){
                    if (slope == comparePoint.slopeTo(a[j+count])){
                        count++;

//                        System.out.println(comparePoint.slopeTo(a[j]));
                    }

                    else {
                        //count = 0;
                        break;
                    }
                }


                if (count>2){
//                    System.out.println(comparePoint.slopeTo(a[j+count-1]));
//                    System.out.println(count + " "+i + " " + j);

//                    System.out.println(comparePoint.toString());

//                    if (comparePoint.slopeTo(new Point(21876, 29551)) == Double.NEGATIVE_INFINITY){
//                        int count1 = j;
////                        System.out.println(comparePoint);
//                        while(count1<j+count){
//
//                            System.out.println(comparePoint.slopeTo(a[count1]) + " "+ count1);
//                            System.out.println(a[count1]);
//                            count1++;
//                        }
//                        System.out.println();
//
//                    }
//

                    Point[] b = Arrays.copyOfRange(a,j,j+count);
//                    System.out.println(Arrays.binarySearch(b,comparePoint));
//                    //if (Arrays.binarySearch(b,comparePoint) < 0){
//                    for (Point x :b){
//                        StdOut.print(x);
//                        StdOut.print(" -> ");
//
//                    }
//                    StdOut.println();

                    b = Arrays.copyOf(b,b.length+1);
                    b[b.length-1] = b[0];
                    b[0] = comparePoint;
                   // }
                //    else continue;

                    Arrays.sort(b);

                    Point[] current_result = new Point[2];
                    current_result[0] = b[0];
                    current_result[1] = b[b.length-1];
//                    current_result[2] = new Point(b.length,0);
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
//                    else break;
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
