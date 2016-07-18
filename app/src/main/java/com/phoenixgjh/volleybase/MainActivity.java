package com.phoenixgjh.volleybase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.phoenixgjh.volleybase.base.AbsBaseActivity;
import com.phoenixgjh.volleybase.utils.TimeUtil;

public class MainActivity extends AbsBaseActivity {

    private long clickTime = 0;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState, View contentView) {

    }

    /**
     * 退出应用
     */
    private void exit() {
        if ((TimeUtil.getTimestramp() - clickTime) > 2000) {
            showToast(getResources().getString(R.string.exit_app_hint));
            clickTime = TimeUtil.getTimestramp();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
