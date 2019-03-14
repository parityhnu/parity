package com.binqing.utilproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binqing.utilproject.ParityApplication;
import com.binqing.utilproject.R;
import com.binqing.utilproject.Utils.NavUtil;

public class MineFragment extends Fragment {

    private RelativeLayout mRlUnSignIn;
    private RelativeLayout mRlSignIn;
    private RelativeLayout mRlModify;
    private RelativeLayout mRlCollection;
    private TextView mTvName;

    private boolean mIsSignIn;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        initData();
        initView(v);
        initLisenter();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsSignIn = ParityApplication.getInstance().isSignIn();
        refreshView();
    }

    private void initData() {
        mIsSignIn = ParityApplication.getInstance().isSignIn();
    }

    private void initView(View view) {
        mRlUnSignIn = view.findViewById(R.id.rl_sign_in);
        mRlSignIn = view.findViewById(R.id.rl_content_signin);
        mRlModify = view.findViewById(R.id.rl_modify);
        mRlCollection = view.findViewById(R.id.rl_collection);
        mTvName = view.findViewById(R.id.tv_name);
        refreshView();
    }

    private void refreshView() {
        if (mIsSignIn) {
            mRlSignIn.setVisibility(View.VISIBLE);
            mRlUnSignIn.setVisibility(View.GONE);
            mTvName.setText(ParityApplication.getInstance().getAccountName());
        } else {
            mRlSignIn.setVisibility(View.GONE);
            mRlUnSignIn.setVisibility(View.VISIBLE);
        }
    }

    private void initLisenter() {
        mRlUnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsSignIn) {
                    return;
                }
                NavUtil.Nav2LoginActivity(getActivity());
            }
        });

        mRlSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsSignIn) {
                    return;
                }
                NavUtil.Nav2ModifyActivity(getActivity());
            }
        });

        mRlCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsSignIn) {
                    NavUtil.Nav2CollectionActivity(getActivity());
                } else {
                    NavUtil.Nav2LoginActivity(getActivity());
                }
            }
        });

        mRlModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsSignIn) {
                    NavUtil.Nav2ModifyActivity(getActivity());
                } else {
                    NavUtil.Nav2LoginActivity(getActivity());
                }

            }
        });
    }

}
