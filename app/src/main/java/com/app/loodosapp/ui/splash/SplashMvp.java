package com.app.loodosapp.ui.splash;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by Ahmet Oguz Er on 20.10.2019
 */
public interface SplashMvp {

    interface View {

        FirebaseRemoteConfig returnFirebaseRemoteConfig();
        void displaySplashMessage();
        void openNextActivity();
    }

    interface Presenter{

        void getRemoteConfig();

    }
}
