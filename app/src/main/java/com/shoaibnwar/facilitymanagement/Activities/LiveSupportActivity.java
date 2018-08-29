package com.shoaibnwar.facilitymanagement.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.shoaibnwar.facilitymanagement.R;
import com.shoaibnwar.facilitymanagement.Utilities.URLS;

/*import com.zopim.android.sdk.api.ChatApi;
import com.zopim.android.sdk.api.ZopimChatApi;*/

import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.prechat.ZopimChatActivity;


public class LiveSupportActivity extends AppCompatActivity {

    private WebView mWebView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_support);
        Settings.canDrawOverlays(LiveSupportActivity.this);
        ZopimChat.init("5xpYtVHc6MrPkVuFDjGay8tFb3FYKG3L");
       // ChatApi chat = ZopimChatApi.start(LiveSupportActivity.this);
        startActivity(new Intent(getApplicationContext(), ZopimChatActivity.class));
        finish();

        //setupViews();
    }

    @SuppressLint("JavascriptInterface")
    private void setupViews() {
        mWebView = (WebView) findViewById(R.id.youtube_webview);
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new Object() {

        }, "demo");

        mWebView.loadUrl(URLS.CHAT_URL);

    }
}
