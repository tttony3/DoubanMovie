package com.tttony3.doubanmovie.ui;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tttony3.doubanmovie.R;
import com.tttony3.doubanmovie.adapter.BooksRecyclerViewAdapter;
import com.tttony3.doubanmovie.adapter.OnItemClickListener;
import com.tttony3.doubanmovie.bean.BookBean;
import com.tttony3.doubanmovie.interfaces.SubscriberOnNextListener;
import com.tttony3.doubanmovie.net.HttpMethods;
import com.tttony3.doubanmovie.net.NormalSubscriber;
import com.tttony3.doubanmovie.net.ProgressSubscriber;

import java.util.List;

/**
 * Created by Mr_tao on 2016/5/26.
 */
public class ChartBooksFragment extends LazyFragment {
    private static final String KEY_ID = "ViewTransitionValues:id";
    private String TAG = "ChartBooksFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private boolean isPrepared;
    private String mParam1;
    private String mParam2;

    private View view;
    private RecyclerView mRecyclerView;
    private BooksRecyclerViewAdapter mChartRecyclerViewAdapter;
    private OnFragmentInteractionListener mListener;
    ProgressSubscriber<List<BookBean>> progressSubscriber;
    NormalSubscriber<List<BookBean>> normalSubscriber;

    public ChartBooksFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopMoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChartBooksFragment newInstance(String param1, String param2) {
        ChartBooksFragment fragment = new ChartBooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chart_books, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_chartbooks);

        isPrepared = true;
        lazyLoad();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFirstVisible() {
        getBooks();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        Log.v(TAG, "lazyLoad");
        if (mChartRecyclerViewAdapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mChartRecyclerViewAdapter = new BooksRecyclerViewAdapter(this.getContext(), null, "Chart");
            mRecyclerView.setAdapter(mChartRecyclerViewAdapter);
            mChartRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View v, int position) {
                    Intent intent = new Intent(getActivity(), BooksDetailActivity.class);
                    intent.putExtra(KEY_ID, v.getTransitionName());
                    intent.putExtra("name", mChartRecyclerViewAdapter.getItem(position).getTitle());
                    intent.putExtra("id", mChartRecyclerViewAdapter.getItem(position).getBookid());
                    intent.putExtra("bitmap", drawableToBitmap(((ImageView) v.findViewById(R.id.img_book)).getDrawable()));
                    ActivityOptions activityOptions
                            = ActivityOptions.makeSceneTransitionAnimation(getActivity(), v.findViewById(R.id.img_book), "img");

                    startActivity(intent, activityOptions.toBundle());
                }
            });
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getBooks() {
        Log.v(TAG, "getBooks");
        progressSubscriber = new ProgressSubscriber<>(new SubscriberOnNextListener<List<BookBean>>() {
            @Override
            public void onNext(List<BookBean> subjucts) {
                mChartRecyclerViewAdapter.addList(subjucts);
                mChartRecyclerViewAdapter.isFirst = true;
            }
        }, getActivity());
        HttpMethods.getInstance(HttpMethods.BOOK).getChartBooks(progressSubscriber);
    }

}
