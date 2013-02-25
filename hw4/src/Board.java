import static java.lang.Math.abs;
import static java.lang.Math.tan;

public class Board {
    private final int N;
    private final int[][] tiles;
    private final int manhattanVar;

    public Board(int[][] blocks){  // construct a board from an N-by-N array of blocks
        N = blocks.length ;
//        tiles = blocks;
        tiles = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                tiles[i][j] = blocks[i][j];
        manhattanVar = manhattanVar();
    }

    public int dimension(){  // board dimension N
        return N;
    }

    private int compareTo(Board b1){
        if (manhattan()<b1.manhattan()) return -1;
        if (manhattan()>b1.manhattan()) return 1;
        return 0;

    }
    public int hamming(){   // number of blocks out of place
        int wrong = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) continue;
                if (tiles[i][j] != N*i+j+1) wrong++;
            }
        }
        return wrong;
    }
    public int manhattan(){
        return manhattanVar;
    }
    private int manhattanVar(){  // sum of Manhattan distances between blocks and goal
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) continue;
                if (tiles[i][j] != N*i+j+1) {
                    distance += manDist(tiles[i][j],i,j);
                };
            }
        }
        return distance;
    }

    private int manDist(int tile, int i, int j) { // Manhattan Distance for point
        int icorrect = (tile-1) / N;
        int jcorrect = tile - icorrect*N - 1;
        return abs(i-icorrect)+abs(j-jcorrect);
    }

    public boolean isGoal(){                // is this board the goal board?
        return manhattan()==0;
    }

    public Board twin(){// a board obtained by exchanging two adjacent blocks in the same row
        int[][] twinTiles=createClone();
//        Board twin=new Board(tiles);

        int row;
        if (tiles[0][0]==0 || tiles[0][1]==0) row = 1; // if "0" in first row - take second, else take first
        else row = 0;

        int swap = twinTiles[row][0];
        twinTiles[row][0] = twinTiles[row][1];
        twinTiles[row][1] = swap;
        return new Board(twinTiles);
    }

    public boolean equals(Object y){  // does this board equal y?
        if (y == null) return false;
        if (y.getClass() == this.getClass()){
            Board that = (Board) y;
            if (N!=that.N) return false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tiles[i][j] != that.tiles[i][j]) return false;
                }
            }
            return true;
        }
        else return toString().equals(y.toString());
//        return manhattan() == y.manhattan();
    }
    public Iterable<Board> neighbors(){  // all neighboring boards
        Queue<Board> q=new Queue();
        int[] r = getEmpty();
        int[][] copy = createClone();
        int iEmpty = r[0];
        int jEmpty = r[1];
        int iNew;
        int jNew;

        iNew = iEmpty + 1;
        jNew = jEmpty;
        if (iNew<N && iNew>=0 && jNew<N && jNew>=0){
            copy[iEmpty][jEmpty] = tiles[iNew][jNew];
            copy[iNew][jNew] = 0;
            q.enqueue(new Board(copy));
        }

        iNew = iEmpty - 1;
        jNew = jEmpty;
        copy = createClone();

        if (iNew<N && iNew>=0 && jNew<N && jNew>=0){
            copy[iEmpty][jEmpty] = tiles[iNew][jNew];
            copy[iNew][jNew] = 0;
            q.enqueue(new Board(copy));
        }
        iNew = iEmpty;
        jNew = jEmpty + 1;
        copy = createClone();
        if (iNew<N && iNew>=0 && jNew<N && jNew>=0){
            copy[iEmpty][jEmpty] = tiles[iNew][jNew];
            copy[iNew][jNew] = 0;
            q.enqueue(new Board(copy));
        }
        iNew = iEmpty;
        jNew = jEmpty - 1;
        copy = createClone();
        if (iNew<N && iNew>=0 && jNew<N && jNew>=0){
            copy[iEmpty][jEmpty] = tiles[iNew][jNew];
            copy[iNew][jNew] = 0;
            q.enqueue(new Board(copy));
        }
        return q;
    }
    private int[] getEmpty(){
        int[] r = new int[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0){
                    r[0] = i;
                    r[1] = j;
                    return r;
                }
            }
        }
        return null;
    }

    private int[][] createClone(){
        int[][] r = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                r[i][j] = tiles[i][j];
        return r;


    }
    public String toString() {  // string representation of the board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }


}