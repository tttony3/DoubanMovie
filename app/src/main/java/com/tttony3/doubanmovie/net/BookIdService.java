package com.tttony3.doubanmovie.net;

import com.tttony3.doubanmovie.bean.BookIdBean;
import com.tttony3.doubanmovie.bean.CastsBean;
import com.tttony3.doubanmovie.bean.MoviesBean;
import com.tttony3.doubanmovie.bean.SubjectBean;
import com.tttony3.doubanmovie.bean.USboxBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tttony3 on 2016/5/21.
 */
public interface BookIdService {

    @GET("gettopbooks/start/{start}/num/{num}")
    Observable<BookIdBean> getTopBooks(@Path("start") String start, @Path("num") String num);

    @GET("getchartbooks")
    Observable<BookIdBean> getChartBooks();
}
