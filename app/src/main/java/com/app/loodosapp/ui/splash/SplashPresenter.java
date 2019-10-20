package com.app.loodosapp.ui.splash;

import android.support.annotation.NonNull;
import com.app.loodosapp.BuildConfig;
import com.app.loodosapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/**
 * Created by Ahmet Oguz Er on 20.10.2019
 */
public class SplashPresenter implements SplashMvp.Presenter {

    SplashMvp.View mView;

    public SplashPresenter(SplashMvp.View v ) { mView = v; }

    @Override
    public void getRemoteConfig() {
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mView.returnFirebaseRemoteConfig().setConfigSettingsAsync(configSettings);
        mView.returnFirebaseRemoteConfig().setDefaultsAsync(R.xml.remote_config_defaults);
        mView.returnFirebaseRemoteConfig().fetch(60*5).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    mView.returnFirebaseRemoteConfig().activateFetched();
                    mView.displaySplashMessage();
                    mView.openNextActivity();
                }else {

                }
            }
        });
    }

}
