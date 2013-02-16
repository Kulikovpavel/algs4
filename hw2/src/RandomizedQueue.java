import java.util.Iterator;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private Item[] a;

    public RandomizedQueue(){           // construct an empty randomized queue
        a = (Item[]) new Object[2];
    }
    public boolean isEmpty() { return N == 0; }
    public int size()        { return N;      }

    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public void enqueue(Item item) {    // add the item
        if (item == null) throw new java.lang.NullPointerException();
        if (N == a.length) resize(2*a.length);    // double size of array if necessary

        a[N++] = item;                            // add item

        Random randomGenerator = new Random();
        int n_rand = randomGenerator.nextInt(N);
        Item change = a[n_rand];
        a[n_rand] = item;
        a[N-1] = change;
    }
    public Item dequeue() {             // delete and return a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = a[N-1];
        a[N-1] = null;                              // to avoid loitering
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == a.length-2) resize(a.length-2);
        return item;
    }

    public Item sample(){               // return (but do not delete) a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
//        Random randomGenerator = new Random();
        int n_rand = StdRandom.uniform(N);
        Item item = a[n_rand];
        return item;

    }
    public Iterator<Item> iterator(){// return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    };

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current_n;
        private int[] loop;
        public RandomizedQueueIterator(){
            loop = new int[N];
            current_n = 0;
            for( int ind=0; ind<N;ind++){
                loop[ind] = ind;

            }
            StdRandom.shuffle(loop);
        }
        public void remove(){ throw new java.lang.UnsupportedOperationException();}
        public boolean hasNext(){ return current_n<N;}
        public Item next(){
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            current_n ++;
            return a[loop[current_n-1]];
        }

    }
}
