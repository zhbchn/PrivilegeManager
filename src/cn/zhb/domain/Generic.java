package cn.zhb.domain;

import java.util.UUID;

public class Generic {
	private String id;

    public Generic() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
