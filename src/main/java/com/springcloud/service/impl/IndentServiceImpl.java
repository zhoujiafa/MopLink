package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.bean.dos.*;
import com.springcloud.bean.query.IndentQuery;
import com.springcloud.bean.vo.IndentDtVO;
import com.springcloud.bean.vo.IndentVO;
import com.springcloud.mapper.IndentDetailMapper;
import com.springcloud.mapper.IndentMapper;
import com.springcloud.service.IndentService;
import com.springcloud.util.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName : MOPNeedOrderServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */
@Transactional
@Service
public class IndentServiceImpl implements IndentService {

    @Autowired
    IndentMapper indentMapper;

    @Autowired
    IndentDetailMapper indentDetailMapper;

    /*@Override
    public SaveResult saveIndent(Map<String, Object> map) {
        Map<String, Object> maps = new HashMap<String, Object>();
        //maps.put("companyCode", map.get("companyCode"));
        maps.put("companyCode", "0181");
        //maps.put("docNum",  map.get("orderNo"));
        maps.put("docNum",  "DO8191209190458085");
        maps.put("createName", "admin");
        maps.put("IsRetransmit", true);
        maps.put("IsCompulsorySubmission", true);
        maps.put("ResultInt", 0);
        maps.put("ResultString", "成功");
        SaveResult result = indentMapper.saveIndent(maps);
        //result.setDocNum(String.valueOf(result.getDocNum()));
        return result;
    }*/

    @Override
    public List<IndentVO> list(String companyCode, String orderNo) {
        List<IndentVO> lineVOList = new ArrayList<>();
        List<Indent> indentList;
        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(orderNo)) {
            QueryWrapper<Indent> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("companyCode", companyCode);
                queryWrapper.eq("docNum", orderNo);
            indentList = indentMapper.selectList(queryWrapper);
            for (Indent indent : indentList) {
                IndentVO indentVO = new IndentVO();
                BeanUtils.copyProperties(indent, indentVO);
                lineVOList.add(indentVO);
            }
        }
        return lineVOList;
    }

    @Override
    public IndentVO getOne(String companyCode, String orderNo) {
        IndentVO indentVO = new IndentVO();
        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(orderNo)) {
            QueryWrapper<Indent> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("companyCode", companyCode);
            queryWrapper.eq("docNum", orderNo);
            Indent search = indentMapper.selectOne(queryWrapper);
                if(search !=null){
                    QueryWrapper<IndentDetail> indentDetailQueryWrapper = new QueryWrapper<>();
                    indentDetailQueryWrapper.eq("docNum", search.getDocNum());
                    List<IndentDetail> indentDetailList = indentDetailMapper.selectList(indentDetailQueryWrapper);
                    if(indentDetailList!=null && indentDetailList.size()>0){
                        List<IndentDtVO> indentDtVOList = new ArrayList<>();
                        for(IndentDetail indentDetail : indentDetailList){
                            IndentDtVO indentDtVO = new IndentDtVO();
                            BeanUtils.copyProperties(indentDetail, indentDtVO);
                            indentDtVOList.add(indentDtVO);
                        }
                        indentVO.setList(indentDtVOList);
                    }
                    BeanUtils.copyProperties(search, indentVO);
                }

        }
        return indentVO;
    }

    @Override
    public QueryResult<IndentVO> page(long page, long size, IndentQuery query) {
        QueryWrapper<Indent> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryEntity(query, queryWrapper);

        Page<Indent> pageinfo = new Page(page, size);
        pageinfo.setSearchCount(true);
        IPage<Indent> ipage = indentMapper.selectPage(pageinfo, queryWrapper);

        QueryResult queryResult = new QueryResult<Indent>();
        BeanUtils.copyProperties(ipage, queryResult);

        /*queryResult.setRecords(ipage.getRecords());
        queryResult.setTotal(ipage.getTotal());*/

        return queryResult;
    }





    /**
     * 查询实体处理
     *
     * @param
     * @param queryWrapper
     * @return
     */
    private QueryWrapper<Indent> queryEntity(IndentQuery query, QueryWrapper<Indent> queryWrapper) {
        if (query != null) {
            if (!StringUtils.isEmpty(query.getCompanyName())) {
                queryWrapper.eq("companyName", query.getCompanyName());
            }
            if (!StringUtils.isEmpty(query.getOrderNo())) {
                queryWrapper.eq("docNum", query.getOrderNo());
            }
            return queryWrapper;
        }
        return queryWrapper;
    }
}
