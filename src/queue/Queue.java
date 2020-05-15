package queue;

/**
 * @program: data-Structure
 * @description: 队列
 * @author: wanshubin
 * @create: 2019-07-07 14:10
 **/

public interface Queue<T> {
    /**
    * @Description: 入队
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    void enQueue(T e);
    /** 
    * @Description: 出队 
    * @Param:  
    * @return:  
    * @Author: wanshubin
    * @Date:  
    */ 
    T deQueue();
    /** 
    * @Description: 查看队首位置的元素 
    * @Param:  
    * @return:  
    * @Author: wanshubin
    * @Date:  
    */ 
    T getFront();
    /** 
    * @Description: 获取队列的长度 
    * @Param:  
    * @return:  
    * @Author: wanshubin
    * @Date:  
    */ 
    int getSize();
    /** 
    * @Description: 判断是否为空队列 
    * @Param:  
    * @return:  
    * @Author: wanshubin
    * @Date:  
    */ 
    boolean isEmpty();

}
