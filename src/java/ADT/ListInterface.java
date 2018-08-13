/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 * ListInterface.java An interface for the ADT list. Entries in the list have
 * positions that begin with 1.
 *
 * @author Frank M. Carrano
 * @version 2.0
 * @param <T>
 */
public interface ListInterface<T> {

  public boolean add(T newEntry);
  public boolean add(int newPosition, T newEntry);
  public T remove(int givenPosition);
  public void clear();
  public boolean replace(int givenPosition, T newEntry);
  public T getEntry(int givenPosition);
  public boolean contains(T anEntry);
  public int getNumberOfEntries();
  public boolean isEmpty();
  public boolean isFull();
}
