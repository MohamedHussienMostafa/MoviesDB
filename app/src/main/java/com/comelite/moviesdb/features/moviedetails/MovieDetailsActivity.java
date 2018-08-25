package com.comelite.moviesdb.features.moviedetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.comelite.moviesdb.R;
import com.comelite.moviesdb.core.BaseActivity;
import com.comelite.moviesdb.core.MoviesDBApplication;
import com.comelite.moviesdb.features.moviedetails.di.DaggerMovieDetailsComponent;
import com.comelite.moviesdb.features.moviedetails.di.MovieDetailsModule;
import com.comelite.moviesdb.models.MergedResponse;
import com.comelite.moviesdb.models.MovieModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.movie_image)
    ImageView movieImage;
    @BindView(R.id.movie_title_tv)
    TextView movieTitleTv;
    @BindView(R.id.movie_release_date_tv)
    TextView movieReleaseDateTv;

    @BindView(R.id.cast_layout)
    LinearLayout castLayout;

    @BindView(R.id.plot_explanation_tv)
    TextView plotExplanationTv;

    public final static String MOVIE = "movie";

    private MovieModel movieModel;

    @Inject
    MovieDetailsPresenter movieDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        DaggerMovieDetailsComponent.builder()
                .netComponent(((MoviesDBApplication) getApplicationContext()).getNetComponent())
                .movieDetailsModule(new MovieDetailsModule(this))
                .build().inject(this);

        movieModel = (MovieModel) getIntent().getExtras().getSerializable(MOVIE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());

        bindUI();
    }

    private void bindUI(){
        Glide.with(this).load(movieModel.getRealPosterPath()).into(movieImage);
        movieTitleTv.setText(movieModel.getTitle());
        movieReleaseDateTv.setText(getResources().getString(R.string.release_date) +
                movieModel.getReleaseDate());
    }

    @Override
    public void showContent(MergedResponse mergedResponse) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showLoading() {
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//
//        allVehiclesMenu = menu.findItem(R.id.all_vehicles_menu);
//        allVehiclesMenu.setVisible(false);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.all_vehicles_menu) {
//            NavigationManager.startVehiclesMapScreen(VehiclesListActivity.this, moviesModels);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}