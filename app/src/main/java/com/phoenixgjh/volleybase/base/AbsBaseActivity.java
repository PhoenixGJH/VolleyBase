package com.phoenixgjh.volleybase.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * AbsBaseActivity
 * Created by Phoenix on 2016/7/18.
 */
public abstract class AbsBaseActivity extends AppCompatActivity {
    private static final String TAG = "AbsBaseActivity";
    private static final int NO_LAYOUT = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        befInit();
        int id = getLayoutID();
        View contentView;
        if (id != NO_LAYOUT) {
            contentView = getLayoutInflater().inflate(id, null);
            ButterKnife.bind(this, contentView);
            setContentView(contentView);
            init(savedInstanceState, contentView);
        } else {
            Log.e(TAG, "Layout is null");
        }
    }

    /**
     * 获取Layout
     *
     * @return LayoutId
     */
    protected abstract int getLayoutID();

    /**
     * 初始化
     *
     * @param savedInstanceState 保存的数据
     * @param contentView        当前activity的layout
     */
    protected abstract void init(Bundle savedInstanceState, View contentView);

    /**
     * 初始化之前
     */
    protected void befInit() {

    }

    public Context getContext() {
        return getApplicationContext();
    }

    public void showToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }
}
