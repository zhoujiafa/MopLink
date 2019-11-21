package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName : xtm14
 * @Description : 讯商门店信息
 * @Author : Joe
 * @Date: 2019-11-20 16:18
 */
@Data
@TableName("xtm14")
public class xtm14 {


    String customerName;
    String customerCode;
    String xunsoftDeptCode;

}
