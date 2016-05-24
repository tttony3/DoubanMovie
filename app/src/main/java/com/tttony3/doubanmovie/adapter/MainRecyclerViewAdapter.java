package com.tttony3.doubanmovie.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tttony3.doubanmovie.R;
import com.tttony3.doubanmovie.bean.SubjectBean;
import com.tttony3.doubanmovie.interfaces.GetMoreMoviesListener;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    private String TAG = "MainRecyclerViewAdapter";
    private Context mContext;
    public boolean isFirst = true;
    private GetMoreMoviesListener getMoreMoviesListener;
    List<SubjectBean> subjects;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MainRecyclerViewAdapter(Context mContext, List<SubjectBean> subjects) {
        this.mContext = mContext;
        if (null != subjects)
            this.subjects = subjects;
        else
            this.subjects = new ArrayList<>();

    }

    public void setGetMoreMoviesListener(GetMoreMoviesListener getMoreMoviesListener) {
        this.getMoreMoviesListener = getMoreMoviesListener;
    }

    public void setList(List<SubjectBean> subjects) {
        this.subjects.clear();
        this.subjects.addAll(subjects);
        notifyDataSetChanged();
    }

    public void addList(List<SubjectBean> subjects) {
        this.subjects.addAll(subjects);
        notifyDataSetChanged();
    }

    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mView = view;
        viewHolder.mTitle = (TextView) view.findViewById(R.id.tv_movie_title);
        viewHolder.mDate = (TextView) view.findViewById(R.id.tv_movie_pubdates);
        viewHolder.mRate = (TextView) view.findViewById(R.id.tv_movie_rating);
        viewHolder.mCasts = (TextView) view.findViewById(R.id.tv_movie_casts);
        viewHolder.mDirectors = (TextView) view.findViewById(R.id.tv_movie_directors);
        viewHolder.mImage = (ImageView) view.findViewById(R.id.img_movie);
        return viewHolder;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final MainRecyclerViewAdapter.ViewHolder holder, final int position) {
        SubjectBean tmp = subjects.get(position);
        holder.mDate.setText(tmp.getYear());
        holder.mTitle.setText(tmp.getTitle());
        holder.mCasts.setText(tmp.getCasts().get(0).getName());
        holder.mDirectors.setText(tmp.getDirectors().get(0).getName());
        holder.mRate.setText(tmp.getRating().getAverage() + "");
        Glide.with(mContext).load(tmp.getImages().getLarge()).into(holder.mImage);
        // holder.mImage.setImageURI(Uri.parse(tmp.getImages().getLarge()));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(v, position);
            }
        });
        if (position == subjects.size() - 1 && isFirst) {
            Log.v(TAG, "get more movies");
            if (null != getMoreMoviesListener) {
                isFirst = false;
                getMoreMoviesListener.getMoreMovies();
            }
        }

    }

    public String getItemImgUri(int position) {
        return subjects.get(position).getImages().getLarge();
    }

    public SubjectBean getItem(int position) {
        return subjects.get(position);
    }

    public String getItemTitle(int position) {
        return subjects.get(position).getTitle();
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mTitle;
        public TextView mDate;
        public TextView mRate;
        public TextView mDirectors;
        public TextView mCasts;
        public ImageView mImage;

        public ViewHolder(View view) {
            super(view);
        }
    }

}
