package Trie;

import java.util.TreeMap;

/**
 * @program: data-Structure
 * @description:
 * @author: wanshubin
 * @create: 2019-10-13 15:49
 **/
public class Trie {

    public class Node {
        //是否是一个单词的结尾
        public Boolean isWord;

        public TreeMap<Character, Node> next;

        public Node(Boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }

    }

    private Node root;
    //树中单词的数量
    private int size;

    public Trie() {
        //初始化节点
        root = new Node();
        size = 0;
    }

    //像树中添加单词
    public void add(String word) {
        Node nextNode = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (nextNode.next.get(c) == null) {
                nextNode.next.put(c, new Node());
            }
            nextNode = nextNode.next.get(c);
        }
        //判断是否为新添加的单词
        if (!nextNode.isWord) {
            nextNode.isWord = true;
            size++;
        }
    }

    //查询树中是否包含某个单词
    public Boolean contains(String word) {
        Node nextRoot = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (nextRoot.next.get(c) == null) {
                return false;
            }
            nextRoot = nextRoot.next.get(c);
        }

        return nextRoot.isWord;
    }

    //查询树中是否有以prefix为前缀的单词
    public Boolean isPrefix(String prefix) {
        Node nextRoot = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (nextRoot.next.get(c) == null) {
                return false;
            }
            nextRoot = nextRoot.next.get(c);
        }
        return true;
    }

    //模糊查询单词 .可以代表任意字母  b..k
    public Boolean containsLimit(String word) {
        return containsLimit(root, word, 0);
    }

    private Boolean containsLimit(Node node, String word, int index) {

        if (index == word.length()) {
            //是否为单词的最后一位
            return node.isWord;
        }
        char c = word.charAt(index);

        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return containsLimit(node.next.get(c), word, index + 1);
        }
        //. 可以代表所有字母，因此需要遍历所有子树
        for (Character nodeKey : node.next.keySet()) {
            if (containsLimit(node.next.get(nodeKey), word, index + 1)) {
                return true;
            }
        }
        return false;
    }


    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        Node node = trie.new Node();
        trie.add("book");
        System.out.println(trie.contains("books"));

        System.out.println(trie.containsLimit("b.o.."));

    }
}
