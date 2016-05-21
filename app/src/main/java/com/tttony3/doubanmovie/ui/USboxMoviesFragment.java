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
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tttony3.doubanmovie.R;
import com.tttony3.doubanmovie.adapter.OnItemClickListener;
import com.tttony3.doubanmovie.adapter.USboxRecyclerViewAdapter;
import com.tttony3.doubanmovie.bean.USboxBean;
import com.tttony3.doubanmovie.interfaces.SubscriberOnNextListener;
import com.tttony3.doubanmovie.net.HttpMethods;
import com.tttony3.doubanmovie.net.ProgressSubscriber;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link USboxMoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link USboxMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class USboxMoviesFragment extends LazyFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    USboxRecyclerViewAdapter mUSboxRecyclerViewAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    private RecyclerView mRecyclerView;
    private boolean isPrepared;

    public USboxMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment USboxMoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static USboxMoviesFragment newInstance(String param1, String param2) {
        USboxMoviesFragment fragment = new USboxMoviesFragment();
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
        view = inflater.inflate(R.layout.fragment_usbox_movies, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_usbox);
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
        getUSBox();
    }

    private static final String KEY_ID = "ViewTransitionValues:id";

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        if (mUSboxRecyclerViewAdapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mUSboxRecyclerViewAdapter = new USboxRecyclerViewAdapter(this.getContext(), null);
            mRecyclerView.setAdapter(mUSboxRecyclerViewAdapter);

            mUSboxRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View v, int position) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra(KEY_ID, v.getTransitionName());
                    intent.putExtra("bean", mUSboxRecyclerViewAdapter.getItem(position));
                    intent.putExtra("type", "us");
                    intent.putExtra("bitmap", drawableToBitmap(((ImageView) v.findViewById(R.id.img_movie)).getDrawable()));
                    ActivityOptions activityOptions
                            = ActivityOptions.makeSceneTransitionAnimation(getActivity(), v.findViewById(R.id.img_movie), "img");

                    startActivity(intent, activityOptions.toBundle());
                }
            });
        }
        // getUSBox();

    }

    ProgressSubscriber progressSubscriber;

    private void getUSBox() {
        progressSubscriber = new ProgressSubscriber<>(new SubscriberOnNextListener<USboxBean>() {
            @Override
            public void onNext(USboxBean bean) {
                mUSboxRecyclerViewAdapter.addList(bean.getSubjects());
            }
        }, getActivity());
        HttpMethods.getInstance().getUSBox(progressSubscriber);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
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
}
