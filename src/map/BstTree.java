package map;

import java.util.*;

/**
 * @program: data-Structure
 * @description: 使用二分搜索树实现map(映射数据结构)
 * @author: wanshubin
 * @create: 2019-08-07 10:50
 **/
public class BstTree<K extends Comparable,E > {

    private Node root;
    private int size;

    class Node{
        public E val;
        public K key;
        public Node leftNode;
        public Node rightNode;

        public Node(K key,E val){
            this.key=key;
            this.val=val;
        }
    }


    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void add(K k,E e){
        root=add(k,e,root);
    }

    private Node add(K k,E e,Node node){
        if (node==null){
            size++;
            return new Node(k,e);
        }
        if (k.compareTo(node.key)>0){
            node.rightNode=add(k,e,node.rightNode);
        }else if(k.compareTo(node.key)<0){
            node.leftNode=add(k,e,node.leftNode);
        }
        return node;
    }

    public E get(K k){
        Node node = get(k, root);
        return node!=null? node.val:null;
    }

    private Node get(K k,Node node){
        if (node==null){
            return null;
        }
        if(k.compareTo(node.key)==0){
            return node;
        }else if(k.compareTo(node.key)>0){
           return node=get(k,node.rightNode);
        } else {
            return node=get(k,node.leftNode);
        }
    }
    //是否存在key
    public boolean containsKey(K k){
        Node node = this.get(k, root);
        return node!=null;
    }


    public void remove(K k){
        remove(k,root);
    }

    private Node remove(K k,Node node){
        if (node==null){
            return null;
        }
        if (k.compareTo(node.key)==0){
            if(node.leftNode!=null&&node.rightNode==null){
                Node leftNode = node.leftNode;
                node.leftNode=null;
                size--;
                return leftNode;
            }
            if(node.rightNode!=null&&node.leftNode==null){
                Node rightNode = node.rightNode;
                node.rightNode=null;
                return rightNode;
            }else if(node.rightNode==null&&node.leftNode==null){
                return null;
            }

            //存在双孩子节点需要查找前驱或者后继节点

            //前驱
            Node precursor = maximum(node.leftNode);

            //删除原前驱节点，即左节点中最大节点
            Node removeMaximum = removeMaximum(node.leftNode);

            precursor.rightNode=node.rightNode;
            precursor.leftNode=node.leftNode;
            node.rightNode=null;
            node.leftNode=null;
            return precursor;
        }else if(k.compareTo(node.key)>0){
            node.rightNode=remove(k,node.rightNode);
        }else if(k.compareTo(node.key)<0){
            node.leftNode=remove(k,node.leftNode);
        }
        return node;
    }

    //删除最大元素
    public void removeMaximum(){
        root=removeMaximum(root);
    }

    //返回删除最大元素后的根节点
    private Node removeMaximum(Node node){
        if(node.rightNode==null){
            Node leftNode = node.leftNode;
            node.leftNode=null;
            return leftNode;
        }
        node.rightNode=removeMaximum(node.rightNode);
        return node;
    }


    //删除最小元素
    public void removeMinimum(){
        root=removeMinimum(root);
    }

    //返回删除最小元素后的根节点
    private Node removeMinimum(Node node){
        if(node.leftNode==null){
            Node rightNode = node.rightNode;
            node.rightNode=null;
            return rightNode;

        }
        node.leftNode=removeMaximum(node.leftNode);
        return node;
    }

    //返回最大元素
    public E maximum(){
        return maximum(root).val;
    }
    //返回最大节点
    private Node maximum(Node node){

        if(node.rightNode==null){
            return node;
        }
        return maximum(node.rightNode);

    }
    //返回最小元素
    public E minimum(){
        return minimum(root).val;
    }

    //返回最小节点
    private Node minimum(Node node){
        if (node.leftNode==null){
            return node;
        }
        return minimum(node.leftNode);
    }


    //-------------遍历--------------
    //前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if (node==null){
            return;
        }
        System.out.println(node.val);
        preOrder(node.leftNode);
        preOrder(node.rightNode);
        return;
    }
    //中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node==null){
            return;
        }
        inOrder(node.leftNode);
        System.out.println(node.val);
        inOrder(node.rightNode);
        return;
    }

    //后续遍历
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if (node == null) {
            return;
        }
        postOrder(node.leftNode);
        postOrder(node.rightNode);
        System.out.println(node.val);
    }

    //非递归前序遍历
    public void preOrderNF(){
        Stack<Node> stack = new Stack<>();
        if (root==null){
            return;
        }
        stack.push(root);

        while (!stack.isEmpty()){
            Node node = stack.pop();
            System.out.println(node.val);
            if (node.rightNode!=null){
                stack.push(node.rightNode);
            }
            if (node.leftNode!=null){
                stack.push(node.leftNode);
            }
        }
    }

    //层序遍历
    public void levelOrder(){
        Queue<Node> queue=new LinkedList<>();
        if (root==null){
            return;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.remove();
            System.out.println(node.val);
            if (node.leftNode!=null){
                queue.add(node.leftNode);
            }
            if (node.rightNode!=null){
                queue.add(node.rightNode);
            }
        }
    }

    public Set<K> getKeys(){
        Set<K> set = new TreeSet<>();
        getKeys(set,root);
        return set;
    }

    private void getKeys(Set<K> set,Node node){
        if (node==null){
            return;
        }
        getKeys(set,node.leftNode);
        set.add(node.key);
        getKeys(set,node.rightNode);
    }


    public static void main (String[] args){
        BstTree<Integer,Integer> bstTree = new BstTree<>();
        bstTree.add(10,10);
        bstTree.add(5,5);
        bstTree.add(15,15);
        bstTree.add(2,2);
        bstTree.add(8,8);
        bstTree.add(12,12);
        bstTree.add(18,18);
        //删除最大元素
        //bstTree.removeMaximum();
        Set<Integer> keys = bstTree.getKeys();
        System.out.println("是否存在："+bstTree.containsKey(15));
        System.out.println("返回最大值："+bstTree.maximum());
        System.out.println("返回最小值："+bstTree.minimum());
        //bstTree.remove(9);
//        bstTree.preOrder();
        System.out.println(bstTree.get(5)+":------------");
//        bstTree.preOrderNF();

        bstTree.levelOrder();
//        bstTree.inOrder();
//        bstTree.postOrder();

//        System.out.println(bstTree.removeOuterParentheses("(()())(())(()(()))"));
    }

    public String removeOuterParentheses(String S) {
        int start=0;
        int end=0;

        Stack<String> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char at = S.charAt(i);
            if(at=='('){
                end++;
                stack.push(String.valueOf(at));
            }else{
                end++;
                stack.pop();
                if (stack.isEmpty()){
                    //获得一组基础组合
                    builder.append(S.substring(start+1,end-1));
                    System.out.println(builder.toString());
                    start=end;
                }
            }

        }
        return builder.toString();
    }

}

