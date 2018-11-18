package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-09 11:2
 * 用户实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private Integer userId;//用户id
    private Emp emp;//员工
    private String userPwd;//密码
    private Integer roleId;//角色     1--管理员      2--普通用户
}
