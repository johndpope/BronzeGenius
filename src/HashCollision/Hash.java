package HashCollision;

import java.util.LinkedList;

public class Hash<K, V>
{
	private final int MAX_SIZE = 10;
	LinkedList< Cell<K, V> >[] items;
	
	public Hash()
	{
		//items = new LinkedList< Cell<K, V> >[MAX_SIZE];
		items = (LinkedList<Cell<K, V>>[]) new LinkedList[MAX_SIZE];
	}
	
	/* Really, really ugly hash. */
	public int hashCodeOfKey(K key)
	{
		return key.toString().length() % items.length;
	}
	
	public void put(K key, V value)
	{
		int x = hashCodeOfKey(key);
		if (items[x] == null)
		{
			items[x] = new LinkedList<Cell<K, V>>();
		}
		
		for (Cell<K, V> c : items[x])
		{
			if(c.equalsTo(key))
			{
				items[x].remove(c);
				break;
			}
		}
		
		Cell<K, V> cell = new Cell<K, V>(key, value);
		items[x].add(cell);
	}
	
	public V get(K key)
	{
		int x = hashCodeOfKey(key);
		if (items[x] == null) return null;
		
		for (Cell<K,V> cell : items[x])
		{
			if(cell.equalsTo(key))
			{
				return cell.getValue();
			}
		}
		
		return null;
	}
}
