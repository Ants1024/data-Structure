package bst;

import java.util.*;

/**
 * @program: data-Structure
 * @description: 二分搜索树 : 二分搜索树每个节点的值都大于左子树的所有节点，小于右子树的所有节点；
 * @author: wanshubin
 * @create: 2019-07-21 10:34
 **/
public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        E e;
        Node left, right;

        public Node(E e) {
            this.e = e;
        }

    }

    private Node root;//根节点
    private int size;//节点数量


    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
//       -------------使用循环添加元素----------------
//        if (root == null) {
//            root = new Node(e);
//            size++;
//            return;
//        }
//        Node flag = root;
//        for (int i = 0; i < size; i++) {
//            if (e.compareTo(flag.e) < 0) {
//                //e<flag.e 小于往左
//                if (flag.left == null) {
//                    flag.left = new Node(e);
//                    size++;
//                    return;
//                }
//                flag = flag.left;
//                continue;
//            } else if (e.compareTo(flag.e) > 0) {
//                //e>flag.e 大于往右插
//                if (flag.right == null) {
//                    flag.right = new Node(e);
//                    size++;
//                    return;
//                }
//                flag = flag.right;
//                continue;
//            } else {
//                return;
//            }
//        }
//     -------------使用循环添加元素----------------
//        ---------未优化的递归算法
//        if(root==null){
//            root= new Node(e);
//            size++;
//            return;
//        }
//        add(root,e);
//     -------------未优化的递归算法-----------------
//     -------------优化后的递归算法-----------------
        root = add2(root, e);
//     -------------优化后的递归算法-----------------
    }

    //递归算法添加元素
    private void add(Node node, E e) {

        if (e.compareTo(node.e) < 0) {
            //e<node.e 小于往左
            if (node.left == null) {
                node.left = new Node(e);
                size++;
                return;
            }
            add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            //e>node.e 大于往右插
            if (node.right == null) {
                node.right = new Node(e);
                size++;
                return;
            }
            add(node.right, e);
        } else {//相等 不进行操作
            return;
        }
    }

    //优化后的递归算法添加元素
    // 核心思想：可以将null也看成 node的子节点，
    // 因此只需要去判断 e是向左插入还是右插入，当递归到的节点==null 只需添加节点即可，无需再次判断e与node.e的关系
    private Node add2(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {//向左插入
            node.left = add2(node.left, e);
        } else if (e.compareTo(node.e) > 0) {//向右插入
            node.right = add2(node.right, e);
        }//等于情况不做处理

        return node;
    }

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);

    }

    private void preOrderNF() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.e);
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

    }

    //中序遍历 得到的树中元素的顺序排列
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //后续遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //层序遍历
    private void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.e);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }

    // 是否存在
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            //向左树查询
            return contains(node.left, e);
        } else { //e.compareTo(node.e)>0
            //向右树查询
            return contains(node.right, e);
        }
    }

    //首先找到要删除的节点
    //然后找到对应节点的前驱或者后继节点, 前驱就是指当前节点的左子树中最大的元素节点, 后继就是指当前节点右子树中最小的元素节点
    //使用后继节点替换当前节点, 然后再删除要删除的节点
    public void remove(E e) {
            remove(root,e);
    }

    private Node remove(Node node, E e) {

        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) == 0) {
            //判断是否存在孩子节点
            if (node.left != null && node.right == null) {
                Node leftNode = node.left;//将删除节点的子节点保存
                //node.left = null;
                size--;
                return leftNode;//返回删除节点的孩子树
            }else if(node.right != null && node.left == null){
                Node rightNode = node.right;
                //node.right=null;
                size--;
                return rightNode;
            }else if(node.right == null&&node.left == null){
                return null;
            }
            //查找后继 即删除节点的right节点树中的最小节点(查找right中的最小值)
            Node successor = minimum(node.right);
            //删除右孩子中的最小节点
            successor.right=removeMinimum(node.right);//删除右子树中的最小元素后返回的根
            //将删除节点的左孩子赋给新的根节点
            successor.left=node.left;
            //断开删除节点与子孩子的联系
            node.left=node.right=null;
            //返回删除节点后的新根
            return successor;
        }
        if (e.compareTo(node.e) > 0) {
            Node rightRoot = remove(node.right, e);//返回的删除节点后的根节点
            node.right = rightRoot;
        }
        if (e.compareTo(node.e) < 0) {
            Node leftRoot = remove(node.left, e);//返回的删除节点后的根节点
            node.left = leftRoot;
        }
        return node;
    }

    public E removeMinimum() {
        E minimum = minimum();
        root=removeMinimum(root);
        return minimum;
    }

    private Node removeMinimum(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMinimum(node.left);
        return node;
    }

    public E removeMaximum() {
        E maximum = maximum();
        root = removeMaximum(root);
        return maximum;
    }

    private Node removeMaximum(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMaximum(node.right);
        return node;
    }

    private E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("size is null");
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("size is null");
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    public Boolean isBst(){
        ArrayList<E> list = new ArrayList<>();
        inOrder(root,list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i))>0){
                return false;
            }

        }
        return true;

    }

    private void inOrder(Node node, List list){
        if (node==null){
            return;
        }
        inOrder(node.left,list);
        list.add(node.e);
        inOrder(node.right,list);
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(41);
        tree.add(58);
        tree.add(30);
        tree.add(20);
        tree.add(35);
        tree.add(50);
        tree.add(60);
        tree.add(42);
        tree.add(53);
        tree.add(59);
        tree.add(63);

//        System.out.println("-----非递归前序序遍历-----");
//        tree.preOrderNF();
        //tree.inOrder();
        System.out.println("-----层序遍历-----");
        tree.levelOrder();
//        tree.remove(59);
        tree.removeMaximum();
        System.out.println("-----层序遍历-----");
        tree.levelOrder();

        System.out.println(tree.isBst());
    }

}
