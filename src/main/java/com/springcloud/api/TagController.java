package com.springcloud.api;

import com.springcloud.bean.dos.Tag;
import com.springcloud.bean.dos.TagInfo;
import com.springcloud.bean.dos.User;
import com.springcloud.bean.vo.TagInfoVo;
import com.springcloud.bean.vo.TagVo;
import com.springcloud.service.TagInfoService;
import com.springcloud.service.TagService;
import com.springcloud.util.ResponseBean;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
* @ClassName : XsProductInfoController
* @Description : 吊牌信息
* @Author : Joe
* @Date: 2020-04-30 13:50
*/
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;


    @ApiOperation(value = "记录列表(按个人手机号查询)", notes = "记录列表(按个人手机号查询)")
    @PostMapping("/tagListByPhone")
    public ResponseBean<Tag> tagListByPerson(@RequestParam("phone") String phone) {

        List<Tag> tagList = tagService.tagListByPhone(phone);
        return ResponseBean.ok(tagList);
    }

}
