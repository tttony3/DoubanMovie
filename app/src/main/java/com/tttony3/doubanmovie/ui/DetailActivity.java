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
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tttony3.doubanmovie.R;
import com.tttony3.doubanmovie.bean.MoviesBean;
import com.tttony3.doubanmovie.bean.USboxBean;

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
        Fresco.initialize(this);
        setContentView(R.layout.activity_detail);
        type = getIntent().getStringExtra("type");
        if (type.equals("us"))
            usBean = (USboxBean.SubjectsBean) getIntent().getSerializableExtra("bean");
        else
            bean = (MoviesBean.SubjectsBean) getIntent().getSerializableExtra("bean");
        bm = getIntent().getParcelableExtra("bitmap");
        backdrop = (ImageView) findViewById(R.id.backdrop);
        backdrop.setImageBitmap(bm);
        //  Glide.with(this).load(bean.getImages().getLarge()).dontAnimate().into(backdrop);
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
            tvOriginalTitle.setText(usBean.getSubject().getOriginal_title());
            tvRating.setText(usBean.getSubject().getRating().getAverage() + "分");
            for (int i = 0; i < usBean.getSubject().getCasts().size(); i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.gallery_item, mGalleryCasts, false);
                ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
                Glide.with(this).load(usBean.getSubject().getCasts().get(i).getAvatars().getLarge()).crossFade().into(img);
                TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
                txt.setText(usBean.getSubject().getCasts().get(i).getName());
                mGalleryCasts.addView(view);
            }
            for (int i = 0; i < usBean.getSubject().getGenres().size(); i++) {
                if (i != usBean.getSubject().getGenres().size() - 1)
                    tvGenres.append(usBean.getSubject().getGenres().get(i) + " ");
                else
                    tvGenres.append(usBean.getSubject().getGenres().get(i));
            }
            for (int i = 0; i < usBean.getSubject().getDirectors().size(); i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.gallery_item, mGalleryDirectors, false);
                ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
                Glide.with(this).load(usBean.getSubject().getDirectors().get(i).getAvatars().getLarge()).crossFade().into(img);
                TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
                txt.setText(usBean.getSubject().getDirectors().get(i).getName());
                mGalleryDirectors.addView(view);
            }
        } else {
            tvOriginalTitle.setText(bean.getOriginal_title());
            tvRating.setText(bean.getRating().getAverage() + "分");
            for (int i = 0; i < bean.getCasts().size(); i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.gallery_item, mGalleryCasts, false);
                ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
                Glide.with(this).load(bean.getCasts().get(i).getAvatars().getLarge()).crossFade().into(img);
                TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
                txt.setText(bean.getCasts().get(i).getName());
                mGalleryCasts.addView(view);
            }
            for (int i = 0; i < bean.getGenres().size(); i++) {
                if (i != bean.getGenres().size() - 1)
                    tvGenres.append(bean.getGenres().get(i) + " ");
                else
                    tvGenres.append(bean.getGenres().get(i));
            }
            for (int i = 0; i < bean.getDirectors().size(); i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.gallery_item, mGalleryDirectors, false);
                ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
                Glide.with(this).load(bean.getDirectors().get(i).getAvatars().getLarge()).crossFade().into(img);
                TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
                txt.setText(bean.getDirectors().get(i).getName());
                mGalleryDirectors.addView(view);
            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
