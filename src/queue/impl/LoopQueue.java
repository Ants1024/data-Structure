package queue.impl;

import queue.Queue;

/**
 * @program: data-Structure
 * @description: 循环队列   front==tail 表示空队列，（tail+1）%队列长度==front 表示满队需要扩容
 * @author: wanshubin
 * @create: 2019-07-08 20:29
 **/
public class LoopQueue<T> implements Queue<T> {

    private  T data[];

    private int size;

    private int front=0;//队首的下标

    private int tail=0;//队尾的下标

    public LoopQueue(int capacity){
        data=(T[])new Object[capacity+1];
    }

    public LoopQueue(){
        this(10);
    }
    //获取队列总容量
    public int getCapacity(){
        //由于tail==front为空队，导致队列中实际上有一位是不能插入值的
        // 如： 8，9，空，2，3，4，5，6，7；此时tail=2 虽然此下标是空的但是插入数据后 tail就会等于front 导致队列在非空情况下，被判为空
        // 因此用户实际能使用的容量为 capacity-1
        return data.length-1;
    }


    @Override
    public void enQueue(T e) {
        //由于是循环队列 tail有可能会移到数组的前端  如： 8，9，空，2，3，4，5，6，7；此时tail=2 ，front=3 且满队
        if((tail+1)%data.length==front){//取余只能整数除以整数，若除数比被除数大，直接除数就是余数
            //队列满咯，需要扩容
            resize(getCapacity()*2);
        }
        data[tail]=e;
        size++;
        tail=(tail+1)%data.length;//由于是循环队列，需要补全队列前端已出队产生的空余位置
    }

    @Override
    public T deQueue() {
        if(isEmpty()){
            throw new ArrayIndexOutOfBoundsException("LoopQueue is null");
        }
        if(size==data.length/4&&data.length/2!=0){
            resize(getCapacity()/2);
        }
        T e=data[front];
        data[front]=null;
        front=(front+1)%data.length;
        size--;
        return e;
    }

    @Override
    public T getFront() {
        T e=data[front];
        return e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (tail==front){
            return true;
        }
        return false;
    }

    public void resize(int capacity){
        T[] newData = (T[]) new Object[capacity+1];
        for (int i=0;i<size;i++){
            newData[i]=data[(front+i)%data.length];
        }
        front=0;
        tail=size;
        data=newData;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LoopQueue: front[");
        for (int i = front; i!=tail ; i=(i+1)%data.length) {
            builder.append(data[i]).append(",");
        }
        String s = builder.toString();
        s= s.substring(0, s.length() - 1);

        return s+"] tail";
    }

    public static void main (String[] args){
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for (int i=0;i<8;i++){
            loopQueue.enQueue(i);
        }
        System.out.println(loopQueue.deQueue());
        System.out.println(loopQueue.deQueue());
        loopQueue.enQueue(66);
        loopQueue.enQueue(77);
        loopQueue.enQueue(88);
        loopQueue.enQueue(99);
        System.out.println(loopQueue.deQueue());
        System.out.println(loopQueue.deQueue());
        System.out.println(loopQueue.toString());
        System.out.println("capacity:"+loopQueue.getCapacity());
        for (int i=100;i<120;i++){
            loopQueue.enQueue(i);
        }
        System.out.println("capacity:"+loopQueue.getCapacity());
        System.out.println("size:"+loopQueue.getSize());
        System.out.println(loopQueue.toString());

    }
}
