package multithread.fundation;

public class ThreadDemo {

    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread-1");
        MyThread t2 = new MyThread("Thread-2");
        t1.start();
        t2.start();
    }

    static class MyThread extends Thread {
        private Thread t;
        private final String threadName;

        MyThread(String name) {
            threadName = name;
            System.out.println("Creating " + threadName);
        }

        @Override
        public void start() {
            System.out.println("Starting " + threadName);
            if (t == null) {
                t = new Thread(this, threadName);
            }
            t.start();
        }

        @Override
        public void run() {
            System.out.println("Running " + threadName);
            try {
                for (int i = 4; i > 0; i--) {
                    System.out.println(threadName + " --> step " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted");
            }
            System.out.println(threadName + " exiting");
        }
    }
}
