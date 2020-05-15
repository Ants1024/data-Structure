package heap;
import array.Array;

/**
 * @program: data-Structure
 * @description: 堆-最大优先堆--使用数组实现  特点： 堆是一个完全二叉树，每个孩子节点都小于自己的父亲节点
 * @author: wanshubin
 * @create: 2019-08-15 15:07
 **/
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    //heapify:将任意数组整理为堆的形状，此方法时间复杂度为O(n),而使用重新构建堆结构的方式时间复杂度为O(nlogn)
    public  MaxHeap(E[] arr){
        data =new Array(arr);
        //从最后一个叶子节点的父节点开始进行下沉操作
        for (int i = parent(arr.length-1); i >=0; i--) {
            siftDown(i);
        }
    }

    public boolean isEmpty(E e){
        return data.getSize()==0;
    }

    public int getSize(){
        return data.getSize();
    }

    //添加操作可以看做数据上浮过程，将需要添加的元素插入数组的最后，在向上比较，如果大于但前父节点则交换位置
    public void add(E e){
        data.add(e);
        //获取但前节点父节点位置
        siftUp(data.getSize()-1,e);

    }
    //index 上浮的起始位置
    private void siftUp(int index,E e){
        //e是否比当前父节点大，大就上浮
        while (index>0&&e.compareTo(data.getElement(parent(index)))>0){
            //交换位置
//            data.swap(index,parent(index));
//            index=parent(index);

            //此方法可减少3倍的复制次数
            data.addIndex(index,data.getElement(parent(index)));
            index=parent(index);
        }
            data.addIndex(index,e);
    }

    public E findMax(){
        return data.getFirstElement();
    }
    //取出数据后，将最后一个元素移动到队首，再使用下沉的方式将堆的结构特征合法化
    public E extractMax(){
        E max = findMax();
        //将队尾的元素移动到队首
        data.swap(0,data.getSize()-1);
        data.remove(data.getSize()-1);
        //下沉
        siftDown(0);
        return max;
    }
    //对比子节点中最大的元素，如果小于它则交换位置
    private void siftDown(int index) {
        //获取该节点左孩子在数组中的下标
        int leftChild = leftChild(index);
        //获取该节点右孩子在数组中的下标
        int rightChild = rightChild(index);
        int maxChild=0;
        //判断下标是否越界
        while (leftChild<data.getSize()){
            //判断是否存在右孩子
            if(leftChild+1>=data.getSize()){
                maxChild=leftChild;
            }else{
                 maxChild=data.getElement(leftChild).compareTo(data.getElement(rightChild))>0? leftChild:rightChild;
            }
            if (data.getElement(index).compareTo(data.getElement(maxChild))<0){
                //交换位置
                data.swap(index,maxChild);
                index=maxChild;
                leftChild = leftChild(maxChild);
                rightChild=rightChild(maxChild);
            }else{
                break;
            }
        }
    }
    //取出最大元素并添加新元素
    public E replace(E e){
        E max = findMax();
        data.addIndex(0,e);
        siftDown(0);
        return max;
    }




    //获取子节点的父节点的下标
    private int parent(int index){
        return (index-1)/2;
    }
    //获取父节点的左孩子节点的下标
    private int leftChild(int index){
        return index*2+1;
    }
    //获取父节点的右孩子节点的下标
    private int rightChild(int index){
        return index*2+2;
    }

    public static void main (String[] args){
        Integer[] ints = {1, 2, 4, 67, 123, 65, 23, 7, 32, 87};
        MaxHeap<Integer> maxHeap = new MaxHeap<>(ints);
//        maxHeap.add(921);
//        maxHeap.add(488);
//        maxHeap.add(652);
//        maxHeap.add(960);
//        maxHeap.add(917);
//        maxHeap.add(395);
//        maxHeap.add(676);
//        maxHeap.add(560);
//        maxHeap.add(713);
//        maxHeap.add(817);
//
//        maxHeap.extractMax();
//        maxHeap.extractMax();



        System.out.println(maxHeap.getSize());
//        int n=10000;
//        Random random = new Random();
//        for (int i = 0; i <n; i++) {
//            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
//        }
//        int [] arr=new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i]=maxHeap.extractMax();
//            System.out.println(":"+arr[i]);
//        }
//        for (int i = 1; i < n; i++) {
//            if (arr[i-1]<arr[i]){
//                System.out.println("位置1："+arr[i-1]);
//                System.out.println("位置2："+i+"值："+arr[i]);
//                throw new RuntimeException("失败");
//            }
//        }

    }

}
