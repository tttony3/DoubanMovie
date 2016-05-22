package com.tttony3.doubanmovie.net;

import com.tttony3.doubanmovie.bean.MoviesBean;
import com.tttony3.doubanmovie.bean.SubjectBean;
import com.tttony3.doubanmovie.bean.USboxBean;
import com.tttony3.doubanmovie.ui.CastDetailActivity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tttony3 on 2016/5/21.
 */
public interface MovieService {
    @GET("top250")
    Observable<MoviesBean> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("us_box")
    Observable<USboxBean> getUSBox();

    @GET("subject/{id}")
    Observable<SubjectBean> getSubject(@Path("id") String id);

    @GET("celebrity/{id}")
    Observable<CastDetailActivity> getCastDetail(@Path("id") String id);
}
