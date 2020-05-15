package array;

import java.util.*;

/**
 * @program: data-Structure
 * @description:
 * @author: wanshubin
 * @create: 2019-09-16 16:36
 **/
public class test {

    public static void main(String[] args) {
        test test = new test();
//        test.generate(30);

        // System.out.println(test.maxProfit(new int[]{11,2,3,4,8}));
        // 1 2 3 4 5 6 7 --> 5,6,7,1,2,3,4
//        test.rotate_2(new int[]{1,2},3);

        //test.moveZeroes(new int[]{0,3,0,1,12});
        String s = test.longestCommonPrefix();
        System.out.println(s);
    }

    //生成杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList();
        for (int i = 1; i < numRows; i++) {
            List<Integer> intList = new ArrayList();
            for (int j = 1; j <= i; j++) {
                intList.add(getInteger(i, j));
            }
            list.add(intList);
        }
        return list;
    }

    public Integer getInteger(int i, int j) {
        if (i == j || j == 1) {
            return 1;
        }
        return getInteger(i - 1, j - 1) + getInteger(i - 1, j);
    }


    //去除排序后数组中的重复元素
    public int removeDuplicates(int[] nums) {
        int size = 0;
        Boolean flag = true;
        //前指针
        int frontIndex = 0;
        //后指针
        int afterIndex = 0;
        //0,1,1,1,2,2,3,3
        while (flag) {
            afterIndex++;
            if (afterIndex >= nums.length) {
                flag = false;
                continue;
            }
            if (nums[frontIndex] - nums[afterIndex] != 0) {
                if (afterIndex - frontIndex != 1) {
                    nums[frontIndex + 1] = nums[afterIndex];
                }
                frontIndex++;
                size++;
            }


        }
        return size;
    }


    public int maxProfit(int[] prices) {
        int profit = 0;//利润
        for (int i = 1; i < prices.length; i++) {
            int cost = prices[i] - prices[i - 1];
            if (cost > 0) {
                profit += cost;
            }
        }
        return profit;
    }

    // 1 2 3 4 5 6 7 --> 5,6,7,1,2,3,4
    public void rotate_2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        //1 2 3 4 5 6 7 ----> 7 6 5 4 2 3 1
        reverse(nums, 0, n - 1);
        //7 6 5 4 2 3 1 --->5 6 7 4 2 3 1
        reverse(nums, 0, k - 1);
        //5 6 7 4 2 3 1 --->5,6,7,1,2,3,4
        reverse(nums, k, n - 1);

    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    // 1 1 2 2 3 3
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int flag = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[flag] - nums[i] == 0) {
                flag = i + 1;
                i = flag;
            } else {
                return nums[flag];
            }
        }
        return nums[nums.length - 1];
    }

    //使用 异或的方式
    public int singleNumber2(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        LinkedList<Integer> set = new LinkedList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        int[] max;
        int[] min;
        if (nums1.length > nums2.length) {
            max = nums1;
            min = nums2;
        } else {
            max = nums2;
            min = nums1;
        }


        for (int i : max) {
            set.add(i);
        }
        List<Integer> list = new LinkedList<>();
        for (int i : min) {
            if (set.contains(i)) {
                set.remove(set.indexOf(i));
                list.add(i);
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }


    public void moveZeroes(int[] nums) {
        // 0,3,0,1,12

        int size = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                continue;
            } else {
                int flag = i;
                for (int j = i + 1; j < nums.length; j++) {
                    int data = nums[i];
                    nums[flag] = nums[j];
                    flag++;
                }

            }

        }
    }

    public String longestCommonPrefix() {

        String[] strs = new String[]{"flower","flow","flight"};
        Integer flag = null;

        char[] chars = strs[0].toCharArray();
        int num= chars.length;

        //快速失败g
        Boolean b=true;
        for (int i = 1; i < strs.length; i++) {
            if(!b){
               break;
            }
            for (int j = 0; j < num; j++) {
                System.out.println("循环次数："+num);
                if ((j < strs[i].length() &&
                        chars[j] == strs[i].charAt(j))) {
                    flag = j;
                }else{
                    num=j;
                    if(j==0){
                        b=false;
                        flag=null;
                    }
                    break;
                }
            }
        }
        if(flag!=null){
            System.out.println(":"+flag);
            return strs[0].substring(0,flag+1);
        }
        return "-";
    }

}
