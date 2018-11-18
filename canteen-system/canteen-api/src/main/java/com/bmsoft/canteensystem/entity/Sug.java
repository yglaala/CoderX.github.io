package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:22
 * 用餐建议实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Sug implements Serializable{

    private Integer sugId;//用餐建议id
    private Integer userId;//用户id
    private String sugContent;//用餐建议内容
    private String sugCreateDate;//建议创建时间
    private String sugType;//反馈类型
}
