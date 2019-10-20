package com.app.loodosapp.ui.movie;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import com.app.loodosapp.data.model.MovieModel;
import com.app.loodosapp.data.network.AppApi;
import com.app.loodosapp.ui.main.MainMvp;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public interface MovieMvp {

    interface View {

         void init();
         void showMovie(MovieModel movieModel);
         void openMovieDetailFragment(MovieModel movieModel, android.view.View view);
         AppApi getApiService();
         Context getContext();
         FragmentManager getFragmentManagerFromView();
         ActionBar getActionBar();

    }

    interface Presenter {

        void fetchMovieData(String etSearch);
        void showFragmentWithTransition(Fragment current, Fragment newFragment, String tag, android.view.View sharedView, String sharedElementName);

    }
}
