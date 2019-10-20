package com.app.loodosapp.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public interface MainMvp {

    interface View {

        FragmentManager getFragmentManagerFromView();
        void showProgress();
        void hideProgress();
        void hideKeyboard();
    }

    interface Presenter{

        void replaceFragment(Fragment fragment);

    }

}
