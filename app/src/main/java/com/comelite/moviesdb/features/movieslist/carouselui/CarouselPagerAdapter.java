package com.comelite.moviesdb.features.movieslist.carouselui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.comelite.moviesdb.R;
import com.comelite.moviesdb.features.movieslist.MovieItemFragment;
import com.comelite.moviesdb.features.movieslist.MoviesListActivity;
import com.comelite.moviesdb.models.MovieModel;

import java.util.ArrayList;

public class CarouselPagerAdapter extends FragmentPagerAdapter
        implements ViewPager.OnPageChangeListener {

    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private MoviesListActivity activity;
    private FragmentManager fragmentManager;
    private float scale;
    private ArrayList<MovieModel> movieModels;

    public CarouselPagerAdapter(MoviesListActivity activity, FragmentManager fm,
                                ArrayList<MovieModel> movieModels) {
        super(fm);
        this.fragmentManager = fm;
        this.activity = activity;
        this.movieModels = movieModels;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {
            if (position == MoviesListActivity.FIRST_PAGE)
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % MoviesListActivity.count;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieItemFragment.newInstance(activity, movieModels, position, scale);
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = MoviesListActivity.count * MoviesListActivity.LOOPS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
                CarouselLinearLayout cur = getRootView(position);
                CarouselLinearLayout next = getRootView(position + 1);

                cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
                next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @SuppressWarnings("ConstantConditions")
    private CarouselLinearLayout getRootView(int position) {
        return (CarouselLinearLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.root_container);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + activity.moviesViewPager.getId() + ":" + position;
    }
}
