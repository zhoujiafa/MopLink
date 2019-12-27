package com.springcloud.api;

import com.springcloud.bean.dos.GoodsDivision;
import com.springcloud.bean.query.GoodsDictQuery;
import com.springcloud.bean.vo.GoodsDictVO;
import com.springcloud.service.GoodsDivisionService;
import com.springcloud.service.GoodsDictService;
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
@RequestMapping("/goodsDict")
public class GoodsDictController {

    @Autowired
    GoodsDictService goodsDictService;
    @Autowired
    GoodsDivisionService goodsDivisionService;

    @ApiOperation(value = "导入商品物料信息", notes = "导入商品物料信息")
    @PostMapping("/import")
    public ResponseBean<Boolean> importGoodsDict(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        return ResponseBean.ok(goodsDictService.batchImport(fileName, file));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean<PageResult<GoodsDictVO>> page(@PathVariable int page, @PathVariable int size, GoodsDictQuery goodsDictQuery) {

        return ResponseBean.ok(goodsDictService.page(page,size,goodsDictQuery));
    }

    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public ResponseBean<List<GoodsDictVO>> list(GoodsDictQuery goodsDictQuery) {

        return ResponseBean.ok(goodsDictService.list(goodsDictQuery));
    }

    @ApiOperation(value = "更新信息（物料关联）", notes = "更新信息（物料关联）")
    @PostMapping("/update")
    public ResponseBean<Boolean> update(@RequestBody Map data) {
        if (StringUtils.isBlank(data.get("goodsDictID").toString())) {
            return ResponseBean.fail("编码不能为空！");
        }
        GoodsDivision goodsDivision = goodsDivisionService.getOneBybarCode(data.get("barCode").toString());
        return ResponseBean.ok(goodsDictService.update(Integer.valueOf(data.get("goodsDictID").toString()), goodsDivision));
    }


}
