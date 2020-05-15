package heap;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @program: data-Structure
 * @description:给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * @author: wanshubin
 * @create: 2019-08-16 16:10
 **/
public class LeetCode {
    //频率低的元素优先级越高
    class Freq implements Comparable<Freq>{
        int e,freq;

        public Freq(int e,int freq){
            this.e=e;
            this.freq=freq;
        }

        @Override
        public int compareTo(Freq o) {
            if (this.freq>o.freq){
                return -1;
            }else if(this.freq<o.freq){
                return 1;
            }
            return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        //key=元素,value=元素出现的频率
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        //----统计元素出现的频率
        for (int num : nums) {
            if (treeMap.containsKey(num)){
                //map中存在此元素
                treeMap.put(num,treeMap.get(num)+1);
            }else{
                treeMap.put(num,1);
            }
        }

        PriorityQueue<Freq> queue = new PriorityQueue<>();
        //将满足条件的元素插入优先级队列中
        for (int key:treeMap.keySet()){
            if (queue.getSize()<k){
                queue.enQueue(new Freq(key,treeMap.get(key)));
            }else if (treeMap.get(key)>queue.getFront().freq){
                queue.deQueue();
                queue.enQueue(new Freq(key,treeMap.get(key)));
            }
        }

        LinkedList<Integer> freqs = new LinkedList<>();

        while (!queue.isEmpty()){
            freqs.add(queue.deQueue().e);
        }
        return freqs;
    }

    public static void main (String[] args){
        LeetCode leetCode = new LeetCode();
        int[] ints={1,1,1,1,2,2,3};
        leetCode.topKFrequent(ints,2);
    }
}
