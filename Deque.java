import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;
    private class Node{
        Item item;
        Node next;
        Node previous;
    }
    public Deque(){
        first = null;
        last = null;
        size = 0;
    }                           // construct an empty deque
    public boolean isEmpty(){
        return size==0;
    }// is the deque empty?
    public int size(){
        return size;
    }                        // return the number of items on the deque
    public void addFirst(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        first.next = oldfirst;
        
        if (isEmpty())last=first;
        else oldfirst.previous = first;
        size++;
    }// insert the item at the front
    public void addLast(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        last.next = null;
        if (isEmpty()) first=last;
        else oldlast.next = last;
        
        size++;
    }// insert the item at the end
    public Item removeFirst(){
        if (size>1){
            Item item = first.item;
            first.next.previous = null;
            first = first.next;
            size--;
            return item;}
        else if (size==1){
            Item item = first.item;
            first = null;
            last = null;
            size--;
            return item;
        }
        return null;
    }// delete and return the item at the front
    public Item removeLast(){
        if (size>1){
            Item item = last.item;
            last.previous.next = null;
            last = last.previous;
            size--;
            return item;}
        else if (size==1){
            Item item = last.item;
            first = null;
            last = null;
            size--;
            return item;
        }
        return null;
    }// delete and return the item at the end
    public Iterator<Item> iterator(){return new DequeIterator();  }
    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext(){return current!=null;}
        public void remove(){ throw new UnsupportedOperationException(String.format("Can not remove items with iterator"));}
        public Item next(){
            if (!hasNext()){
                throw new NoSuchElementException(String.format("No next element"));
            }
            Item item = current.item;
            current= current.next;
            return item;
            
        }
        
    }
    
    // return an iterator over items in order from front to end
    public static void main(String[] args){
        Deque<String> d = new Deque<String>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-")) d.addFirst(item);
//            else if (!d.isEmpty()) StdOut.print(d.removeLast() + " ");
//        }
        d.addFirst("a");
        d.addFirst("b");
        d.addFirst("c");
        for(String i:d){
            StdOut.println(i.toString());
        }
        StdOut.println("(" + d.size() + " left on Deque)");
    }// unit testing
}