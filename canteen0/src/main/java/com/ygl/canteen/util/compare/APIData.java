package com.ygl.canteen.util.compare;

public class APIData {

    private String appid;
    private String urlA;
    private String urlB;

    public String getAppid() {
        return appid;
    }

    public APIData setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getUrlA() {
        return urlA;
    }

    public APIData setUrlA(String urlA) {
        this.urlA = urlA;
        return this;
    }

    public String getUrlB() {
        return urlB;
    }

    public APIData setUrlB(String urlB) {
        this.urlB = urlB;
        return this;
    }

    @Override
    public String toString() {
        return "APIData{" +
                "appid='" + appid + '\'' +
                ", urlA='" + urlA + '\'' +
                ", urlB='" + urlB + '\'' +
                '}';
    }
}
