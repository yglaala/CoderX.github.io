package com.bmsoft.canteensystem.mapper;
/**
 * USER-PROVIDER EmpMapper
 * @Author liugaoyang
 */
import com.bmsoft.canteensystem.entity.Emp;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmpMapper {

    /**
     * 通过empNo获取emp对象
     * @param empNo
     * @return
     */
    @Select("SELECT * FROM tb_emp where emp_no = #{empNo}")
    @Results(
            @Result(property = "dept",
            column = "dept_id",
            one = @One(select = "com.bmsoft.canteensystem.mapper.DeptMapper.findById")))
    public Emp findById(String empNo);


    /**
     * 通过empNo获取empPicPath相片路径
     * @param empNo
     * @return
     */
    @Select("SELECT emp_pic_path FROM tb_emp where emp_no = #{empNo}")
    public String getPicByEmpNo(String empNo);

    /**
     * 通过EmpNo获取empNumberId身份证号
     * @param empNo
     * @return
     */
    @Select("SELECT emp_number_id FROM tb_emp where emp_no = #{empNo}")
    public String getEmpNumberIdByEmpNo(String empNo);
}
