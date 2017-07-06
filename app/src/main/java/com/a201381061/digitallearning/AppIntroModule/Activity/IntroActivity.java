package com.a201381061.digitallearning.AppIntroModule.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.a201381061.digitallearning.AppIntroModule.Fragment.FirstFragment;
import com.a201381061.digitallearning.AppIntroModule.Fragment.SecondFragment;
import com.a201381061.digitallearning.AppIntroModule.Fragment.ThirdFragment;
import com.a201381061.digitallearning.LoginModule.Activity.LoginActivity;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.SessionController;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;


public class IntroActivity extends AppIntro {

    FirstFragment firstFragment;
    SecondFragment secondFragment;
    ThirdFragment thirdFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFragment();
        addSlide(firstFragment);
        addSlide(secondFragment);
        addSlide(thirdFragment);

        addSlide(AppIntroFragment.newInstance("Tes","Deskripsi", R.drawable.splash_image,getResources().getColor(R.color.colorPrimary)));

        showSkipButton(true);
        setProgressButtonEnabled(true);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        SessionController sessionController = new SessionController(IntroActivity.this);
        sessionController.openedForFirstTime();
        startActivity(new Intent(IntroActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    private void createFragment(){
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
    }
}
