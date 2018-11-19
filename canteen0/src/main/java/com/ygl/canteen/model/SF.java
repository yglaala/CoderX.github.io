package com.ygl.canteen.model;

public class SF {

	private int id;
	private Menu menu;
	private Emp emp;
	private String createdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public SF() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SF(int id, Menu menu, Emp emp, String createdate) {
		super();
		this.id = id;
		this.menu = menu;
		this.emp = emp;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "SF [id=" + id + ", menu=" + menu + ", emp=" + emp
				+ ", createdate=" + createdate + "]";
	}

}
