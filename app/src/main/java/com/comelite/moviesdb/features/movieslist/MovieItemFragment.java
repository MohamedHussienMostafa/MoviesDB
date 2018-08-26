package com.comelite.moviesdb.features.movieslist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.comelite.moviesdb.R;
import com.comelite.moviesdb.features.NavigationManager;
import com.comelite.moviesdb.features.movieslist.carouselui.CarouselLinearLayout;
import com.comelite.moviesdb.models.MovieModel;

import java.util.ArrayList;

public class MovieItemFragment extends Fragment {

    private static final String POSITION = "position";
    private static final String SCALE = "scale";
    private static final String MOVIES_MODELS = "movies models";

    private int screenWidth;
    private int screenHeight;

    public static Fragment newInstance(Activity activity, ArrayList<MovieModel> movieModels,
                                       int pos, float scale) {
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, pos);
        bundle.putFloat(SCALE, scale);
        bundle.putSerializable(MOVIES_MODELS, movieModels);

        return Fragment.instantiate(activity, MovieItemFragment.class.getName(), bundle);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        final int position = this.getArguments().getInt(POSITION);
        float scale = this.getArguments().getFloat(SCALE);
        ArrayList<MovieModel> movieModels = (ArrayList<MovieModel>)
                this.getArguments().getSerializable(MOVIES_MODELS);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth / 2,
                screenHeight / 2);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_movie_item,
                container, false);

        CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
        ImageView movieImage = (ImageView) linearLayout.findViewById(R.id.movie_image);
        TextView movieTitleTv = (TextView) linearLayout.findViewById(R.id.movie_title_tv);
        TextView movieReleaseDateTv = (TextView) linearLayout.findViewById(R.id.movie_release_date_tv);

        movieImage.setLayoutParams(layoutParams);
        Glide.with(this).load(movieModels.get(position).getRealPosterPath()).into(movieImage);

        movieTitleTv.setText(movieModels.get(position).getTitle());
        movieReleaseDateTv.setText(getActivity().getResources().getString(R.string.release_date) +
                movieModels.get(position).getReleaseDate());

        movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationManager.openMovieDetailsScreen(getActivity(), movieModels.get(position));
            }
        });

        root.setScaleBoth(scale);

        return linearLayout;
    }

    /**
     * Get device screen width and height
     */
    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }
}