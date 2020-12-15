package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName : TagInfo
 * @Description : 吊牌信息
 * @Author : Joe
 * @Date: 2020-10-15 13:49
 */
@Data
@TableName("tagInfo")
public class TagInfo {

    String tagNo;
    String uniquecode;
    String productcode;
    String color;

}
