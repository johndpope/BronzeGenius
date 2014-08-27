package RecursionAndDynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import HashCollision.Cell;


public class RecursionAndDynamic {

	public String name = this.setName();
	public String setName()
	{
		return "chenxu";
	}
	
	public String getName()
	{
		return name;
	}
	
	public enum Color
	{
		White, Red, Green, Black, Yellow
	}

	public enum Cent
	{
		//25, 10, 5, 1
	}
	
	public static void main(String[] args) {
		RecursionAndDynamic rd = new RecursionAndDynamic();
		echoln(rd.name);
		echoln(rd.getName());
		exit();
		// TODO Auto-generated method stub
		String exp = "1&0|0^1";//&0|1^1";
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		HashMap<String, Integer> hash1 = new HashMap<String, Integer>(); 
		echoln(checkExpValidation(exp));
		echo("\n"+expressionParentheses(exp, false, hash));
		//echo("\n"+expressionParenthesesOP(exp, false, hash1));
		//echo("\n"+expressionParenthesesOP(exp, false));
		exit();
		
		
		
		boolean ff = true;
		String ss = ff + "";
		System.out.print(ss);
		HashMap<Cell<String, Boolean>, Integer> hashR = new HashMap<Cell<String, Boolean>, Integer>();
		String strsss = "abc";
		Cell<String, Boolean> c1 = new Cell<String, Boolean>(strsss, true);
		Cell<String, Boolean> c2 = new Cell<String, Boolean>(strsss, true);
		hashR.put(c1, 2);
		if (hashR.containsKey(c2))
		{
			System.out.print("yes");
		}
		else
		{
			System.out.print("no");
		}
		System.exit(0);
		
		//ArrayList<Box> boxes = null;
		//System.out.print(boxes.size());
		ArrayList<Box> boxes1 = new ArrayList<Box>();
		System.out.print(boxes1.size());
		System.exit(0);
		
		
		final int SIZE = 8;
		boolean[][] chess = new boolean[SIZE][SIZE]; 
		System.out.print(placeQueen(chess));
		
		System.exit(0);
		
		
		System.out.print(makeChange(25, 25));
		System.exit(0);
		
		
		Set<String> set = generateParens(4); 
		ArrayList<String> set1 = generateParens1(4); 
		printSetString((HashSet<String>)set);
		printArrayString(set1);
		System.exit(0);
		
		
		String str= "lyroechan";
		System.out.print(str.charAt(str.length()-1));
		System.out.print(str.substring(0, str.length()));//or: str.substring(0)
		//ArrayList<String> perms = getPermutations(str);
		//printArrayString(perms);
		System.exit(0);
		
		int A[] = {0, 1, 2, 3, 4};
		ArrayList<ArrayList<Integer>> subSets = getSubSets(A, 3);
		printTwoDimensionalArrayList(subSets);
		ArrayList<ArrayList<Integer>> subSets1 = getSubSets1(A, 3);
		printTwoDimensionalArrayList(subSets1);
		System.exit(0);
		
		
		
		
		
		int n = 13;
		int[] ways = new int[n+1];
//		for (int i : ways)
//		{
//			System.out.print(i+" ");
//		}
//		return;
		System.out.printf("N stairs: %d", countWays(n));
		System.out.printf("N stairs: %d", countWays1(n));
		System.out.printf("N stairs: %d", countWays2(n, ways));

	}
	
	public static void echo(Object output)
	{
		System.out.print(output);
	}
	
	public static void echoln(Object output)
	{
		System.out.print(output);
		System.out.print("\n");
	}
	
	public static void exit()
	{
		System.exit(0);
	}
	
	public static void printSetString(HashSet<String> al)
	{
		if(al.isEmpty()) System.out.print("null");
		for(String i : al)
		{
			System.out.print(i+"\t");
		}
		System.out.print("\n");
	}
	
	public static void printArrayString(ArrayList<String> al)
	{
		if(al.isEmpty()) System.out.print("null");
		for(String i : al)
		{
			System.out.print(i+"\t");
		}
		System.out.print("\n");
	}
	
	public static void printArrayList(ArrayList<Integer> al)
	{
		if(al.isEmpty()) System.out.print("null");
		for(int i : al)
		{
			System.out.print(i+"\t");
		}
		System.out.print("\n");
	}
	
	public static void printTwoDimensionalArrayList(ArrayList<ArrayList<Integer>> al)
	{
		if(al.isEmpty()) System.out.print("null");
		for(ArrayList<Integer> i : al)
		{
			printArrayList(i);
		}
		System.out.print("\n");
	}
	
	public static int countWays(int n)
	{
		if (n < 0) return 0;
		if (n == 0) return 1;
		int ways = 0;
		if( 1 <= n)
		{
			//ways++; // it is for calculate all steps
			ways += countWays(n-1);
		}
		if( 2 <= n)
		{
			//ways++; // it is for calculate all steps
			ways += countWays(n-2);
		}
		if( 3 <= n)
		{
			//ways++; // it is for calculate all steps
			ways += countWays(n-3);
		}

		return ways;
	}
	
	public static int countWays1(int n)
	{
		if (n < 0) return 0;
		else if (n == 0) return 1;
		else return countWays1(n-1) + countWays1(n-2) + countWays1(n-3);
	}
	
	public static int countWays2(int n, int[] ways)
	{
		if (n < 0) return 0;
		else if (n == 0) 
		{
			ways[n] = 1;
		}
		else if (ways[n] > 0) //(ways[n] > -1) //ways[n] has been set a value, not the initialized value
		{
			return ways[n];
		}
		else
		{
			ways[n] = countWays2(n-1, ways) + countWays2(n-2, ways) + countWays2(n-3, ways);
		}
		return ways[n];
	}
	
	public static int getPath()
	{
		return 1;
	}
	
	public static ArrayList<ArrayList<Integer>> getSubSets(int A[], int length)
	{
	    if(length <= 0) return null;
	    ArrayList<ArrayList<Integer>> subSets = new ArrayList<ArrayList<Integer>>();
	    if(length == 1)
	    {
	        subSets.add(new ArrayList<Integer>());
	        
	        ArrayList<Integer> newSet = new ArrayList<Integer>();
	        newSet.add(A[length-1]);
	        subSets.add(newSet);
	        //subSets.add(new ArrayList<Integer>(A[length-1]));
	    }
	    else
	    {
	    	ArrayList<ArrayList<Integer>> prevSubSets = getSubSets(A, length-1);
	    	subSets.addAll(prevSubSets);
	        for (ArrayList<Integer> it : prevSubSets)
	        {
	        	ArrayList<Integer> newIt = new ArrayList<Integer>();
	        	newIt.addAll(it);
	        	newIt.add(A[length-1]);
	            subSets.add(newIt); //don't modify subSets in for-each of subSets
	        }
	    }
	    
	    return subSets;
	}
	//n个数的集合{a.0, a.1, a.2, ..., a.n-1}，其全部子集表示为P(n)，在从中选取元素组成子集时，每个元素有两种选择：选择加入，或不加入。即，n位数，每位的值有两种可能：0或1。则P(n)一共有2^n个，即00...00 到 11...11中每一个数对应一个子集。
	public static ArrayList<ArrayList<Integer>> getSubSets1(int A[], int length)
	{
		if(length <= 0) return null;
	    ArrayList<ArrayList<Integer>> subSets = new ArrayList<ArrayList<Integer>>();
	    int max = 1 << length;
	    for(int i = 0; i < max; ++i)
	    //int max = (1 << length) - 1;
	    //for(int i = 0; i <= max; ++i)
	    {
	    	ArrayList<Integer> subSet = new ArrayList<Integer>();
	    	for(int j = 0; j < length; ++j)
	    	{
	    		if(((i >> j) & 1) == 1 )
	    		{
	    			subSet.add(A[j]);
	    		}
	    	}
	    	subSets.add(subSet);
	    }
	    return subSets;
	}
	
	//get all permutations of a string: p(n) = p(n-1) * n
	public static ArrayList<String> getPermutations(String str)
	{
		if(str == null) return null;
		
		int length = str.length();
		ArrayList<String> perms = new ArrayList<String>();
		if(length == 0) perms.add("");
		else if (length == 1)
		{
			perms.add(str);
		}
		else
		{
			char c = str.charAt(length-1);
			ArrayList<String> prevPerms = getPermutations(str.substring(0, length-1));
			for (String s : prevPerms)
			{
				for (int i = 0; i <= s.length(); ++i)
				{
					String ns = insertCharAt(s, c, i);
					perms.add(ns);
				}
			}
		}
		
		return perms;
	}
	
	public static String insertCharAt(String str, char c, int index)
	{
		if (str == null) return null;
		
		int length = str.length();
		if (length <= 0) return "" + c;
		else if (index > length) return str + c;
		//else if (index == 0) return c + str;
		else
		{
			String first = str.substring(0, index);
			String second = str.substring(index);
			return first + c + second;
		}
	}
	
	public static String insertStringAt(String str, String c, int index)
	{
		if (str == null) return null;
		if (c == null || c.length() == 0) return str;
		
		int length = str.length();
		if (length <= 0) return c;
		else if (index > length) return str + c;
		//else if (index == 0) return c + str;
		else
		{
			String first = str.substring(0, index);
			String second = str.substring(index);
			return first + c + second;
		}
	}
	
	public static Set<String> generateParens(int remaining)
	{
		
		Set<String> set = new HashSet<String>();
		if (remaining == 0) set.add("");
		else 
		{
			Set<String> prev = generateParens(remaining - 1);
			for (String str : prev)
			{
				for (int i = 0; i < str.length(); ++i)
				{
					if (str.charAt(i) == '(')
					{
						String s = insertStringAt(str, "()", i+1);
						set.add(s);
					}
				}
				if(!set.contains("()" + str))
				{
					set.add("()" + str);
				}
			}
		}
		return set;
	}
	
	public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int count)
	{
		if (leftRem < 0 || rightRem < leftRem) return; // invalid state
		
		if (leftRem == 0 && rightRem ==0)              // no more parens left
		{
			String s = String.copyValueOf(str);
			list.add(s);
		}
		else
		{
			/* Add left paren, if there are any left parens remaining. */
			if (leftRem > 0)
			{
				str[count] = '(';
				addParen(list, leftRem-1, rightRem, str, count+1);
			}
			
			/* Add right paren, if expression is valid. */
			if (rightRem > leftRem)
			{
				str[count] = ')';
				addParen(list, leftRem, rightRem-1, str, count+1);
			}
		}
	}
	
	public static ArrayList<String> generateParens1(int count)
	{
		char[] str = new char[count*2];
		ArrayList<String> list = new ArrayList<String>();
		addParen(list, count, count, str, 0);
		return list;
	}
	
	/* Change the pixel which has a color equals to ocolor to ncolor. Stops when meeting a pixel which has a different color from ocolor. */
	public static boolean paintFill(Color[][] screen, int x, int y, Color ocolor, Color ncolor)
	{
		/* X represents the number of columns, Y represents the number of rows. */
		if (x < 0 || x >= screen[0].length || y < 0 || y > screen.length)
		{
			return false;
		}
		
		if (screen[x][y] == ocolor)
		{
			screen[x][y] = ncolor;
			paintFill(screen, x-1, y, ocolor, ncolor);
			paintFill(screen, x+1, y, ocolor, ncolor);
			paintFill(screen, x, y-1, ocolor, ncolor);
			paintFill(screen, x, y+1, ocolor, ncolor);
		}
		
		return true;
	}
	
	/* cents: 25,10,5,1. find all ways to represents n cents with them. */
	public static int makeChange(int n, int cent)//ArrayList<ArrayList<Cell<Integer, Integer>>> makeChange(int n, int start)
	{
		if (n < 0) return 0;
		//if (n == 0) return 1;
		
		//ArrayList<ArrayList<Cell<Integer, Integer>>> results = new ArrayList<ArrayList<Cell<Integer, Integer>>>();
		/*if (n == 0)
		{
			ArrayList<Cell<Integer, Integer>> newList = new ArrayList<Cell<Integer, Integer>>();
			newList.add(new Cell<Integer, Integer>(25, 0));
			newList.add(new Cell<Integer, Integer>(10, 0));
			newList.add(new Cell<Integer, Integer>(5, 0));
			newList.add(new Cell<Integer, Integer>(1, 0));
			results.add(newList);
		}*/
		int next_cent = 25;
		switch (cent)
		{
		case 25:
			next_cent = 10;
			break;
		case 10:
			next_cent = 5;
			break;
		case 5:
			next_cent = 1;
			break;
		case 1:
			return 1;
			
		}

		int ways = 0;
		for (; n >= 0; n -= cent)
		{
			ways += makeChange(n, next_cent);
		}
		return ways;
	}
	
	public static int placeQueen(int row, boolean[][] chess)
	{
		if(row >= chess.length)
		{
			//print the route
			for (int i = 0; i < chess.length; i++)
			{
				for (int j = 0; j < chess[0].length; j++)
				{
					System.out.print(chess[i][j] + " ");
					//if(chess[i][j]) System.out.print();
				}
				System.out.print("\n");
			}
			System.out.print("\n");
			return 1;
		}
		
		int ways = 0;
		for (int i = 0; i < chess[row].length; ++i)
		{
			if(isValid(chess, row, i))
			{
				chess[row][i] = true;
				ways += placeQueen(row+1, chess);
				chess[row][i] = false;
			}
		}
	
		return ways;
	}

	private static boolean isValid(boolean[][] chess, int row, int col)
	{
		int i, j;
		for (i = 0; i < chess.length; ++i)
		{
			if(chess[i][col] == true) return false;
		}
		
		for (i = 0; i < chess[row].length; ++i)
		{
			if(chess[row][i] == true) return false;
		}
		
		i = row+1;
		j = col+1;
		while(i < chess.length && j < chess[row].length)
		{
			if(chess[i][j] == true) return false;
			i++;
			j++;
		}
		
		i = row-1;
		j = col-1;
		while(i >= 0 && j >= 0)
		{
			if(chess[i][j] == true) return false;
			i--;
			j--;
		}
		
		i = row-1;
		j = col+1;
		while(i >= 0 && j < chess[row].length)
		{
			if(chess[i][j] == true) return false;
			i--;
			j++;
		}
		
		i = row+1;
		j = col-1;
		while(i < chess.length && j >= 0)
		{
			if(chess[i][j] == true) return false;
			i++;
			j--;
		}
		return true;
	}
	
	public static int placeQueen(boolean[][] chess)
	{
		return placeQueen(0, chess);
	}
	
	public static ArrayList<Box> createMaxStack(Box[] boxes, Box bottom)
	{
		ArrayList<Box> maxStack = null;
		int max_height = stackHeight(maxStack);
		for (int i = 0; i < boxes.length; i++)
		{
			if (bottom.isLarger(boxes[i]))
			{
				ArrayList<Box> newStack = createMaxStack(boxes, boxes[i]);
				int new_height = stackHeight(newStack);
				if ( new_height > max_height)
				{
					maxStack = newStack;
					max_height = new_height;
				}
			}
		}
		
		if (maxStack == null)
		{
			maxStack = new ArrayList<Box>();
		}
		
		if (bottom != null)
		{
			maxStack.add(0, bottom);
		}
		
		return maxStack;
	}

	public static ArrayList<Box> createMaxStackDP(Box[] boxes, Box bottom, HashMap<Box, ArrayList<Box>> hash)
	{
		if (bottom != null && hash.containsKey(bottom)) return hash.get(bottom);
		
		ArrayList<Box> maxStack = null;
		int max_height = stackHeight(maxStack);
		for (int i = 0; i < boxes.length; i++)
		{
			if (bottom.isLarger(boxes[i]))
			{
				ArrayList<Box> newStack = (ArrayList<Box>)createMaxStackDP(boxes, boxes[i], hash).clone();
				int new_height = stackHeight(newStack);
				if ( new_height > max_height)
				{
					maxStack = newStack;
					max_height = new_height;
				}
			}
		}
		
		if (maxStack == null)
		{
			maxStack = new ArrayList<Box>();
		}
		
		if (bottom != null)
		{
			maxStack.add(0, bottom);
		}
		hash.put(bottom, maxStack);
		return maxStack;
	}
	
	public static int stackHeight(ArrayList<Box> newStack)
	{
		if(newStack == null) return 0;
		return newStack.size();
	}
	
	/* return ways of one expression with parentheses. */
	public static int expressionParentheses(String exp, boolean result, HashMap<String, Integer> hash)
	{
		if (!checkExpValidation(exp)) return 0;
		
		
		String key = "" + exp + result;
		if(hash.containsKey(key)) return hash.get(key);
		
		
		if (exp.length() == 1)
		{
			return (exp.equals("1") && result) || (exp.equals("0") && !result) ? 1 : 0;
		}
		
		int ways = 0;
		for (int i = 1; i < exp.length(); i+=2)
		{
			if (result)
			{
				switch (exp.charAt(i))
				{
				case '&':
					ways += expressionParentheses(exp.substring(0, i), true, hash) * expressionParentheses(exp.substring(i+1), true, hash);
					break;
				case '|':
					ways += expressionParentheses(exp.substring(0, i), true, hash) * expressionParentheses(exp.substring(i+1), true, hash);
					ways += expressionParentheses(exp.substring(0, i), true, hash) * expressionParentheses(exp.substring(i+1), false, hash);
					ways += expressionParentheses(exp.substring(0, i), false, hash) * expressionParentheses(exp.substring(i+1), true, hash);
					break;
				case '^':
					ways += expressionParentheses(exp.substring(0, i), true, hash) * expressionParentheses(exp.substring(i+1), false, hash);
					ways += expressionParentheses(exp.substring(0, i), false, hash) * expressionParentheses(exp.substring(i+1), true, hash);
					break;
				}
			}
			else
			{
				switch (exp.charAt(i))
				{
				case '&':
					ways += expressionParentheses(exp.substring(0, i), false, hash) * expressionParentheses(exp.substring(i+1), true, hash);
					ways += expressionParentheses(exp.substring(0, i), true, hash) * expressionParentheses(exp.substring(i+1), false, hash);
					ways += expressionParentheses(exp.substring(0, i), false, hash) * expressionParentheses(exp.substring(i+1), false, hash);
					break;
				case '|':
					ways += expressionParentheses(exp.substring(0, i), false, hash) * expressionParentheses(exp.substring(i+1), false, hash);
					break;
				case '^':
					ways += expressionParentheses(exp.substring(0, i), true, hash) * expressionParentheses(exp.substring(i+1), true, hash);
					ways += expressionParentheses(exp.substring(0, i), false, hash) * expressionParentheses(exp.substring(i+1), false, hash);
					break;
				}
			}
		}
		hash.put(key, ways);
		return ways;
	}
	
	/* For an expression of n operators, there are totally 2n!/(n+1)!n! ways for parenthesizing it.
	 * (2n)!/[(n+1)! * n!] = f(expression, true) + f(expression, false)
	 * So we only need to calculate total ways for parenthesizing the expression to true, and we can know how many ways of false.
	 *  
	 * */
	public static int expressionParenthesesOP(String exp, boolean result, HashMap<String, Integer> hash)
	{
		if (!checkExpValidation(exp)) return 0;
		
		int ways = 0;
		String key = exp;
		if(hash.containsKey(key)) ways = hash.get(key);
		else
		{
			if (exp.length() == 1)
			{
				return exp.equals("1") ? 1 : 0;
			}
			
			for (int i = 1; i < exp.length(); i+=2)
			{
				switch (exp.charAt(i))
				{
				case '&':
					ways += expressionParenthesesOP(exp.substring(0, i), true, hash) * expressionParenthesesOP(exp.substring(i+1), true, hash);
					break;
				case '|':
					int left = i / 2;
					int right = (exp.length() - 1) / 2 - left - 1;
					int lways = totalParenthesizing(left);
					int rways = totalParenthesizing(right);
					int total =  lways * rways;
					ways += (total - (expressionParenthesesOP(exp.substring(0, i), false, hash)) * expressionParenthesesOP(exp.substring(i+1), false, hash));
					break;
				case '^':
					ways += expressionParenthesesOP(exp.substring(0, i), true, hash) * expressionParenthesesOP(exp.substring(i+1), false, hash);
					ways += expressionParenthesesOP(exp.substring(0, i), false, hash) * expressionParenthesesOP(exp.substring(i+1), true, hash);
					break;
				}
			}
			hash.put(key, ways);
		}
		return result ? ways : (totalParenthesizing((exp.length() - 1) / 2) - ways);
	}
	
	
	
	
	public static int expressionParenthesesOP(String exp, HashMap<String, Integer> hash)
	{
		if (!checkExpValidation(exp)) return 0;
		
		
		String key = exp;
		if(hash.containsKey(key)) return hash.get(key);
		
		if (exp.length() == 1)
		{
			return exp.equals("1") ? 1 : 0;
		}
		
		int ways = 0;
		for (int i = 1; i < exp.length(); i+=2)
		{
			int left = i / 2;
			int right = (exp.length() - 1) / 2 - left - 1;
			int lways = totalParenthesizing(left);
			int rways = totalParenthesizing(right);
			int total =  lways * rways;
			switch (exp.charAt(i))
			{
			case '&':
				ways += expressionParenthesesOP(exp.substring(0, i), hash) * expressionParenthesesOP(exp.substring(i+1), hash);
				break;
			case '|':
				ways += total - (lways - expressionParenthesesOP(exp.substring(0, i), hash)) * (rways - expressionParenthesesOP(exp.substring(i+1), hash));
				break;
			case '^':
				ways += expressionParenthesesOP(exp.substring(0, i), hash) * (rways - expressionParenthesesOP(exp.substring(i+1), hash));
				ways += (lways - expressionParenthesesOP(exp.substring(0, i), hash)) * expressionParenthesesOP(exp.substring(i+1), hash);
				break;
			}
		}
		
		hash.put(key, ways);
		return ways;
	}
	
	public static int expressionParenthesesOP(String exp, boolean result)
	{
		HashMap<String, Integer> hash = new HashMap<String, Integer>(); 
		if(result) return expressionParenthesesOP(exp, hash);
		else return totalParenthesizing((exp.length() - 1) / 2) - expressionParenthesesOP(exp, hash);
	}
	
	// (2n)!/[(n+1)! * n!]
	public static int totalParenthesizing(int n)
	{
		if (n <= 0) return 0;
		
		int sum = 1;
		int temp = 1;
		for (int i = 1; i <= 2 * n; i++)
		{
			sum *= i;
			if (i == n) temp = sum;
		}
		
		return sum / (temp * (n + 1) * temp);
	}
	
	public static boolean checkExpValidation(String exp)
	{
		if (exp.length() % 2 != 1) return false;
		
		char[] chars = exp.toCharArray();
		for (int i = 0; i < chars.length; i++)
		{
			switch(i % 2)
			{
			case 0:
				if(chars[i] != '0' && chars[i] != '1')
				{
					return false;
				}
				break;
			case 1:
				if(chars[i] != '&' && chars[i] != '^' && chars[i] != '|')
				{
					return false;
				}
				break;
			}
		}
		return true;
	}
}
























