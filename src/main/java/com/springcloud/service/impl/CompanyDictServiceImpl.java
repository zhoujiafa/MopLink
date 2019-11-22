package com.springcloud.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.bean.dos.AssociateCompany;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.vo.CompanyDictVO;
import com.springcloud.exceptions.ServiceException;
import com.springcloud.mapper.AssociateCompanyMapper;
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
    @Autowired
    AssociateCompanyMapper associateCompanyMapper;

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();
        // 数据处理
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);
        // 需要验证
        importParams.setNeedVerfiy(false);
        ExcelImportResult<CompanyDict> result = ExcelImportUtil.importExcelMore(file.getInputStream(), CompanyDict.class, importParams);
        List<CompanyDict> commpanyList = result.getList();
        boolean bool = companyDictMapper.batchInsert(commpanyList);
        return bool;
    }


    @Override
    public PageResult<CompanyDictVO> page(CompanyDictQuery companyDictQuery) {
        PageHelper.startPage(companyDictQuery.getNum(), companyDictQuery.getSize());
        HashMap<String, Object> map = new HashMap<>(16);
        if (StringUtils.isNotBlank(companyDictQuery.getCompanyName())) {
            map.put("companyName", companyDictQuery.getCompanyName());
        }
        List<CompanyDict> responseList = companyDictMapper.getPageCompanyDict(map);
        PageInfo page = new PageInfo(responseList);
        List<CompanyDictVO> listVO = new ArrayList<>();
        listVO = DtoToBean(responseList);
        return new PageResult<CompanyDictVO>(page, listVO);
    }

    @Override
    public List<CompanyDictVO> list(CompanyDictQuery query) {
        QueryWrapper<CompanyDict> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query.getCompanyCode())) {
            queryWrapper.like("companyCode", query.getCompanyCode());
        }
        if (StringUtils.isNotBlank(query.getCompanyName())) {
            queryWrapper.like("companyName", query.getCompanyName());
        }
        if (StringUtils.isNotBlank(query.getMopDeptCode())) {
            queryWrapper.like("mopDeptCode", query.getMopDeptCode());
        }
        List<CompanyDict> listFromData = companyDictMapper.selectList(queryWrapper);

        return DtoToBean(listFromData);
    }

    @Override
    public Boolean update(Integer companyDictID, AssociateCompany searchModel) {
        CompanyDict updateModel = null;
        if (companyDictID != null) {

            updateModel = companyDictMapper.selectById(companyDictID);
            if (updateModel == null) {
                throw new ServiceException("当前公司ID为" + companyDictID + "：暂无此公司查询信息，关联失败！");
            }
            updateModel.setCustomerCode(searchModel.getCustomerCode());
            updateModel.setCustomerName(searchModel.getCustomerName());
            updateModel.setXunsoftDeptCode(searchModel.getXunsoftDeptCode());
        }
        return companyDictMapper.updateById(updateModel) > 0;
    }





    private List<CompanyDictVO> DtoToBean(List<CompanyDict> list) {
        List<CompanyDictVO> listVo = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (CompanyDict companyDict : list) {
                CompanyDictVO companyDictVO = new CompanyDictVO();
                BeanUtils.copyProperties(companyDict, companyDictVO);
                listVo.add(companyDictVO);
            }
        }
        return listVo;
    }

}
