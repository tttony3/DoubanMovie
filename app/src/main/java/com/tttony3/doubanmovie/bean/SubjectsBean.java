package com.tttony3.doubanmovie.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tangli on 2016/5/23.
 * Website: https://github.com/tttony3
 */
public class SubjectsBean implements Serializable {
    private int box;
    @SerializedName("new")
    private boolean newX;
    private int rank;
    /**
     * rating : {"max":10,"average":7.9,"stars":"40","min":0}
     * genres : ["动作","科幻","冒险"]
     * title : 美国队长3
     * casts : [{"alt":"https://movie.douban.com/celebrity/1017885/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/1359877729.49.jpg","large":"http://img3.doubanio.com/img/celebrity/large/1359877729.49.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/1359877729.49.jpg"},"name":"克里斯·埃文斯","id":"1017885"},{"alt":"https://movie.douban.com/celebrity/1016681/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/4938.jpg","large":"http://img3.doubanio.com/img/celebrity/large/4938.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/4938.jpg"},"name":"小罗伯特·唐尼","id":"1016681"},{"alt":"https://movie.douban.com/celebrity/1054453/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/37050.jpg","large":"http://img3.douban.com/img/celebrity/large/37050.jpg","medium":"http://img3.douban.com/img/celebrity/medium/37050.jpg"},"name":"斯嘉丽·约翰逊","id":"1054453"}]
     * collect_count : 141282
     * original_title : Captain America: Civil War
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1321812/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/51466.jpg","large":"http://img3.doubanio.com/img/celebrity/large/51466.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/51466.jpg"},"name":"安东尼·罗素","id":"1321812"},{"alt":"https://movie.douban.com/celebrity/1320870/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/1462849809.13.jpg","large":"http://img3.douban.com/img/celebrity/large/1462849809.13.jpg","medium":"http://img3.douban.com/img/celebrity/medium/1462849809.13.jpg"},"name":"乔·罗素","id":"1320870"}]
     * year : 2016
     * images : {"small":"http://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2332503406.jpg","large":"http://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2332503406.jpg","medium":"http://img3.doubanio.com/view/movie_poster_cover/spst/public/p2332503406.jpg"}
     * alt : https://movie.douban.com/subject/25820460/
     * id : 25820460
     */

    private SubjectBean subject;

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public SubjectBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectBean subject) {
        this.subject = subject;
    }


}
