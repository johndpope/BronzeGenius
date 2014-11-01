package RecursionAndDynamic;

import java.util.Iterator;

public class CircularArray<T> implements Iterator<T> {
	private T[] items;
	private int head = 0;
	
	public CircularArray(int size)
	{
		items = (T[]) new Object[size];
	}
	
	private int convert(int index)
	{
		int temp = (index + head) % items.length;
		return temp >= 0 ? temp : items.length + temp;
		
//		if (index + head >= 0)
//		{
//			return (index + head) % items.length;
//		}
//		else
//		{
//			return items.length + (index + head) % items.length;
//		}
	}
	
	public void rotate(int index)
	{
		head = convert(index);
	}
	
	public T get(int index)
	{
		if (index < 0 || index >= items.length)
		{
			throw new java.lang.IndexOutOfBoundsException("Index bounds: 0 ~ " + items.length);
		}
		return items[convert(index)];
	}
	
	public void set(int index, T t)
	{
		items[convert(index)] = t;
	}
	
	
	
	
	/* code for iterator */
	public Iterator<T> iterator()
	{
		return new CircularArrayIterator<T>(this);
	}

	private class CircularArrayIterator<T1> implements Iterator<T1>
	{
		private int _current = -1;
		private T1[] _items;

		public CircularArrayIterator(CircularArray<T1> circularArray) {
			// TODO Auto-generated constructor stub
			this._items = circularArray.items;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return _current < _items.length - 1;
		}

		@Override
		public T1 next() {
			// TODO Auto-generated method stub
			_current++;
			return _items[convert(_current)];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("...");
			
		}
		
		
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
