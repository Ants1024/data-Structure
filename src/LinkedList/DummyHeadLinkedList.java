package LinkedList;

/**
 * @program: data-Structure
 * @description: 数据结构-链表(带虚拟头尾节点的链表)
 * @author: wanshubin
 * @create: 2019-07-12 15:27
 **/
public class DummyHeadLinkedList<E> {

    private class Node {
        public E data;
        public Node next;//用于存储下一个节点的位置

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node node) {
            this.data = data;
            this.next = node;
        }
    }

    private Node dummyHead = new Node(null, null);
    private Node tailIndex=dummyHead;//保存链表的尾节点的内存位置，避免每次从尾部插入节点需要从头部遍历至尾部
    private int size=0;

    public DummyHeadLinkedList(E data) {
        Node node = new Node(data);
        tailIndex.next=node;
        tailIndex=node;
        size++;
    }

    public DummyHeadLinkedList() {

    }

    public void addFirst(E data) {
        add(0,data);
    }

    public void addLast(E data) {
        add(size,data);
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed Illegal index ");
        }
        if(size==index){
            Node node = new Node(data);
            tailIndex.next=node;//tailIndex 存储的是尾部节点的内存地址
            tailIndex=node;//新尾部内存地址=插入元素的内存地址
            size++;
            return;
        }
        Node flag = dummyHead;
        for (int i = 0; i < index; i++) {//获取插入下标的前一个节点,由于存在一个虚拟的头节点，因此index就是插入位置的前一个节点
            flag = flag.next;
        }
//        Node node = new Node(data);
//        node.next=flag.next;
//        flag.next=node;
        flag.next = new Node(data, flag.next);

        size++;
    }

    public E removeLast(){
        //size=指向的是最后一个节点的下一个节点，即空节点
        return remove(size-1);
    }

    public E remove(int index){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed Illegal index ");
        }
        Node flag=dummyHead;
        //由于链表的实际长度是size+1(存在一个虚拟头节点)，因此循环后取出来的即是删除节点的前一个节点
        for (int i = 0; i < index; i++) {
            flag=flag.next;
        }
        //将删除节点的上一节点的next指向删除节点的next节点
        E e=flag.next.data;
        flag.next=flag.next.next;

        size--;
        return e;
    }
    /**
    * @Description: 通过递归获取符合条件e的上一个节点
    * @Param:
    * @return: 如果e不存在则返回 null
    * @Author: wanshubin
    * @Date:
    */
    private Node recursionElement(E e,Node node){

        if(node.next==null||node.data.equals(e)){
            return node;
        }
        if(node.next.data.equals(e)){
            return node;
        }
        return recursionElement(e,node.next);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Linked front [");
        Node flag = dummyHead.next;
        for (int i = 0; i < size; i++) {
            res.append(flag.data);
            flag = flag.next;
            if (i + 1 != size) {
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        DummyHeadLinkedList<Integer> linkedList = new DummyHeadLinkedList<Integer>(88);
        for (int i = 0; i < 10; i++) {
            linkedList.add(i, i);
        }
        System.out.println("原数据："+linkedList.toString());
        linkedList.addFirst(99);
        System.out.println("头部位置插入后："+linkedList.toString());
        linkedList.addLast(10);
        System.out.println("尾部位置插入后："+linkedList.toString());
        System.out.println("删除的节点值"+linkedList.remove(0));
        System.out.println("删除节点后："+linkedList.toString());
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(linkedList.sumArray(ints,0));

    }
    //0 1 2 3 4   0+1 + 2+3  +4+0
    public int sumArray(int[] array,int index){
        if(index==array.length){
            return  0;
        }
        int i = array[index] + sumArray(array, index + 1);
        return i;
    }

}
