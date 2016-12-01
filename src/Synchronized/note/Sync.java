package Synchronized.note;

/**
 * Created by xuch on 2016/1/29.
 */
public class Sync
{
    // non-synchronized method 
    public void non_synchronized()
    {
        System.out.println("non synchronized method");
    }

    // syn the method
    public synchronized void sync(int i)
    {
        System.out.println("inside the sync " + i);
        System.out.println("sync test begin " + i);
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("sync test ends " + i);
        System.out.println("end of the sync " + i + "\n");
    }

    // syn the code snippet
    public void sync_1(int i)
    {
        System.out.println("inside the sync_1 " + i);
        synchronized (this)
        {
            System.out.println("sync_1 test begin " + i);
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("sync_1 test ends " + i);
        }
        System.out.println("end of the sync_1 " + i + "\n");
    }

    public synchronized void sync_2(int i)
    {
        System.out.println("inside the sync_2 " + i);
        System.out.println("sync_2 test begin " + i);
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("sync_2 test ends " + i);
        System.out.println("end of the sync_2 " + i + "\n");
    }

    // syn the static method
    public static synchronized void sync_static(int i)
    {
        System.out.println("inside the sync_static " + i);
        System.out.println("sync_static test begin " + i);
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("sync_static test ends " + i);
        System.out.println("end of the sync_static " + i + "\n");
    }

    // syn the static snippet
    public static void sync_static_1(int i)
    {
        System.out.println("inside the sync_static_1 " + i);
        synchronized (Sync.class)
        {
            System.out.println("sync_static_1 test begin " + i);
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("sync_static_1 test ends " + i);
        }
        System.out.println("end of the sync_static_1 " + i + "\n");
    }

    public static void main(String[] args)
    {
        //test_1();
        //test_2();
        /*test_3();
        test_4();
        test_5();
        test_6();
        test_7();
        test_8();*/
        //test_9();
        test_10();
    }

    // 每个Sync对象都可以执行自己的sync方法，对象之间互不干涉，即多个对象可以并行执行自己的sync方法
    public static void test_1()
    {
        for (int i = 0; i < 3; i++)
        {
            Thread thread = new MyThread(new Sync(), i);
            thread.start();
        }
    }

    // 同一个Sync对象只能依次执行sync方法，不能同时执行
    // multithread 执行开始顺序并不是按照thread对象创建和start的顺序，是随机的，例如thread0在thread2之前start，但是实际执行可能thread2在thread0之前
    public static void test_2()
    {
        Sync sync = new Sync();
        for ( int i = 0; i < 3; i++ )
        {

            Thread thread = new MyThread(sync, i);
            thread.start();
        }
    }
    // 每个Sync对象都可以执行自己的sync代码片段，对象之间互不干涉，即多个对象可以同时执行自己的sync代码片段
    public static void test_3()
    {
        for ( int i = 0; i < 3; i++ )
        {
            Thread thread = new MyThread(new Sync(), i)
            {
                @Override
                public void run()
                {
                    this.sync.sync_1(this.i);
                }
            };
            thread.start();
        }
    }

    // 同一个Sync对象只能依次执行完sync代码片段，不能并行执行sync代码片段，但是对于非sync代码片段（例如同一method中sync代码片段之前或之后的代码）可以并行执行，可以在当前thread的对象执行sync片段时，另一thread的对象可以执行同一method中的非sync代码片段。当然，这里的不同thread使用的都是同一对象
    public static void test_4()
    {
        Sync sync = new Sync();
        for ( int i = 0; i < 3; i++ )
        {
            Thread thread = new MyThread(sync, i)
            {
                @Override
                public void run()
                {
                    this.sync.sync_1(this.i);
                }
            };
            thread.start();
        }
    }




    // synchronized static method锁住的是当前Class,不是某个具体对象，所以synchronized static method任何时候都只能单一运行，不能并行运行

    // 每个Sync对象都可以执行自己的sync方法，对象之间互不干涉，即多个对象可以并行执行自己的sync方法
    public static void test_5()
    {
        for ( int i = 0; i < 3; i++ )
        {
            Thread thread = new MyThread(new Sync(), i)
            {
                @Override
                public void run()
                {
                    this.sync.sync_static(this.i);
                }
            };
            thread.start();
        }
    }

    // 对于synchronized static method，任何时候都只能对所属Class单一执行，不能并行执行
    public static void test_6()
    {
        Sync sync = new Sync();
        for ( int i = 0; i < 3; i++ )
        {

            Thread thread = new MyThread(sync, i)
            {
                @Override
                public void run()
                {
                    this.sync.sync_static(this.i);
                }
            };
            thread.start();
        }
    }

    // 对于synchronized static snippet，任何时候都只能被所属Class单一执行，不能并行执行，与具体的Class对象无关
    // 但是non-synchronized static snippet可以并行执行，这个也与具体的某个Class对象无关
    public static void test_7()
    {
        for ( int i = 0; i < 3; i++ )
        {
            Thread thread = new MyThread(new Sync(), i)
            {
                @Override
                public void run()
                {
                    this.sync.sync_static_1(this.i);
                }
            };
            thread.start();
        }
    }

    // 对于synchronized static snippet，任何时候都只能被所属Class单一执行，不能并行执行，与具体的Class对象无关
    // 但是non-synchronized static snippet可以并行执行，这个也与具体的某个Class对象无关
    // 例如同一method中sync代码片段之前或之后的代码,可以并行执行，可以在当前thread执行sync片段时，另一thread可以执行同一method中的非sync代码片段。当然，这里的不同thread使用的都是同一对象或相同的Class，其实对于static代码，与对象无关
    public static void test_8()
    {
        Sync sync = new Sync();
        for ( int i = 0; i < 3; i++ )
        {
            Thread thread = new MyThread(sync, i)
            {
                @Override
                public void run()
                {
                    this.sync.sync_static_1(this.i);
                }
            };
            thread.start();
        }
    }

    // 测试同一个Instance中的multiple synchronized methods
    public static void test_9()
    {
        Sync sync = new Sync();
        for ( int i = 0; i < 3; i++ )
        {
            Thread thread = new MyThread(sync, i)
            {
                @Override
                public void run()
                {
                    this.sync.sync(this.i);
                    this.sync.non_synchronized();
                    this.sync.sync_2(this.i);
                }
            };
            thread.start();
        }
    }

    // 测试同一个Instance中的multiple synchronized methods
    public static void test_10()
    {
        Sync sync = new Sync();
        for ( int i = 0; i < 3; i++ )
        {
            Thread thread = new MyThread(sync, i)
            {
                @Override
                public void run()
                {
                    this.sync.sync(this.i);
                    this.sync.sync_2(this.i);
                    this.sync.sync_static(this.i); // syn static method
                }
            };
            thread.start();

            thread = new MyThread(sync, i)
            {
                @Override
                public void run()
                {
                    this.sync.non_synchronized();
                    this.sync.sync_static_1(this.i); // syn static snippet
                }
            };
            thread.start();
        }
    }
}


/*
* a thread will be thrown into a runnable pool when executing thread.start
* then the OS will decide which one to run at the first time.
 * actually the running sequence does not depends on the sequence of executing thread.start, it depends on the sequence of OS running threads.
 * Generally it is unpredictable to run the threads by OS after thread.runnable().
* */

/*
*
* only when Class or Instance finishes executing one synchronized method, then the Class or Instance can Selection another synchronized method.
* 如果一个类有多个不同的synchronized methods，那么当前Class或者Instance在执行其中一个synchronized method时，不能去并行执行其他synchronized method。
* 即，同一个Class中的synchronized static methods/snippet是共享同一个Class lock的，只要当前Class处于locked状态，就不能再去并行执行任何当前thread或者其他threads的同一Instance的synchronized static methods/sinppet
* 同一个Instance中的synchronized methods/snippet是共享同一个Instance lock的，只要当前Instance处于locked状态，就不能再去执行任何thread的任何synchronized method/snippet（无论是否是同一method），但是不影响同时执行non-synchronized method或snippet
* 【PS】Class lock 和 Instance lock是两个独立的lock，互不影响。但要注意的是，一个Instance也是属于这个Class，所以任一个Instance调用synchronized static method时，这个Class处于locked状态，任何其他synchronized static method/snippet 都不能再同时执行。
* 【PS】Class/Instance所拥有的synchronized method一定在自己的类定义中，但是synchronized snippet经常在自己的类定义外。
* 【PS】共享同一个lock的method/snippet必须永远避免相互调用的局面。否则会引起deadlock死锁。
* 【PS】永远不要在Class自己的一个synchronized method/snippet中调用自己的另一个synchronized method/snippet。因为他们是共用同一个lock，这会造成死锁deadlock
* 【PS】要十分谨慎，当在Class自己的一个synchronized method/snippet中调用另一个Class的synchronized method/snippet。因为这也可能会造成死锁deadlock
* 由于一个Instance只能同时执行一个其所拥有的synchronized method，当synchronized method中调用另一个synchronized method/snippet时，有可能会引起死锁deadlock。请看如下例子：
*
* */
class MyThread extends Thread
{
    Sync sync;
    int i;

    public MyThread(Sync sync, int i)
    {
        this.sync = sync;
        this.i = i;
    }

    public void run()
    {
        sync.sync(this.i);
    }
}

//
/**
 * 想要让同一个Instance能够同时执行多个synchronized methods。可以在类定义中创建几个新锁进行绑定，即每个method绑定一个独立的object，看如下例子：
 *
 * 1. 同一个Instance可以在多个thread里同时执行inc1() 和 inc2()
 * 2. 同一个Instance不能并行执行多个inc1()，因为同一个Instance拥有同一个lock1
 * 3. 同一个Instance不能并行执行多个inc2()，因为同一个Instance拥有同一个lock2
 *
 *
 */
class MsLunch
{
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1()
    {
        synchronized (lock1)
        {
            c1++;
        }
    }

    public void inc2()
    {
        synchronized (lock2)
        {
            c1++;
        }
    }
}