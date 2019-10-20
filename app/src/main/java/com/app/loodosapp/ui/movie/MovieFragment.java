package com.app.loodosapp.ui.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.*;
import com.app.loodosapp.R;
import com.app.loodosapp.data.model.MovieModel;
import com.app.loodosapp.data.network.ApiClient;
import com.app.loodosapp.data.network.AppApi;
import com.app.loodosapp.ui.main.MainActivity;
import com.app.loodosapp.ui.movieDetail.MovieDetailFragment;

import java.util.ArrayList;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public class MovieFragment extends Fragment implements MovieMvp.View {

    @BindView(R.id.etSearch) EditText etSearch;
    @BindView(R.id.tvNotFound) TextView tvNotFound;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private Unbinder unbinder;
    private MovieMvp.Presenter mPresenter;
    private MovieAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private MainActivity mActivity;
    private ArrayList<MovieModel> mList;

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void init() {
        mActivity = (MainActivity) getActivity();
        mList = new ArrayList<>();
        getActivity().setTitle("Movie App");
        getActionBar().setHomeButtonEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        mPresenter = new MoviePresenter(this);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        if(adapter != null){
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    @OnEditorAction(R.id.etSearch)
    protected boolean onSearchFromKeyboard(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            mActivity.hideKeyboard();
            onSearchClick();
            return true;
        }
        return false;
    }

    @OnClick(R.id.btnSearh)
    public void onSearchClick(){
        mActivity.showProgress();
        mList.clear();
        tvNotFound.setVisibility(View.GONE);
        mPresenter.fetchMovieData(etSearch.getText().toString());
    }

    @Override
    public void showMovie(MovieModel movieModel) {
        mActivity.hideProgress();
        mList.add(movieModel);
        if(movieModel.Response .equals("True")){
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new MovieAdapter(getActivity(),mList,this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }else{
            recyclerView.setVisibility(View.GONE);
            tvNotFound.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void openMovieDetailFragment(MovieModel movieModel, View view) {
        mPresenter.showFragmentWithTransition(MovieFragment.newInstance(),
                MovieDetailFragment.newInstance(movieModel), "movieDetail", view, "transition" + movieModel.Year);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public AppApi getApiService() {
        return ApiClient.getClient(getActivity()).create(AppApi.class);
    }

    @Override
    public FragmentManager getFragmentManagerFromView() {
        return getFragmentManager();
    }

    @Override
    public ActionBar getActionBar() {
        return mActivity.getSupportActionBar();
    }

}
