package unionfind_并查集.impl;

import unionfind_并查集.UF;

/**
 * @program: data-Structure
 * @description: 并查集第二版 将 parent数组看成一颗下标 指向父亲 树
 * @author: wanshubin
 * @create: 2019-10-23 10:22
 **/
public class UnionFindV2 implements UF {

    private int[] parent; //parent[i]表示的是第i个元素指向的父节点

    public UnionFindV2(int size){
        parent=new int[size];
        for (int i = 0; i < size; i++) {
            parent[i]=i;
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
        int pId = find(p);
        int qId = find(q);
        if (pId==qId){
            return;
        }
        parent[p]=qId;
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
        UnionFindV2 findV2 = new UnionFindV2(5);
        findV2.unionElements(0,1);
        findV2.unionElements(3,4);
        findV2.unionElements(2,4);
        findV2.unionElements(1,3);
        System.out.println(findV2.isConnected(0,3));
    }

}
