package ua.kture.peresunko.Lab3;

import java.util.Comparator;

public interface MyList<E> {
	
	public boolean addAtTheTop(E e);
	public boolean addAtTheEnd(E e);
	public void add(E e, int index);
	public void clear();
	boolean removeAtTheTop();
	boolean removeAtTheEnd();
	void remove(int index);
	public Object[] toArray();
	public int size();
	public boolean contains(E e);
	public void sort(Comparator<E> comparator);
	
}
