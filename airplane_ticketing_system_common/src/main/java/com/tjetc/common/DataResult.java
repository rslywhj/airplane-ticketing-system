package com.tjetc.common;

import java.io.Serializable;

public class DataResult implements Serializable {
    private static final long serialVersionUID = -8652135406086375105L;
    private int state;
    private String msg;
    private Object data;

    public DataResult() {
    }

    public DataResult(int state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public boolean isQuery() {
        return msg == null;
    }
}
