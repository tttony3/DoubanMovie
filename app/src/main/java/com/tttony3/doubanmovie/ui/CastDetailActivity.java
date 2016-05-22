package com.tttony3.doubanmovie.ui;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tttony3.doubanmovie.R;
import com.tttony3.doubanmovie.utils.GlideCircleTransform;

public class CastDetailActivity extends AppCompatActivity {
    ImageView img ;
    String id ;
    String imgurl;
    String name ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        imgurl = getIntent().getStringExtra("imgurl");
        name = getIntent().getStringExtra("name");
        setContentView(R.layout.activity_cast_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);

        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        Glide.with(this).load(imgurl).transform(new GlideCircleTransform(this)).into(img);
    }
}
