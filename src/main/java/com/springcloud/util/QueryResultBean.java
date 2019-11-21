package com.springcloud.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description: TODO
 * <p>
 * date: 2019/11/20 0:25
 * @author: rocky
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResultBean<T> {

    private List<T> records;
    private long total;
}
