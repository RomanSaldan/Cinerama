package com.lynx.cinerama.presentation.screens.movies.info;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.presentation.adapters.SimilarAdapter;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.custom.cast_view.CastLayout;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;
import com.lynx.cinerama.presentation.screens.movies.MoviesActivity;
import com.lynx.cinerama.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 10/26/2016.
 */

@EFragment(R.layout.fragment_movie_info)
public class MovieInfoFragment extends BaseFragment<MoviesActivity> implements MovieInfoContract.MovieInfoView {

    private MovieInfoContract.MovieInfoPresenter presenter;

    @FragmentArg
    protected ResponseMovieInfo responseMovieInfo;

    @Bean
    protected SimilarAdapter similarAdapter;

    //region view injections
    @ViewById
    protected ImageView ivPoster_FMI;

    @ViewById
    protected TextView tvMovieTitle_FMI;

    @ViewById
    protected TextView tvMovieTagline_FMI;

    @ViewById
    protected TextView tvMovieGenres_FMI;

    @ViewById
    protected TextView tvMovieCountries_FMI;

    @ViewById
    protected TextView tvMovieCompany_FMI;

    @ViewById
    protected TextView tvMovieRating_FMI;

    @ViewById
    protected TextView tvMovieDuration_FMI;

    @ViewById
    protected TextView tvMovieDate_FMI;

    @ViewById
    protected TextView tvMovieBudget_FMI;

    @ViewById
    protected TextView tvMovieReleaseInfo_FMI;

    @ViewById
    protected TextView tvMovieRevenue_FMI;

    @ViewById
    protected CastLayout cclCast_MFI;

    @ViewById
    protected TextView tvMovieOverview_FMI;

    @ViewById
    protected ImageView btnImdb_FMI;

    @ViewById
    protected ImageView btnWeb_FMI;

    @ViewById
    protected LinearLayout llSimilar_FMI;

    @ViewById
    protected FrameLayout flSimilar_FMI;

    @ViewById
    protected RecyclerView rvSimilar_FMI;

    @ViewById
    protected LinearLayout llReviews_FMI;

    @ViewById
    protected RippleView rippleReviews_FMI;

    @ViewById
    protected RecyclerView rvReviews_FMI;
    //endregion

    @AfterInject
    protected void initPresenter() {
        new MovieInfoPresenter(this, responseMovieInfo);
    }

    @AfterViews
    protected void initUI() {
        cclCast_MFI     .setItemListener(this::clickCastItem);
        cclCast_MFI     .setMoreListener(this::clickCastMore);
//        similarAdapter  .setSimilarListener(this::clickSimilarItem);

        RxView.clicks(btnImdb_FMI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe((aVoid) -> clickImdbButton());
        RxView.clicks(btnWeb_FMI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe((aVoid) -> clickWebButton());
        RxView.clicks(ivPoster_FMI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe((aVoid) -> clickPoster());
        RxView.clicks(flSimilar_FMI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> clickSimilarMore());
        RxView.clicks(rippleReviews_FMI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> clickReviewsMore());

        presenter.subscribe();
    }

    @Override
    public void displayPosterImage(String url) {
        Glide.with(this)
                .load(Constants.BASE_IMAGE_URL + url)
                .centerCrop()
                .into(ivPoster_FMI);
    }

    @Override
    public void displayTitle(String title) {
        tvMovieTitle_FMI.setText(title);
    }

    @Override
    public void displayTagline(String tagline) {
        tvMovieTagline_FMI.setText(tagline);
    }

    @Override
    public void displayGenres(String genres) {
        tvMovieGenres_FMI.setText(genres);
    }

    @Override
    public void displayMovieCompanies(String companies) {
        tvMovieCompany_FMI.setText(companies);
    }

    @Override
    public void displayMovieCountries(String countries) {
        tvMovieCountries_FMI.setText(countries);
    }

    @Override
    public void enableImdbButton(boolean isEnabled) {
        btnImdb_FMI.setVisibility(isEnabled ? View.VISIBLE : View.GONE);
    }

    @Override
    public void enableWebButton(boolean isEnabled) {
        btnWeb_FMI.setVisibility(isEnabled ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayRating(String rating) {
        tvMovieRating_FMI.setText(rating);
    }

    @Override
    public void displayBudget(String budget) {
        tvMovieBudget_FMI.setText(budget);
    }

    @Override
    public void displayRevenue(String revenue) {
        tvMovieRevenue_FMI.setText(revenue);
    }

    @Override
    public void displayReleaseStatus(String status) {
        tvMovieReleaseInfo_FMI.setText(status);
    }

    @Override
    public void displayReleaseDate(String date) {
        tvMovieDate_FMI.setText(date);
    }

    @Override
    public void displayDuration(String duration) {
        tvMovieDuration_FMI.setText(duration);
    }

    @Override
    public void setupCircleCastView(MovieCredits data) {
        cclCast_MFI.setCredits(data);
    }

    @Override
    public void displayMovieOverview(String overview) {
        tvMovieOverview_FMI.setText(overview);
    }

    @Override
    public void setupMovieSimilar(ArrayList<SimilarDH> similarDHs) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvSimilar_FMI.setLayoutManager(llm);
        similarAdapter.setListDH(similarDHs);
        similarAdapter.setOnCardClickListener(position -> clickSimilarItem(similarAdapter.getItem(position).movieSimilar.id));
        rvSimilar_FMI.setAdapter(similarAdapter);
    }

    @Override
    public void setupMovieReviews(MovieReviews movieReviews) {

    }

    @Override
    public void setCastVisibility(boolean isVisible) {
        cclCast_MFI.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setSimilarVisibility(boolean isVisible) {
        rvSimilar_FMI.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setReviewVisibility(boolean isVisible) {
        rvReviews_FMI.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPosterClickable(boolean isClickable) {
        ivPoster_FMI.setClickable(isClickable);
    }

    @Override
    public void clickPoster() {
        presenter.openFullscreenPoster(getContext());
    }

    @Override
    public void clickCastItem(int personId) {
        presenter.startCastItem(personId);
    }

    @Override
    public void clickCastMore() {
        presenter.startMoreCast();
    }

    @Override
    public void clickImdbButton() {
        presenter.startImdbScreen(getContext());
    }

    @Override
    public void clickWebButton() {
        presenter.startWebScreen(getContext());
    }

    @Override
    public void clickSimilarItem(int similarMovieId) {
        presenter.startSimilarMovieScreen(similarMovieId);
    }

    @Override
    public void clickSimilarMore() {
        presenter.startMoreSimilarMovies();
    }

    @Override
    public void clickReviewsMore() {
        presenter.startReviewsMore();
    }

    @Override
    public void refreshMovieInfo(int movieID) {
        mActivity.refreshMovieInfo(movieID);
    }

    @Override
    public void setPresenter(MovieInfoContract.MovieInfoPresenter presenter) {
        this.presenter = presenter;
    }
}
