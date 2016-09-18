package MultiThreading;

import java.util.Date;

/**
 * Created by xuch on 2015/12/29.
 */
class RunnableThread implements Runnable
{
    // not marked as volatile
    private int value;

    // marked as volatile
    private volatile boolean missedIt;

    private long creationTime;


    public RunnableThread()
    {
        value = 10;
        missedIt = false;
        creationTime = System.currentTimeMillis();
    }

    private int tickets = 10; //shouldd not be static if ..
    @Override
    public void run()
    {
        //synchronized(MultiThreading.RunnableThread.class)
        /*synchronized(this)
        {
            for (int i = 0; i < 10; i++)
                if (tickets > 0) System.out.println("Ticket " + tickets-- + " sold.");
        }*/

        print("entering run()");

        // check the value changed or not
        while ( value < 20 )
        {
            if ( missedIt )
            {
                int curValue = value;

                // 在一个任意对象上执行同步语句,目的是为了让该线程在进入和离开同步代码块时
                // 将该线程中的所有变量的私有拷贝与共享内存中的原始值进行比较
                // 从未发现没有用volatile标记的标量所发生的变化
                Object lock = new Object();
                synchronized ( lock )
                {

                }

                // 离开synchronized后,此时将value的值赋给valueAfterSync
                int valueAfterSync = value;
                print("in run() - see value=" + curValue + ", but rumor has it that it changed");
                print("in run() - valueAfterSync=" + valueAfterSync);
                break;
            }
        }

        print("leaving run()");
    }

    public void workMethod() throws InterruptedException
    {
        print("entering workMethod()");
        print("in workMethod() - about to sleep for 2 seconds");
        Thread.sleep(2000);

        // change value only here
        missedIt = true;
        //value = 50;

        print("in workMethod() - just set value=" + value);
        print("in workMethod() - about to sleep for 5 seconds");
        Thread.sleep(5000);

        // change missedIt only here
        //missedIt = true;
        value = 50;

        print("in workMethod() - just set missedIt=" + missedIt);
        print("in workMethod() - about to sleep for 3 seconds");
        Thread.sleep(3000);
        print("leaving ()workMethod");
    }

    private void print(String msg)
    {
        long interval = System.currentTimeMillis() - creationTime;
        String tmpStr = "    " + (interval / 1000.0) + "000";
        int pos = tmpStr.indexOf(".");
        String secStr = tmpStr.substring(pos - 2, pos + 4);
        String nameStr = "    " + Thread.currentThread().getName();
        nameStr = nameStr.substring(nameStr.length() - 8, nameStr.length());
        System.out.println(secStr + " " + nameStr + ": " + msg);
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
        System.out.println((new Date()).getTime());
        try
        {
            // 通过构造函数获取时钟当前时间
            RunnableThread rt = new RunnableThread();

            // 暂停100ms,让实时时钟稍稍超前获取到的时间,使print()中创建的消息打印的时间值大于0
            Thread.sleep(100);

            Thread t = new Thread(rt);
            t.start();

            Thread.sleep(100);
            rt.workMethod();
        }
        catch ( InterruptedException e )
        {
            System.err.println("one of the sleeps was interruppted");
        }
        System.exit(1);


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

/* volatile
 * http://blog.csdn.net/ns_code/article/details/17382679
 * http://blog.csdn.net/ns_code/article/details/17382679
 *
 *
 */


