package com.comelite.moviesdb.models;

import com.comelite.moviesdb.apis.Response;

import java.util.ArrayList;

public class MergedResponse {
    private Response<ArrayList<MovieModel>> popularMovies;
    private Response<ArrayList<MovieModel>> topRatedMovies;
    private Response<ArrayList<MovieModel>> upComingMovies;

    public MergedResponse(Response<ArrayList<MovieModel>> popularMovies,
                          Response<ArrayList<MovieModel>> topRatedMovies,
                          Response<ArrayList<MovieModel>> upComingMovies){
        this.popularMovies = popularMovies;
        this.topRatedMovies = topRatedMovies;
        this.upComingMovies = upComingMovies;
    }

    public Response<ArrayList<MovieModel>> getPopularMovies() {
        return popularMovies;
    }

    public Response<ArrayList<MovieModel>> getTopRatedMovies() {
        return topRatedMovies;
    }

    public Response<ArrayList<MovieModel>> getUpComingMovies() {
        return upComingMovies;
    }
}
