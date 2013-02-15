import java.util.Random;

import static java.lang.Math.sqrt;

public class PercolationStats {
    private double[] results;
    private int size;
    public PercolationStats(int N, int T){// perform T independent computational experiments on an N-by-N grid
        if (N<=0 || T<=0){
            throw new java.lang.IllegalArgumentException();
        }
        results = new double[T];
        size = N;
        for (int i = 0;i<T;i++){
            results[i] = step(new Percolation(N));
            //    System.out.println(results[i]);
        }
    }
    private double step(Percolation perc){
        Random randomGenerator = new Random();
        int i_rand = randomGenerator.nextInt(size)+1;
        int j_rand = randomGenerator.nextInt(size)+1;
        double count = 0;
        while (!perc.percolates()){
            while(perc.isOpen(i_rand, j_rand)){
                i_rand = randomGenerator.nextInt(size)+1;
                j_rand = randomGenerator.nextInt(size)+1;
            }


            count += 1;
            perc.open(i_rand,j_rand);
        }
        return count/(size * size);
    }

    public double mean(){       // sample mean of percolation threshold
        double sum = 0;
        for(double e:results){
            sum += e;
        }
        return sum / results.length;
    }
    public double stddev(){     // sample standard deviation of percolation threshold
        double dev = 0;
        double mean = mean();
        for(double e:results){
            dev += (e - mean)* ( e-mean);
        }
        return sqrt(dev / (results.length-1));
    }
    public double confidenceLo(){             // returns lower bound of the 95% confidence interval
        double mean = mean();
        double sigma = stddev();
        return mean - 1.96*sigma/ sqrt(results.length);
    }
    public double confidenceHi(){            // returns upper bound of the 95% confidence interval
        double mean = mean();
        double sigma = stddev();
        return mean + 1.96*sigma/ sqrt(results.length);
    }
    public static void main(String[] args){  // test client, described below
        int N = new Integer(args[0]);
        int T = new Integer(args[1]);
        PercolationStats perc_stat = new PercolationStats(N,T);
        System.out.println(perc_stat.mean());
        System.out.println(perc_stat.stddev());
        System.out.println(Double.toString(perc_stat.confidenceLo())+", "+Double.toString(perc_stat.confidenceHi()) );




    }
}
