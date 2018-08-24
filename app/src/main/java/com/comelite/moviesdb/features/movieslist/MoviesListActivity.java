package com.comelite.moviesdb.features.movieslist;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.comelite.moviesdb.R;
import com.comelite.moviesdb.core.MoviesDBApplication;
import com.comelite.moviesdb.features.movieslist.carouselui.CarouselPagerAdapter;
import com.comelite.moviesdb.features.movieslist.di.DaggerMoviesListComponent;
import com.comelite.moviesdb.features.movieslist.di.MoviesListModule;
import com.comelite.moviesdb.models.MergedResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListActivity extends AppCompatActivity implements MoviesListContract.View {

    @BindView(R.id.movies_content_layout)
    LinearLayout moviesContentLayout;

    @BindView(R.id.movies_radio_group)
    RadioGroup moviesRadioGroup;
    @BindView(R.id.top_rated_radio_button)
    RadioButton topRatedRadioButton;
    @BindView(R.id.popular_radio_button)
    RadioButton popularRadioButton;
    @BindView(R.id.up_comping_radio_button)
    RadioButton upComingRadioButton;

    @BindView(R.id.movies_view_pager)
    public ViewPager moviesViewPager;

    @BindView(R.id.layout_loading)
    RelativeLayout loadingLayout;
    @BindView(R.id.layout_error)
    LinearLayout errorLayout;
    @BindView(R.id.error_message_tv)
    TextView errorMessageTv;

    public final static int LOOPS = 1000;
    public CarouselPagerAdapter adapter;
    public static int count = 10;
    public static int FIRST_PAGE = 10;

    private final static int TOP_RATED_TAB = 0;
    private final static int POPULAR_TAB = 1;
    private final static int UP_COMING_TAB = 2;

    @Inject
    MoviesListPresenter vehiclesListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        ButterKnife.bind(this);

        DaggerMoviesListComponent.builder()
                .netComponent(((MoviesDBApplication) getApplicationContext()).getNetComponent())
                .moviesListModule(new MoviesListModule(this))
                .build().inject(this);

        vehiclesListPresenter.loadPopularMovies();
    }

    @Override
    public void showContent(MergedResponse mergedResponse) {

        moviesContentLayout.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        loadingLayout.setVisibility(View.GONE);

        setAdapterProperties();
        setSelectedTab(mergedResponse, POPULAR_TAB);

        moviesViewPager.addOnPageChangeListener(adapter);

        moviesViewPager.setCurrentItem(FIRST_PAGE);
        moviesViewPager.setOffscreenPageLimit(3);

        handleTabSelection(mergedResponse);
    }

    private void handleTabSelection(final MergedResponse mergedResponse) {
        moviesRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                if (checkId == R.id.top_rated_radio_button) {
                    setSelectedTab(mergedResponse, TOP_RATED_TAB);
                } else if (checkId == R.id.popular_radio_button) {
                    setSelectedTab(mergedResponse, POPULAR_TAB);
                } else {
                    setSelectedTab(mergedResponse, UP_COMING_TAB);
                }
            }
        });
    }

    private void setSelectedTab(MergedResponse mergedResponse, int tabId) {
        if (tabId == TOP_RATED_TAB) {
            topRatedRadioButton.setChecked(true);
            adapter = new CarouselPagerAdapter(this, getSupportFragmentManager(),
                    mergedResponse.getTopRatedMovies().getResponse());
        } else if (tabId == POPULAR_TAB) {
            popularRadioButton.setChecked(true);
            adapter = new CarouselPagerAdapter(this, getSupportFragmentManager(),
                    mergedResponse.getPopularMovies().getResponse());
        } else {
            upComingRadioButton.setChecked(true);
            adapter = new CarouselPagerAdapter(this, getSupportFragmentManager(),
                    mergedResponse.getUpComingMovies().getResponse());
        }

        moviesViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setAdapterProperties() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 4) * 2);
        moviesViewPager.setPageMargin(-pageMargin);
    }

    @Override
    public void showError(String message) {

        errorLayout.setVisibility(View.VISIBLE);
        errorMessageTv.setText(message);
        loadingLayout.setVisibility(View.GONE);
        moviesContentLayout.setVisibility(View.GONE);
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        moviesContentLayout.setVisibility(View.GONE);
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