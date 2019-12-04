package com.springcloud.service;

import com.springcloud.bean.dos.GoodsDivision;
import com.springcloud.bean.dos.GoodsDict;
import com.springcloud.bean.query.GoodsDictQuery;
import com.springcloud.bean.vo.GoodsDictVO;
import com.springcloud.util.QueryResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author darren.zhou
 * @Description: 商品物料接口
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
public interface GoodsDictService {

    /**
     * 导入excel文件
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    boolean batchImport(String fileName, MultipartFile file) throws Exception;

    /**
     * 分页模糊查询
     *
     * @param page
     * @param size
     * @param goodsDictQuery
     * @return
     */
    QueryResult<GoodsDict> page(long page, long size, GoodsDictQuery goodsDictQuery);


    /**
     * 列表查询
     *
     * @param goodsDictQuery
     * @return
     */
    List<GoodsDictVO> list(GoodsDictQuery goodsDictQuery);

    /**
     * 更新
     *
     * @param goodsDictID
     * @param goodsDivision
     * @return
     */
    Boolean update(Integer goodsDictID, GoodsDivision goodsDivision);

}