import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rq;
    private int N;
    public RandomizedQueue(){
        rq = (Item[]) new Object[2];
        N=0;
    }// construct an empty randomized queue
    public boolean isEmpty(){ return N==0;}            // is the queue empty?
    public int size(){ return N;}// return the number of items on the queue
    public void enqueue(Item item){
        if (item==null) throw new NullPointerException("New item is null");
        if (N==rq.length) resize(2*rq.length);
        rq[N++]=item;
    }// add the item
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Stack Underflow");
        int select_num = StdRandom.uniform(N);
        Item item = rq[select_num];
        for (int i=select_num+1;i<N;i++)
        {
            rq[i-1]=rq[i];
        }
        N--;
        rq[N]=null;
        if (N > 0 && N == rq.length/4) resize(rq.length/2);
        return item;
    }// delete and return a random item
    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException("Stack Underflow");
        int select_num = StdRandom.uniform(N);
        return rq[select_num];
    }                     // return (but do not delete) a random item
    private void resize(int capacity) {    
        Item[] copy = (Item[])new Object[capacity];    
        for (int i = 0; i < N; i++)       
            copy[i] = rq[i];    
        rq = copy; }
    public Iterator<Item> iterator() { return new ArrayIterator(); }
    private class ArrayIterator implements Iterator<Item>    {        
        private int i = N;
        public boolean hasNext() {  return N!= 0; }       
        public void remove()     {  
            throw new UnsupportedOperationException(String.format("Can not remove items with iterator"));/* not supported */  }    
        public Item next()       {  
            if (!hasNext()){
                throw new NoSuchElementException(String.format("No next element"));
            }
            return dequeue();       }    } 
    // return an independent iterator over items in random order
    public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("a");
        rq.enqueue("b");
        rq.enqueue("c");
//        StdOut.println(rq.dequeue());
//        StdOut.println(rq.dequeue());
//        StdOut.println(rq.dequeue());
//        StdOut.println(rq.sample());
//        StdOut.println(rq.sample());
//        StdOut.println(rq.sample());
        for(String i:rq){
            StdOut.println(i);
        }
    }   // unit testing
}