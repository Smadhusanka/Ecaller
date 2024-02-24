package com.example.ecaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class callActivity extends AppCompatActivity {

    EditText username;
    TextView heyUser;
    ZegoSendCallInvitationButton audioCall, videoCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        username = findViewById(R.id.username);
        heyUser = findViewById(R.id.heyUser);
        audioCall = findViewById(R.id.audioCall);
        videoCall = findViewById(R.id.videoCall);

        String userID = getIntent().getStringExtra("userID");
        heyUser.setText("Hey "+userID);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String targetUserID = username.getText().toString().trim();
                setAudioCall(targetUserID);
                setVideoCall(targetUserID);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    void setAudioCall(String targetUserID){
        audioCall.setIsVideoCall(false);
        audioCall.setResourceID("zego_uikit_call");
        audioCall.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }

    void setVideoCall(String targetUserID){
        videoCall.setIsVideoCall(true);
        videoCall.setResourceID("zego_uikit_call");
        videoCall.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }
}