import java.util.*;import java.util.concurrent.*;

public class POSAPingPong
{
    static boolean done = false;  // True when good strike occur, 10% probability

    private static class SingleThread implements Runnable{
        private Semaphore main;  // this thread Semaphore
        private Semaphore second;  // another thread Semaphore
        private String word;   // "Ping!" or "Pong!" word
        private Random rand;   // random generator for Strike event

        public SingleThread(Semaphore main, Semaphore second, String word){
            this.main = main;
            this.second = second;
            this.word = word;
            this.rand = new Random();
        }

        public void run(){
            while (!done){
                try
                {
                    main.acquire();   //  wait when this semaphore will be available
                    if (done) break;  // if last strike was good, break this thread, that was waiting for release
                    if (rand.nextInt(100)>90) {   // if Strike ( 10% probability ) -> done
                        done = true;
                        System.out.println(word);  // print "Ping!" or "Pong!" word
                        System.out.println("Done!");
                    }
                    else{
                        System.out.println(word);
                    }
                    second.release();  // release second semaphore
                }
                catch (InterruptedException intEx)
                {
                    intEx.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Ready… Set… Go!");

        Semaphore pingSemaphore = new Semaphore(1);
        Semaphore pongSemaphore = new Semaphore(0);

        SingleThread ping = new SingleThread(pingSemaphore,pongSemaphore,"Ping!");   // thread for "ping"
        SingleThread pong = new SingleThread(pongSemaphore,pingSemaphore,"Pong!");   // thread for "pong"
        new Thread(ping).start();
        new Thread(pong).start();
    }
}