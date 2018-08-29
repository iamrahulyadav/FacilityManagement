package com.shoaibnwar.facilitymanagement.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoaibnwar.facilitymanagement.ChatSystem.SimgleChat.ChatMainActivity;
import com.shoaibnwar.facilitymanagement.R;

public class ChatActictivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_actictivity);

        startActivity(new Intent(ChatActictivity.this, ChatMainActivity.class));
        finish();
    }
}