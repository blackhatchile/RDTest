package com.example.guest.rdesingtest.WebViewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.guest.rdesingtest.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String url = getIntent().getStringExtra("access_web");

        WebView web = findViewById(R.id.webviewer);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(url);
    }
}
