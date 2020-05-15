package queue.impl;

import queue.Queue;

/**
 * @program: data-Structure
 * @description:
 * @author: wanshubin
 * @create: 2019-07-08 21:07
 **/
public class Main {
    public static void main (String[] args){

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("arrayQueue:"+testQueue(arrayQueue,10000000));
        //System.out.println("loopQueue:"+testQueue(loopQueue,10000000));

    }
    private static Double testQueue(Queue<Integer> queue, int openCount){

        long startTm=System.nanoTime();
        for (int i=0;i<openCount;i++)
            queue.enQueue(i);
        for (int i=0;i<openCount;i++)
            queue.deQueue();
        long endTm=System.nanoTime();

        return (endTm-startTm)/1000000000.0;
    }


}
