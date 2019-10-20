package com.app.loodosapp.ui.main;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.loodosapp.R;
import com.app.loodosapp.ui.movie.MovieFragment;
import android.support.v7.app.ActionBar;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public class MainActivity extends AppCompatActivity implements MainMvp.View {

    @BindView(R.id.imgProgress) ImageView imgProgress;
    private AnimationDrawable mainanimation;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar();
        mPresenter = new MainPresenter(this);
        mPresenter.replaceFragment(MovieFragment.newInstance());
        mainanimation = (AnimationDrawable) imgProgress.getDrawable();
        mainanimation.setCallback(imgProgress);

    }

    @Override
    public void showProgress() {
        imgProgress.setVisibility(View.VISIBLE);
        mainanimation.start();
    }

    @Override
    public void hideProgress() {
        imgProgress.setVisibility(View.GONE);
        mainanimation.stop();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (!getFragmentManager().popBackStackImmediate())
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            } else {
                super.onBackPressed();
            }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               onBackPressed();
               break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public FragmentManager getFragmentManagerFromView() {
        return getSupportFragmentManager();
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()){
               hideKeyboard();
            }

        }
        return super.dispatchTouchEvent(ev);
    }
}
