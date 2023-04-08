package com.moh.hamadpulse.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moh.hamadpulse.R;

public class DialogMsg {
    private static Dialog pDialog;
    TextView msg,titlemsg,txt_vital_date;
    LinearLayout date_layout;

    public DialogMsg(Context act) {
        pDialog = new Dialog(act);
        pDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pDialog.setContentView(R.layout.msg_dialog);
        msg = pDialog.findViewById(R.id.loading_message);
        titlemsg = pDialog.findViewById(R.id.title);
        txt_vital_date = pDialog.findViewById(R.id.txt_vital_date);
        date_layout = pDialog.findViewById(R.id.date_layout);

//        img = (ImageView)pDialog.findViewById(R.id.loading_img);
        //animation = AnimationUtils.loadAnimation(act, R.anim.fade_in);
        //msg.startAnimation(animation);
        pDialog.setCancelable(true);
    }

    public void showDialog(String my_msg) {
        msg.setText(my_msg);
        pDialog.show();
    }
    public void showDialog(String my_msg,String date) {
        msg.setText(my_msg);
        if(date!=null){
            date_layout.setVisibility(View.VISIBLE);
            txt_vital_date.setText(date);
        }
        pDialog.show();
    }
    public void hideDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
        /*android.os.Handler mHandler=new android.os.Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }
        },3000);*/


    }


}
