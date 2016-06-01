package com.tttony3.doubanmovie.net;

import com.tttony3.doubanmovie.bean.BookBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Mr_tao on 2016/5/21.
 */
public interface BookIdService {

    @GET("gettopbooks/start/{start}/num/{num}")
    Observable<List<BookBean>> getTopBooksId(@Path("start") String start, @Path("num") String num);

    @GET("getchartbooks")
    Observable<List<BookBean>> getChartBooks();
}
