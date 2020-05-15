package queue.impl;

import queue.Queue;

/**
 * @program: data-Structure
 * @description: 队列，优化出队需要将队列中所有元素向前移一位的操作；  时间复复杂度O(n)---->O(1),
 * @author: wanshubin
 * @create: 2019-07-07 14:11
 **/
public class ArrayQueue<T> implements Queue<T> {

    private T [] data;
    private  int size;

    private int front=0;//队首元素的下标

    private int tail=0;//队尾元素的下标

    public ArrayQueue(int capacity){
         data = (T[])new Object[capacity];
    }

    public  ArrayQueue(){
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
        data[tail]=e;
        tail++;
        size++;
    }

    @Override
    public T deQueue() {

        if (size==0){
            throw new ArrayIndexOutOfBoundsException("ArrayQueue is null");
        }
        if(size==data.length/4&&data.length/2!=0){
            reSize(data.length/2);
            //缩容后需要将队首的下标恢复为0。
            front=0;
            //缩容后所有元素都先前移动size个位置，因此需要将tail的位置下标恢复为新队列的队尾
            tail=size;
        }
        T t = data[front];
        data[front]=null;
        front++;
        size--;
        return t;
    }

    @Override
    public T getFront() {
        if (size==0){
            throw new ArrayIndexOutOfBoundsException("ArrayQueue is null");
        }
        T t = data[front];
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
            //不能从0开始，因为front才是记录当前队列队首位置的下标对象，front之前的的元素其实是已被移除了的元素；
            newData[i]=data[front+i];
        }
        data=newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue:").append("front [");
        for (int i = 0; i < size; i++) {
            res.append(data[front+i]).append(",");
        }
        String s = res.toString();
        String s1 = s.substring(0, s.length() - 1);
        return s1+"]";
    }

    public static void main (String[] args){
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 12; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue.toString());
        for (int i = 0; i < 10; i++) {
            queue.deQueue();
        }
        queue.enQueue(99);
        System.out.println("当前队首元素："+queue.getFront());
        System.out.println("移除队首元素："+queue.deQueue());
        System.out.println("队首中剩余的元素："+queue.toString());
        System.out.println("队列的总容量："+queue.getCapacity());

    }
}
