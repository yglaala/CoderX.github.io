package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengpeng
 * @create 2018-10-16 20:30
 */
@Mapper
public interface UserDao {
    /**
     * 功能描述:根据用户id查询用户信息
     * @param  * @param userId 
     * @return com.bmsoft.canteensystem.entity.User
     */
    User findById(Integer userId);
}
