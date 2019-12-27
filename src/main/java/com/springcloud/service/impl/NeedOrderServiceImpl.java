package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.bean.dos.*;
import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.query.NeedOrderQuery;
import com.springcloud.bean.vo.IndentDtVO;
import com.springcloud.bean.vo.NeedOrderDetailVO;
import com.springcloud.bean.vo.NeedOrderVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.mapper.NeedOrderDetailMapper;
import com.springcloud.mapper.NeedOrderMapper;
import com.springcloud.service.NeedOrderService;
import com.springcloud.util.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName : MOPNeedOrderServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */

@Service
public class NeedOrderServiceImpl implements NeedOrderService {

    @Autowired
    NeedOrderMapper needOrderMapper;
    @Autowired
    NeedOrderDetailMapper needOrderDetailMapper;

    @Override
    public SaveResult saveNeedOrder(Map<String, Object> map) {
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("companyCode", "0324");
        maps.put("docNum", "303248530001");
        maps.put("createName", new Date());
        maps.put("IsRetransmit", false);
        maps.put("IsCompulsorySubmission", false);
        maps.put("ResultInt", 0);
        maps.put("ResultString", "成功");
        SaveResult needOrder = new SaveResult();
        needOrderMapper.saveNeedOrder(maps);

        needOrder.setDocNum(String.valueOf(needOrder.getDocNum()));
        return needOrder;
    }

    @Override
    public NeedOrderVO getNeedOrderByNeedNo(String companyCode, String needOrderNo) {
        NeedOrderVO needOrderVO = new NeedOrderVO();
        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(needOrderNo)) {
            QueryWrapper<NeedOrder> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("companyCode", companyCode);
                queryWrapper.eq("needNo", needOrderNo);
            NeedOrder needOrder = needOrderMapper.selectOne(queryWrapper);
            if(needOrder !=null){
                QueryWrapper<NeedOrderDetail> query= new QueryWrapper<>();
                query.eq("docNum", needOrder.getDocNum());
                List<NeedOrderDetail> needOrderDetails = needOrderDetailMapper.selectList(query);
                if(needOrderDetails!=null && needOrderDetails.size()>0){
                    List<NeedOrderDetailVO> needOrderDetailVOList = new ArrayList<>();
                    for(NeedOrderDetail needOrderDetail : needOrderDetails){
                        NeedOrderDetailVO needOrderDetailVO = new NeedOrderDetailVO();
                        BeanUtils.copyProperties(needOrderDetail, needOrderDetailVO);
                        needOrderDetailVOList.add(needOrderDetailVO);
                    }
                    needOrderVO.setList(needOrderDetailVOList);
                }
                BeanUtils.copyProperties(needOrder, needOrderVO);
            }
        }
        return needOrderVO;
    }


    @Override
    public QueryResult<NeedOrderVO> page(long page, long size, NeedOrderQuery query) {
        QueryWrapper<NeedOrder> queryWrapper = new QueryWrapper<>();
        if (query != null) {
            if (!StringUtils.isEmpty(query.getCompanyName())) {
                queryWrapper.like("companyName", query.getCompanyName());
            }
            if (!StringUtils.isEmpty(query.getDocNum())) {
                queryWrapper.eq("docNum", query.getDocNum());
            }
        }
        Page<NeedOrder> pageinfo = new Page(page, size);
        pageinfo.setSearchCount(true);
        IPage<NeedOrder> ipage = needOrderMapper.selectPage(pageinfo, queryWrapper);

        QueryResult queryResult = new QueryResult<MOPIndent>();
        BeanUtils.copyProperties(ipage, queryResult);
        return queryResult;
    }




    /**
     * 查询实体处理
     *
     * @param
     * @param queryWrapper
     * @return
     */
    private QueryWrapper<MOPNeedOrder> queryEntity(MOPNeedOrderQuery query, QueryWrapper<MOPNeedOrder> queryWrapper) {
        if (query != null) {
            if (!StringUtils.isEmpty(query.getCompanyCode())) {
                queryWrapper.eq("companyCode", query.getCompanyCode());
            }
            if (!StringUtils.isEmpty(query.getNeedOrderNo())) {
                queryWrapper.like("needOrderNo", query.getNeedOrderNo());
            }
            return queryWrapper;
        }
        return queryWrapper;
    }
}
