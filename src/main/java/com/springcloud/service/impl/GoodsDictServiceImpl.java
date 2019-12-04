package com.springcloud.service.impl;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.bean.ao.GoodsDictAO;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.dos.GoodsDict;
import com.springcloud.bean.dos.GoodsDivision;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.query.GoodsDictQuery;
import com.springcloud.bean.vo.GoodsDictVO;
import com.springcloud.exceptions.ServiceException;
import com.springcloud.mapper.GoodsDictMapper;
import com.springcloud.service.GoodsDictService;
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
public class GoodsDictServiceImpl extends ServiceImpl<GoodsDictMapper, GoodsDict> implements GoodsDictService {


    @Autowired
    GoodsDictMapper goodsDictMapper;

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();
        // 数据处理
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);
        // 需要验证
        importParams.setNeedVerfiy(false);
        ExcelImportResult<GoodsDictAO> result = ExcelImportUtil.importExcelMore(file.getInputStream(), GoodsDictAO.class, importParams);
        List<GoodsDictAO> goodsDicsVOtList = result.getList();
        List<GoodsDict> goodsDictList = new ArrayList<>();
        if(goodsDicsVOtList.size()>0 && goodsDicsVOtList !=null){
           for(GoodsDictAO goodsDictAO:goodsDicsVOtList){
               GoodsDict goodsDict = new GoodsDict();
               BeanUtils.copyProperties(goodsDictAO,goodsDict);
               goodsDict.setSpecName(goodsDictAO.getColourId()+goodsDictAO.getSizeId());
               goodsDictList.add(goodsDict);
           }
        }
        boolean bool = goodsDictMapper.batchInsert(goodsDictList);
        return bool;
    }

    @Override
    public QueryResult<GoodsDict> page(long page, long size, GoodsDictQuery goodsDictQuery) {

        QueryWrapper<GoodsDict> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryEntity(goodsDictQuery, queryWrapper);
        Page<GoodsDict> pageinfo = new Page(page, size);
        pageinfo.setSearchCount(true);
        IPage<GoodsDict> ipage = goodsDictMapper.selectPage(pageinfo, queryWrapper);
        QueryResult queryResult = new QueryResult<CompanyDict>();
        BeanUtils.copyProperties(ipage, queryResult);
        return queryResult;
    }

    @Override
    public List<GoodsDictVO> list(GoodsDictQuery goodsDictQuery) {
        return null;
    }

    @Override
    public Boolean update(Integer goodsDictID, GoodsDivision goodsDivision) {
        GoodsDict updateModel = null;
        if (goodsDictID != null) {
            updateModel = goodsDictMapper.selectById(goodsDictID);
            if (updateModel == null) {
                throw new ServiceException("当前物料信息ID为" + goodsDictID + "：暂无此查询信息，关联失败！");
            }
            updateModel.setBarCode(goodsDivision.getBarCode());
            updateModel.setBarName(goodsDivision.getBarName());
            updateModel.setPrice(goodsDivision.getPrice());
        }
        return goodsDictMapper.updateById(updateModel) > 0;
    }


    /**
     * 查询实体处理barCode
     *
     * @param
     * @param queryWrapper
     * @return
     */
    private QueryWrapper<GoodsDict> queryEntity(GoodsDictQuery goodsDictQuery, QueryWrapper<GoodsDict> queryWrapper) {
        if (goodsDictQuery != null) {
            if (!StringUtils.isEmpty(goodsDictQuery.getSkuBarcode())) {
                queryWrapper.eq("skuBarcode", goodsDictQuery.getSkuBarcode());
            }
            if (!StringUtils.isEmpty(goodsDictQuery.getItemCode())) {
                queryWrapper.eq("itemCode", goodsDictQuery.getItemCode());
            }
            return queryWrapper;
        }
        return queryWrapper;
    }
}
