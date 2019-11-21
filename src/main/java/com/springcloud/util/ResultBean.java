package com.rocky.mp.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: TODO
 * <p>
 * date: 2019/11/20 0:22
 * @author: rocky
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean {

    private int code;
    private String msg;
    private Object data;
}
