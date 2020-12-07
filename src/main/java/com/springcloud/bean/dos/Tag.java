package com.springcloud.bean.dos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName : Tag
 * @Description : 吊牌信息
 * @Author : Joe
 * @Date: 2020-10-15 13:49
 */
@Data
public class Tag {

    String tagNo;
    int count;
    String creator;
    String address;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date createtime;
    String remark;

}
