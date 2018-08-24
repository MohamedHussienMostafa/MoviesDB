package com.comelite.moviesdb.features.movieslist;


import com.comelite.moviesdb.apis.MoviesApi;
import com.comelite.moviesdb.apis.Response;
import com.comelite.moviesdb.core.constants.APIsConstants;
import com.comelite.moviesdb.models.MergedResponse;
import com.comelite.moviesdb.models.MovieModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MoviesListPresenter implements MoviesListContract.Presenter {

    public Retrofit retrofit;
    MoviesListContract.View mView;

    @Inject
    public MoviesListPresenter(Retrofit retrofit, MoviesListContract.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }

    @Override
    public void loadPopularMovies() {

        mView.showLoading();

        Single<Response<ArrayList<MovieModel>>> popularMoviesList =
                retrofit.create(MoviesApi.class).getPopularMoviesList(APIsConstants.API_KEY_VALUE);

        Single<Response<ArrayList<MovieModel>>> topRatedMoviesList =
                retrofit.create(MoviesApi.class).getTopRatedMoviesList(APIsConstants.API_KEY_VALUE);

        Single<Response<ArrayList<MovieModel>>> upcomingMoviesList =
                retrofit.create(MoviesApi.class).getUpcomingMoviesList(APIsConstants.API_KEY_VALUE);

        Single<MergedResponse> source =
                Single.zip(popularMoviesList, topRatedMoviesList, upcomingMoviesList,
                        MergedResponse::new);

        source
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<MergedResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MergedResponse mergedResponse) {
                        mView.showContent(mergedResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }
                });
    }

//    new Observer<Response<ArrayList<MovieModel>>>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onNext(Response<ArrayList<MovieModel>> response) {
//            mView.showContent(response.getResponse());
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            mView.showError(e.getMessage());
//        }
//
//        @Override
//        public void onComplete() {
//            mView.showComplete();
//        }
//
//    }
}
