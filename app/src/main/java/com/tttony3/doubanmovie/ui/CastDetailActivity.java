package com.tttony3.doubanmovie.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tttony3.doubanmovie.R;
import com.tttony3.doubanmovie.bean.CastsBean;
import com.tttony3.doubanmovie.bean.WorksBean;
import com.tttony3.doubanmovie.interfaces.SubscriberOnNextListener;
import com.tttony3.doubanmovie.net.HttpMethods;
import com.tttony3.doubanmovie.net.NormalSubscriber;
import com.tttony3.doubanmovie.utils.GlideCircleTransform;

public class CastDetailActivity extends AppCompatActivity {
    ImageView img ;
    String id ;
    String imgurl;
    String name ="";
    LinearLayout galleryWorks;
    TextView tvUrl;
    TextView tvBornPlace;
    private static final String KEY_ID = "ViewTransitionValues:id";
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
        tvBornPlace = (TextView) findViewById(R.id.tv_born_place);
        tvUrl = (TextView) findViewById(R.id.url);
        galleryWorks = (LinearLayout) findViewById(R.id.gallery_works);

        Glide.with(this).load(imgurl).transform(new GlideCircleTransform(this)).into(img);

        HttpMethods.getInstance().getCastDetail(new NormalSubscriber<CastsBean>(new SubscriberOnNextListener<CastsBean>() {
            @Override
            public void onNext(CastsBean o) {
                tvUrl.setText(o.getMobile_url());
                tvBornPlace.setText(o.getBorn_place());
                for (final WorksBean tmp : o.getWorks()) {
                    View view = LayoutInflater.from(CastDetailActivity.this).inflate(R.layout.gallery_item_castworks, galleryWorks, false);
                    final ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
                    TextView txt = (TextView) view.findViewById(R.id.gallery_item_text);
                    txt.setText(tmp.getSubject().getTitle());
                    Glide.with(CastDetailActivity.this).load(tmp.getSubject().getImages().getLarge()).into(img);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(CastDetailActivity.this, DetailActivity.class);
                            intent.putExtra(KEY_ID, v.getTransitionName());
                            intent.putExtra("bean", tmp.getSubject());
                            intent.putExtra("bitmap", drawableToBitmap(img.getDrawable()));
                            ActivityOptions activityOptions
                                    = ActivityOptions.makeSceneTransitionAnimation(CastDetailActivity.this, v.findViewById(R.id.gallery_item_image), "img");

                            startActivity(intent, activityOptions.toBundle());
                        }
                    });
                    galleryWorks.addView(view);
                }

            }
        }, this), id);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}
