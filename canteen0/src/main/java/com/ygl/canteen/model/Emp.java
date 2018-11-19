package com.ygl.canteen.model;

import java.util.List;

public class Emp {

    private int id;
    private String username;
    private String pwd;
    private Dept dept;
    private String empno;
    private String name;
    private List<Suggestion> suggestions;
    private String createdate;
    private Role role;

    public Emp() {
        super();
    }

    public Emp(int id) {
        this.id = id;
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Emp(int id, String username, String pwd, Dept dept, String empno, String name, List<Suggestion> suggestions, String createdate, Role role) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.dept = dept;
        this.empno = empno;
        this.name = name;
        this.suggestions = suggestions;
        this.createdate = createdate;
        this.role = role;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getEmpno() {
        return empno;
    }

    public Emp setEmpno(String empno) {
        this.empno = empno;
        return this;
    }

    public String getName() {
        return name;
    }

    public Emp setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", dept=" + dept +
                ", empno='" + empno + '\'' +
                ", name='" + name + '\'' +
                ", suggestions=" + suggestions +
                ", createdate='" + createdate + '\'' +
                ", role=" + role +
                '}';
    }
}
