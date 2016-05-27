package com.tttony3.doubanmovie.net;

import android.util.Log;

import com.tttony3.doubanmovie.bean.BookIdBean;
import com.tttony3.doubanmovie.bean.CastsBean;
import com.tttony3.doubanmovie.bean.MoviesBean;
import com.tttony3.doubanmovie.bean.SubjectBean;
import com.tttony3.doubanmovie.bean.SubjectsBean;
import com.tttony3.doubanmovie.bean.USboxBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tttony3 on 2016/5/21.
 */
public class HttpMethods {
    public static final String BOOK ="book";
    private String TAG = "HttpMethods";
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    public static final String BOOKID_URL = "http://laddermaps.com/index.php/getbooks/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService mMovieService;
    private BookIdService mBookIdService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mMovieService = retrofit.create(MovieService.class);
    }

    private HttpMethods(String type) {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    if(type.equals(BOOK)){
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BOOKID_URL)
                .build();

        mBookIdService = retrofit.create(BookIdService.class);
    }
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
        private static final HttpMethods BOOKID_INSTANCE = new HttpMethods(BOOK);
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public static HttpMethods getInstance(String type) {
        if(type.equals(BOOK))
            return SingletonHolder.BOOKID_INSTANCE;
        else
            return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取老司机给的ID
     */
    public void getTopBook(Subscriber<List<BookIdBean>> subscruber, String start, String num){

    }

    /**
     * 用于获取豆瓣电影Top250的数据
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param start      起始位置
     * @param count      获取长度
     */
    public void getTopMovie(Subscriber<List<SubjectBean>> subscriber, int start, int count) {
        Log.v(TAG, "getTopMovie");
        mMovieService.getTopMovie(start, count)
                .map(new Func1<MoviesBean, List<SubjectBean>>() {
                    @Override
                    public List<SubjectBean> call(MoviesBean moviesBean) {
                        return moviesBean.getSubjects();
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用于获取豆瓣北美票房榜的数据
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void getUSBox(Subscriber<List<SubjectBean>> subscriber) {
        Log.v(TAG, "getUSBox");
        mMovieService.getUSBox()
                .map(new Func1<USboxBean, List<SubjectBean>>() {
                    @Override
                    public List<SubjectBean> call(USboxBean uSboxBean) {
                        Log.v(TAG, uSboxBean.toString());
                        List<SubjectBean> list = new ArrayList<SubjectBean>();
                        for (SubjectsBean tmp : uSboxBean.getSubjects()) {
                            list.add(tmp.getSubject());
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用于获取影片详细介绍
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param id 影片id
     */
    public void getSubjuct(Subscriber<String> subscriber, String id){
        Log.v(TAG,"getSubject "+id);
        mMovieService.getSubject(id)
                .map(new Func1<SubjectBean, String>() {
                    @Override
                    public String call(SubjectBean bean) {
                        return bean.getSummary();
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 用于获取影片详细介绍
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param id         影片id
     */
    public void getCastDetail(Subscriber<CastsBean> subscriber, String id) {
        Log.v(TAG, "getSubject " + id);
        mMovieService.getCastDetail(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

}
