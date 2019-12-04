package com.springcloud.bean.ao;

import com.springcloud.bean.dos.CompanyDict;
import lombok.Data;

import java.util.List;

/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@Data
public class CompanyDictAO {

    List<CompanyDict> data;
    String assoId;
}
