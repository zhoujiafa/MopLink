package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.bean.dos.MOPNeedOrder;
import com.springcloud.bean.dos.NeedOrder;
import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.query.NeedOrderQuery;
import com.springcloud.bean.vo.NeedOrderVO;
import com.springcloud.bean.vo.SaveResult;
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
    public List<NeedOrderVO> list(String companyCode, String needOrderNo) {
        List<NeedOrderVO> lineVOList = new ArrayList<>();
        List<NeedOrder> needOrderList;
        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(needOrderNo)) {
            QueryWrapper<NeedOrder> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("companyCode", companyCode);
                queryWrapper.like("needNo", needOrderNo);
            needOrderList = needOrderMapper.selectList(queryWrapper);
            for (NeedOrder needOrder : needOrderList) {
                NeedOrderVO needOrderVO = new NeedOrderVO();
                BeanUtils.copyProperties(needOrder, needOrderVO);
                lineVOList.add(needOrderVO);
            }
        }
        return lineVOList;
    }


    @Override
    public QueryResult<NeedOrderVO> page(long page, long size, NeedOrderQuery query) {
        return null;
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
