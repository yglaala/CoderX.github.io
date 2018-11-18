package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Sug;

import java.util.List;

/**
 * @author chengpeng
 * @create 2018-10-18 15:37
 */
public interface SugService {
    /**
     * 根据反馈类型查询反馈信息
     * @param  * @param sug
     * @param pageNum 当前页数
     * @param pageSize 每页大小
     * @return java.util.List<com.bmsoft.canteensystem.entity.Sug>
     */
    List<Sug> find(Sug sug, int pageNum, int pageSize);

    /**
     * 根据反馈类型获取总记录数
     * @param  * @param sug
     * @return java.lang.Integer
     */
    Integer getCount(Sug sug);

    /**
     * 添加反馈信息
     * @param  * @param sug
     * @return boolean
     */
    boolean add(Sug sug);

}
