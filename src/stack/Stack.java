package stack;

/**
 * @program: data-Structure
 * @description:
 * @author: wanshubin
 * @create: 2019-07-06 10:57
 **/

public interface Stack<T> {

    /**
    * @Description: 入栈
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    void push(T e);

    /**
    * @Description: 出栈
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    T pop();
    /** 
    * @Description: 查看当前栈顶元素 
    * @Param:  
    * @return:  
    * @Author: wanshubin
    * @Date:  
    */ 
    T peek();
    
    /** 
    * @Description: 获取栈中元素的数量 
    * @Param:  
    * @return:  
    * @Author: wanshubin
    * @Date:  
    */ 
    int getSize();

    /** 
    * @Description: 判断栈是否为空 
    * @Param:  
    * @return:  
    * @Author: wanshubin
    * @Date:  
    */ 
    Boolean isEmpty();
    
}
