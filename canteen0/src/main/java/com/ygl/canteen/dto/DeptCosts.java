package com.ygl.canteen.dto;

import com.ygl.canteen.util.StringUtil;

import java.util.List;

public class DeptCosts {

    private String date = "北明各部门人员食堂";
    private List<DeptCost> deptCostList;

    public String getDate() {
        return date;
    }

    public DeptCosts setDate(String date) {
        if (date.equals(null) || date.length() < 4) {
            this.date += "历史";
        }
        if (date.length() >= 4) {
            this.date += date.substring(0,4) + "年";
        }
        if (date.length() >= 7) {
            this.date += StringUtil.StringToInt(date.substring(5, 7)) + "月";
        }
        if(date.length() >= 10){
            this.date += StringUtil.StringToInt(date.substring(8,10))+"日";
        }
        this.date += "消费统计";
        return this;
    }

    public List<DeptCost> getDeptCostList() {
        return deptCostList;
    }

    public DeptCosts setDeptCostList(List<DeptCost> deptCostList) {
        this.deptCostList = deptCostList;
        return this;
    }

    @Override
    public String toString() {
        return "DeptCosts{" +
                "date='" + date + '\'' +
                ", deptCostList=" + deptCostList +
                '}';
    }

    /* public static  void main(String[] args){

        DeptCosts deptCosts = new DeptCosts();
        deptCosts.setDate("");
        System.out.println(deptCosts.getDate());
    }*/
}
