package com.springcloud.api;

import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.vo.CompanyDictVO;
import com.springcloud.service.CompanyDictService;
import com.springcloud.util.PageResult;
import com.springcloud.util.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName : DataLineController
 * @Description : 数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/companydict")
public class CompanyDictController {

    @Autowired
    CompanyDictService companyDictService;

    @ApiOperation(value = "导入公司商品信息" , notes = "导入公司商品信息")
    @PostMapping("/import")
    public ResponseBean<Boolean> importCompanyDict(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        return  ResponseBean.ok(companyDictService.batchImport(fileName, file));
    }

    @ApiOperation(value = "分页查询" , notes = "分页查询")
    @PostMapping("/page")
    public ResponseBean<PageResult<CompanyDictVO>> page(CompanyDictQuery companyDictQuery) {

        return  ResponseBean.ok(companyDictService.page(companyDictQuery));
    }

    @ApiOperation(value = "分页查询" , notes = "分页查询")
    @GetMapping("/list")
    public ResponseBean<List<CompanyDictVO>> list(CompanyDictQuery companyDictQuery) {

        return  ResponseBean.ok(companyDictService.list(companyDictQuery));
    }

}
