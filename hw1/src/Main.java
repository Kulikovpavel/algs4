public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Percolation perc = new Percolation(1);
        System.out.println(perc.percolates());
//
//        perc.open(1,1);
//        System.out.println(perc.percolates());
//        perc.open(3,3);
//        perc.open(2,2);
//        perc.open(3,1);
//        System.out.println(perc.isFull(3,1));
//        System.out.println(perc.percolates());
//        System.out.println(perc.convert(2,1));
//        System.out.println(perc.percolates());
        System.out.println(new PercolationStats(10,10).mean());
    }


}
