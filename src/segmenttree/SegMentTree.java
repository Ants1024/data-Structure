package segmenttree;

/**
 * @program: data-Structure
 * @description: 线段树
 *  定义：线段树每个节点保存的是指定区间内聚合(聚合的具体实现根据业务需求而定，比如区间求和)后的数据而非整个区间内所有的值
 * @author: wanshubin
 * @create: 2019-09-27 09:37
 **/
public class SegMentTree<E> {

    /**
    *  根据融合逻辑组成的线段树
    */
    private E[] tree;

    /**
     *  基础数据
    */
    private E[] data;

    private Merge<E> merge;



    public SegMentTree(E [] array,Merge<E> merge){

        this.merge=merge;
        //根据基础数据长度，计算线段树所需要的节点长度
        tree=(E[])new Object[array.length*4];

        data=(E[])new Object[array.length];

        //创建数据副本
        for(int i=0;i<array.length;i++){
            data[i]=array[i];
        }
        buildSegMentTree(0,0,array.length-1);
    }

    /**
    * @Description: 构建线段树
    * @Param:   treeIndex: 当前节点下标 , l与r代表节点所负责的区间范围  l:左边界, r:右边界
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    private void buildSegMentTree(int treeIndex,int l,int r){

        //递归终止条件
        if(l==r){
            tree[treeIndex]=data[l];
            return;
        }
        //获取区间中间值
        int mid=l+(r-l)/2;
        //获取左孩子的下标
        int leftIndex = leftChild(treeIndex);
        //获取右孩子的小标
        int rightIndex=rightChild(treeIndex);

        //构建左孩子
        buildSegMentTree(leftIndex,l,mid);
        //构建右孩子
        buildSegMentTree(rightIndex,mid+1,r);

        //将区间数据聚合后存入线段树中
        tree[treeIndex] = this.merge.merge(tree[leftIndex], tree[rightIndex]);

    }
    /**
    * @Description: 查询指定区间范围类聚合后的数据
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    public E query(int indexL,int indexR){
        return query(0,0,data.length-1,indexL,indexR);
    }
    /**
     * 查询以treeIndex为根 (l,r)范围内，indexL到indexR区间内聚合的数据
     * */
    private E query(int treeIndex,int l,int r,int indexL,int indexR){
        //递归终止条件
        if(l==indexL&&r==indexR){
           return tree[treeIndex];
        }
        //(0,mid) (mid,data.length-1)
        int mid=l+(r-l)/2;

        //获取左孩子的下标
        int leftIndex = leftChild(treeIndex);
        //获取右孩子的小标
        int rightIndex=rightChild(treeIndex);

        //判断indexL到indexR区间是在左孩子还是右孩子或者左右都有一部分

        if (indexR<=mid){
            //indexL到indexR区间在左孩子范围内
            return query(leftIndex,l,mid,indexL,indexR);
        }else if(indexL>=mid+1){
            //indexL到indexR区间在左孩子范围内
           return query(rightIndex,mid+1,r,indexL,indexR);
        }
        //indexL到indexR区间在左右皆有

        //获取左边的数据
        E leftData=query(leftIndex,l,mid,indexL,indexR);
        //获取右边的数据
        E rightData=query(rightIndex,mid+1,r,indexL,indexR);
        //聚合左右的数据
        return this.merge.merge(leftData,rightData);

    }

    private  int leftChild(int index){
        return index*2+1;
    }

    private int rightChild(int index){
        return index*2+2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i <tree.length ; i++) {
            if (tree[i]!=null){
                res.append(tree[i]);
            }else{
                res.append("null");
            }
            if (i!=tree.length-1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
    /**
    * @Description: 区间求和测试类
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    public static void main (String[] args){
        SegMentTree<Integer> tree = new SegMentTree<>(new Integer[]{1, 2, 3, 4, 5, 6}, new Merge<Integer>() {
            @Override
            public Integer merge(Integer l, Integer r) {
                return l + r;
            }
        });
        System.out.println(tree.toString());
    }
}
