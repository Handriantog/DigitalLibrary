package com.a201381061.digitallearning.LoginModule.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a201381061.digitallearning.HomeModule.Activity.MainActivity;
import com.a201381061.digitallearning.LoginModule.Utility.FirebaseAuthUtil;
import com.a201381061.digitallearning.R;

public class SplashScreen extends AppCompatActivity {

    private final int WAKTU_TUNGGU = 3000;
    private boolean userExist = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        userExist = new FirebaseAuthUtil().checkIfUserExist();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userExist){
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },WAKTU_TUNGGU);
    }
}
