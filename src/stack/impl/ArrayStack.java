package stack.impl;

import stack.Stack;

/**
 * @program: data-Structure
 * @description: 栈：数据结构是先进后出
 * @author: wanshubin
 * @create: 2019-07-06 11:02
 **/
public class ArrayStack<T> implements Stack<T> {

    private T data[];

    private int size;


    public ArrayStack(int capacity) {
        data = (T[]) new Object[capacity];
    }

    public ArrayStack() {
        this(10);
    }

    public int getCapacity() {//获取栈的总容量
        return data.length;
    }


    @Override
    public void push(T e) {
        //判断栈是否已满
        if (size == data.length) {
            //进行扩容
            reSize(2 * data.length);
        }
        //压入栈顶
        data[size] = e;
        size++;
    }

    @Override
    public T pop() {//获取并移除栈的最后一个元素
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack is null");
        }
        //当实际使用的容量低于总容量的1/4时进行缩容，为了防止时间复杂度震荡
        if (size <= data.length / 4 && data.length / 2 != 0) {
            reSize(data.length / 2);
        }
        T t = data[size - 1];
        data[size - 1] = null;
        //移除栈顶元素
        size--;
        return t;

    }

    @Override
    public T peek() {//获取栈的最后应该元素
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack is null");
        }
        return data[size - 1];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void reSize(int capacity) {
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack:").append("[");
        for (T datum : data) {
            res.append(datum).append(",");
        }
        String s = res.toString();
        String stack = s.substring(0, s.length() - 1) + "] top";
        return stack;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.pop();
        System.out.println(stack.toString());
        System.out.println("isEmpty:"+stack.isEmpty());
        System.out.println("peek:"+stack.peek());
        System.out.println("Size:"+stack.getSize());

    }
}
