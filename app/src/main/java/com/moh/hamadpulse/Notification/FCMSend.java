package com.moh.hamadpulse.Notification;

import android.content.Context;
import android.os.StrictMode;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class FCMSend {
    private static String base_url = "https://fcm.googleapis.com/fcm/send";
    private static String server_key = "AAAAQU3aBmw:APA91bFCDgJLt68ZB1GcBwKaN5VlVR69rq-zLgHyNiDC6yu1Ln8l" +
            "-r61znGaLgulD7QFDWeIPU_ABaOVfcHBjmH9LrrHdh18SGSyrnppyf9zzxnRuOSnVN9oa2mglhj7y1caPpq6oOwE";

    public static void pustNotification(Context context, String token, String title, String message) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject json = new JSONObject();
        try {
            json.put("to", token);
            JSONObject notification = new JSONObject();
            notification.put("title", title);
            notification.put("body", message);
            json.put("notification", notification);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, base_url, json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Authorization", server_key);
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
