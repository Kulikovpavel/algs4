import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Etlau
 * Date: 11.02.13
 * Time: 21:49
 * To change this template use File | Settings | File Templates.
 */
public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;
    private class Node{
        Item item;
        Node next = null;
        Node prev = null;
    }
    public Deque(){                   // construct an empty deque
        first = null;
        last = null;
        size = 0;
    }
    public boolean isEmpty(){           // is the deque empty?
        return  size==0;
    }
    public int size(){                  // return the number of items on the deque
        return size;
    }
    public void addFirst(Item item){   // insert the item at the front
        checkItem(item);

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst!=null) oldfirst.prev = first;
        first.prev = null;
        size += 1;
        if (size == 1) last = first;
    }
    public void addLast(Item item){    // insert the item at the end
        checkItem(item);
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.prev = oldlast;
        last.next = null;
        if (oldlast!=null) oldlast.next = last;
        size += 1;
        if (size == 1) first = last;
    }
    public Item removeFirst(){         // delete and return the item at the front
        removeCheck();
        Item item = first.item;
        first = first.next;
        if(first!=null) first.prev = null;
        size -= 1;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    public Item removeLast(){         // delete and return the item at the end
        removeCheck();
        Item item = last.item;
        last = last.prev;
        if (last!=null) last.next = null;
        size -= 1;
        if (isEmpty()) first = null;   // to avoid loitering
        return item;
    }
    public Iterator<Item> iterator(){ // return an iterator over items in order from front to end
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public void remove(){ throw new java.lang.UnsupportedOperationException();}
        public boolean hasNext(){ return current != null;}
        public Item next(){
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private void checkItem(Item item){
        if (item==null) throw new java.lang.NullPointerException();
    }
    private void removeCheck(){
        if (this.isEmpty()) throw new java.util.NoSuchElementException();
    }



}


