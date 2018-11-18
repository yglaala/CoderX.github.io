package com.bmsoft.canteensystem.service;

/**
 * USER-PROVIDER   EmpService层
 * @Author liugaoyang
 */

public interface EmpService {

    //获取员工照片路径逻辑接口
    public String getPic(String empNo);

    //获取员工身份证号码逻辑接口
    public String getEmpNumberId(String empNo);
}
