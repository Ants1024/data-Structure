package LinkedList;

import java.util.ArrayList;

/**
 * @program: data-Structure
 * @description: 数据结构-链表(不带虚拟头节点)
 * @author: wanshubin
 * @create: 2019-07-10 21:11
 **/
public class LinkedList<E> {


    private class Node {
        public E e;//存储数据的值
        public Node next;//下各节点的位置

        public Node(E e) {
            this.e = e;
            this.next = null;
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private Node head;//头节点的位置
    private int size;//链的长度


    public LinkedList(E e){
        head = new Node(e);
        size++;
    }

    public LinkedList(){
        head=new Node(null);//虚拟节点
    }


    public void addFirst(E e) {
        //新节点
//      Node node = new Node(e);
//      更新新节点的下一个节点指向
//      node.next=head;
        //替换旧节点
//      head=node;
        head = new Node(e, head);
        size++;
    }

    public void addLast(E e){
        add(size,e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {//链表充许在最尾端添加节点
            throw new IllegalArgumentException(" add failed Illegal index ");
        }
        if (index == 0) {
            addFirst(e);
        } else {//指定位置插入，必须存在一个节点
            Node flag = head;
            for (int i = 0; i < index - 1; i++) {//获取到index前一个元素
                flag = flag.next;
            }
            Node node = new Node(e);
            node.next = flag.next;
            flag.next = node;
        }
        size++;
    }

    public E getNode(int index){
        if(index==0){
            return head.e;
        }
        if (index < 0 || index >=size) {
            throw new IllegalArgumentException(" add failed Illegal index ");
        }
        Node flag=head;
        for (int i = 0; i <index; i++) {
           flag=flag.next;
        }
        return flag.e;
    }

    public void remove(int index){
        if (index < 0 || index >=size) {
            throw new IllegalArgumentException(" add failed Illegal index ");
        }
        if(index==0){
            head = head.next;
            size--;
            return;
        }

        Node flag=head;
        for (int i = 0; i < index-1; i++) {//获取到需要删除节点的上一个节点
            flag=flag.next;
        }
        Node node = flag.next;//需要被删除的节点
        flag.next=node.next;//删除：flag.next指向的是需要删除的节点 所以只需要将 flag.next指向需要删除节点的下一个节点即可

        size--;
    }

    public void removeElementOne(E e){
        if (size==0){
            throw new IllegalArgumentException("LinkedList is null");
        }
        Node flag=head;
        if(head.e.equals(e)){
            head=flag.next;//将当前节点的下一个节点改为删除节点的下一个；
            size --;
            return;
        }
        //通过循环获取删除节点的上一个节点
//        for (int i = 0; i < size-1; i++) {
//            if(flag.next.e.equals(e)){//判断当前节点的下一个是否为需要删除的节点
//                flag.next=flag.next.next;//将当前节点的下一个节点改为删除节点的下一个；
//                size --;
//                return;
//            }
//            flag=flag.next;
//        }
        Node node = recursionElement(e, head);//递归获取指定节点的上一个节点
        if(node.next==null){//意味着不存在需要删除的元素
            return;
        }
        node.next=node.next.next;
        size--;
    }

    public void removeAll(E e){
       removeElements(e, head);
    }

    /**
    * @Description:  递归删除链表中所有值为e的节点
    * @Param:  e：链表中需要删除的值，head ：当前节点的下一个节点
    * @return:  当if语句成立时 返回的是传入节点的下一个节点，否则返回传入节点
    * @Author: wanshubin
    * @Date:
    */
    private Node removeElements(E e,Node head){
        if (head==null){
            return null;
        }
        head.next=removeElements(e,head.next);

        if(head.e.equals(e)){
            size--;
            return head.next;//这返回的是删除节点的下一个节点 head.next.next
        }
        return head;
    }

   
   /** 
   * @Description: 通过递归获取符合条件e的上一个节点 
   * @Param:  
   * @return:  
   * @Author: wanshubin
   * @Date:  
   */  
    private Node recursionElement(E e,Node node){
        //[9,8,7,6,5,4,3,2,1,0]
        if (node.next==null||node.e.equals(e)){
            return node;
        }
        if(node.next.e.equals(e)){
            return node;
        }
        return recursionElement(e,node.next);
    }

    //获取指定元素第一次出现的下标
    public int getFirstElementIndex(E e){
        Node flag=head;
        for (int i = 0; i < size; i++) {
            if(flag.e.equals(e)){
                return i;
            }
            flag=flag.next;
        }
        return  -1;
    }


    //获取指定元素在链中所有的下标
    public Integer[] getElementIndexAll(E e){
        ArrayList<Integer> list = new ArrayList();
        Node flag=head;
        for (int i = 0; i < size; i++) {
            if(flag.e.equals(e)){
                list.add(i);
            }
            flag=flag.next;
        }
        Integer[] array = (Integer[])list.toArray();
        return array;
    }

    public void clear(){
        head=null;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList: front[");
        Node flag=head;
        for (int i = 0; i < size; i++) {
            res.append(flag.e);
            flag=flag.next;
            if(i+1!=size){
                res.append(",");
            }
        }
        return res.toString()+"] tail";
    }

    public static void main (String[] args){
        LinkedList<Integer> linkedList = new LinkedList(0);
        for (int i = 1; i < 1000; i++) {
            linkedList.addFirst(i);
        }
//        for (int i = 1; i < 10; i++) {
//            linkedList.addFirst(i);
//        }
        System.out.println("插入前："+linkedList.toString());
        linkedList.add(5,0);
        System.out.println("插入后："+linkedList.toString());
        linkedList.remove(0);
        System.out.println("删除后："+linkedList.toString());
        System.out.println("获取元素第一次出现的地方："+linkedList.getFirstElementIndex(3));
        System.out.println("根据指定元素删除链中的节点前："+ linkedList.toString());
        linkedList.removeElementOne(9);//根据指定元素删除链中的节点(只删除第一次出现的)
        System.out.println("根据指定元素删除链中的节点后："+ linkedList.toString());
        linkedList.removeAll(9);
        System.out.println("删除链表所有指定元素："+linkedList.toString());
    }
}
