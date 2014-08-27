
public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "1|2|3||4|5|6";
		String sa[] = str.split("\\||");
		
		
		
		
		System.out.print("Hello World~");
		
		String[] ints = new String [5];
		for(String i : ints)
		{
			System.out.print(i);
		}
		
		//System.out.print(ints[5]);
	}

}
