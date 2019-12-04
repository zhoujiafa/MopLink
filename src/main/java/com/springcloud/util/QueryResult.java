package com.springcloud.util;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryResult<T> {
    /**
     * 数据列表
     */
    private List<T> records;
    /**
     * 数据总数
     */
    private long total;



}