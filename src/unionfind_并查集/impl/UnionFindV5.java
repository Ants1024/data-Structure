package unionfind_并查集.impl;

import unionfind_并查集.UF;

/**
 * @program: data-Structure
 * @description: 并查集第五版
 * 优化 find方法
 * 方式 引入 路径压缩算法 parent[index]=parent[parent[index]];
 * 解决：防止退化成一个链表，减低树的高度
 * @author: wanshubin
 * @create: 2019-10-23 10:22
 **/
public class UnionFindV5 implements UF {

    private int[] parent; //parent[i]表示的是第i个元素指向的父节点
    private int[] rank; //表示树的深度

    public UnionFindV5(int size){
        parent=new int[size];
        rank=new int[size];
        for (int i = 0; i < size; i++) {
            parent[i]=i;
            rank[i]=1;//默认树的深度为1
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public Boolean isConnected(int p, int q) {
        return find(p)==find(q);

    }

    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);// p元素的父节点
        int qId = find(q);// q元素的父节点
        //判断是不是属于同一个族群
        if (pId==qId){
            return;
        }
        //判断树的深度来决定谁为父节点 深度越浅越优
        //防止退化为链表
        if(rank[pId]<rank[qId]){
            parent[pId]=qId;
        }else if(rank[qId]>rank[qId]){
            parent[qId]=pId;
        }else{
            parent[pId]=qId;
            rank[qId]+=1;
        }
    }

    /**
    * @Description: 查询索引所对应rank数组中的父节点
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    private int find(int index){
        //是否还有上级节点
        while (index!=parent[index]){
            //parent数组中存储的是上级节点的下标，
            //所以此处的是让index等于index上上级节点，从而达到降低树深度的目的
            parent[index]=parent[parent[index]];
            index=parent[index];
        }
        return index;
    }

    public static void main (String[] args){
        UnionFindV5 findV2 = new UnionFindV5(5);
        findV2.unionElements(0,1);
        findV2.unionElements(3,4);
        findV2.unionElements(2,4);
        findV2.unionElements(1,3);
        System.out.println(findV2.isConnected(0,3));
    }

}
