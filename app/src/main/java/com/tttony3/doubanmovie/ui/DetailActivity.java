package com.tttony3.doubanmovie.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
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
import com.tttony3.doubanmovie.bean.MoviesBean;
import com.tttony3.doubanmovie.bean.USboxBean;
import com.tttony3.doubanmovie.interfaces.SubscriberOnNextListener;
import com.tttony3.doubanmovie.net.HttpMethods;
import com.tttony3.doubanmovie.net.NormalSubscriber;
import com.tttony3.doubanmovie.utils.GlideCircleTransform;

import java.util.List;
import java.util.Objects;

/**
 * Created by tttony3 on 2016/5/21.
 */
public class DetailActivity extends AppCompatActivity {
    String TAG = "DetailActivity";
    ImageView backdrop;
    MoviesBean.SubjectsBean bean;
    USboxBean.SubjectsBean usBean;
    TextView tvOriginalTitle;
    TextView tvRating;
    TextView tvCasts;
    TextView tvGenres;
    TextView tvDirectors;
    TextView tvsummary;
    Bitmap bm;
    LinearLayout mGalleryCasts;
    LinearLayout mGalleryDirectors;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        type = getIntent().getStringExtra("type");
        if (type.equals("us"))
            usBean = (USboxBean.SubjectsBean) getIntent().getSerializableExtra("bean");
        else
            bean = (MoviesBean.SubjectsBean) getIntent().getSerializableExtra("bean");
        bm = getIntent().getParcelableExtra("bitmap");
        backdrop = (ImageView) findViewById(R.id.backdrop);
        backdrop.setImageBitmap(bm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if (type.equals("us"))
            collapsingToolbar.setTitle(usBean.getSubject().getTitle());
        else
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

        if (type.equals("us")) {
            fullCastsGallery(usBean.getSubject().getCasts(), mGalleryDirectors);
            fullDirectorsGallery(usBean.getSubject().getDirectors(), mGalleryDirectors);
            tvOriginalTitle.setText(usBean.getSubject().getOriginal_title());
            tvRating.setText(usBean.getSubject().getRating().getAverage() + "分");
            for (String tmp : usBean.getSubject().getGenres()) {
                tvGenres.append(tmp + " ");
            }
        } else {
            fullCastsGallery(bean.getCasts(), mGalleryDirectors);
            fullDirectorsGallery(bean.getDirectors(), mGalleryDirectors);
            tvOriginalTitle.setText(bean.getOriginal_title());
            tvRating.setText(bean.getRating().getAverage() + "分");
            for (String tmp : bean.getGenres()) {
                tvGenres.append(tmp + " ");
            }
        }

        HttpMethods.getInstance().getSubjuct(new NormalSubscriber<String>(new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String str) {
                tvsummary.setText("\t\t" + str);
            }
        }, DetailActivity.this), (type.equals("us") ? usBean.getSubject().getId() : bean.getId()));
    }

    private void fullDirectorsGallery(List directors, LinearLayout mGalleryDirectors) {
        for (Object tmp : directors) {
            View view = LayoutInflater.from(this).inflate(R.layout.gallery_item, mGalleryDirectors, false);
            ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
            TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
            if (type.equals("us")) {
                if (((USboxBean.SubjectsBean.SubjectBean.DirectorsBean) tmp).getAvatars() != null) {
                    Glide.with(this).load(((USboxBean.SubjectsBean.SubjectBean.DirectorsBean) tmp).getAvatars().getLarge()).transform(new GlideCircleTransform(this)).crossFade().into(img);
                    view.setOnClickListener(new ImgOnclickListener(((USboxBean.SubjectsBean.SubjectBean.DirectorsBean) tmp).getId(),
                            ((USboxBean.SubjectsBean.SubjectBean.DirectorsBean) tmp).getAvatars().getLarge(),
                            ((USboxBean.SubjectsBean.SubjectBean.DirectorsBean) tmp).getName()));
                }txt.setText(((USboxBean.SubjectsBean.SubjectBean.DirectorsBean) tmp).getName());

            } else {
                if (((MoviesBean.SubjectsBean.DirectorsBean) tmp).getAvatars() != null) {
                    Glide.with(this).load(((MoviesBean.SubjectsBean.DirectorsBean) tmp).getAvatars().getLarge()).crossFade().transform(new GlideCircleTransform(this)).into(img);
                    view.setOnClickListener(new ImgOnclickListener(((MoviesBean.SubjectsBean.DirectorsBean) tmp).getId(),
                            ((MoviesBean.SubjectsBean.DirectorsBean) tmp).getAvatars().getLarge(),
                            ((MoviesBean.SubjectsBean.DirectorsBean) tmp).getName()));
                }txt.setText(((MoviesBean.SubjectsBean.DirectorsBean) tmp).getName());

            }
            mGalleryDirectors.addView(view);

        }

    }

    private static final String KEY_ID = "ViewTransitionValues:id";

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


    private void fullCastsGallery(List casts, LinearLayout mGalleryDirectors) {
        for (Object tmp : casts) {
            View view = LayoutInflater.from(this).inflate(R.layout.gallery_item, mGalleryDirectors, false);
            ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
            TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
            if (type.equals("us")) {
                if (((USboxBean.SubjectsBean.SubjectBean.CastsBean) tmp).getAvatars() != null) {
                    Glide.with(this).load(((USboxBean.SubjectsBean.SubjectBean.CastsBean) tmp).getAvatars().getLarge()).crossFade().transform(new GlideCircleTransform(this)).into(img);
                    view.setOnClickListener(new ImgOnclickListener(((USboxBean.SubjectsBean.SubjectBean.CastsBean) tmp).getId(),
                            ((USboxBean.SubjectsBean.SubjectBean.CastsBean) tmp).getAvatars().getLarge(),
                            ((USboxBean.SubjectsBean.SubjectBean.CastsBean) tmp).getName()));
                }txt.setText(((USboxBean.SubjectsBean.SubjectBean.CastsBean) tmp).getName());

            } else {
                if (((MoviesBean.SubjectsBean.CastsBean) tmp).getAvatars() != null) {
                    Glide.with(this).load(((MoviesBean.SubjectsBean.CastsBean) tmp).getAvatars().getLarge()).crossFade().transform(new GlideCircleTransform(this)).into(img);
                    view.setOnClickListener(new ImgOnclickListener(((MoviesBean.SubjectsBean.CastsBean) tmp).getId(),
                            ((MoviesBean.SubjectsBean.CastsBean) tmp).getAvatars().getLarge(),
                            ((MoviesBean.SubjectsBean.CastsBean) tmp).getName()));
                }txt.setText(((MoviesBean.SubjectsBean.CastsBean) tmp).getName());

            }
            mGalleryCasts.addView(view);
        }

    }

}
