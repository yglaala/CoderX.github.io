package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author guoguo
 * 员工表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Emp implements Serializable {

    private String empNo;//员工号
    private String empNumberId;//身份证号
    private String empName;//员工名
    private String empPicPath;//员工图片路径
    private Dept dept;//员工部门
}
