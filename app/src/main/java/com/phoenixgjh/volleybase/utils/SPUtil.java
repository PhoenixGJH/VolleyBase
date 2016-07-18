package com.phoenixgjh.volleybase.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phoenixgjh.volleybase.app.BaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phoenix on 2016/7/18.
 */
public class SPUtil {
    private static SPUtil instance;
    private Context context;

    public static SPUtil getInstance() {
        if (instance == null) {
            synchronized (SPUtil.class) {
                if (instance == null) {
                    instance = new SPUtil();
                }
            }
        }
        return instance;
    }

    private SPUtil() {
        context = BaseApplication.getContext();
    }

    private List<String> mList_searchHistory = new ArrayList<>();

    /**
     * 写入搜索历史
     *
     * @param searchStr
     */
    public void setSearchHistory(String searchStr) {
        if (mList_searchHistory.contains(searchStr)) {
            return;
        }
        mList_searchHistory.add(searchStr);
        String jsonStr = "";
        Gson gson = new Gson();
        jsonStr = gson.toJson(mList_searchHistory);
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("searchHistory", jsonStr);
        editor.apply();
    }

    /**
     * 读取搜索历史
     *
     * @return
     */
    public List<String> getSearchHistory() {
        String jsonStr = context.getSharedPreferences("userInfo", Context.MODE_APPEND).getString("searchHistory", null);
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, new TypeToken<List<String>>() {
        }.getType());
    }

    /**
     * 清除搜索历史
     */
    public void clearSearchHistory() {
        mList_searchHistory.clear();
        String jsonStr = "";
        Gson gson = new Gson();
        jsonStr = gson.toJson(mList_searchHistory);
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("searchHistory", jsonStr);
        editor.apply();
    }
}
