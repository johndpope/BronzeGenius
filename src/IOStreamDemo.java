/**
 * Created by xuch on 2015/12/15.
 */
import java.io.*;
import java.util.HashMap;
import java.util.Map;
//import com.bruceeckel.tools.*;

public class IOStreamDemo {
    public static void main(String[] args) {
        Map<String, String> mp = new HashMap<>();
        mp.put("1", "hello");
        Map<String, String> map = mp;
        System.out.println(map.get("1"));

        /*try {
            // 1. Buffered input file
            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(args[0])));
            String s, s2 = new String();
            while ((s = in.readLine()) != null) s2 += s + "\n";
            in.close();

            // 2. Input from memory
            StringBufferInputStream in2 = new StringBufferInputStream(s2);
            int c;
            while ((c = in2.read()) != -1) System.out.print((char) c);

            // 3. formatted memory input
            try {
                DataInputStream in3 = new DataInputStream(new StringBufferInputStream(s2));
                while (true) System.out.print((char)in3.readByte());
            } catch (EOFException e) {
                System.out.println("end of stramencountered");
            }

            // 4. line numbering & file output
            try {
                LineNumberInputStream li = new LineNumberInputStream(new StringBufferInputStream(s2));
                DataInputStream in4 = new DataInputStream(li);
                PrintStream out1 = new PrintStream(new BufferedOutputStream(new FileOutputStream("IOdemo.out")));

                while ((s = in4.readLine()) != null) out1.println("Line" + li.getLineNumber() + s);
                out1.close(); // finalize() not reliable
            } catch (EOFException e) {
                System.out.println("End of stramencountered");
            }

            // 5. Storing & recovering data
            try {
                DataOutputStream out2 = new DataOutputStream(new BufferedOutputStream(new FileInputStream("Data.txt")));
                System.out.println(in5.readLine());
                System.out.println(in5.readDouble());
            } catch (EOFException e) {
                System.out.println("End of stramencountered");
            }

            // 6. Reading/writing randomaccess files
            RandomAccessFile rf = new RandomAccessFile("rtest.dat", "rw");
            for (int i = 0; i < 10; i++) rf.writeDouble(i * 1.414);
            rf.close();

            rf = new RandomAccessFile("rtest.dat", "rw");
            rf.seek(5 * 8);
            rf.writeDouble(47.0001);
            rf.close();

            rf = new RandomAccessFile("rtest.dat", "r");
            for (int i = 0; i < 10; i++)
                System.out.println("value" + i + ": " + rf.readDouble());
            rf.close();

            // 7. fileinput shorthand
            InFile in6 = new InFile(args[0]);
            String s3 = new String();
            System.out.printlnl("First line in file: " + in6.readLine());
            in6.close();

            // 8. formatted file output shorthand
            PrintFile out3 = new PrintFile("Data2.txt");
            out3.print("test of printfile");
            out3.close();

            // 9. datafile output shorthand
            //OutFile

        }*/
    }
}
