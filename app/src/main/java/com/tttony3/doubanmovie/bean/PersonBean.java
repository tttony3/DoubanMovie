package com.tttony3.doubanmovie.bean;

import java.io.Serializable;

/**
 * Created by tangli on 2016/5/24.
 * Website: https://github.com/tttony3
 */
public class PersonBean implements Serializable {
    protected AvatarsBean avatars;
    protected String name;
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
        this.avatars = avatars;
    }
}
