/***
 * Created by Ellen Veomett, using code from David Galles
 * for CS 245
 * A good deal of this code is from:
 * https://www.cs.usfca.edu/~galles/cs245/lecture/LinkedList.java.html
 * Note: right now we don't use the List interface or the ListIterator
 */

public class LinkedList {
    public static void main(String[] args) {
        /***
         * The code below gives you a sample of creating and printing a linked list.
         * See the other parts of this class for the full class code
         */
        LinkedList myLL = new LinkedList();
        myLL.add(3);
        myLL.add(0);
        myLL.add(-2);
        myLL.print();
        myLL.reverseList();
        myLL.print();

        /***
         * The code below is simply to highlight for you the fact that the LinkedList
         * class allows for different objects.
         */
        LinkedList stringLL = new LinkedList();
        stringLL.add("hello");
        stringLL.add("world");
        stringLL.print();
        stringLL.reverseList();
        stringLL.print();

    }

    /*----------------------------------------------------- */
    /* Private Data Members -- LinkedList */
    /*----------------------------------------------------- */

    private Link head;
    private Link tail;
    private int length;

    /*----------------------------------------------------- */
    /* Constructor -- LinkedList */
    /*----------------------------------------------------- */

    LinkedList() {
        head = tail = new Link();
        length = 0;
    }

    public void reverseList() {
        /***
         * THIS IS THE reverseList CODE THAT YOU WILL BE EDITING
         * DO NOT EDIT ANY OTHER CODE EXCEPT FOR THIS METHOD, PLUS THE MAIN METHOD
         * Note: the code below is not what you want to keep. What you
         * *do* want to keep is that you reverse the linked list, and make a new head
         * which points to the first element of the reversed linked list
         */
        
        
    
        Link newHead = new Link(); // new node that will point to the first element in the reversed linked list
        Link current = head.next(); // Start with the first element of the original list
        Link previous = null;
        tail = current; // Set the tail to the first element of the original list
    
        while (current != null) {
            Link next = current.next(); // next = current.next 
            current.setNext(previous); // Reverse the current node's pointer to the previous node 
            previous = current; // Move the previous pointer to the current node
            current = next; //current = next node 
        }
    
        newHead.setNext(previous); // Set the new head's next to the reversed list
        head = newHead; // Update the head to point to the first element of the reversed list
        
    }
    /*----------------------------------------------------- */
    /* Public Methods -- LinkedList */
    /*----------------------------------------------------- */

    public void clear() {
        head.setNext(null);
        tail = head;
        length = 0;
    }

    public int size() {
        return length;
    }

    public void add(Object elem) {
        tail.setNext(new Link(elem, null));
        tail = tail.next();
        length++;
    }

    public void add(int index, Object elem) {
        assert (index >= 0 && index <= length) : "Index not in list";
        Link tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        tmp.next = new Link(elem, tmp.next);
        length++;
    }

    public void remove(int index) {
        assert (index >= 0 && index < length) : "Index not in list";
        Link tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        tmp.next = tmp.next.next;
        length--;
    }

    public void remove(Object elem) {
        Link tmp = head;
        while (tmp.next != null && !tmp.next.element.equals(elem)) {
            tmp = tmp.next;
        }
        if (tmp.next != null) {
            tmp.next = tmp.next.next;
            length--;
        }

    }

    public Object get(int index) {
        assert (index >= 0 && index < length) : "Index not in list";
        Link tmp = head.next;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.element;
    }

    public void print() {
        Link curLink = head;
        while (curLink.next != null) {
            curLink = curLink.next;
            System.out.print(curLink.element + " ");
        }
         System.out.print("\n");
    }

    /*----------------------------------------------------- */
    /* Nested class -- Link */
    /*----------------------------------------------------- */

    private class Link {

        /*----------------------------------------------------- */
        /* Private Data Members -- Link */
        /*----------------------------------------------------- */

        private Object element;
        private Link next;

        /*----------------------------------------------------- */
        /* Constructors -- Link */
        /*----------------------------------------------------- */

        Link(Object elem, Link nextelem) {
            element = elem;
            next = nextelem;
        }

        Link(Object elem) {
            element = elem;
        }

        Link() {
        }

        /*----------------------------------------------------- */
        /* Access Methods -- Link */
        /*----------------------------------------------------- */

        Link next() {
            return next;
        }

        Object element() {
            return element;
        }

        void setNext(Link nextelem) {
            next = nextelem;
        }

        void setElement(Object elem) {
            element = elem;
        }
    }

}
