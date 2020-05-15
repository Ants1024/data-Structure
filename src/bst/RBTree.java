package bst;

import java.util.*;

/**
 * @program: data-Structure
 * @description: 红黑树(左倾)
 * @author: wanshubin
 * @create: 2019-10-29 15:35
 **/
public class RBTree<E extends Comparable<E>> {

    private final int MAX_HEIGHT_DIFFERENCE = 1;

    private static final Boolean RED = true;
    private static final Boolean BLACK = false;

    private class Node {
        public E e;
        public Node left, right;
        public boolean colour;

        public Node(E e) {
            this.e = e;
            this.colour = RED;
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


    private Boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.colour;
    }
    //左旋转
    private Node leftRotate(Node node){

        Node newRoot = node.right;
        node.right=newRoot.left;
        newRoot.left=node;
        newRoot.colour=node.colour;
        node.colour=RED;
        return newRoot;
    }
    //右旋转
    private Node rightRotate(Node node){
        Node newRoot = node.left;
        node.left=newRoot.right;
        newRoot.right=node;
        newRoot.colour=node.colour;
        node.colour=RED;
        return newRoot;
    }
    //颜色反转--将父节点改为红色，左右孩子改为黑色
    private void flipColors(Node node){
        node.colour=RED;
        node.left.colour=BLACK;
        node.right.colour=BLACK;
    }

    public void add(E e) {
        root = add(root, e);
        root.colour=BLACK;
    }

    //优化后的递归算法添加元素
    // 核心思想：可以将null也看成 node的子节点，
    // 因此只需要去判断 e是向左插入还是右插入，当递归到的节点==null 只需添加节点即可，无需再次判断e与node.e的关系
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {//向左插入
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {//向右插入
            node.right = add(node.right, e);
        }//等于情况不做处理

        //判断是否需要左旋转
        //左子树不是红色且右子树是红色
        if(!isRed(node.left)&&isRed(node.right)){
            node=leftRotate(node);
        }

        //判断是否需要右旋转
        //左子树为红且左子树的左子树也为红色
        if(isRed(node.left)&&isRed(node.left.left)){
            node=rightRotate(node);
        }
        //判断是否需要颜色反转
        if(isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }

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
            if (node.right != null) {
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
        remove(root, e);
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
            } else if (node.right != null && node.left == null) {
                Node rightNode = node.right;
                //node.right=null;
                size--;
                return rightNode;
            } else if (node.right == null && node.left == null) {
                return null;
            }
            //查找后继 即删除节点的right节点树中的最小节点(查找right中的最小值)
            Node successor = minimum(node.right);
            //删除右孩子中的最小节点
            successor.right = removeMinimum(node.right);//删除右子树中的最小元素后返回的根
            //将删除节点的左孩子赋给新的根节点
            successor.left = node.left;
            //断开删除节点与子孩子的联系
            node.left = node.right = null;
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
        root = removeMinimum(root);
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
        if (size == 0) {
            throw new IllegalArgumentException("size is null");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("size is null");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public Boolean isBst() {
        ArrayList<E> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }

        }
        return true;

    }

    private void inOrder(Node node, List list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.e);
        inOrder(node.right, list);
    }

    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>();
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
        for (int i = 0; i < 1000; i++) {
            tree.add(i);
        }
        System.out.println(tree.isBst());
    }
}
