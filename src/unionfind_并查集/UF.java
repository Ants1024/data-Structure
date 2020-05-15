package unionfind_并查集;

/**
 * @program: data-Structure
 * @description:
 * @author: wanshubin
 * @create: 2019-10-23 10:18
 **/

public interface UF {

    int getSize();
    /**
    * @Description: 是否连接
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    Boolean isConnected(int p, int q);

    /**
    * @Description: 合并 p 和 q
    * @Param:
    * @return:
    * @Author: wanshubin
    * @Date:
    */
    void unionElements(int p,int q);
}
