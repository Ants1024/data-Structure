package stack.impl;

import stack.Stack;

/**
 * @program: data-Structure
 * @description: 已链表为基础实现的栈
 * @author: wanshubin
 * @create: 2019-07-14 09:37
 **/
public class LinkedStack<E> implements Stack<E> {

    private Node dummyHead=new Node(null);//虚拟头节点
    private int size;

    private class Node{
        public E e;
        public Node next;

        public Node(E e){
            this.e=e;
        }
        public Node(E e,Node node){
            this.e=e;
            this.next=node;
        }
    }

    @Override
    public void push(E e) {
//        Node node = new Node(e);
//        Node next = dummyHead.next;
//        next.next=node;
//        dummyHead.next=next;
        dummyHead.next=new Node(e,dummyHead.next);
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException(" Stack is null ");
        }
        E e = dummyHead.next.e;
        dummyHead.next=dummyHead.next.next;
        size--;
        return e;
    }

    @Override
    public E peek() {
        E e = dummyHead.next.e;
        return e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Boolean isEmpty() {
        if(size==0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack front [");
        Node flag=dummyHead.next;//跳过虚拟头节点
        for (int i = 0; i < size; i++) {
            res.append(flag.e);
            if (i+1!=size){
                res.append(",");
            }
            flag=flag.next;
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main (String[] args){
        LinkedStack<Integer> stack = new LinkedStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        System.out.println("原数据："+stack.toString());
        System.out.println("出栈："+stack.pop());
        System.out.println("出栈后"+stack.toString());
        System.out.println("查看栈顶元素："+stack.peek());
    }
}
