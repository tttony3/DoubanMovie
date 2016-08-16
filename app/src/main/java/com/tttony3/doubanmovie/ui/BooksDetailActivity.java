package com.tttony3.doubanmovie.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tttony3.doubanmovie.R;
import com.tttony3.doubanmovie.bean.BookBean;
import com.tttony3.doubanmovie.bean.BookInfoBean;
import com.tttony3.doubanmovie.interfaces.SubscriberOnNextListener;
import com.tttony3.doubanmovie.net.HttpMethods;
import com.tttony3.doubanmovie.net.NormalSubscriber;

import retrofit2.http.HTTP;

/**
 * Created by Mr_tao on 2016/5/31.
 */
public class BooksDetailActivity extends AppCompatActivity {
    private String TAG = "BooksDetailActivity";
    ImageView backdrop;
    BookBean bean;

    String id;
    String name;
    TextView book_author;
    TextView book_rating;
    TextView book_page;
    TextView book_binding;
    TextView book_publish;
    TextView book_author_info;
    TextView book_info;
    Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        //bean = (BookBean) getIntent().getSerializableExtra("bean");
        bm = getIntent().getParcelableExtra("bitmap");
        backdrop = (ImageView) findViewById(R.id.backdrop);
        backdrop.setImageBitmap(bm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle(name);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.translate));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.graywhite));

        initview();
    }

    private void initview() {
        book_author = (TextView) findViewById(R.id.book_author);
        book_rating = (TextView) findViewById(R.id.book_rating);
        book_page = (TextView) findViewById(R.id.book_page);
        book_binding = (TextView) findViewById(R.id.book_binding);
        book_publish = (TextView) findViewById(R.id.book_publish);
        book_author_info = (TextView) findViewById(R.id.book_author_info);
        book_info = (TextView) findViewById(R.id.book_info);


        HttpMethods.getInstance(HttpMethods.BOOK_INFO).getBookInfo(new NormalSubscriber<BookInfoBean>(new SubscriberOnNextListener<BookInfoBean>() {
            @Override
            public void onNext(BookInfoBean bookinfo) {
                book_author.setText(bookinfo.getAuthor().get(0));
                book_rating.setText(bookinfo.getRating().getAverage() + "分");
                book_page.setText(bookinfo.getPages());
                book_binding.setText(bookinfo.getBinding());
                book_publish.setText(bookinfo.getPublisher());
                book_author_info.setText("\t\t" + bookinfo.getAuthor_intro());
                book_info.setText("\t\t" + bookinfo.getSummary());
                Glide.with(BooksDetailActivity.this).load(bookinfo.getImages().getLarge()).into(backdrop);
            }
        }, BooksDetailActivity.this), id);


    }
}
