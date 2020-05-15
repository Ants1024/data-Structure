package stack.impl;

import java.util.Stack;

/**
 * @program: data-Structure
 * @description:
 * @author: wanshubin
 * @create: 2019-07-06 14:03
 **/

public class leetcode {
    public static void main (String[] args){
        Solution solution = new Solution();
        System.out.println(solution.isValid("[]{}"));
    }
}


class Solution {


    public boolean isValid(String s) {

        Stack<Character> stack = new Stack();

        for (char cc : s.toCharArray()) {
            if (cc == '{') {
                stack.push('}');
            } else if (cc == '[') {
                stack.push(']');
            } else if (cc == '(') {
                stack.push(')');
            } else {
                if (stack.isEmpty() || stack.pop() != cc) {
                    return false;
                }
            }

        }
        return stack.isEmpty();
    }
}
