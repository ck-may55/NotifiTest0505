package com.example.chie.notifitest0429;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by chie on 2017/04/29.
 */

public class MyFirebaseInstanceIdservice extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIdservice";
    private String token;
    private String id;

    @Override
    public void onTokenRefresh() {
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "RefreshedToken = " + token);

        id = FirebaseInstanceId.getInstance().getId();
        Log.d(TAG, "RefreshedToken = " + id);
        //トークンの値をサーバーへ送信
        sendRegistrationToServer();
    }

    private void sendRegistrationToServer() {
        submit(token, id);
    }
    private void submit(String token, String id) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refToken = database.getReference("token");
        refToken.setValue(token);

        DatabaseReference refId = database.getReference("id");
        refId.setValue(id);
    }

}
