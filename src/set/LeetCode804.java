package set;

import java.util.TreeSet;

/**
 * @program: data-Structure
 * @description:
 * @author: wanshubin
 * @create: 2019-08-06 20:37
 **/
public class LeetCode804 {
        public static void main (String[] args){
            String[] words = {"gin", "zen", "gig", "msg"};
            LeetCode804 code804 = new LeetCode804();
            System.out.println(code804.uniqueMorseRepresentations(words));

        }

    public int uniqueMorseRepresentations(String[] words) {
        TreeSet<String> treeSet = new TreeSet<>();
        String[] codes=new String[] {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            for (int i=0;i<word.length();i++){
                builder.append(codes[word.charAt(i)-'a']);
            }
            System.out.println(builder.toString());
            treeSet.add(builder.toString());
            builder.delete(0,builder.length());
        }
         return treeSet.size();
    }


}
