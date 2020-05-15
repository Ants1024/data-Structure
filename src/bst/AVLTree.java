package bst;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: data-Structure
 * @description: AVL平衡二叉树
 * @author: wanshubin
 * @create: 2019-10-29 15:35
 **/
public class AVLTree<E extends Comparable<E>> {

    private final int MAX_HEIGHT_DIFFERENCE = 1;

    private class Node {
        public E e;
        public Node left, right;
        private int height;

        public Node(E e) {
            this.e = e;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    private void add(E e) {
        root=add(root, e);
    }

    public Node add(Node root, E e) {
        if (root == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(root.e) < 0) {//向左插入
            root.left = add(root.left, e);
        } else if (e.compareTo(root.e) > 0) {//向右插入
            root.right = add(root.right, e);
        }//等于情况不做处理//节点高度计算方式：1+左右孩子中height最大的值
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        //获取平衡因子 左孩子的height-右孩子height
        int balanceFactor = getBalanceFactor(root);
        //判断是否需要进行自平衡
        if (Math.abs(balanceFactor) > MAX_HEIGHT_DIFFERENCE) {
            //进行自平衡操作
            //四种不平衡情况
            //LL  插入元素在不平衡节点的左侧的左侧(左侧左子树的高大于或等于左侧右子树的高) 采用右旋转
            if (balanceFactor > 1 && getBalanceFactor(root.left) >= 0) {
                return rightSwitch(root);
            }
            //RR 插入元素在不平衡节点的右侧的右侧 采用左旋转(右侧左子树的小于等于右侧右子树的高)
            if (balanceFactor < -1 && getBalanceFactor(root.right) <= 0) {
                return leftSwitch(root);
            }
            //不平衡节点在左侧的右子树：LR 左侧的左子树的高小于右子树的高(平衡因子小于0)
            //使用 左旋转(当前节点的左子树)----简化为LL情况----使用右旋转(当前节点)----->平衡

            //LR
            //          y                           y                           T3
            //         / \      左旋转             /   \                        /  \
            //        x  T4  ------------>       T3   T4    右旋转             x   y
            //       / \                        /          -------->         / \   \
            //      z  T3                      x                            z  T5  T4
            //          \                     / \
            //          T5                   z  T5
            if (balanceFactor > 1 && getBalanceFactor(root.left) < 0) {
                root.left = leftSwitch(root.left);
                return rightSwitch(root);
            }
            //不平衡节点在右侧的左子树：RL 右侧的左子树的高大于右子树(平衡因子大于0)
            //使用 右旋转(当前节点的右子树)---->简化为RR的情况----->左旋转(当前节点)---->平衡
            //RL
            //          y                           y                           z
            //         / \      右旋转             /   \                        / \
            //        x  T4  ------------>       x    z    左旋转              y  T4
            //           / \                           \   -------->         /   / \
            //          z  T3                          T4                   x   T5 T3
            //          \                             / \
            //          T5                           T5 T3
            if (balanceFactor > 1 && getBalanceFactor(root.right) > 0) {
                root.right = rightSwitch(root.right);
                return leftSwitch(root);
            }
        }
        return root;
    }


    //右旋转
    //          y                           x
    //         / \      右旋转             /   \
    //        x  T4  ------------>       z     y
    //       / \                        / \   / \
    //      z  T3                      T1 T2 T3  T4
    //     / \   \                            \
    //    T1 T2  T5                           T5
    private Node rightSwitch(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        y.left = T3;
        x.right = y;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //左旋转
    //           y                            x
    //         /  \      左旋转              /   \
    //        T4   x ------------>         y     z
    //            / \                     / \   / \
    //           T3  z                   T4 T3 T1 T2
    //          /   / \                     /
    //         T5  T1 T2                   T5
    private Node leftSwitch(Node node) {
        Node rootNode = node.right;
        Node leftRoot = rootNode.left;
        node.right = leftRoot;
        rootNode.left = node;
        //更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        rootNode.height = Math.max(getHeight(rootNode.left), getHeight(rootNode.right)) + 1;
        return rootNode;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //获取平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        //左孩子的height-右孩子height
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断是否为二分搜索树
    public Boolean isBst() {
        ArrayList<E> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) >0) {
                return false;
            }
        }
        return true;
    }

    //判断树是否为平衡二叉树
    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node node) {
        if (node == null) {
            return true;
        }
        //获取平衡因子
        int balanceFactor = getBalanceFactor(node);
        //如果当前节点的平衡因子>1 则不是平衡二叉树
        if (Math.abs(balanceFactor) > MAX_HEIGHT_DIFFERENCE) {
            return false;
        }

        return isBalance(node.left) && isBalance(node.right);
    }


    private void inOrder(Node node, List list){
        if (node==null){
            return;
        }
        inOrder(node.left,list);
        list.add(node.e);
        inOrder(node.right,list);
    }

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    //要删除一个节点，可能会遇到3种情况:
    //A. 这个节点没子节点，此情况最为简单，直接删除这个节点即可。
    //B. 这个节点只有一个子节点，把这个子节点和这个节点的父节点相连，然后删除这个节点
    //c.找到对应节点的前驱或者后继节点, 前驱就是指当前节点的左子树中最大的元素节点, 后继就是指当前节点右子树中最小的元素节点
    public void deleteValue(E e) {
        deleteValue(root, e);
    }

    //返回的是删除节点后的根节点
    private Node deleteValue(Node node, E e) {
        if (node == null) {
            return null;
        }
        Node resNode;
        if (e.compareTo(node.e) == 0) {
            //判断是否存在孩子节点
            if (node.left != null && node.right == null) {
                Node leftNode = node.left;//将删除节点的子节点保存
                node.left = null;
                size--;
                resNode = leftNode;//返回删除节点的孩子树
            } else if (node.right != null && node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                resNode = rightNode;
            } else if (node.right == null && node.left == null) {
                return null;
            } else {
                //查找后继 即删除节点的right节点树中的最小节点(查找right中的最小值)
                Node successor = findMiniValue(node.right);
                //删除右孩子中的最小节点
                successor.right = deleteValue(node.right, successor.e);//删除右子树中的最小元素后返回的根
                //将删除节点的左孩子赋给新的根节点
                successor.left = node.left;
                //断开删除节点与子孩子的联系
                node.left = node.right = null;
                //返回删除节点后的新根
                resNode = successor;
            }
        }
        if (e.compareTo(node.e) > 0) {
            Node rightRoot = deleteValue(node.right, e);//返回的删除节点后的根节点
            node.right = rightRoot;
        }
        if (e.compareTo(node.e) < 0) {
            Node leftRoot = deleteValue(node.left, e);//返回的删除节点后的根节点
            node.left = leftRoot;
        }
        resNode = node;

        if(resNode==null){
            return null;
        }
        //更新高度
        resNode.height = 1 + Math.max(getHeight(resNode.left), getHeight(resNode.right));
        //获取平衡因子
        int balanceFactor = getBalanceFactor(resNode);
        //判断是否需要自平衡
        if (Math.abs(balanceFactor) > MAX_HEIGHT_DIFFERENCE) {
            //1。判断不不平衡节点是在左还是右 balanceFactor>1 在左 balanceFactor <-1 在右
            //2。判断是属于那种不平衡情况
            //不平衡节点在左侧的左子树：LL 左侧的左子树的高大于等于左侧右子树的高(平衡因子大于等于0)
            //使用 右旋转
            if (balanceFactor > 1 && getBalanceFactor(resNode.left) >= 0) {
                return rightSwitch(resNode);
            }
            //不平衡节点在左侧的右子树：LR 左侧的左子树的高小于右子树的高(平衡因子小于0)
            //使用 左旋转(当前节点的左子树)----简化为LL情况----使用右旋转(当前节点)----->平衡

            //LR
            //          y                           y                           T3
            //         / \      左旋转             /   \                        /  \
            //        x  T4  ------------>       T3   T4    右旋转             x   y
            //       / \                        /          -------->         / \   \
            //      z  T3                      x                            z  T5  T4
            //          \                     / \
            //          T5                   z  T5
            if(balanceFactor>1&&getBalanceFactor(resNode.left)<0){
                //左旋转
                resNode.left=leftSwitch(resNode.left);
                //右旋转
                return rightSwitch(resNode);
            }
            //不平衡节点在右侧的右子树：RR 右侧的左子树的高小于右子树的高(平衡因子小于等于0)
            //使用 左平衡
            if(balanceFactor <-1 && getBalanceFactor(resNode.right)<=0){
                return leftSwitch(resNode);
            }
            //不平衡节点在右侧的左子树：RL 右侧的左子树的高大于右子树(平衡因子大于0)
            //使用 右旋转(当前节点的右子树)---->简化为RR的情况----->左旋转(当前节点)---->平衡
            //RL
            //          y                           y                           z
            //         / \      右旋转             /   \                        / \
            //        x  T4  ------------>       x    z    左旋转              y  T4
            //           / \                           \   -------->         /   / \
            //          z  T3                          T4                   x   T5 T3
            //          \                             / \
            //          T5                           T5 T3
            if(balanceFactor <-1&&getBalanceFactor(resNode.right)>=0){
                resNode.right=rightSwitch(resNode.right);
                return leftSwitch(resNode);
            }
        }
        return resNode;
    }

    public E findMaxValue() {
        return findMaxValue(root).e;
    }

    private Node findMaxValue(Node root) {
        if (root.right == null) {
            return root;
        }
        return findMaxValue(root.right);
    }

    public E findMiniValue() {
        return findMiniValue(root).e;
    }

    private Node findMiniValue(Node root) {
        if (root.left == null) {
            return root;
        }
        return findMaxValue(root.left);
    }

    public E removeMinimum() {
        E minimum = findMiniValue();
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

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
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
        tree.deleteValue(78);
        tree.deleteValue(59);
        tree.deleteValue(2);
        tree.deleteValue(44);
        tree.deleteValue(55);
        System.out.println(tree.isBst());
        System.out.println(tree.isBalance());

    }
}
