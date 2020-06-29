package com.springcloud.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.bean.ao.CompanyDictAO;
import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.vo.CompanyDictVO;
import com.springcloud.exceptions.ServiceException;
import com.springcloud.mapper.CompanyDivisionMapper;
import com.springcloud.mapper.CompanyDictMapper;
import com.springcloud.service.CompanyDictService;
import com.springcloud.util.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : CompanyDictServiceImpl
 * @Description : 公司目录
 * @Author : Joe
 * @Date: 2019/11/18 10:51
 */

@Service
@Transactional(readOnly = true)
public class CompanyDictServiceImpl extends ServiceImpl<CompanyDictMapper, CompanyDict> implements CompanyDictService {

    @Autowired
    CompanyDictMapper companyDictMapper;
    @Autowired
    CompanyDivisionMapper companyDivisionMapper;

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
    public QueryResult<CompanyDict> page(long page, long size, CompanyDictQuery companyDictQuery) {

        QueryWrapper<CompanyDict> queryWrapper = new QueryWrapper<>();
        //queryWrapper = queryEntity(companyDictQuery, queryWrapper);
        if (!StringUtils.isEmpty(companyDictQuery.getCompanyName())) {
            queryWrapper.like("companyName", companyDictQuery.getCompanyName());
        }
        if (!StringUtils.isEmpty(companyDictQuery.getCompanyCode())) {
            queryWrapper.like("companyCode",companyDictQuery.getCompanyCode());
        }
        Page<CompanyDict> pageinfo = new Page(page, size);
        pageinfo.setSearchCount(true);

        IPage<CompanyDict> ipage = companyDictMapper.selectPage(pageinfo, queryWrapper);

        QueryResult queryResult = new QueryResult<CompanyDict>();
        BeanUtils.copyProperties(ipage, queryResult);


        return queryResult;
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
    public Boolean update(Integer companyDictID, CompanyDivision searchModel) {
        CompanyDict updateModel = null;
        if (companyDictID != null) {
            updateModel = companyDictMapper.selectById(companyDictID);
            if (updateModel == null) {
                throw new ServiceException("当前公司ID为" + companyDictID + "：暂无此公司查询信息，关联失败！");
            }
            updateModel.setXunsoftDeptName(searchModel.getXunsoftDeptName());
            updateModel.setCustomerCode(searchModel.getCustomerCode());
            updateModel.setCustomerName(searchModel.getCustomerName());
            updateModel.setXunsoftDeptCode(searchModel.getXunsoftDeptCode());
        }
        return companyDictMapper.updateById(updateModel) > 0;
    }

    @Override
    public Boolean save(CompanyDictAO companyDictAO) {

        CompanyDict companyDict = new CompanyDict();
        BeanUtils.copyProperties(companyDictAO,companyDict);
        return companyDictMapper.save(companyDict);
    }

    @Override
    public Boolean delete(CompanyDictAO companyDictAO) {

        QueryWrapper<CompanyDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("companyCode", companyDictAO.getCompanyCode());
        return companyDictMapper.delete(queryWrapper) > 0;
    }

    @Override
    public Boolean update(List<Integer> companyDictID, CompanyDivision companyDivision) {
        return null;
    }

    @Override
    public Boolean getOneByCompanyDictId(Integer companyDictId) {
        CompanyDict companyDict = companyDictMapper.selectById(companyDictId);
        if (companyDict != null && StringUtils.isNotBlank(companyDict.getCustomerName())) {
            throw new ServiceException("已存在");
        }
        return true;
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


    /**
     * 查询实体处理
     *
     * @param
     * @param queryWrapper
     * @return
     */
    private QueryWrapper<CompanyDict> queryEntity(CompanyDictQuery companyDictQuery, QueryWrapper<CompanyDict> queryWrapper) {
        if (companyDictQuery != null) {
            if (!StringUtils.isEmpty(companyDictQuery.getCompanyName())) {
                queryWrapper.like("companyName", companyDictQuery.getCompanyName());
            }
            if (!StringUtils.isEmpty(companyDictQuery.getCompanyCode())) {
                queryWrapper.like("companyCode",companyDictQuery.getCompanyCode());
            }
            return queryWrapper;
        }
        return queryWrapper;
    }

}
