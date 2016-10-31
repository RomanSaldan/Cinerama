package com.lynx.cinerama.presentation.screens.movies.info.review_details;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.presentation.adapters.ReviewDetailsAdapter;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/28/2016.
 */

@EActivity(R.layout.activity_review_details)
public class ReviewDetailsActivity extends AppCompatActivity implements ReviewDetailsContract.ReviewDetailsView {

    private ReviewDetailsContract.ReviewDetailsPresenter presenter;

    @Extra
    protected String movieTitle;

    @Extra
    protected MovieReviews movieReviews;

    @Bean
    protected ReviewDetailsAdapter reviewDetailsAdapter;

    @ViewById
    protected Toolbar toolbar_ARD;

    @ViewById
    protected RecyclerView rvReviewDetails_ARD;

    @AfterInject
    protected void initPresenter() {
        new ReviewDetailsPresenter(this, movieTitle, movieReviews);
    }

    @AfterViews
    protected void initUI() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvReviewDetails_ARD.setLayoutManager(llm);
        rvReviewDetails_ARD.setAdapter(reviewDetailsAdapter);
        reviewDetailsAdapter.setOnReviewDetailsListener(this::clickReviewLink);

        presenter.subscribe();
    }

    @Override
    public void setupToolbar(String title) {
        toolbar_ARD.setTitle(title);
        setSupportActionBar(toolbar_ARD);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void displayReviews(ArrayList<ReviewDH> reviewDHs) {
        reviewDetailsAdapter.setListDH(reviewDHs);
    }

    @Override
    public void clickReviewLink(String url) {
        presenter.openReviewLink(url);
    }

    @Override
    public void displayReviewLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void setPresenter(ReviewDetailsContract.ReviewDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @OptionsItem(android.R.id.home)
    protected void homeSelected() {
        finish();
    }
}