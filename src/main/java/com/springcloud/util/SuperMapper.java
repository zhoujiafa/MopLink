package com.springcloud.util;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import javafx.scene.control.Pagination;

import java.util.List;
import java.util.Map;

public interface SuperMapper<T> extends BaseMapper<T> {
    /**
     * 查询所有信息
     * @param condition
     * @return
     */
    List<T> getAllByCondition(Map<String, Object> condition);

    /**
     * 批量删除
     * @param list
     * @return
     */
    long batchDelete(List<T> list);

    /**
     * 批量更新
     * @param list
     * @return
     */
    long batchUpdate(List<T> list);

    /**
     * 批量新增
     * @param list
     * @return
     */
    long batchInsert(List<T> list);

    /**
     *分页查询
     * @param page
     * @param condition
     * @return
     */
    List<T> getPageByCondition(Pagination page, Map<String, Object> condition);

}
