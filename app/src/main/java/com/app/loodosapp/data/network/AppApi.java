package com.app.loodosapp.data.network;

import com.app.loodosapp.data.model.MovieModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public interface AppApi {
    @GET("/")
    Observable<MovieModel> getMovie(@Query("apikey")  String apiKey,@Query("t")  String mSearhMovie,@Query("plot")  String mPlot);
}