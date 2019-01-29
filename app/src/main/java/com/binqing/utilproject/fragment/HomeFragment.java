package com.binqing.utilproject.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binqing.utilproject.R;

@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {

    private TextView mTvSearch;
    private OnSearchListener mOnSearchListener;

    public static HomeFragment newInstance(OnSearchListener onSearchListener) {
        HomeFragment fragment = new HomeFragment(onSearchListener);
        return fragment;
    }

    @SuppressLint("ValidFragment")
    public HomeFragment(OnSearchListener onSearchListener) {
        mOnSearchListener = onSearchListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initLisenter();
        return view;
    }

    private void initView(View view) {
        mTvSearch = view.findViewById(R.id.tv_search);
    }

    private void initLisenter() {
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSearchListener != null) {
                    mOnSearchListener.onSearch();
                }
            }
        });
    }

    public interface OnSearchListener{
        void onSearch();
    }

}
