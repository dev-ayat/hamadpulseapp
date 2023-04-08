package com.moh.hamadpulse;


import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class MyRequest {

    static RequestQueue mRequestQueue;
    static StringRequest mStringRequest;
    public static int POST = Request.Method.POST;
    public static int GET = Request.Method.GET;

    public interface CallBack {
        void Result(String response);
        void Error(VolleyError error);
    }

    public static void makeRquest(Context cx, String url, final Map<String, String> my_params, final CallBack mCallBack) {
//        if (Controller.IsConnected()) {
            Log.e("makeRquest", "url" + url);
            Log.e("makeRquest", "params" + my_params.toString());
            mRequestQueue = Volley.newRequestQueue(cx);
            mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("makeRquest", "response=" + response);
                    mCallBack.Result(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("makeRquest", "error=" + error.toString());
                    String cause = "";
//                    if (error instanceof TimeoutError) {
//                        cause = "Timeout";
//                    } else if (error instanceof NoConnectionError) {
//                        cause = "No connection";
//                    } else if (error instanceof AuthFailureError) { // 403's fall in this category
//                        cause = "Auth failure";
//                    } else if (error instanceof ServerError) { // 404's fall in this category
//                        cause = "Server error";
//                    } else if (error instanceof NetworkError) {
//                        cause = "Network error";
//                    } else if (error instanceof ParseError) {
//                        cause = "Network parse error";
//                    }
                    Controller.view_error(error, cx);
//                    Toast.makeText(cx, cause, Toast.LENGTH_SHORT).show();
//                    mCallBack.Error(error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return my_params;
                }

            };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
//        }
//        else
//            Toast.makeText(cx, "يرجى التحقق من الاتصال بالإنترنت", Toast.LENGTH_SHORT).show();

    }
}
