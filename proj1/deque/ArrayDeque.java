package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8]; // Cast is necessary due to generic array creation
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }
    public void resize(int capacity)
    {
        T[] resizedArray = (T[]) new Object[capacity];
        int nextStart = (nextFirst + 1) % items.length; //start from the first element
        for(int i = 0; i < size; i++){ //size rather than length, because only the elements are in considered
            resizedArray[i] = items[(nextStart + i) % items.length];
        }
        items = resizedArray;
//        size = capacity;  can be omitted, due to the rewrite
        nextFirst = capacity - 1;   //start from the behind
        nextLast = size;    // se nextLast to the current size;
    }
    public void addFirst(T item){
        if(size == items.length){
            resize((items.length) * 2);
        items[nextFirst] = item;
        size ++;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        // resize? or size == items.length?
//        if(nextFirst == nextLast){
//            resize(); // call the resize when the array is full, rather than just 2 next equals
        }
    }
    public void addLast(T item){
        if(size == items.length){
            resize(items.length * 2);
        }
        items[nextLast] = item;
        size ++;
        nextLast = (nextLast + 1 - items.length) % items.length;
    }
    public boolean isEmpty(){
        if(size == 0)
            return  true;
        else
            return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for(int i = 0; i < size; i++){   //still wrong! print all the elements
            System.out.print(items[(nextFirst + 1 + i)% items.length] + " ");
        }
        System.out.println();
    }
    public T removeFirst(){
        if(isEmpty())   return null;
        nextFirst = (nextFirst + 1 - items.length) % items.length;
        T temp = items[nextFirst];
        items[nextFirst] = null;
        size --;
        if(items.length >= 16 && size < items.length/4){
            resize(items.length/2);
        }
        return temp;
    }
    public T removeLast(){
        if(isEmpty())   return null;
        nextLast = (nextLast - 1 + items.length) % items.length;
        T temp = items[nextLast];
        items[nextLast] = null;
        size --;
        if(items.length >= 16 && size < items.length/4){
            resize(items.length/2);
        }
        return temp;
    }
    public T get(int index){
        if(index < 0 || index >= size) return null;
        int actual = (nextFirst + 1 + index) % items.length;
        return items[actual];
    }
}
