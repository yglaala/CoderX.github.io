package com.ygl.canteen.mapper;

import com.ygl.canteen.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {

    boolean add(Permission permission);
    List<Permission> list(int roleId);
}
