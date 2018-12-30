package ThreadTest;

class StateTest extends Thread{
    boolean waiting= true;
    boolean ready= false;

    StateTest() {
    }

    public void run() {
        String thrdName = Thread.currentThread().getName();
        System.out.println(thrdName + " starting.");
        while(waiting)
            System.out.println("waiting:"+waiting);
        System.out.println("waiting...");
        startWait();
        try {
            Thread.sleep(1000);
        }
        catch(Exception exc) {
            System.out.println(thrdName + " interrupted.");
        }
        System.out.println(thrdName + " terminating.");
    }

    synchronized void startWait() {
        try {
            /*
            Causes the current thread to wait until another thread invokes the
                    * {@link java.lang.Object#notify()} method or the
                    * {@link java.lang.Object#notifyAll()} method for this object.
                    * In other words, this method behaves exactly as if it simply
                    * performs the call {@code wait(0)}.*/
            while(!ready) wait();
        }
        catch(InterruptedException exc) {
            System.out.println("wait() interrupted");
        }
    }

    synchronized void notice() {
        ready = true;
        /* /**
     * Wakes up a single thread that is waiting on this object's
     * monitor. If any threads are waiting on this object, one of them
     * is chosen to be awakened.*/
        notify();
    }
}
class StateTestMain {
    public static void main(String args[]) throws Exception{
        StateTest thrd = new StateTest();
        thrd.setName("MyThread #1");
        showThreadStatus(thrd);
        thrd.start();
        Thread.sleep(50);
        showThreadStatus(thrd);
        thrd.waiting = false;
        Thread.sleep(50);
        showThreadStatus(thrd);
        thrd.notice();
        Thread.sleep(50);
        showThreadStatus(thrd);
        while(thrd.isAlive())
            System.out.println("alive");
        showThreadStatus(thrd);
    }
    static void showThreadStatus(Thread thrd) {
        System.out.println(thrd.getName() + "Alive:=" + thrd.isAlive() + " State:=" + thrd.getState());
    }
}
