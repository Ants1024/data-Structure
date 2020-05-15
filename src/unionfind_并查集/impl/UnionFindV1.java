package unionfind_并查集.impl;

import unionfind_并查集.UF;

/**
 * @program: data-Structure
 * @description: 并查集第一版
 * @author: wanshubin
 * @create: 2019-10-23 10:22
 **/
public class UnionFindV1 implements UF {

    //parent[i]表示的是第i个元素指向的父节点
    private int[] parent;

    public UnionFindV1(int size){
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
        if(pId==qId){
          return;
        }
        for (int i = 0; i < parent.length; i++) {
            if (pId==find(i)){
                parent[i]=qId;
            }
        }
    }

    /**
    * @Description: 查询索引所对应的集合编号
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    private int find(int index){
        return parent[index];
    }

    public static void main (String[] args){
        UnionFindV1 findV1 = new UnionFindV1(5);
        findV1.unionElements(0,1);
        findV1.unionElements(2,3);

        findV1.unionElements(2,1);

    }

}
