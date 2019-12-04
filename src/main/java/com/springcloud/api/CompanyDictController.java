package com.springcloud.api;

import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.vo.CompanyDictVO;
import com.springcloud.service.CompanyDivisionService;
import com.springcloud.service.CompanyDictService;
import com.springcloud.util.PageResult;
import com.springcloud.util.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    @Autowired
    CompanyDivisionService companyDivisionService;

    @ApiOperation(value = "导入公司商品信息", notes = "导入公司商品信息")
    @PostMapping("/import")
    public ResponseBean<Boolean> importCompanyDict(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        return ResponseBean.ok(companyDictService.batchImport(fileName, file));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean<PageResult<CompanyDictVO>> page(@PathVariable int page, @PathVariable int size, CompanyDictQuery companyDictQuery) {

        return ResponseBean.ok(companyDictService.page(page,size,companyDictQuery));
    }

    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public ResponseBean<List<CompanyDictVO>> list(CompanyDictQuery companyDictQuery) {

        return ResponseBean.ok(companyDictService.list(companyDictQuery));
    }

    @ApiOperation(value = "更新信息（公司关联经销商信息）", notes = "更新信息（公司关联经销商信息）")
    @PostMapping("/update")
    public ResponseBean<Boolean> update(@RequestBody Map data) {
        if (StringUtils.isBlank(data.get("companyDictID").toString())) {
            return ResponseBean.fail("客户编码customerCode不能为空！");
        }
        CompanyDivision companyDivision = companyDivisionService.getOneBycustomerCode(data.get("customerCode").toString());
        return ResponseBean.ok(companyDictService.update(Integer.valueOf(data.get("companyDictID").toString()), companyDivision));
    }


//    @ApiOperation(value = "更新信息（公司关联）", notes = "更新信息（公司关联）")
//    @PostMapping("/update")
//    public ResponseBean<Boolean> update(@RequestParam("companyDictID") Integer companyDictID,@RequestParam("customerCode")  String customerCode) {
//        if (StringUtils.isBlank(customerCode)) {
//            return ResponseBean.fail("客户编码【customerCode】不能为空！");
//        }
//        AssociateCompany associateCompany = associateCompanyService.getOneBycustomerCode(customerCode);
//        return ResponseBean.ok(companyDictService.update(companyDictID, associateCompany));
//    }

}
