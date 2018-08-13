/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author JT
 */
public interface LinkedListInterface<T> {
    public boolean sortDesc();
    public boolean sortAsc();
    
    public boolean exchange(int index1, int index2);
    
    public boolean append(LinkedListInterface secondLinkedList);
    //public boolean append(LinkedListInterface secondLinkedList, int index);// Canceled due to the second linked list's tail will affect both linked list.
    
    public boolean add(T newElement, int index);
    public boolean addFirst(T newElement);
    public boolean addLast(T newElement);
    
    public boolean remove(int index);
    public boolean remove(T element);
    public boolean removeFirst();
    public boolean removeLast();
    public boolean removeAll();
    
    public boolean replace(T newElement, int index);
    
    public T get(int index);
    
    public T peekFirst();
    public T peekLast();
    
    public T poll(int index);
    //public T poll(T element);// Maybe no need use due to pass data return same data. Duplicate function with remove().
    public T pollFirst();
    public T pollLast();
    
    public boolean contains(T element);
    
    public int size();
    public boolean isEmpty();
}
