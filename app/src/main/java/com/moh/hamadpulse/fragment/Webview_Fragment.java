package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moh.hamadpulse.R;

public class Webview_Fragment extends Fragment {
    String url;
    private WebView webview;
    public Webview_Fragment() {

    }
//https://docs.google.com/gview?embedded=true&url=
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        //WebView filewebView = view.findViewById(R.id.filewebView);
        url = getArguments().getString("url");
        setHasOptionsMenu(true);/// to disable icon from menu
        return view;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webview = view.findViewById(R.id.webview);
        //    txtscnane = view.findViewById(R.id.txtscnane);
        webview.getSettings().setAppCacheEnabled(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setInitialScale(1);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(true);

        
        webview.loadUrl(url);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_dept);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    public void onResume() {
        super.onResume();
        // Set title bar
        //  ((ActivityPatient) getActivity()).setTitle("الملف الإلكتروني");
    }
}
