package com.springcloud.service;


import com.springcloud.bean.dos.CompanyDict;
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
     *  列表查询
     * @param companyDict
     * @return
     */
    List<CompanyDict> listCompanyDict(CompanyDict companyDict);

    /**
     * 导入excel文件
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    boolean batchImport(String fileName, MultipartFile file) throws Exception;

}
