package com.springcloud.service;


import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.vo.CompanyDictVO;
import com.springcloud.util.QueryResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @ClassName : DataLineService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
public interface CompanyDictService {

    /**
     * 导入excel文件
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
     * @param companyDictQuery
     * @return
     */
    QueryResult<CompanyDict> page(long page, long size, CompanyDictQuery companyDictQuery);

    /**
     * 列表查询
     * @param companyDictQuery
     * @return
     */
    List<CompanyDictVO> list(CompanyDictQuery companyDictQuery);

    /**
     * 更新
     * @param companyDivision
     * @param companyDictID
     * @return
     */
    Boolean update(Integer companyDictID, CompanyDivision companyDivision);

    /**
     * 更新
     * @param companyDivision
     * @param companyDictID
     * @return
     */
    Boolean update(List<Integer> companyDictID, CompanyDivision companyDivision);

    /**
     * 单体查询
     * @param companyDictId
     * @return
     */
    Boolean getOneByCompanyDictId(Integer companyDictId);

}
