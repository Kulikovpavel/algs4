import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 22.02.13
 * Time: 23:59
 * To change this template use File | Settings | File Templates.
 */
public class Solver{

    private int minMoves;
    private final Board init;
    private SearchNode lastnode;

    public Solver(Board initial){            // find a solution to the initial board (using the A* algorithm)
        init = initial;
        minMoves = -1;

    }
    public boolean isSolvable(){ // is the initial board solvable?
        if (minMoves != -1) return true;
        Board twin = init.twin();
        Solver twinSolver = new Solver(twin);
        Iterator<SearchNode> i = this.iterator();

        Iterator<SearchNode> twinI = twinSolver.iterator();
        Board res = null;
        SearchNode node = null;
        while (i.hasNext() && twinI.hasNext()){
            node = i.next();
            res = node.current;
            twinI.next();
        }
        if (res.isGoal()) {
            minMoves = node.steps;
            lastnode = node;
        }
        return res.isGoal();
    }
    public int moves(){                      // min number of moves to solve initial board; -1 if no solution
        if (!isSolvable()) return -1;
        return minMoves;
    }
    public Iterable<Board> solution(){// sequence of boards in a shortest solution; null if no solution
        if (!isSolvable()) return null;
        Stack<Board> q = new Stack<Board>();
        SearchNode node = lastnode;
        while (node.prev != null){
            q.push(node.current);
            node = node.prev;
        }
        q.push(node.current);
        return q;

    }

    private Iterator<SearchNode> iterator() {
        return new SolverIterator();
    }

    private class SearchNode implements Comparable<SearchNode>{
        int steps;
        SearchNode prev;
        public Board current;
        int cost;
        public SearchNode(Board board, SearchNode previous){
            current = board;
            prev = previous;
            if (previous == null) steps = 0;
            else steps = previous.steps + 1;
            cost = board.manhattan()+ steps;
        }

        public int compareTo(SearchNode b) {

            if (cost > b.cost) return 1;
            else if (cost == b.cost) return 0;
            else return -1;
        }
    }

    private class SolverIterator implements Iterator<SearchNode> {
        boolean goalReached;
        MinPQ<SearchNode> pq;

        public SolverIterator(){

            pq = new MinPQ<SearchNode>();
            pq.insert(new SearchNode(init, null));
            goalReached = false;
        }
        public boolean hasNext() {
            if (goalReached) return false; // stop at goal
            return !pq.isEmpty();
        }

        public SearchNode next() {
            SearchNode prev = pq.delMin();
            Board res = prev.current;
            if (res.isGoal()){
                goalReached = true;
                return prev;
            }
            for (Board x:res.neighbors()){
//                if (prev.steps != 0 && x.equals(prev.prev.current)) System.out.println("bingo");
                if (prev.steps == 0 || !x.equals(prev.prev.current)){

                    pq.insert(new SearchNode(x,prev));
                };
            };
//            if (res.isGoal()) goalReached = true;
//            System.out.println(pq.size());
            return pq.min();

        }

        public void remove() {
            throw new UnsupportedOperationException();//To change body of implemented methods use File | Settings | File Templates.
        }
    }
    public static void main(String[] args)  { // solve a slider puzzle (given below)
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        Stopwatch s= new Stopwatch();
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        System.out.println(s.elapsedTime());
    }

}