package com.ygl.canteen.mapper;

import com.ygl.canteen.model.Status;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StatusMapper {

    @Select("select * from t_status where id = #{id}")
    Status getStatusById(int id);
}
