import java.io.IOException;
import java.util.*;


class Mouse {
	private int mouseNumber;
	Mouse(int i) {
		mouseNumber = i;
	}

	// magic method. if not override, then will can Object.toString() by default
	public String toString() {
		return "This is Mouse #" + mouseNumber;
	}

	void print(String msg) {
		if (msg != null) System.out.println(msg);
		System.out.println("Mouse number " + mouseNumber);
	}
}

class Dog {}

class MouseTrap {
	static void caughtYa(Object m) {
		Mouse mouse = (Mouse)m; // cast from Objest, can detect wrong object
		mouse.print("Caught one!");
	}
}

class Counter {
	int i = 1;
	public String toString() {
		return Integer.toString(i);
	}
}

public class HelloWorld {

	static {
		System.out.println("Executed before main()");
	}

	static void exit() {
		System.exit(0);
	}

	static void prl(String str) {
		System.out.println(str);
	}

	static void printBitSet(BitSet b) {
		System.out.println("bits: " + b);
		String bbits = new String();
		for (int i = 0; i < b.size(); i++) {
			bbits += (b.get(i) ? "1" : "0");
		}
		System.out.println("bit pattern: " + bbits);
	}

	public static void f() throws Exception {
		System.out.println("originating the exception in f()");
		throw new Exception("thrown from f()");
	}

	public static void g() throws Throwable {
		try {
			f();
		} catch (Exception e) {
			System.out.println("Inside g(), e.printStackTrace()");
			e.printStackTrace();
			//throw e;
			throw e.fillInStackTrace(); // fillInStackTrace() returns a Throwable
		}
	}

	public static HashMap<String, String> testNonInitializationObject(boolean flag) {
		HashMap<String, String> map = new HashMap<>();
		if (flag)
		{
			map.put("hello", "world");
		}

		return map;
	}

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub

		/*
		* map.get(key) return null if key not exist, do not throw exception
		*
		*
		* */
		try
		{

			System.out.println(Integer.parseInt("000000048"));
			System.exit(0);
			HashMap<String, String> result = testNonInitializationObject(true);
			System.out.println("result is " + (result.isEmpty() ? "empty" : "not empty"));
			for (Map.Entry e : result.entrySet())
			{
				System.out.println(e.getKey() + "=" + e.getValue());
			}
			System.out.println("as=" + result.get("as"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.exit(0);


		String _str = "hello world!", _str1 = "你好！";
		byte[] _str2 = _str.getBytes("utf-16be");
		byte[] _str3 = _str1.getBytes("utf8");
		System.out.println(String.valueOf(_str2));
		System.out.println(String.valueOf(_str3));
		exit();


		int count = 0;
		while (count < 10) {
			try {
				if (count++ == 0) throw new Exception();
				System.out.println("No exception");
			} catch (Exception e) {
				System.out.println("Exception thrown");
			} finally {
				System.out.println("in finally clouse");
				if (count == 3) break;
			}
		}
		// 貌似finally在这里没有作用，因为无论有无finally，finally块里的语句都会被执行。但是在某些情况下会用到fanally，比如在有可能抛出很多种类异常的情况下，在抛出异常后需要进行一些恢复或者清理工作，不可能在每个catch块里去重复这种操作的代码，所以就可以用一个finally块解决问题。
		System.out.println("count: " + count);
		exit();

		try {
			g();
		} catch (Exception e) {
			System.out.println("Caught in main, e.printStackTrace()");
			e.printStackTrace();
			throw new NullPointerException("from main");
		}
		exit();



		try {
			throw new Exception("Here is my exception");
		} catch (Exception e) {
			System.out.println("Caught Exception");
			System.out.println("e.getMessage(): " + e.getMessage());
			System.out.println("e.toString(): " + e.toString());
			System.out.println("e.printStackTrace(): ");
			e.printStackTrace();
		}
		exit();





















		int ii = 0;
		prl(Integer.toString(ii));
		exit();

		StrSortVector ssv = new StrSortVector();
		ssv.addElement("d");
		ssv.addElement("D");
		ssv.addElement("a");
		ssv.addElement("b");
		ssv.addElement("t");
		ssv.addElement("R");
		ssv.addElement("B");
		ssv.addElement("C");
		ssv.addElement("c");
		Enumeration em = ssv.elements();
		while (em.hasMoreElements()) System.out.println(em.nextElement());
		exit();

		Properties p = System.getProperties();
		p.list(System.out);
		exit();

		Hashtable ht = new Hashtable();
		for (int i = 0; i < 1000000000; i++) {
			Integer r = new Integer((int)(Math.random() * 20));
			if (ht.containsKey(r)) ((Counter)ht.get(r)).i++;
			else ht.put(r, new Counter());
		}
		System.out.println(ht);
		exit();

		AssocArray aa = new AssocArray();
		for (char c = 'd'; c <= 'z'; c++) aa.put(String.valueOf(c), String.valueOf(c).toUpperCase());
		char[] ca = {'d', 'e', 'i', 'o', 'u'};
		for (int i = 0; i < ca.length; i++) System.out.println("Upper case: " + aa.get(String.valueOf(ca[i])));

		exit();

		Random rand = new Random();
		byte bt = (byte)rand.nextInt();
		BitSet bb = new BitSet();
		for(int i = 7; i >= 0; i--) {
			if (((1 << i) & bt) != 0) bb.set(i); // set 1 by default
			else bb.clear(i); // set 0 by default
		}
		System.out.println("byte value: " + bt);
		printBitSet(bb);

		short st = (short)rand.nextInt();
		BitSet bs = new BitSet();
		/*for(int i = 15; i >= 0; i--) {
			if (((1 << i) & st) != 0) bs.set(i); // set 1 by default
			else bs.clear(i); // set 0 by default
		}*/
		for (int i = 0, j = 1; i < 16; i++, j <<= 1) {
			if ((j & st) != 0) bs.set(i);
			else bs.clear(i);
		}
		System.out.println("short value: " + st);
		printBitSet(bs);

		int it = rand.nextInt();
		BitSet bi = new BitSet();
		for (int i = 0, j = 1; i < 32; i++, j <<= 1) {
			if ((j & it) != 0) bi.set(i);
			else bi.clear();
		}
		System.out.println("int value: " + it);
		printBitSet(bi);

		BitSet b127 = new BitSet();
		b127.set(127); // length is 128: index range 0 ~ 127
		System.out.println("set bit 127: " + b127);
		BitSet b255 = new BitSet(65);
		b255.set(255);
		System.out.println("set bit 255: " + b255);
		BitSet b1023 = new BitSet(512);
		//b1023.set(1023);
		b1023.set(1024); // out of the range of 512
		System.out.println("set bit 1023: " + b1023);

		exit();







		Vector mice = new Vector();
		for (int i =0; i < 3; i++) {
			mice.addElement(new Mouse(i));
		}
		//mice.addElement(new Dog());

		for (int i = 0; i < mice.size(); i++) {
			System.out.println("Free mouse: " + mice.elementAt(i));
			MouseTrap.caughtYa(mice.elementAt(i)); // detect the wrong class, and exception thrown here when running
		}

		Enumeration en = mice.elements();
		while (en.hasMoreElements()) {
			System.out.println(en.nextElement().toString());
		}
		exit();



		try {
			String current = new java.io.File(".").getCanonicalPath();
			System.out.println("Current dir:" + current);
			String currentDir = System.getProperty("user.dir");
			System.out.println("Current dir using System:" + currentDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		exit();

		String str = "1|2|3||4|5|6";
		String sa[] = str.split("\\||");

		label:
		for (int i = 0; ; i++) {
			System.out.println(i);
			if (i == 5) break label; // break即终止了当前循环,但是不是移到下一行代码,而是回到label处,但是已终止的循环不会继续或重复.
		}

		prl("after label");

		// switch 选择因子必须是int或者char,即整数型
		int swt = 1;
		switch(swt) {
			case 1:
			case 2:
			case 3:
			case 4:
				prl("" + swt++);
			case 5:
			default:
				break;
		}

		while (true)
		{
			double d = Math.random();
			prl(""+d);
			if (d == 1.0 || d == 0.0) break;

		}
		exit();
		for(int i = 0; i != 100; i++) {
			double f = Math.random(); // 返回[0, 1) 区间的数,包括0,不包括1
			int a = (int)(f * 26);
			char c = (char)(f * 26 + 'a');
			//System.out.println(f + "\t" + a + "\t" + c);
			System.out.printf("%-30f,%5d,%5s\n", f, a, c);
		}
		exit();


		// diff
		// diff1--
		// diff2
		// from git


		// from intellij

		boolean flag = false;
		System.out.println("flag: " + flag);
		System.exit(0);


		System.out.print("Hello World~");
		
		String[] ints = new String [5];
		for(String i : ints)
		{
			System.out.print(i);
		}
		
		//System.out.print(ints[5]);
	}

}


class AssocArray extends Dictionary
{
	private Vector keys = new Vector();
	private Vector values = new Vector();

	public int size() {return keys.size();}
	public boolean isEmpty() {return keys.isEmpty();}

	public Object put(Object key, Object value) {
		keys.addElement(key);
		values.addElement(value);
		return key;
	}

	public Object get(Object key) {
		int index = keys.indexOf(key);
		if (index == -1) return null;
		return values.elementAt(index);
	}

	public Object remove(Object key) {
		int index = keys.indexOf(key);
		if (index == -1) return null;
		keys.removeElementAt(index);
		Object rt = values.elementAt(index);
		values.removeElementAt(index);
		return rt;
	}

	public Enumeration keys() {return keys.elements();}

	public Enumeration elements() {return values.elements();}

}

interface Compare
{
	boolean lessThan(Object l, Object r);
	boolean lessThanOrEqual(Object l, Object r);
}

class SortVector extends Vector {
	private Compare compare;

	public SortVector(Compare comp) {
		compare = comp;
	}

	public void sort() {
		quickSort(0, size() - 1);
	}

	private void quickSort(int left, int right) {
		if (right > left) {
			Object ol = elementAt(right);
			int i = left - 1;
			int j = right;
			while (true) {
				while (compare.lessThan(elementAt(++i), ol));
				while (j > 0) if (compare.lessThanOrEqual(elementAt(--j), ol)) break;
				if (i >= j) break;
				swap(i, j);
			}
			swap(i, right);
			quickSort(left, i - 1);
			quickSort(i + 1, right);
		}
	}

	private void swap(int l, int r) {
		Object tmp = elementAt(l);
		setElementAt(elementAt(r), l);
		setElementAt(tmp, r);
	}
}

class StrSortVector {
	private SortVector v = new SortVector(
		// anonymous inner class: 匿名内部类，没有具体的名字，可以保证只在内部使用
		new Compare() {
			public boolean lessThan(Object l, Object r) {
				return ((String)l).toLowerCase().compareTo(((String)r).toLowerCase()) < 0;
			}

			public boolean lessThanOrEqual(Object l, Object r) {
				return ((String)l).toLowerCase().compareTo(((String)r).toLowerCase()) <= 0;
			}
		}
	);

	private boolean sorted = false;
	public void addElement(String s) {
		v.addElement(s);
		sorted = false;
	}

	public String elementAt(int index) {
		if (!sorted) {
			v.sort();
			sorted = true;
		}
		return (String)v.elementAt(index);
	}

	public Enumeration elements() {
		if (!sorted) {
			v.sort();
			sorted = true;
		}
		return v.elements();
	}
}



// https://w.amazon.com/index.php/Linux/Desktop/DNS
// https://w.amazon.com/index.php/Linux/Desktop/Single_user_mode