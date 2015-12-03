/**
 * Created by chenxu on 11/22/15.
 */
//: Garbage.java
//  一个java文件可以有多个class,但是最多只能有一个public class,可以有多个非public class,如果有public class,则文件名必须与public类名同名,否则可以不必和任意类名同名

class Chair {
    static boolean gcrun = false;
    static boolean f = false;
    static  int created = 0;
    static  int finalized = 0;
    int i;

    Chair()
    {
        i = ++created;
        if (created == 47) System.out.println("Created 47");
        System.out.printf("i: %5d, created: %5d.\n", i, created);
    }

    protected void finalize()
    {
        if (!gcrun)
        {
            gcrun = true;
            System.out.println(
                    "Beginning to finalize after " +
                            created + " Chairs have been created");
            System.out.println("------------------------");
        }

        if (i == 47) // 当前进行垃圾清理的Chair的编号,当垃圾清理进行编号到47时,就可以停止Chair创建工作.
        {
            System.out.println(
                    "Finalizing Chair #47, " +
                            "Setting flag to stop Chair creation");
            f = true;
            System.out.println("------------------------");
        }

        finalized++;
        if (finalized >= created)
        {
            System.out.println("All " + finalized + " finalized");
            System.out.println("------------------------");
        }
    }
}

public class Garbage {
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Usages: \n" + "java Garbage before\n  or: \n" + "java Garbage after");
            return;
        }

        while(!Chair.f)
        {
            new Chair();
            new String("To take space");
        }

        System.out.println(
            "After all Chairs have been created\n" +
            "total created =" + Chair.created +
            ", total finalized=" + Chair.finalized
        );

        if (args[0].equals("before"))
        {
            System.out.println("gc():");
            System.gc();
            System.out.println("run Finalization():");
            System.runFinalization();
        }

        System.out.println("bye!");
        if (args[0].equals("after")) System.runFinalizersOnExit(true);
    }
} ///:~
