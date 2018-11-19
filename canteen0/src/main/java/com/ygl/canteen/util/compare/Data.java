package com.ygl.canteen.util.compare;

public class Data {

    private String session_id;
    private float similarity;
    private int fail_flag;

    public String getSession_id() {
        return session_id;
    }

    public Data setSession_id(String session_id) {
        this.session_id = session_id;
        return this;
    }

    public float getSimilarity() {
        return similarity;
    }

    public Data setSimilarity(float similarity) {
        this.similarity = similarity;
        return this;
    }

    public int getFail_flag() {
        return fail_flag;
    }

    public Data setFail_flag(int fail_flag) {
        this.fail_flag = fail_flag;
        return this;
    }

    @Override
    public String toString() {
        return "Data{" +
                "session_id='" + session_id + '\'' +
                ", similarity=" + similarity +
                ", fail_flag=" + fail_flag +
                '}';
    }
}
