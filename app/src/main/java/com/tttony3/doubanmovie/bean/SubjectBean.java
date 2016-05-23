package com.tttony3.doubanmovie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tangli on 2016/5/22.
 */
public class SubjectBean implements Serializable {
    /**
     * max : 10
     * average : 9.6
     * stars : 50
     * min : 0
     */

    private RatingBean rating;
    /**
     * rating : {"max":10,"average":9.6,"stars":"50","min":0}
     * reviews_count : 3821
     * wish_count : 61978
     * douban_site :
     * year : 1994
     * images : {"small":"http://img3.douban.com/view/movie_poster_cover/ipst/public/p480747492.jpg","large":"http://img3.douban.com/view/movie_poster_cover/lpst/public/p480747492.jpg","medium":"http://img3.douban.com/view/movie_poster_cover/spst/public/p480747492.jpg"}
     * alt : https://movie.douban.com/subject/1292052/
     * id : 1292052
     * mobile_url : https://movie.douban.com/subject/1292052/mobile
     * title : 肖申克的救赎
     * do_count : null
     * share_url : http://m.douban.com/movie/subject/1292052
     * seasons_count : null
     * schedule_url :
     * episodes_count : null
     * countries : ["美国"]
     * genres : ["犯罪","剧情"]
     * collect_count : 927113
     * casts : [{"alt":"https://movie.douban.com/celebrity/1054521/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/17525.jpg","large":"http://img3.douban.com/img/celebrity/large/17525.jpg","medium":"http://img3.douban.com/img/celebrity/medium/17525.jpg"},"name":"蒂姆·罗宾斯","id":"1054521"},{"alt":"https://movie.douban.com/celebrity/1054534/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/34642.jpg","large":"http://img3.douban.com/img/celebrity/large/34642.jpg","medium":"http://img3.douban.com/img/celebrity/medium/34642.jpg"},"name":"摩根·弗里曼","id":"1054534"},{"alt":"https://movie.douban.com/celebrity/1041179/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/5837.jpg","large":"http://img3.doubanio.com/img/celebrity/large/5837.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/5837.jpg"},"name":"鲍勃·冈顿","id":"1041179"},{"alt":"https://movie.douban.com/celebrity/1000095/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/7827.jpg","large":"http://img3.doubanio.com/img/celebrity/large/7827.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/7827.jpg"},"name":"威廉姆·赛德勒","id":"1000095"}]
     * current_season : null
     * original_title : The Shawshank Redemption
     * summary : 20世纪40年代末，小有成就的青年银行家安迪（蒂姆·罗宾斯 Tim Robbins 饰）因涉嫌杀害妻子及她的情人而锒铛入狱。在这座名为肖申克的监狱内，希望似乎虚无缥缈，终身监禁的惩罚无疑注定了安迪接下来灰暗绝望的人生。未过多久，安迪尝试接近囚犯中颇有声望的瑞德（摩根·弗里曼 Morgan Freeman 饰），请求对方帮自己搞来小锤子。以此为契机，二人逐渐熟稔，安迪也仿佛在鱼龙混杂、罪恶横生、黑白混淆的牢狱中找到属于自己的求生之道。他利用自身的专业知识，帮助监狱管理层逃税、洗黑钱，同时凭借与瑞德的交往在犯人中间也渐渐受到礼遇。表面看来，他已如瑞德那样对那堵高墙从憎恨转变为处之泰然，但是对自由的渴望仍促使他朝着心中的希望和目标前进。而关于其罪行的真相，似乎更使这一切朝前推进了一步……
     * 本片根据著名作家斯蒂芬·金（Stephen Edwin King）的原著改编。©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1047973/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/230.jpg","large":"http://img3.douban.com/img/celebrity/large/230.jpg","medium":"http://img3.douban.com/img/celebrity/medium/230.jpg"},"name":"弗兰克·德拉邦特","id":"1047973"}]
     * comments_count : 159372
     * ratings_count : 695919
     * aka : ["月黑高飞(港)","刺激1995(台)","地狱诺言","铁窗岁月","消香克的救赎"]
     */

    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    /**
     * small : http://img3.douban.com/view/movie_poster_cover/ipst/public/p480747492.jpg
     * large : http://img3.douban.com/view/movie_poster_cover/lpst/public/p480747492.jpg
     * medium : http://img3.douban.com/view/movie_poster_cover/spst/public/p480747492.jpg
     */

    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    /**
     * alt : https://movie.douban.com/celebrity/1054521/
     * avatars : {"small":"http://img3.douban.com/img/celebrity/small/17525.jpg","large":"http://img3.douban.com/img/celebrity/large/17525.jpg","medium":"http://img3.douban.com/img/celebrity/medium/17525.jpg"}
     * name : 蒂姆·罗宾斯
     * id : 1054521
     */

    private List<CastsBean> casts;
    /**
     * alt : https://movie.douban.com/celebrity/1047973/
     * avatars : {"small":"http://img3.douban.com/img/celebrity/small/230.jpg","large":"http://img3.douban.com/img/celebrity/large/230.jpg","medium":"http://img3.douban.com/img/celebrity/medium/230.jpg"}
     * name : 弗兰克·德拉邦特
     * id : 1047973
     */

    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public String toString() {
        return title + " " + id;
    }


}
