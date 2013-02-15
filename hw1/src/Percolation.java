public class Percolation {
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF full_grid;
    private int size;
    private int start;
    private int end;
    private boolean[] system;

    public Percolation(int N){             // create N-by-N grid, with all sites blocked
        grid = new WeightedQuickUnionUF(N * N + 2);
        full_grid = new WeightedQuickUnionUF(N * N + 1);
        size = N;
        system = new boolean[N * N];
        start = N*N;
        end = start + 1;
        for (int i = 0; i<N; i++){         // connect all top slots with water
            full_grid.union(i,start);
        }
        for (int i = 0; i<N; i++){         // connect all botton slots with end
            grid.union(N * N - 1 - i, end);
        }

    }
    private void check(int i, int j){
        if (i<=0 || i>size || j<=0 || j>size) throw new java.lang.IndexOutOfBoundsException();
    }
    public void open(int i, int j){        // open site (row i, column j) if it is not already
        check(i,j);
        if (isOpen(i,j)) return;
        if (convert(i,j)<size){
            grid.union(start,convert(i,j));
        }
        system[convert(i,j)] = true;
        int new_i = i-1;
        int new_j = j;
        if (new_i>=1 && new_i<size+1 && new_j>=1 && new_j<size+1 && this.isOpen(new_i,new_j)){
            grid.union(convert(i,j),convert(new_i,new_j));
            full_grid.union(convert(i,j),convert(new_i,new_j));
        }
        new_i = i+1;
        new_j = j;
        if (new_i>=1 && new_i<size+1 && new_j>=1 && new_j<size+1 && this.isOpen(new_i,new_j)){
            grid.union(convert(i,j),convert(new_i,new_j));
            full_grid.union(convert(i,j),convert(new_i,new_j));
        }
        new_i = i;
        new_j = j-1;
        if (new_i>=1 && new_i<size+1 && new_j>=1 && new_j<size+1 && this.isOpen(new_i,new_j)){
            grid.union(convert(i,j),convert(new_i,new_j));
            full_grid.union(convert(i,j),convert(new_i,new_j));
        }
        new_i = i;
        new_j = j+1;
        if (new_i>=1 && new_i<size+1 && new_j>=1 && new_j<size+1 && this.isOpen(new_i,new_j)){
            grid.union(convert(i,j),convert(new_i,new_j));
            full_grid.union(convert(i,j),convert(new_i,new_j));
        }
    }

    public boolean isOpen(int i, int j){   // is site (row i, column j) open?
        check(i,j);
        return system[convert(i,j)];
    }

    public boolean isFull(int i, int j){   // is site (row i, column j) full?
        check(i,j);
        return ( isOpen(i,j) && full_grid.connected(convert(i,j),start) );
    }

    public boolean percolates(){            // does the system percolate?
        return grid.connected(start,end);
    }

    private int convert(int i, int j){     // convert indexes in array iterator
        return j + (i - 1) * size -1;
    }
}