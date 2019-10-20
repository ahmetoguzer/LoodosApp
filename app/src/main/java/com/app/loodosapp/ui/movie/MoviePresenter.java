package com.app.loodosapp.ui.movie;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Toast;
import com.app.loodosapp.R;
import com.app.loodosapp.utils.Constants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public class MoviePresenter implements MovieMvp.Presenter {

    MovieMvp.View mView;

    public MoviePresenter(MovieMvp.View v ) { mView = v; }

    @SuppressLint("CheckResult")
    @Override
    public void fetchMovieData(String etSearch) {
        mView.getApiService()
                .getMovie(Constants.APİ_KEY,etSearch,"full")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieModel -> {
                    mView.showMovie(movieModel);
                },throwable -> {
                    Toast.makeText(mView.getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    @Override
    public void showFragmentWithTransition(Fragment current, Fragment newFragment, String tag, View sharedView, String sharedElementName) {
        FragmentManager fragmentManager = mView.getFragmentManagerFromView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            current.setSharedElementReturnTransition(TransitionInflater.from(mView.getContext()).inflateTransition(R.transition.default_transition));
            current.setExitTransition(TransitionInflater.from(mView.getContext()).inflateTransition(android.R.transition.no_transition));

            newFragment.setSharedElementEnterTransition(TransitionInflater.from(mView.getContext()).inflateTransition(R.transition.default_transition));
            newFragment.setEnterTransition(TransitionInflater.from(mView.getContext()).inflateTransition(android.R.transition.no_transition));
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.main_frame, newFragment,tag);
        ft.addToBackStack(tag);
        ft.addSharedElement(sharedView, sharedElementName);
        ft.commit();
    }
}
