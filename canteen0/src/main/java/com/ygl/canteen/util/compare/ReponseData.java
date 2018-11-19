package com.ygl.canteen.util.compare;

public class ReponseData {

    private int code;
    private String message;
    private Data data;

    public int getCode() {
        return code;
    }

    public ReponseData setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReponseData setMessage(String message) {
        this.message = message;
        return this;
    }

    public Data getData() {
        return data;
    }

    public ReponseData setData(Data data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ReponseData{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
