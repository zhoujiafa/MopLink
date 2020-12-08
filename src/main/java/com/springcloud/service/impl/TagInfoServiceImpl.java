package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.analyticaldata.OrderDetail;
import com.springcloud.bean.ao.UserAO;
import com.springcloud.bean.dos.GoodsDict;
import com.springcloud.bean.dos.Tag;
import com.springcloud.bean.dos.TagInfo;
import com.springcloud.bean.dos.User;
import com.springcloud.bean.vo.TagInfoVo;
import com.springcloud.bean.vo.UserVO;
import com.springcloud.mapper.GoodsDictMapper;
import com.springcloud.mapper.TagInfoMapper;
import com.springcloud.mapper.TagMapper;
import com.springcloud.mapper.UserMapper;
import com.springcloud.service.TagInfoService;
import com.springcloud.service.UserService;
import com.springcloud.util.ResponseBean2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : UserServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */
@Transactional
@Service
public class TagInfoServiceImpl implements TagInfoService {

    @Autowired
    TagInfoMapper tagInfoMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    GoodsDictMapper goodsDictMapper;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHMMss");

    @Override
    public Boolean saveTagInfo(List<TagInfo> tagInfo, String remaek, User user) {

        List<TagInfo> list = new ArrayList<>();
        String now = format.format(new Date());
        for (TagInfo tagInfos : tagInfo) {
            TagInfo addmodel = new TagInfo();
            BeanUtils.copyProperties(tagInfos, addmodel);
            addmodel.setTagNo(now.trim());
            //addmodel.setCreatetime(new Date());
            //addmodel.setCreator(user.getUserName());
            list.add(addmodel);
        }
        Tag tag = new Tag();
        if (tagInfoMapper.batchInsert(list)) {
            tag.setRemark(remaek);
            tag.setTagNo(now.trim());
            tag.setCount(list.size());
            tag.setCreator(user.getUserName());
            tag.setAddress(user.getAddress());
            tag.setCreatetime(new Date());

        }
        return tagMapper.insert(tag) > 0;
    }

    @Override
    public ResponseBean2 tagColorByCode(List<String> tagCodeList, int tab) {

//        List<String> s = new ArrayList<>();
//        s.add("01102Z052XL");
//        s.add("01103ZAH3XL");
//        s.add("01102Z05L");
//        s.add("01102ZAH2XL");
//        s.add("01103Z05XL");
//        s.add("01103Z05S");
//        s.add("01103Z05L");
        //tagCodeList.add("01102Z052XL");
        if (tab == 1) {
            List<GoodsDict> dictListByTab1 = goodsDictMapper.selectBatchItemCode2(tagCodeList);
            List<TagInfoVo> response = new ArrayList<>();
            for (GoodsDict goodsDict : dictListByTab1) {
                TagInfoVo tagInfoVo = new TagInfoVo();
                tagInfoVo.setColor(goodsDict.getSpecName());
                tagInfoVo.setItemCode(goodsDict.getItemCode());
                tagInfoVo.setCount(goodsDict.getCount());
                tagInfoVo.setColor("空");
                tagInfoVo.setTotalCount(0);
                //tagInfoVo.setCount();
                response.add(tagInfoVo);
            }
            return ResponseBean2.ok(response);
        }
        List<GoodsDict> dictListByTab2 = goodsDictMapper.selectBatchItemCode(tagCodeList);
        if (dictListByTab2.size() == 0 || dictListByTab2 == null) {
            return ResponseBean2.fail("所扫吊牌款号颜色不存在，请使用QQ联系管理员");
        }
        List<TagInfoVo> model = new ArrayList<>();
        int count = 0;
        Set<String> set2 = new HashSet<>();
        for (GoodsDict goodsDict : dictListByTab2) {
            goodsDict.setCount(1);
            set2.add(goodsDict.getSkuBarcode());
            count += goodsDict.getCount();
            TagInfoVo tagInfoVo = new TagInfoVo();
            String color = goodsDict.getSpecName();
            String colorName = color.replaceAll("[^\u4E00-\u9FA5]", "");
            tagInfoVo.setColor(colorName);
            tagInfoVo.setItemCode(goodsDict.getItemCode());
            tagInfoVo.setCount(1);

            model.add(tagInfoVo);
        }
        if(count!=tagCodeList.size()){
            Set<String> set1 = new HashSet<>();
            for(String  str:tagCodeList){
                set1.add(str);
            }
            set1.removeAll(set2);
            return ResponseBean2.fail("款号颜色："+set1+"不存在，请QQ联系管理员！");
        }
        List<TagInfoVo> norepeatlist = new ArrayList<TagInfoVo>();
        List<TagInfoVo> repeatlist = new ArrayList<TagInfoVo>();
        for (int i = 0; i < model.size(); i++) {
            if (!norepeatlist.contains(model.get(i))) {
                norepeatlist.add(model.get(i));
            } else {
                repeatlist.add(model.get(i));
            }
        }
        for (TagInfoVo t1 : repeatlist) {
            for (TagInfoVo t2 : norepeatlist) {
                if (t1.getColor().equals(t2.getColor()) && t1.getItemCode().equals(t2.getItemCode())) {
                    t2.setCount(t2.getCount() + 1);
                }
            }
        }
        System.out.println("去除重复后的list集合" + norepeatlist);
        return ResponseBean2.ok(norepeatlist);
    }

    @Override
    public List<TagInfo> tagColorByPerson(String phone) {

        List<TagInfo> list = tagInfoMapper.tagColorByPerson(phone);

        return list;
    }

    @Override
    public TagInfo tagInfodoesItExist(String uniqueCode) {

        QueryWrapper<TagInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uniqueCode",uniqueCode);

        return tagInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public List<TagInfo> tagInfoListBytagNo(String tagNo) {

        QueryWrapper<TagInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tagNo",tagNo);
        List<TagInfo> list = tagInfoMapper.selectList(queryWrapper);

        return list;
    }
}
