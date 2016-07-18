package com.phoenixgjh.volleybase.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by Phoenix on 2016/7/18.
 */
public abstract class AbsBaseFragment extends Fragment {
    private static final String TAG = "AbsBaseFragment";
    public static final int NO_LAYOUT = 0;
    private View mContentView;

    public Context getContext() {
        return ((AbsBaseActivity) getActivity()).getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutID(), container, false);
        ButterKnife.bind(this, mContentView);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState, mContentView);
    }

    /**
     * 获取当前Fragment的布局文件ID
     *
     * @return
     */
    protected abstract int getLayoutID();

    /**
     * 初始化Fragment
     *
     * @param savedInstanceState
     * @param mContentView
     */
    protected abstract void init(Bundle savedInstanceState, View mContentView);

    protected void showToast(String info) {
        Toast.makeText(this.getContext(), info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回ContentView
     *
     * @return
     */
    protected View getContentView() {
        return mContentView;
    }

    public String getTitle() {
        return null;
    }


}
