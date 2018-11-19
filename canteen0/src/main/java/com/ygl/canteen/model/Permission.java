package com.ygl.canteen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
public class Permission {

    private int id;
    private String name;
    private Permission parentPermission;
    private List<Permission> childPermissions;
    /*private List<Role> roles;*/
}
