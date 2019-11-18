package com.springcloud.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.service.CompanyDictService;
import com.springcloud.util.RequestPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/import")
    public boolean importCompanyDict(@RequestParam("file") MultipartFile file) {
        boolean bool = false;
        String fileName = file.getOriginalFilename();
        try {
            bool = companyDictService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bool;
    }

   /* @ApiOperation(value = "根据条件分页查询列表companydict:page", notes = "根据条件分页查询记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/page")
    Page<CompanyDictVO> getOriginStudentPageList(RequestPage request, HttpServletRequest httpServletRequest) {
        Page page = new Page<CompanyDictQuery>(request.getPageIndex(), request.getPageSize());
        page.setCondition();
        return originstudentService.getPage(page);
    }*/


}
