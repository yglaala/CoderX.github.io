package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Sug;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chengpeng
 * @create 2018-10-18 14:09
 */
@Mapper
public interface SugDao {

    /**
     * 根据反馈类型查询反馈信息
     * @param  * @param sug 
     * @return java.util.List<com.bmsoft.canteensystem.entity.Sug>
     */
    List<Sug> find(Sug sug);

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
