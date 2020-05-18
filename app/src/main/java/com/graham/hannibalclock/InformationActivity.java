package com.graham.hannibalclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class InformationActivity extends AppCompatActivity {

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        myWebView = (WebView) findViewById(R.id.myWebView);
        myWebView.loadUrl("file:///android_asset/hannibalclock.html");
    }
}
