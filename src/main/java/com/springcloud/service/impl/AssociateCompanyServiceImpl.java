package com.springcloud.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.vo.CompanyDictVO;
import com.springcloud.mapper.CompanyDictMapper;
import com.springcloud.service.CompanyDictService;
import com.springcloud.util.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
* @ClassName : CompanyDictServiceImpl
* @Description : 公司目录
* @Author : Joe
* @Date: 2019/11/18 10:51
*/

@Service
@Transactional(readOnly = true)
public class CompanyDictServiceImpl implements CompanyDictService {

    @Autowired
    CompanyDictMapper companyDictMapper;


    @Override
    public PageResult<CompanyDictVO> page(CompanyDictQuery companyDictQuery) {
        PageHelper.startPage(companyDictQuery.getNum(),companyDictQuery.getSize());
        HashMap<String,Object> map = new HashMap<>(16);
        if(StringUtils.isNotBlank(companyDictQuery.getCompanyName())){
            map.put("companyName",companyDictQuery.getCompanyName());
        }
        List<CompanyDict> responseList = companyDictMapper.getPageCompanyDict(map);
        PageInfo page = new PageInfo(responseList);
        List<CompanyDictVO> listVO = new ArrayList<>();
        if(responseList!=null && responseList.size()>0){
            for(CompanyDict companyDict:responseList){
                CompanyDictVO companyDictVO = new CompanyDictVO();
                BeanUtils.copyProperties(companyDict,companyDictVO);
                listVO.add(companyDictVO);
            }
        }
        return new PageResult<CompanyDictVO>(page,listVO);
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
