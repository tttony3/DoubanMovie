package com.tttony3.doubanmovie.bean;

/**
 * Created by tangli on 2016/5/23.
 * Website: https://github.com/tttony3
 */

import java.io.Serializable;
import java.util.List;

public class WorksBean implements Serializable {
    /**
     * rating : {"max":10,"average":9.3,"stars":"50","min":0}
     * genres : ["动画","惊悚","冒险"]
     * title : 花园墙外 第一季
     * casts : [{"alt":"https://movie.douban.com/celebrity/1354353/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img3.douban.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"科林·迪恩","id":"1354353"},{"alt":"https://movie.douban.com/celebrity/1054395/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/51597.jpg","large":"http://img3.doubanio.com/img/celebrity/large/51597.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/51597.jpg"},"name":"伊利亚·伍德","id":"1054395"},{"alt":"https://movie.douban.com/celebrity/1044729/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/56083.jpg","large":"http://img3.douban.com/img/celebrity/large/56083.jpg","medium":"http://img3.douban.com/img/celebrity/medium/56083.jpg"},"name":"梅兰妮·林斯基","id":"1044729"}]
     * collect_count : 3245
     * original_title : Over the Garden Wall
     * subtype : tv
     * directors : [{"alt":"https://movie.douban.com/celebrity/1352267/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/c4A2hFhLBxUcel_avatar_uploaded1444453249.48.jpg","large":"http://img3.doubanio.com/img/celebrity/large/c4A2hFhLBxUcel_avatar_uploaded1444453249.48.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/c4A2hFhLBxUcel_avatar_uploaded1444453249.48.jpg"},"name":"帕特里克·麦克海尔","id":"1352267"}]
     * year : 2014
     * images : {"small":"http://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2212480699.jpg","large":"http://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2212480699.jpg","medium":"http://img3.doubanio.com/view/movie_poster_cover/spst/public/p2212480699.jpg"}
     * alt : https://movie.douban.com/subject/25941842/
     * id : 25941842
     */

    private SubjectBean subject;
    private List<String> roles;

    public SubjectBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectBean subject) {
        this.subject = subject;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


}
