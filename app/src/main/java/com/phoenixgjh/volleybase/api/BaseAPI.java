package com.phoenixgjh.volleybase.api;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.phoenixgjh.volleybase.utils.MapUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * BaseAPI
 * Created by Phoenix on 2016/7/14.
 */
public abstract class BaseAPI<P extends ResponseData> implements Response.Listener<String>, Response.ErrorListener {
    private static final String TAG = "BaseAPI";

    public Request getRequest() {
        if (getMethod() == Request.Method.GET) {
            return new StringRequest(getAPIUrl() + "?" + getSortedParams(), this, this);
        }
        if (getMethod() == Request.Method.POST) {
            return new StringRequest(Request.Method.POST, getAPIUrl(), this, this) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return BaseAPI.this.getPostParams();
                }
            };
        }
        return null;
    }

    /**
     * 获取请求的Url
     *
     * @return url
     */
    protected abstract String getAPIUrl();

    /**
     * 获取返回数据的Bean类
     *
     * @return
     */
    protected abstract Class<P> getResponseDataClazz();

    /**
     * 设置请求方法
     *
     * @return 请求方法
     */
    protected int getMethod() {
        return Request.Method.GET;
    }

    /**
     * 获取参数
     *
     * @return
     */
    protected Map<String, String> getParams() {
        return new HashMap<>();
    }

    /**
     * 获取GET请求参数
     *
     * @return get请求参数
     */
    private String getSortedParams() {
        Map<String, String> map = getParams();
        Iterator<Map.Entry<String, String>> it = MapUtil.sortMap(map).entrySet().iterator();
//        String url_temp_0 = "";
        StringBuilder builder = new StringBuilder();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            builder = builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
//            url_temp_0 = url_temp_0 + "&" + entry.getKey() + "=" + entry.getValue();
        }
        builder.substring(builder.indexOf("&") + 1);
        return builder.toString();
    }

    /**
     * Post请求参数
     *
     * @return
     */
    private Map<String, String> getPostParams() {
        Map<String, String> map = getParams();
//        Iterator<Map.Entry<String, String>> it = MapUtil.sortMap(map).entrySet().iterator();
//        StringBuilder builder = new StringBuilder();
//        while (it.hasNext()) {
//            Map.Entry<String, String> entry = it.next();
//            builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
//        }
//        builder.substring(builder.indexOf("&") + 1);
        return map;
    }

    /**
     * 响应
     *
     * @param data
     */
    protected abstract void OnResponse(P data);

    protected abstract void OnError(Exception error);

    @Override
    public void onResponse(String response) {
        Log.d(TAG + "JsonStr", response);
        try {
            Gson gson = new Gson();
            P p = gson.fromJson(response, getResponseDataClazz());
            OnResponse(p);
        } catch (JsonSyntaxException e) {
            OnError(e);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        OnError(error);
    }
}
