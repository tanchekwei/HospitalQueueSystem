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
public class LinkedList<T> implements LinkedListInterface<T> {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedList() {
        firstNode = null;
        numberOfEntries = 0;
    }

    private Node getFirstNode() {
        return firstNode;
    }
    
    public boolean sortAsc() {
        if(getFirstNode() != null) {
            for(int j = 0; j < size(); j++) {
                Node currNode = getFirstNode();
                for(int i = 0; i < size(); i++) {
                    if(currNode.next == null) {
                        break;
                    }
                    if(currNode.data.hashCode() > currNode.next.data.hashCode()) {
                        exchange(i, i + 1);
                    }
                    currNode = currNode.next;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean sortDesc() {
        if(getFirstNode() != null) {
            for(int j = 0; j < size(); j++) {
                Node currNode = getFirstNode();
                for(int i = 0; i < size(); i++) {
                    if(currNode.next == null) {
                        break;
                    }
                    if(currNode.data.hashCode() < currNode.next.data.hashCode()) {
                        exchange(i, i + 1);
                    }
                    currNode = currNode.next;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean exchange(int index1, int index2) {
        if(isEmpty()) {
            return false;
        } else if (index1 >= numberOfEntries || index2 >= numberOfEntries) {
            return false;
        } else if (index1 == index2) {
            return false;
        } else {
            Node currentNode = null;
            T data1 = null;
            T data2 = null;
            
            currentNode = firstNode;
            for(int i = 0; i < numberOfEntries; i++) {
                if (i == index1) {
                    data1 = currentNode.data;
                }
                if (i == index2) {
                    data2 = currentNode.data;
                }
                currentNode = currentNode.next;
            }
            
            currentNode = firstNode;
            for(int i = 0; i < numberOfEntries; i++) {
                if (i == index1) {
                    currentNode.data = data2;
                }
                if (i == index2) {
                    currentNode.data = data1;
                }
                currentNode = currentNode.next;
            }
        }
        return true;
    }
    
    public boolean append(LinkedListInterface secondLinkedList) {
        // Function : This will append the main linked list with the second linked list that passed in.
        //            Return false if both are empty linkedlist.
        LinkedList secondLL = (LinkedList) secondLinkedList;
        if (isEmpty() || secondLL.isEmpty()) {
            return false;
        } else {
            Node currentNode = firstNode;
            while (currentNode.next != null) {// Loop until point to last node.
                currentNode = currentNode.next;
            }
            Node secondFirstNode = secondLL.getFirstNode();// Load second linked list's first node to link later.
            currentNode.next = secondFirstNode;// Linked list 1's last node's tail point to linked list 2's first node.
            numberOfEntries += secondLL.size();// Increase linked list 1's number of entries by adding on second linked list number of entries.
        }
        return true;
    }

    /*
    public boolean append(LinkedListInterface secondLinkedList, int index) {
        // Function : This will append the main linked list with the second linked list that passed in.
        //            Return false if both are empty linkedlist.
        LinkedList secondLL = (LinkedList) secondLinkedList;
        if(isEmpty() || secondLL.isEmpty()) {
            return false;
        } else {
            Node currentNode = firstNode;
            Node nextNode = null;
            for(int i = 0; i < index - 1 && currentNode.next != null; i++) {// Loop until point to linked list 1's last node.
                currentNode = currentNode.next;
            }
            nextNode = currentNode.next;// Store linked list 3's first node.
            
            // Link linked list 1 with linked list 2 (which passed in to this function).
            Node secondFirstNode = secondLL.getFirstNode();// Load second linked list's first node to link later.
            currentNode.next = secondFirstNode;// Linked list 1's last node's tail point to linked list 2's first node.
            
            // Link linked list 2 witch linked list 3 (which cut in by linked list 2).
            while(currentNode.next != null) {// Loop until last node of linked list 1.
                currentNode = currentNode.next;
            }
            currentNode.next = nextNode;// Link linked list 1 with linked list 3.
            
            numberOfEntries += secondLL.size();// Increase linked list 1's number of entries by adding on second linked list number of entries.
        }
        return true;
    }
     */
    public boolean add(T newElement, int index) {
        // Function : Add new node at selected index.
        Node newNode = new Node(newElement);
        if (isEmpty() && index == 0) {// Only allow add the new node when front index and back index equals to zero and linked list is empty.
            firstNode = newNode;

            numberOfEntries++;
        } else if (index > numberOfEntries) {// When index is greater than size of linked list.
            return false;
        } else if (index == 0) {// When index equals to zero, call function addFirst to add at frontmost.
            addFirst(newElement);
        } else if (index == numberOfEntries) {// When index equals to number of entries, call function addLast to add at back.
            addLast(newElement);
        } else {// Perform cut in function if don't meet any condition above.
            Node currentNode = firstNode;
            // Loop to search the selected index.
            // Break before point to selected index
            // 
            for (int i = 0; i != index - 1; i++) {// Hidden: i < numberOfEntries && 
                currentNode = currentNode.next;
            }
            Node nextNode = currentNode.next;// Store second node.
            currentNode.next = newNode;// Point current node's tail to new node.
            currentNode.next.next = nextNode;// Point new node's tail to original second node.

            numberOfEntries++;// Increase number of entries.
        }

        return true;
    }

    public boolean addFirst(T newElement) {
        // Function : Add new element at the front of link list.
        Node newNode = new Node(newElement);
        if (isEmpty()) {
            firstNode = newNode;// First node equals to new node and the tail point to null.
        } else {
            Node secondNode = firstNode;// Assign original first node to second node.
            firstNode = newNode;        // Point first node to new node.
            firstNode.next = secondNode;// Point first node's tail to second node.
        }

        numberOfEntries++;// Increase number of entries.

        return true;
    }

    public boolean addLast(T newElement) {
        // Function : Add new element at the end of linked list.
        Node newNode = new Node(newElement);

        if (isEmpty()) {
            firstNode = newNode;// First node equals to new node and the tail point to null.
        } else {
            Node currentNode = firstNode;// Assign first node to current node.
            while (currentNode.next != null) {// Search until the end of the linked list.
                currentNode = currentNode.next;// Move current pointer to next node.
            }
            currentNode.next = newNode;// The end of linked list point to new node.
        }

        numberOfEntries++;// Increase number of entries.

        return true;
    }

    public boolean remove(int index) {
        // Function : Remove selected index node.
        if (isEmpty()) {
            return false;// Return false if the linked list is empty.
        } else if (index >= numberOfEntries) {// When index is greater than or equals to size of linked list.
            return false;
        } else if (index == 0) {// When index equals to zero, call function removeFirst to add at frontmost.
            removeFirst();
        } else if (index == numberOfEntries - 1) {// When index equals to last index, call function removeLast to add at back.
            removeLast();
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i != index - 1; i++) {// Loop before selected index.
                currentNode = currentNode.next;
            }
            Node thridNode = currentNode.next.next;// Store thrid node.
            currentNode.next.data = null;// Clear second node's / selected node's data.
            currentNode.next.next = null;// Clear second node's / selected node's tail.
            currentNode.next = null;// Clear first node's tail.
            currentNode.next = thridNode;// Point first node's tail to thrid node.

            numberOfEntries--;// Decrease number of entries.
        }

        return true;
    }

    public boolean remove(T element) {
        if (isEmpty()) {
            return false;// Return false if the linked list is empty.
        } else {
            Node currentNode = firstNode;// Pointer
            int elementIndex = 0;// Use for remove specific index node.
            // Loop until meet same element.
            while (currentNode.next != null && currentNode.data.equals(element) == false && elementIndex != numberOfEntries) {
                currentNode = currentNode.next;
                elementIndex++;
            }
            if (currentNode.data.equals(element)) {
                remove(elementIndex);// Decrease number of entries.
            } else {
                return false;
            }
        }

        return true;
    }

    public boolean removeFirst() {
        // Function : Remove the first node of linked list. Return false if linked list is empty.
        if (isEmpty()) {
            return false;// Return false if the linked list is empty.
        } else {
            firstNode.data = null;// Clear first node data.
            firstNode = firstNode.next;// First node point to next node.

            numberOfEntries--;// Decrease number of entries.
        }

        return true;
    }

    public boolean removeLast() {
        // Function : Remove the last node of linked list. Return false if linked list is empty.
        if (isEmpty()) {
            return false;// Return false if the linked list is empty.
        } else {
            if (numberOfEntries != 1) {// Remove the last node if number of entries not equals to one.
                Node currentNode = firstNode;
                int entriesCount = 0;
                // Loop until pointer point to last second node.
                while (currentNode.next != null && entriesCount < numberOfEntries - 2) {
                    currentNode = currentNode.next;
                    entriesCount++;
                }
                currentNode.next.data = null;// Remove last node's data.
                currentNode.next.next = null;// Remove last node's tail.
                currentNode.next = null;// Remove last second node's pointer.
            } else {// The first node equals to null since the first node is last node.
                firstNode = null;
            }
            numberOfEntries--;// Decrease the number of entries.
        }
        return true;
    }

    public boolean removeAll() {
        // Function : Remove all data and tail in the linked list. Return false if linked list is empty.
        boolean successRemoveAll = false;
        if (isEmpty()) {
            successRemoveAll = false;
        } else {
            //Node currentNode = firstNode;// Pointer point to current node.// Hide because replaced to first node
            Node nextNode = null;// Pointer point to next node.
            while (numberOfEntries > 0) {// Loop until the number of entries equals to zero.
                nextNode = firstNode.next;// Store next node address to nextNode.
                //firstNode.data = null;// Clear current node's data.
                //firstNode.next = null;// Clear current node's tail.
                firstNode = null;// Point current node to null.
                firstNode = nextNode;// Point current node to next node.
                numberOfEntries--;// Decrease number of entries.
            }
            //firstNode = currentNode;// Store final result (Cleared linked list) to first node.
            successRemoveAll = true;
        }

        return successRemoveAll;
    }

    public boolean replace(T newElement, int index) {
        // Function : Replace the selected index's node to new node. Return true if success.
        if (isEmpty()) {
            return false;
        } else if (index >= numberOfEntries) {
            return false;
        } else {
            Node newNode = new Node(newElement);
            remove(index);// Remove specific index's node.
            add(newElement, index);// Cut in specific index's node.
        }

        return true;
    }

    public T get(int index) {
        // Function : Peek data at specific index. Return null if invalid index or empty linked list.
        T selectedData = null;
        if (isEmpty()) {
            // Do nothing.
        } else if (index >= numberOfEntries) {
            // Do nothing.
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index; i++) {// Loop until point to selected index's node.
                currentNode = currentNode.next;
            }
            selectedData = currentNode.data;// Store selected node's data.
        }
        return selectedData;
    }

    public T peekFirst() {
        // Function : Peek the first node's data if it existed. Return null if linked list is empty.
        T firstData = null;
        if (isEmpty()) {
            // Do nothing.
        } else {
            firstData = firstNode.data;
        }

        return firstData;
    }

    public T peekLast() {
        // Function : Peek the last node's data if it existed. Return null if linked list is empty.
        T lastData = null;
        if (isEmpty()) {
            // Do nothing.
        } else {
            // Loop until the pointer point to last node.
            Node currentNode = firstNode;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            lastData = currentNode.data;// Assign last node data to return variable.
        }

        return lastData;
    }

    public T poll(int index) {
        T selectedData = null;
        if (isEmpty()) {
            // Do nothing.
        } else if (index >= numberOfEntries) {
            // Do nothing.
        } else {
            selectedData = get(index);// Get specific index's data.
            remove(index);// Remove the specific index's node.
        }
        return selectedData;
    }

    public T pollFirst() {
        // Function : Remove and retrieve the first node data's if it existed. Return null if linked list is empty.
        T firstData = null;
        if (isEmpty()) {
            // Do nothing.
        } else {
            firstData = firstNode.data;// Store first node's data for return.
            if (firstNode.next != null) {
                firstNode.data = null;// Clear first node data.
                firstNode = firstNode.next;// First node point to second node.
            }

            numberOfEntries--;// Decrease number of entries.
        }

        return firstData;
    }

    public T pollLast() {
        // Function : Remove and retrieve the last node's data if it existed. Return null if linked list is empty.
        T lastData = null;
        if (isEmpty()) {
            // Do nothing.
        } else {
            if (firstNode.next != null) {// Poll when number of entries greater than one.
                Node currentNode = firstNode;
                int entriesCount = 0;
                while (currentNode.next != null && entriesCount < numberOfEntries - 2) {
                    currentNode = currentNode.next;
                    entriesCount++;
                }
                lastData = currentNode.next.data;
                currentNode.next.data = null;// Remove last node's data.
                currentNode.next.next = null;// Remove last node's tail.
                currentNode.next = null;// Remove last second node's pointer.
            } else {// Poll when number of entries equals to one.
                lastData = firstNode.data;// Store first node's data.
                firstNode.data = null;// Remove first node's data.
                firstNode.next = null;// Remove first node's tail.
                firstNode = null;// Point first node to null.
            }

            numberOfEntries--;// Decrease number of entries.
        }
        return lastData;
    }

    public boolean contains(T element) {
        // Function : If element passed in existed, return true.
        boolean foundFlag = false;
        if (isEmpty()) {
            foundFlag = false;
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < numberOfEntries; i++) {
                if (currentNode.data.equals(element)) {
                    foundFlag = true;
                    break;
                }
                currentNode = currentNode.next;
            }
        }

        return foundFlag;
    }

    public int size() {
        // Function : Return the size of linked list.
        return numberOfEntries;// Number of entries equals to size of linked list.
    }

    public boolean isEmpty() {
        // Function : Return the status of linked list to determine is it empty.
        return numberOfEntries == 0;// Return true when linked list is empty or number of entries is zero.
    }

    public String toString() {
        // Function : Return the content of linked list with a string in a line seperate by ' / '.
        String outputStr = "";
        // Start combine the node together if linked list is not empty.
        Node currentNode = firstNode;
        if (currentNode != null) {
            // Combine the data until last second node with seperator ' / '.
            while (currentNode.next != null) {
                outputStr += currentNode.data + " / ";
                currentNode = currentNode.next;
            }
            outputStr += currentNode.data;// Combine the last node without seperator ' / '.
        }
        return outputStr;
    }

    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
