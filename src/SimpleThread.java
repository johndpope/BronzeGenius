/**
 * Created by xuch on 2015/12/29.
 */
class RunnableThread implements Runnable
{
    private int tickets = 10; //shouldd not be static if ..
    @Override
    public void run()
    {
        //synchronized(RunnableThread.class)
        //synchronized(this)
        //{
            for (int i = 0; i < 10; i++)
                if (tickets > 0) System.out.println("Ticket " + tickets-- + " sold.");
        //}
    }
}

public class SimpleThread extends Thread {
    private int countDown= 5;
    private int threadNumber;
    private static int threadCount = 0;
    public SimpleThread() {
        threadNumber = ++threadCount;
        System.out.println("Making " + threadNumber);
    }

    public void run() {
        while (true) {
            System.out.println("Thread " + threadNumber + "(" + countDown + ")");
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        RunnableThread runnableThread = new RunnableThread();
        new Thread(runnableThread).start();
        new Thread(runnableThread).start();
        new Thread(runnableThread).start();

        RunnableThread runnableThread1 = new RunnableThread();
        new Thread(runnableThread1).start();
        new Thread(runnableThread1).start();
        new Thread(runnableThread1).start();
        System.exit(1);
        for (int i = 0; i < 5; i++) (new SimpleThread()).start();
        System.out.println("All threads started");

    }


}
/*
 * main() is a thread, and each simpleThread is a thread.
 * 这些thread随机交叉执行。
 *
 */


