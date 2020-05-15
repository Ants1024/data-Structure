package array;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: data-Structure
 * @description: 自定义数组
 * @author: wanshubin
 * @create: 2019-07-03 21:29
 **/
public class Array<E> {

    private E[] data;//存放数据的容器
    private int size;//数据总量

    /**
     * @Description: 构建指定大小的数组
     * @Param: 容量大小
     * @return:
     * @Author: wanshubin
     * @Date:
     */
    public Array(int capacity) {
        data = (E[])new Object[capacity];
    }

    public Array() {
        this(10);
    }

    public Array(E[] arr){
        this(arr.length);
        for (int i=0;i<arr.length;i++) {
            data[i]=arr[i];
        }
        size=arr.length;
    }

    //获取当前数组中数据长度
    public int getSize() {
        return size;
    }

    //获取数组长度
    public int getCapacity() {
        return data.length;
    }

    /**
     * @Description:
     * @Param: 数据需要插入的位置 -1为在当前数组最后添加
     * @return:
     * @Author: wanshubin
     * @Date:
     */

    public void add(E e) {
        add(size, e);
    }

    //指定位置添加数据添加数据
    public void add(int index, E e) {
        if (index < 0 ) {
            throw new IllegalArgumentException("数组下标越界");
        }
        if (index > size) {//防止数据不紧凑
            throw new IllegalArgumentException("index 需要  小于 size ");
        }
        if(size==data.length){//触发扩容
            reSize(2*data.length);
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    //在第一个位置添加指定数据
    public void addFirst(E e) {
        add(0, e);
    }

    public E getElement(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index不可小于0");
        }
        return data[index];
    }

    //获取第一个元素
    public E getFirstElement() {
        return getElement(0);
    }

    //获取最后一个元素
    public E getLastElement() {
        return getElement(size - 1);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index值非法");
        }
        E e = data[index];
        for (int i = index+1; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        //当前实际使用的容量小于当前总容量1/4时触发缩容(使用四分之一是为解决时间复杂度震荡所带来的性能下降)
        if (size<=data.length/4&&data.length/2!=0){
            reSize(data.length/2);//(缩容至原数组的1/2)
        }
        return e;
    }

    //判断元素是否存在
    public boolean isEmpty(E e) {
        if (size == 0) {
            //非法参数异常
            throw new IllegalArgumentException("array is null");
        }
        for (E datum : data) {
            if (e .equals( datum)) {
                return true;
            }
        }
        return false;
    }

    //获取元素所在的下标
    public int getElementIndex(E e) {
        if (size == 0) {
            throw new IllegalArgumentException("array is null");
        }
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    //获取元素所在的所有下标
    public List getElementIndexAll(E e) {
        List<Integer> list = new ArrayList();
        if (size == 0) {
            throw new IllegalArgumentException("array is null");
        }
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {
                list.add(i);
            }
        }
        return list;
    }

    private void reSize(int capacity){
        E[] newData =(E[]) new Object[capacity];
        for (int i=0;i<this.size;i++){
            newData[i]=data[i];
        }
        data=newData;
    }

    public void swap(int i,int j){
        E e=data[i];
        data[i]=data[j];
        data[j]=e;
    }
    public void addIndex(int index,E e){
        data[index]=e;
    }


    public static void main(String[] args) {
        Array<Integer> array = new Array();
        for (int i = 0; i < 12000; i++) {
            array.add(i);
        }
        System.out.println(array.getCapacity());

        for (int i = 0; i <11000 ; i++) {
            array.remove(1);
        }
        Integer element = array.getElement(0);
        System.out.println(array.getSize());

    }


}
