package com.a201381061.digitallearning.LoginModule.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a201381061.digitallearning.AppIntroModule.Activity.IntroActivity;
import com.a201381061.digitallearning.HomeModule.Activity.MainActivity;
import com.a201381061.digitallearning.LoginModule.Utility.FirebaseSignInUtil;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.SessionController;

public class SplashScreen extends AppCompatActivity {

    private final int WAKTU_TUNGGU = 3000;
    private boolean userExist = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final SessionController sessionController = new SessionController(SplashScreen.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getFirstTimeOpened()){
                    Intent i = new Intent(SplashScreen.this, IntroActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    if(sessionController.isLoggedIn()){
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i = new Intent(SplashScreen.this,LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        },WAKTU_TUNGGU);
    }

    private boolean getFirstTimeOpened(){
        SessionController sessionController = new SessionController(SplashScreen.this);
        return sessionController.firstTimeOpen();
    }
}
