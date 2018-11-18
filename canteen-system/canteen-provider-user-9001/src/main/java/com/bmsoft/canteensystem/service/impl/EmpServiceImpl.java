package com.bmsoft.canteensystem.service.impl;
/**
 * USER-PROVIDER   EmpServiceImpl层
 * @Author liugaoyang
 */

import com.bmsoft.canteensystem.mapper.EmpMapper;
import com.bmsoft.canteensystem.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 获取员工照片路径
     * @param empNo 员工号
     * @return
     */
    @Override
    public String getPic(String empNo) {
        return empMapper.getPicByEmpNo(empNo);
    }

    /**
     * 获取员工身份证号码
     * @param empNo 员工号
     * @return
     */
    @Override
    public String getEmpNumberId(String empNo) {
        return empMapper.getEmpNumberIdByEmpNo(empNo);
    }


}
