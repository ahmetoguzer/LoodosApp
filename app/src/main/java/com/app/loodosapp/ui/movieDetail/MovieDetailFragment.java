package com.app.loodosapp.ui.movieDetail;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.app.loodosapp.R;
import com.app.loodosapp.data.model.MovieModel;
import com.app.loodosapp.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public class MovieDetailFragment extends Fragment implements MovieDetailMvp.View {

    @BindView(R.id.movieImage) ImageView movieImage;
    @BindView(R.id.tvPlot) TextView tvPlot;
    @BindView(R.id.tvYear)  TextView tvYear;
    @BindView(R.id.tvGenre) TextView tvGenre;
    @BindView(R.id.tvName) TextView tvName;
    private Unbinder unbinder;
    private MovieModel movie;

    public static MovieDetailFragment newInstance(MovieModel movieModel) {
        Bundle bundle = new Bundle();
        bundle.putString("transitionName", "transition" + movieModel.Year);
        bundle.putSerializable("movie", movieModel);
        MovieDetailFragment movieDetail = new MovieDetailFragment();
        movieDetail.setArguments(bundle);
        return  movieDetail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        Bundle b = getArguments();
        if (b != null) {
            String transitionName = b.getString("transitionName");
             movie = (MovieModel) b.getSerializable("movie");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                movieImage.setTransitionName(transitionName);

            if (movie != null) {
                if (!TextUtils.isEmpty(movie.Poster))
                    Picasso.get().load(movie.Poster).into(movieImage);
                else
                    movieImage.setImageDrawable(null);

                tvName.setText(movie.Title);
                tvPlot.setText(String.valueOf(movie.Plot));
                tvYear.setText(String.valueOf(movie.Year));
                tvGenre.setText(movie.Genre);
            }
            getActivity().setTitle(movie.Title);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
