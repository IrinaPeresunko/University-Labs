package ua.kture.peresunko.Lab3;

import java.util.Comparator;
import java.util.Iterator;

public interface MyList<E> {
	
	public void add(E e, int index);
	public void clear();
	void remove(int index);
	public Object[] toArray();
	public int size();
	public boolean contains(E e);
	public Iterator<E> iterator();
	public void sort(Comparator<E> comparator);
	
}
