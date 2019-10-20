package com.app.loodosapp.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.app.loodosapp.R;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public class MainPresenter implements MainMvp.Presenter {

    MainMvp.View mView;

    public MainPresenter(MainMvp.View v) {
        mView = v;
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = mView.getFragmentManagerFromView();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main_frame, fragment,backStateName);
        ft.addToBackStack(backStateName);
        ft.commit();
    }

}
