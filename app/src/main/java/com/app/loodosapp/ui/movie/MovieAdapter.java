package com.app.loodosapp.ui.movie;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.loodosapp.R;
import com.app.loodosapp.data.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context mContext;
    private MovieFragment fragment;
    private ArrayList<MovieModel> mList;

    public MovieAdapter(Context mContext, ArrayList<MovieModel> mList,MovieFragment fragment) {
        this.mContext = mContext;
        this.mList = mList;
        this.fragment = fragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvYear) TextView tvYear;
        @BindView(R.id.tvPlot) TextView tvPlot;
        @BindView(R.id.tvActors) TextView tvActors;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvRating) TextView tvRating;
        @BindView(R.id.imgMovie) ImageView imgMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(MovieModel item) {
            Picasso.get().load(item.Poster).into(imgMovie);
            tvPlot.setText(item.Plot);
            tvActors.setText((item.Actors));
            tvYear.setText(item.Year);
            tvTitle.setText(item.Title);
            tvRating.setText(item.imdbRating);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgMovie.setTransitionName("transition" + item.Year);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.openMovieDetailFragment(item, imgMovie);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_movie, parent, false);
        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
