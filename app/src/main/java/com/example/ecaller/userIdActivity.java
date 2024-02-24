package com.example.ecaller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class userIdActivity extends AppCompatActivity {

    EditText usename;
    Button start;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_id);

        usename = findViewById(R.id.username);
        start = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = usename.getText().toString().trim();
                if(userID.isEmpty()){
                    return;
                }
                startService(userID);
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(userIdActivity.this, callActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

    }

    public void startService(String userID){
        Application application =  getApplication();
        long appID = 812102748;
        String appSign ="c2897835172888b20f8cb45aebe85f8673896c612f56f17c14ffd3888b4fe7b8";
        String userName = userID;
        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}