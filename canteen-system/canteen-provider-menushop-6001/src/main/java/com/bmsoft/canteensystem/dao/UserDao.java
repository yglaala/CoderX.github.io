package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.User;

/**
 * 用户Dao接口
 */
public interface UserDao {

    /**
     * 通过id查找用户实体
     * @param id
     * @return
     */
    public User findById(Integer id);
}
