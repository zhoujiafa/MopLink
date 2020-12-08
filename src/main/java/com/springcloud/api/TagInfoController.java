package com.springcloud.api;

import com.springcloud.bean.dos.TagInfo;
import com.springcloud.bean.dos.User;
import com.springcloud.bean.vo.TagInfoVo;
import com.springcloud.service.TagInfoService;
import com.springcloud.util.ResponseBean;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
* @ClassName : XsProductInfoController
* @Description : 讯商商品下单接口
* @Author : Joe
* @Date: 2020-04-30 13:50
*/
@RestController
@RequestMapping("/taginfo")
public class TagInfoController {

    @Autowired
    TagInfoService tagInfoService;

    @ApiOperation(value = "扫描吊牌保存信息", notes = "扫描吊牌保存信息")
    //@RequestMapping(value = "/savetaginfo",method = {RequestMethod.POST,RequestMethod.GET})
    @PostMapping("/savetaginfo")
    public ResponseBean<Boolean> saveTagInfo(@RequestParam("listdata") String listdata,@RequestParam("remark") String remark,@RequestParam("user") String user) {

           //JSONArray jsonArray = JSON.parseArray(str);
            JSONArray array = JSONArray.fromObject (listdata);
            if(array.size()==0){
                return ResponseBean.fail("请录入数据！");
            }
            net.sf.json.JSONObject userinfo = net.sf.json.JSONObject.fromObject(user);
            List<TagInfo> list = net.sf.json.JSONArray.toList(array, new TagInfo (), new JsonConfig());
            User model = (User) net.sf.json.JSONObject.toBean(userinfo, new User(), new JsonConfig());

        return ResponseBean.ok(tagInfoService.saveTagInfo(list,remark,model));
    }


    @ApiOperation(value = "扫描吊牌汇总信息(批量查询)", notes = "扫描吊牌汇总信息")
    @PostMapping("/tagColorByCodes")
    public ResponseBean<TagInfoVo> tagColorByCodes(@RequestParam("listdata") String listdata,int tab) {

        JSONArray array = JSONArray.fromObject (listdata);
        if(array.size()==0){
            return ResponseBean.fail("请录入数据！");
        }
        List<TagInfo> list = net.sf.json.JSONArray.toList(array, new TagInfo (), new JsonConfig());
        List<String> sukList = list.stream().map(TagInfo::getProductcode).collect(Collectors.toList());

        ResponseBean2<List<TagInfoVo>> voList = tagInfoService.tagColorByCode(sukList,tab);

        return ResponseBean.ok(voList);
    }

    @ApiOperation(value = "检查吊牌颜色是否存在(单条查询)", notes = "检查吊牌颜色是否存在")
    @PostMapping("/tagColorByCode")
    public ResponseBean<TagInfoVo> tagColorByCode(@RequestParam("listdata") String listdata) {

        JSONArray array = JSONArray.fromObject (listdata);
        if(array.size()==0){
            return ResponseBean.fail("请录入数据！");
        }
        List<TagInfo> list = net.sf.json.JSONArray.toList(array, new TagInfo (), new JsonConfig());
        List<String> sukList = list.stream().map(TagInfo::getProductcode).collect(Collectors.toList());

        ResponseBean2<List<TagInfoVo>> voList = tagInfoService.tagColorByCode(sukList,0);

        return ResponseBean.ok(voList);
    }

    @ApiOperation(value = "记录列表(按个人查询)", notes = "记录列表(按个人查询)")
    @PostMapping("/tagColorByPerson")
    public ResponseBean<TagInfoVo> tagColorByPerson(@RequestParam("phone") String phone) {

//        net.sf.json.JSONObject userinfo = net.sf.json.JSONObject.fromObject(phone);
//        if(userinfo == null){
//            return ResponseBean.fail("请录入数据！");
//        }

        List<TagInfo> tagInfoList = tagInfoService.tagColorByPerson(phone);
        return ResponseBean.ok(tagInfoList);
    }


    @ApiOperation(value = "检查吊牌是否被扫过(单条查询)", notes = "检查吊牌是否被扫过")
    @PostMapping("/tagInfodoesItExist")
    public ResponseBean<TagInfoVo> tagInfodoesItExist(@RequestParam("uniqueCode") String uniqueCode) {

        TagInfo vo = tagInfoService.tagInfodoesItExist(uniqueCode);

        return ResponseBean.ok(vo);
    }

    @ApiOperation(value = "根据tagNo查询扫描详情列表(单条查询)", notes = "根据tagNo查询扫描详情列表")
    @PostMapping("/tagInfoListBytagNo")
    public ResponseBean<List<TagInfo>> tagInfoListBytagNo(@RequestParam("tagNo") String tagNo) {

        List<TagInfo> volist = tagInfoService.tagInfoListBytagNo(tagNo);

        return ResponseBean.ok(volist);
    }

}
