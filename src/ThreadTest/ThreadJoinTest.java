package ThreadTest;
/*
JDK中对join方法解释为：“等待该线程终止”，换句话说就是：”当前线程等待子线程的终止“。也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了当前线程才能执行。
 */

public class ThreadJoinTest extends Thread {
    private int countDown = 3;
    private static int threadCount = 0;
    public ThreadJoinTest() {
        super("" + ++threadCount);
        start();
    }
    public String toString() {
        return "#" + getName() + ": " + countDown;
    }
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0)
                return;
            try {
                sleep(100);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args)
            throws InterruptedException {
        for (int i = 0; i < 3; i++)
            new ThreadJoinTest().join();
        System.out.println("线程已被挂起");
    }
}