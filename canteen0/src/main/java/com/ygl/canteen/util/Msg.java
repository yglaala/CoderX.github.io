package com.ygl.canteen.util;

import java.util.HashMap;
import java.util.Map;


/*
 * 通用的返回类
 * */
public class Msg  {

	//状态码 100-成功 200-失败
	private int code;
	//提示信息
	private String msg;
	//返回的数据
	private Map<String, Object> info = new HashMap<String,Object>();

    public Msg(int code, String msg, Map<String, Object> info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

    public Msg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Msg(String msg) {
        this.msg = msg;
    }

    public Msg() {
    }

    public static Msg success() {
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("处理成功");
		return result;
	}
	
	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("处理失败");
		return result;
	}
	
	public  Msg add(String key,Object value) {
		this.getInfo().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}
	
}
