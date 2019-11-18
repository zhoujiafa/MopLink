package com.springcloud.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.mapper.CompanyDictMapper;
import com.springcloud.service.CompanyDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
* @ClassName : CompanyDictServiceImpl
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 10:51
*/

@Service
@Transactional(readOnly = true)
public class CompanyDictServiceImpl implements CompanyDictService {

    @Autowired
    CompanyDictMapper companyDictMapper;


    @Override
    public List<CompanyDict> listCompanyDict(CompanyDict companyDict) {
        QueryWrapper<CompanyDict> query = new QueryWrapper<>();
        query.like(StringUtils.isNotBlank(companyDict.getCompanyName()),"companyName",companyDict.getCompanyName());
        return companyDictMapper.selectList(query);
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();
        // 数据处理
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);
        // 需要验证
        importParams.setNeedVerfiy(false);
            ExcelImportResult<CompanyDict> result = ExcelImportUtil.importExcelMore(file.getInputStream(), CompanyDict.class,importParams);
            List<CompanyDict> commpanyList = result.getList();
            boolean bool = companyDictMapper.batchInsert(commpanyList);
        return bool;
    }


}
