package ThreadTest;
/*
获取所有正在运行的线程
 */
public class GetTotalThreads extends Thread {
    public static void main(String[] args) {
        GetTotalThreads t1 = new GetTotalThreads();
        t1.setName("thread1");
        t1.start();
        ThreadGroup currentGroup =
                Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);
        for (int i = 0; i < noThreads; i++)
            System.out.println("线程号：" + i + " = " + lstThreads[i].getName());
    }
}
