package segmenttree;

/**
 * @program: data-Structure
 * @description: 线段树的聚合器
 * @author: wanshubin
 * @create: 2019-09-27 11:42
 **/

public interface Merge<E> {
    /**
    * @Description: 聚合
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    E merge(E l,E r);
}
