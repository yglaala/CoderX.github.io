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
@Accessors(chain = true)
@ToString
public class Role {

	private int id;
	private String name;
	private List<Emp> emps;
	private String createdate;
	private List<EmpPermission> empPermissions;
}
