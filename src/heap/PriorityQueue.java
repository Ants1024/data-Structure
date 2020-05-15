package heap;

import queue.Queue;

/**
 * @program: data-Structure
 * @description: 基于堆实现的优先级队列
 * @author: wanshubin
 * @create: 2019-08-16 16:00
 **/
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> heap;

    public PriorityQueue(){
        heap=new MaxHeap<E>();
    }

    public PriorityQueue(int capacity){
        heap=new MaxHeap<E>(capacity);
    }

    @Override
    public void enQueue(E e) {
        heap.add(e);
    }

    @Override
    public E deQueue() {
        return  heap.extractMax();
    }

    @Override
    public E getFront() {
        return heap.findMax();
    }

    @Override
    public int getSize() {
        return heap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return heap.getSize()==0;
    }
}
