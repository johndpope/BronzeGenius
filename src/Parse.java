import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;


public class Parse
{
	public static Set<String> dictionary  = new HashSet<String>() {{
									add("one");
	}};
	
	
	public int invalid = Integer.MAX_VALUE;
	public String parsed = "";
	public Parse(int inv, String p)
	{
		invalid = inv;
		parsed = p;
	} 
	
	public Parse clone()
	{
		return new Parse(this.invalid, this.parsed);
	}
	 
	public static Parse min(Parse p1, Parse p2)
	{
		if (p1 == null) return p2;
		else if (p2 == null) return p1;
	 
		return p2.invalid < p1.invalid ? p2 : p1;
	}

	public static Parse parse(String sentence, int wordStart, int wordEnd, Hashtable<Integer, Parse> cache)
	{
		if (wordEnd >= sentence.length())
		{
			return new Parse(wordEnd - wordStart, sentence.substring(wordStart).toUpperCase());
		}
		
		if (cache.containsKey(wordStart))
		{
			return cache.get(wordStart).clone();
		}
		
		String currentWord = sentence.substring(wordStart, wordEnd + 1);
		boolean validPartial = Parse.dictionary.contains(currentWord, false);
		boolean validExact = validPartial && Parse.dictionary.contains(currentWord, true);
		
		/* break current word */
		Parse bestExact = Parse.parse(sentence, wordEnd + 1, wordEnd + 1, cache);
		if (validExact)
		{
			bestExact.parsed = currentWord + " " + bestExact.parsed;
		}
		else
		{
			bestExact.invalid += currentWord.length();
			bestExact.parsed = currentWord.toUpperCase() + " " + bestExact.parsed; 
		}
		
		/* extended current word */
		Parse bestExtend = null;
		if (validPartial)
		{
			bestExtend = Parse.parse(sentence, wordStart, wordEnd + 1, cache);
		}
		
		/* find best */
		Parse best = Parse.min(bestExact, bestExtend);
		cache.put(wordStart, best.clone());
		return best;
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	