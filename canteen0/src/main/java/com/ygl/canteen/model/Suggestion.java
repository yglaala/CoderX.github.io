package com.ygl.canteen.model;

public class Suggestion {

	private int id;
	private String sug;
	private Emp emp;
	private String createdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSug() {
		return sug;
	}

	public void setSug(String sug) {
		this.sug = sug;
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

	@Override
	public String toString() {
		return "Suggestion [id=" + id + ", sug=" + sug + ", emp=" + emp
				+ ", createdate=" + createdate + "]";
	}

	public Suggestion(int id, String sug, Emp emp, String createdate) {
		super();
		this.id = id;
		this.sug = sug;
		this.emp = emp;
		this.createdate = createdate;
	}

	public Suggestion() {
		super();
	}

}
