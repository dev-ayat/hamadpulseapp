package com.moh.hamadpulse.fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import com.moh.hamadpulse.R;
import com.ortiz.touchview.TouchImageView;

public class Image_View_Fragment extends DialogFragment {
//    ImageView img_view;
    Bitmap image;
    TouchImageView img_view;
    ImageButton reset_btn,zoomIn,zoomOut,rotateR,rotateL,close_d_btn;
    float zoom=1;
    public Image_View_Fragment(Bitmap image) {
        this.image=image;
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image__view_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        img_view=view.findViewById(R.id.img_view);
        img_view=view.findViewById(R.id.img_view);
        img_view.setMaxZoom(3);
        img_view.setMinZoom(1);

        reset_btn=view.findViewById(R.id.reset_btn);
        zoomIn=view.findViewById(R.id.zoomIn);
        zoomOut=view.findViewById(R.id.zoomOut);
        rotateR=view.findViewById(R.id.rotateR);
        rotateL=view.findViewById(R.id.rotateL);
        close_d_btn=view.findViewById(R.id.close_d_btn);
        close_d_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           dismiss();
            }
        });
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_view.setZoom(1);
                zoom=1;
            }
        });
        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoom=(zoom==3)?3:(zoom+.3f);
                img_view.setZoom(zoom);
            }
        });
        zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoom=(zoom==1)?1:(zoom-.3f);
                img_view.setZoom(zoom);
            }
        });
        rotateR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_view.setPivotX(img_view.getWidth()/2);
                img_view.setPivotY(img_view.getHeight()/2);
                img_view.setRotation(img_view.getRotation()+90);
            }
        });
        rotateL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_view.setPivotX(img_view.getWidth()/2);
                img_view.setPivotY(img_view.getHeight()/2);
                img_view.setRotation(img_view.getRotation()-90);
            }
        });

        img_view.setImageBitmap(image);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }
}