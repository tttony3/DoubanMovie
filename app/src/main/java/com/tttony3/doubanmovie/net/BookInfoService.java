package com.tttony3.doubanmovie.net;

import com.tttony3.doubanmovie.bean.BookInfoBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Mr_tao on 2016/5/31.
 */
public interface BookInfoService {
    @GET("{id}")
    Observable<BookInfoBean> getBookInfo(@Path("id") String id);
}
