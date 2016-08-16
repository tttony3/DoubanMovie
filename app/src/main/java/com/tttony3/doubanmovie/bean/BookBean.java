package com.tttony3.doubanmovie.bean;

import java.io.Serializable;

/**
 * Created by Mr_tao on 2016/5/30.
 */
public class BookBean implements Serializable {
    private String bookid;
    private String topnum;
    private String imgsrc;
    private String title;
    private String description;

    public String getTopnum() {
        return topnum;
    }

    public void setTopnum(String topnum) {
        this.topnum = topnum;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
