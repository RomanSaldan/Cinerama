package com.lynx.cinerama.presentation.screens;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.screens.actor_item.ActorItemActivity_;
import com.lynx.cinerama.presentation.screens.movies.MoviesActivity_;

/**
 * Created by Lynx on 10/26/2016.
 */

public class NavigationActivity extends AppCompatActivity {

    protected DrawerLayout fullView;
    protected NavigationView navigationView;
    protected FrameLayout flProgress_AN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        fullView                        = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_navigation, null);
        FrameLayout activityContainer   = (FrameLayout) fullView.findViewById(R.id.activity_content);
        navigationView                  = (NavigationView) fullView.findViewById(R.id.navigation_view);
        flProgress_AN                   = (FrameLayout) fullView.findViewById(R.id.flProgress_AN);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener((item) -> {
                item.setChecked(true);
                fullView.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.menuItemMovies:
                        displayMenuMovies();
                        break;
                    case R.id.menuItemActors:
                        displayMenuActors();
                        break;
                }
                return true;
            });
        }
    }

    public void displayMenuMovies() {
        MoviesActivity_.intent(this).start();
        finish();

//        MovieItemActivity_.intent(this).start();
//        finish();
    }

    public void displayMenuActors() {
        ActorItemActivity_.intent(this).start();
        finish();
    }

    public void displayProgress(boolean isShown) {
        flProgress_AN.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    public void displayError(String msg) {
        Snackbar.make(fullView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
