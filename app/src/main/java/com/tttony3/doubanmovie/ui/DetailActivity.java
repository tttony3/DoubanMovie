package com.tttony3.doubanmovie.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tttony3.doubanmovie.R;
import com.tttony3.doubanmovie.bean.CastsBean;
import com.tttony3.doubanmovie.bean.DirectorsBean;
import com.tttony3.doubanmovie.bean.PersonBean;
import com.tttony3.doubanmovie.bean.SubjectBean;
import com.tttony3.doubanmovie.interfaces.SubscriberOnNextListener;
import com.tttony3.doubanmovie.net.HttpMethods;
import com.tttony3.doubanmovie.net.NormalSubscriber;
import com.tttony3.doubanmovie.utils.GlideCircleTransform;

import java.util.List;

/**
 * Created by tttony3 on 2016/5/21.
 */
public class DetailActivity extends AppCompatActivity {
    String TAG = "DetailActivity";
    ImageView backdrop;
    SubjectBean bean;

    TextView tvOriginalTitle;
    TextView tvRating;
    TextView tvCasts;
    TextView tvGenres;
    TextView tvDirectors;
    TextView tvsummary;
    Bitmap bm;
    LinearLayout mGalleryCasts;
    LinearLayout mGalleryDirectors;
    private static final String KEY_ID = "ViewTransitionValues:id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bean = (SubjectBean) getIntent().getSerializableExtra("bean");
        bm = getIntent().getParcelableExtra("bitmap");
        backdrop = (ImageView) findViewById(R.id.backdrop);
        backdrop.setImageBitmap(bm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle(bean.getTitle());
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.translate));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.graywhite));

        initView();
    }

    private void initView() {
        tvOriginalTitle = (TextView) findViewById(R.id.tv_original_title);
        tvRating = (TextView) findViewById(R.id.tv_rating);
        tvCasts = (TextView) findViewById(R.id.tv_casts);
        tvGenres = (TextView) findViewById(R.id.tv_genres);
        tvsummary = (TextView) findViewById(R.id.tv_summary);
        tvDirectors = (TextView) findViewById(R.id.tv_directors);
        mGalleryCasts = (LinearLayout) findViewById(R.id.gallery_casts);
        mGalleryDirectors = (LinearLayout) findViewById(R.id.gallery_directors);

        fullGallery(bean.getCasts(), mGalleryCasts);
        fullGallery(bean.getDirectors(), mGalleryDirectors);
        tvOriginalTitle.setText(bean.getOriginal_title());
        tvRating.setText(bean.getRating().getAverage() + "分");
        for (String tmp : bean.getGenres()) {
            tvGenres.append(tmp + " ");
        }

        HttpMethods.getInstance().getSubjuct(new NormalSubscriber<String>(new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String str) {
                tvsummary.setText("\t\t" + str);
            }
        }, DetailActivity.this), bean.getId());
    }

    private <T extends PersonBean> void fullGallery(List<T> directors, LinearLayout mGalleryDirectors) {
        for (T tmp : directors) {
            View view = LayoutInflater.from(this).inflate(R.layout.gallery_item, mGalleryDirectors, false);
            ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
            TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
            if (tmp.getAvatars() != null) {
                Glide.with(this).load(tmp.getAvatars().getLarge()).crossFade().transform(new GlideCircleTransform(this)).into(img);
                view.setOnClickListener(new ImgOnclickListener(tmp.getId(),
                        tmp.getAvatars().getLarge(),
                        tmp.getName()));
            }
            txt.setText(tmp.getName());
            mGalleryDirectors.addView(view);

        }

    }


    class ImgOnclickListener implements View.OnClickListener {
        String id;
        String imgurl;
        String name;

        ImgOnclickListener(String id, String imgurl, String name) {
            this.id = id;
            this.imgurl = imgurl;
            this.name = name;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DetailActivity.this, CastDetailActivity.class);
            intent.putExtra(KEY_ID, v.getTransitionName());
            intent.putExtra("id", id);
            intent.putExtra("imgurl", imgurl);
            intent.putExtra("name", name);
            ActivityOptions activityOptions
                    = ActivityOptions.makeSceneTransitionAnimation(DetailActivity.this, v.findViewById(R.id.gallery_item_image), "castImg");

            startActivity(intent, activityOptions.toBundle());
        }
    }
}
