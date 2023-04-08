package com.moh.hamadpulse.activiteis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;

public class SplashActivity extends AppCompatActivity {
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.e("splash", Controller.pref.getString("LOGIN_MODE", "0"));
        Log.e("splash2", Controller.pref.getString("USER_NAME", "0"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Controller.pref.getString("LOGIN_MODE", "0").equals("1")) {
                    i = new Intent(SplashActivity.this, HomeActivity.class);
                } else {
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, 3000);

//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Controller.AUTH_URL, new com.android.volley.Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONObject jobj = new JSONObject(response);
//                    Log.e("respayat",""+jobj);
//                    Toast.makeText(SplashActivity.this, "Ayat"+ jobj.toString(), Toast.LENGTH_SHORT).show();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        },new com.android.volley.Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // TODO: Handle error
//
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Log.e("params_test", "params");
//                Map<String, String> map = new HashMap<>();
//                map.put("username", "993517325");
//                map.put("password", "993517325");
//                return map;
//            }
//        };
//
//        queue.add(stringRequest);
//

    }

}
