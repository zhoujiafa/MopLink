package com.springcloud.api;

import com.springcloud.service.CompanyDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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


}
