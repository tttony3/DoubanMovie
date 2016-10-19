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
import com.tttony3.doubanmovie.bean.BookBean;
import com.tttony3.doubanmovie.bean.SubjectBean;
import com.tttony3.doubanmovie.interfaces.GetMoreBooksListener;
import com.tttony3.doubanmovie.interfaces.GetMoreMoviesListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_tao on 2016/5/27.
 */
public class BooksRecyclerViewAdapter extends RecyclerView.Adapter<BooksRecyclerViewAdapter.ViewHolder> {
    private String TAG = "BooksViewAdapter";
    private Context mContext;
    public boolean isFirst = true;
    public String mType = null;
    private GetMoreBooksListener getMoreBooksListener;
    List<BookBean> subjects;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BooksRecyclerViewAdapter(Context mContext, List<BookBean> subjects, String mType) {
        this.mContext = mContext;
        this.mType = mType;
        if (null != subjects)
            this.subjects = subjects;
        else
            this.subjects = new ArrayList<>();

    }

    public void setGetMoreBooksListener(GetMoreBooksListener getMoreBooksListener) {
        this.getMoreBooksListener = getMoreBooksListener;
    }

    public void setList(List<BookBean> subjects) {
        this.subjects.clear();
        this.subjects.addAll(subjects);
        notifyDataSetChanged();
    }

    public void addList(List<BookBean> subjects) {
        this.subjects.addAll(subjects);
        notifyDataSetChanged();
    }

    @Override
    public BooksRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem_book, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mView = view;
        viewHolder.mTitle = (TextView) view.findViewById(R.id.tv_book_title);
        //viewHolder.mMark = (TextView) view.findViewById(R.id.tv_book_marks);
        viewHolder.mAuthor = (TextView) view.findViewById(R.id.tv_book_author);
        viewHolder.mPress = (TextView) view.findViewById(R.id.tv_book_press);
        viewHolder.mPressdate = (TextView) view.findViewById(R.id.tv_book_pressdate);
        viewHolder.mPrice = (TextView) view.findViewById(R.id.tv_book_price);
        viewHolder.mImage = (ImageView) view.findViewById(R.id.img_book);
        return viewHolder;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final BooksRecyclerViewAdapter.ViewHolder holder, final int position) {
        Log.v(TAG, position + "" + mType);
        BookBean tmp = subjects.get(position);
        String[] info = tmp.getDescription().split("/");
        for (int i = 0; i < info.length; i++) {
            Log.v(TAG, "info----->" + info[i]);
        }
        if (mType.equals("Chart")) {
            holder.mAuthor.setText(info[0]);
            holder.mPress.setText(info[2]);
            holder.mPressdate.setText(info[1]);
            holder.mPrice.setText(info[3]);
        } else if (mType.equals("Top")) {
            if (info.length == 4) {
                holder.mAuthor.setText(info[0]);
                holder.mPress.setText(info[1]);
                holder.mPressdate.setText(info[2]);
                holder.mPrice.setText(info[3]);
            } else {
                holder.mAuthor.setText(info[0]);
                holder.mPress.setText(info[2]);
                holder.mPressdate.setText(info[3]);
                holder.mPrice.setText(info[4]);
            }
        }
        holder.mTitle.setText(tmp.getTitle());
        // holder.mMark.setText("9.9");
        Glide.with(mContext).load(tmp.getImgsrc()).into(holder.mImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(v, position);
            }
        });
        if (position == subjects.size() - 1 && isFirst) {
            Log.v(TAG, "get more books");
            if (null != getMoreBooksListener) {
                isFirst = false;
                getMoreBooksListener.getMoreBooks();
            }
        }
    }

    public BookBean getItem(int position) {
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
        public TextView mPressdate;
        public TextView mPress;
        //public TextView mMark;
        public TextView mAuthor;
        public TextView mPrice;
        public ImageView mImage;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
