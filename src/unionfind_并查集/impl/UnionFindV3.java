package unionfind_并查集.impl;

import unionfind_并查集.UF;

/**
 * @program: data-Structure
 * @description: 并查集第三版
 * 优化 unionElements
 * 方式 引入额外一个数组来记录 集合中每个元素 节点的数量，然后根据节点数量来决定结合方向
 * 最坏情况 退化成一个链表
 * @author: wanshubin
 * @create: 2019-10-23 10:22
 **/
public class UnionFindV3 implements UF {

    private int[] parent; //parent[i]表示的是第i个元素指向的父节点
    private int[] sz; //表示树的子节点数量

    public UnionFindV3(int size){
        parent=new int[size];
        sz=new int[size];
        for (int i = 0; i < size; i++) {
            parent[i]=i;
            sz[i]=1;//默认树的深度为1
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
        if (pId==qId){
            return;
        }
        //判断树的节点总数来决定谁为父节点 数量越少越优
        if(sz[pId]<sz[qId]){
            parent[qId]=qId;
            sz[qId]+=sz[pId];
        }else {
            parent[qId]=pId;
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
            index=parent[index];
        }
        return index;
    }

    public static void main (String[] args){
        UnionFindV3 findV2 = new UnionFindV3(5);
        findV2.unionElements(0,1);
        findV2.unionElements(3,4);
        findV2.unionElements(2,4);
        findV2.unionElements(1,3);
        System.out.println(findV2.isConnected(0,3));
    }

}
