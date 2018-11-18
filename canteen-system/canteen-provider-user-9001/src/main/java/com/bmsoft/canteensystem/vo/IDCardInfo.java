package com.bmsoft.canteensystem.vo;

/**
 * 用于提取身份证数据的vo
 * @Author liugaoyang
 */
public class IDCardInfo {
    private String name;//姓名
    private String id;//身份证号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
