package com.tttony3.doubanmovie.bean;

import java.io.Serializable;

/**
 * Created by tangli on 2016/5/23.
 * Website: https://github.com/tttony3
 */
public class AvatarsBean implements Serializable {
    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}

