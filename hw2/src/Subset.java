import java.util.Iterator;

public class Subset {
    public static void main(String[] args){
        int firstArg = Integer.parseInt(args[0]);
        RandomizedQueue rand = new RandomizedQueue<String>();
        String s = StdIn.readLine();
        String[] sArray = s.split(" ");
        for (String e: sArray){
            rand.enqueue(e);
        }

        Iterator<String> i = rand.iterator();
        for (int ind=0;ind<firstArg && i.hasNext();ind++){
            System.out.println(i.next());
        }
    }
}
