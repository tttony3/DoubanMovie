package com.tttony3.doubanmovie.ui;

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
import com.tttony3.doubanmovie.bean.MoviesBean;
import com.tttony3.doubanmovie.bean.USboxBean;

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
    }

    private void fullDirectorsGallery(List directors, LinearLayout mGalleryDirectors) {
        for (Object tmp : directors) {
            View view = LayoutInflater.from(this).inflate(R.layout.gallery_item, mGalleryDirectors, false);
            ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
            TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
            if (type.equals("us")) {
                Glide.with(this).load(((USboxBean.SubjectsBean.SubjectBean.DirectorsBean) tmp).getAvatars().getLarge()).crossFade().into(img);
                txt.setText(((USboxBean.SubjectsBean.SubjectBean.DirectorsBean) tmp).getName());
            } else {
                Glide.with(this).load(((MoviesBean.SubjectsBean.DirectorsBean) tmp).getAvatars().getLarge()).crossFade().into(img);
                txt.setText(((MoviesBean.SubjectsBean.DirectorsBean) tmp).getName());
            }
            mGalleryDirectors.addView(view);
        }

    }

    private void fullCastsGallery(List casts, LinearLayout mGalleryDirectors) {
        for (Object tmp : casts) {
            View view = LayoutInflater.from(this).inflate(R.layout.gallery_item, mGalleryDirectors, false);
            ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
            TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
            if (type.equals("us")) {
                Glide.with(this).load(((USboxBean.SubjectsBean.SubjectBean.CastsBean) tmp).getAvatars().getLarge()).crossFade().into(img);
                txt.setText(((USboxBean.SubjectsBean.SubjectBean.CastsBean) tmp).getName());
            } else {
                Glide.with(this).load(((MoviesBean.SubjectsBean.CastsBean) tmp).getAvatars().getLarge()).crossFade().into(img);
                txt.setText(((MoviesBean.SubjectsBean.CastsBean) tmp).getName());
            }
            mGalleryCasts.addView(view);
        }

    }

}
