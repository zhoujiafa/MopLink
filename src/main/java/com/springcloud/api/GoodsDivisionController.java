package com.springcloud.api;

import com.springcloud.bean.dos.GoodsDivision;
import com.springcloud.bean.query.GoodsDivisionQuery;
import com.springcloud.service.GoodsDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName : DataLineController
 * @Description : 数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/goodsDivision")
public class GoodsDivisionController {

    @Autowired
    GoodsDivisionService goodsDivisionService;


    @GetMapping("/one")
    public GoodsDivision getOneBybarCode(String barCode){

        return goodsDivisionService.getOneBybarCode(barCode);
    }

    @GetMapping("/list")
    public List<GoodsDivision> list(GoodsDivisionQuery query){

        return goodsDivisionService.list(query);
    }

}
