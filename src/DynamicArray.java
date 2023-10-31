import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> {
    //remember, java method signatures consists of the name of the method, and the amount, type, and order of paramenters
    //parameters are the placeholders. its like, asking u to input something. methods take in parameters
    //pass arguments are the things u put into the parameters. the actual input. users pass arguments

    //property
    private T[] dynamicArray;
    private int size;

    //constructors
    public DynamicArray(Class<T> tClass){
        dynamicArray = (T[]) Array.newInstance(tClass, 0);
        this.size = dynamicArray.length;
    }
    public DynamicArray(Class<T> tClass, int capacity){
        dynamicArray = (T[]) Array.newInstance(tClass, capacity);
        this.size = dynamicArray.length;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        if (this.size == 0){
            return true;
        }
        return false;
    }

    public T get(int index){
        if (index >= this.size){
            return null;
            //OutOfBounds error
        }
        return dynamicArray[index];
    }

    public boolean contains(T element){
        for (Object e : dynamicArray){
            if (element.equals(e)){
                return true;
            }
        }
        return false;
    }

    public int indexOf(T element){
        for (int i = 0; i < this.dynamicArray.length; i++){
            if (this.dynamicArray[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    public boolean add(T element){
        Object[] dynamicArray2 = Arrays.copyOf(this.dynamicArray, this.size+1);
        dynamicArray2[this.size] = element;
        resize(this.size+1);
        this.dynamicArray = (T[]) dynamicArray2;
        this.size++;

        return true;
    }

    public void add(int index, T element){
        //index is ALLOWED to == this.size
        if (index > this.size){
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        else {
            Object[] dynamicArray2 = new Object[this.size + 1];
            for (int i = 0; i < index; i++) {
                dynamicArray2[i] = this.dynamicArray[i];
            }
            dynamicArray2[index] = element;
            for (int i = index; i < this.size; i++) {
                dynamicArray2[i + 1] = this.dynamicArray[i];
            }

            resize(this.size + 1);
            this.dynamicArray = (T[]) dynamicArray2;

            this.size++;
        }
    }

    public T set(int index, T element){
        if (index >= dynamicArray.length){
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        else{
            T previousElement = this.dynamicArray[index];
            this.dynamicArray[index] = element;

            return previousElement;
        }
    }

    public T remove(int index){
        if (index >= this.dynamicArray.length){
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        else if (this.size == 1){
            T removedElement = this.dynamicArray[index];
            this.dynamicArray = (T[]) Array.newInstance(this.dynamicArray.getClass().getComponentType(), 0);
            this.size = 0;
            return removedElement;
        }
        else{
            T removedElement = this.dynamicArray[index];

            Object[] dynamicArray2 = new Object[this.size - 1];
            for (int i = 0; i < index; i++) {
                dynamicArray2[i] = this.dynamicArray[i];
            }
            for (int i = index+1; i < this.size; i++) {
                dynamicArray2[i-1] = this.dynamicArray[i];
            }

            resize(this.size - 1);
            this.dynamicArray = (T[]) dynamicArray2;

            this.size--;
            return removedElement;
        }
    }

    public T remove(T element){
        if (contains(element)){
            remove(indexOf(element));
            return element;
            //if removed, returns removed element (technically supposed to return boolean "true")
        }
        return null;
        //if nothing removed, returns null (technically supposed to return boolean "false")
    }

    public T removeAll(T element){
        if(!contains(element)){
            return null;
            //if nothing removed, returns null (technically supposed to return boolean "false")
        }
        else{
            while(contains(element)){
                remove(element);
            }
            return element;
            //if removed, returns removed element (technically supposed to return boolean "true")
        }
    }

    public void clear(){
        this.dynamicArray = (T[]) Array.newInstance(this.dynamicArray.getClass().getComponentType(), 0);
        this.size = 0;
    }

    public void print(){
        if(this.size == 0){
            System.out.println("null");
        }
        else{
            System.out.print("[");
            for (int i = 0; i < this.dynamicArray.length - 1; i++){
                System.out.print(this.dynamicArray[i].toString() + ", ");
            }
            System.out.println(this.dynamicArray[this.size - 1].toString() +"]");
        }
    }

    private void resize(int newCapacity){
        this.dynamicArray = (T[]) Array.newInstance(this.dynamicArray.getClass().getComponentType(), newCapacity);
    }
}