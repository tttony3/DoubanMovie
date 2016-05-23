package com.tttony3.doubanmovie.bean;

import java.io.Serializable;

/**
 * Created by tangli on 2016/5/23.
 * Website: https://github.com/tttony3
 */
public class DirectorsBean implements Serializable {
    private String alt;
    /**
     * small : http://img3.douban.com/img/celebrity/small/230.jpg
     * large : http://img3.douban.com/img/celebrity/large/230.jpg
     * medium : http://img3.douban.com/img/celebrity/medium/230.jpg
     */

    private AvatarsBean avatars;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
