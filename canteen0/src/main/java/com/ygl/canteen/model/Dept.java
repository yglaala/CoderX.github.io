package com.ygl.canteen.model;

import java.io.Serializable;
import java.util.List;

public class Dept {

    /*private static final long serialVersionUID = 1L;*/
	private int id;
	private String name;
	private List<Emp> emps;
	private String createdate;

	public Dept(int id) {
		this.id = id;
	}

	public Dept() {
		super();
	}
	
	public Dept(int id, String name, List<Emp> emps,String createdate) {
		super();
		this.id = id;
		this.name = name;
		this.emps = emps;
		this.createdate =createdate;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Emp> getEmps() {
		return emps;
	}
	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}
	
	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + ", emps=" + emps + "]";
	}
	
}
