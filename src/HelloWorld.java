import java.lang.*;

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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		// diff1

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
