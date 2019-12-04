package com.springcloud.api;

import com.springcloud.bean.query.DataLineQuery;
import com.springcloud.bean.vo.DataLineVO;
import com.springcloud.service.DataLineService;
import com.springcloud.util.PageResult;
import com.springcloud.util.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : DataLineController
 * @Description : 数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/dataline")
public class DataLineController {

    @Autowired
    DataLineService dataLineService;

    @ApiOperation(value = "根据companyCode和appId下载信息", notes = "根据companyCode和appId下载信息")
    @PostMapping("/getdataline")
    public ResponseBean<List<DataLineVO>> getdataline(@RequestBody Map data) {
        //String companyCode = data.get("companyCode").toString();
        //String needOrderNo = data.get("needOrderNo").toString();
        String companyCode = "0324";
        String needOrderNo = "303248530001";
        List<DataLineVO> dataLineVO = dataLineService.getdataline(companyCode,needOrderNo);
        return ResponseBean.ok(dataLineVO);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean<PageResult<DataLineVO>> page(@PathVariable int page, @PathVariable int size, DataLineQuery query) {

        return ResponseBean.ok(dataLineService.page(page,size,query));
    }

    @ApiOperation(value = "列表信息", notes = "列表信息")
    @GetMapping("/list")
    public ResponseBean<List<DataLineVO>> list(DataLineQuery query) {

        return ResponseBean.ok(dataLineService.list(query));
    }

    @ApiOperation(value = "保存下载信息", notes = "保存下载信息")
    @PostMapping("/save")
    public ResponseBean<Boolean> save(@RequestBody DataLineVO lineVO) {

        return ResponseBean.ok(dataLineService.saveDataLine(lineVO));
    }

    @ApiOperation(value = "根据companyCode和appId查询本地信息", notes = "根据companyCode和appId查询本地信息")
    @PostMapping("/getone")
    public ResponseBean<List<DataLineVO>> getLocalInfo(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String needOrderNo = data.get("needOrderNo").toString();
        List<DataLineVO> dataLineVO = dataLineService.getLocalInfo(companyCode,needOrderNo);
        return ResponseBean.ok(dataLineVO);
    }

}
