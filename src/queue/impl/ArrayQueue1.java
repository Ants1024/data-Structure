package queue.impl;

import queue.Queue;

/**
 * @program: data-Structure
 * @description: 未优化插入操作的队列
 * @author: wanshubin
 * @create: 2019-07-07 14:11
 **/
public class ArrayQueue1<T> implements Queue<T> {

    private T [] data;
    private  int size;


    public ArrayQueue1(int capacity){
        data = (T[])new Object[capacity];
    }

    public  ArrayQueue1(){
        this(10);
    }

    public int getCapacity(){
        return data.length;
    }

    @Override
    public void enQueue(T e) {
        if(size==data.length){
            //进行扩容
            reSize(2*data.length);
        }
        for (int i=size;i>0;i--){
            data[i]=data[i-1];
        }
        data[0]=e;
        size++;
    }

    @Override
    public T deQueue() {

        if (size==0){
            throw new ArrayIndexOutOfBoundsException("ArrayQueue is null");
        }
        if(size==data.length/4&&data.length/2!=0){
            reSize(data.length/2);
        }
        T t = data[size - 1];
        size--;
        return t;
    }

    @Override
    public T getFront() {
        if (size==0){
            throw new ArrayIndexOutOfBoundsException("ArrayQueue is null");
        }
        T t = data[size - 1];
        return t;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size==0){
            return true;
        }
        return false;
    }


    private void reSize(int capacity){
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < this.size; i++) {
            newData[i]=data[i];
        }
        data=newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue:").append("front [");
        for (int i = 0; i < size; i++) {
            res.append(data[i]).append(",");
        }
        String s = res.toString();
        String s1 = s.substring(0, s.length() - 1);
        return s1+"]";
    }

    public static void main (String[] args){
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 12000; i++) {
            queue.enQueue(i);
        }
        for (int i = 0; i < 10000; i++) {
            System.out.println(queue.deQueue());
        }

        System.out.println(queue.getFront());
        System.out.println(queue.toString());
        System.out.println(queue.getCapacity());

    }
}
