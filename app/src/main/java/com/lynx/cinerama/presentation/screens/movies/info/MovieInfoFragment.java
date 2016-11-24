package com.lynx.cinerama.presentation.screens.movies.info;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.adapters.ReviewsAdapter;
import com.lynx.cinerama.presentation.adapters.SimilarAdapter;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.custom.cast_view.CastLayout;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;
import com.lynx.cinerama.presentation.screens.actors.ActorsActivity_;
import com.lynx.cinerama.presentation.screens.movies.MoviesActivity;
import com.lynx.cinerama.presentation.screens.movies.info.fullscreen_poster.FullscreenPosterActivity_;
import com.lynx.cinerama.presentation.screens.movies.info.review_details.ReviewDetailsActivity_;
import com.lynx.cinerama.presentation.screens.movies.info.similar_details.SimilarDetailsActivity_;
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
    protected int movieID;

    @Bean
    protected MovieRepository movieRepository;

    @Bean
    protected SimilarAdapter similarAdapter;

    @Bean
    protected ReviewsAdapter reviewsAdapter;

    @StringRes(R.string.key_fullscreen_poster)
    protected String keyFullscreenPoster;

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
        new MovieInfoPresenter(this, movieID, movieRepository);
    }

    @AfterViews
    protected void initUI() {
        cclCast_MFI.setItemListener(this::clickCastItem);
        cclCast_MFI.setMoreListener(this::clickCastMore);

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
        RxView.clicks(llReviews_FMI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> clickReviewsMore());

        presenter.subscribe();
    }

    @Override
    public void displayPosterImage(String url) {
        Glide.with(this)
                .load(Constants.BASE_IMAGE_URL + url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        presenter.setPosterBitmap(resource);
                        ivPoster_FMI.setImageBitmap(resource);
                        ivPoster_FMI.setDrawingCacheEnabled(true);
                    }
                });
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
        similarAdapter.setOnCardClickListener((view, position, viewType) -> clickSimilarItem(similarAdapter.getItem(position).movieSimilar.id));
        rvSimilar_FMI.setAdapter(similarAdapter);
    }

    @Override
    public void setupMovieReviews(ArrayList<ReviewDH> reviewDHs) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvReviews_FMI.setLayoutManager(llm);
        reviewsAdapter.setListDH(reviewDHs);
        rvReviews_FMI.setAdapter(reviewsAdapter);
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
        llReviews_FMI.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPosterClickable(boolean isClickable) {
        ivPoster_FMI.setClickable(isClickable);
    }

    @Override
    public void clickPoster() {
        presenter.openFullscreenPoster();
    }

    @Override
    public void clickCastItem(View v,int personId) {
        presenter.startCastItem(v, personId);
    }

    @Override
    public void clickCastMore() {
        presenter.startMoreCast();
    }

    @Override
    public void clickImdbButton() {
        presenter.startImdbScreen();
    }

    @Override
    public void clickWebButton() {
        presenter.startWebScreen();
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
    public void startMoreReviewActivity(int movieID) {
        ReviewDetailsActivity_.intent(this)
                .movieID(movieID)
                .start();
    }

    @Override
    public void startImmdbIntent(String imdbID) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("http://www.imdb.com/title/%s", imdbID)));
        startActivity(intent);
    }

    @Override
    public void startWebIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void startMoreCast() {
        mActivity.openCast();
    }

    @Override
    public void startMoreSimilars(int movieID) {
        SimilarDetailsActivity_.intent(this)
                .movieID(movieID)
                .start();
    }

    @Override
    public void startFullscreenPoster(Bitmap posterBitmap) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), ivPoster_FMI, keyFullscreenPoster);

        FullscreenPosterActivity_.intent(getActivity())
                .imageBitmap(posterBitmap)
                .withOptions(options.toBundle())
                .start();
    }

    @Override
    public void startActorInfoScreen(View v, int actorID) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), v, "circleCast");

        ActorsActivity_.intent(getActivity())
                .actorID(actorID)
                .withOptions(options.toBundle())
                .start();
        getActivity().finish();
    }

    @Override
    public void refreshMovieInfo(int movieID) {
        Glide.get(getActivity()).clearMemory();
        mActivity.refreshMovieInfo(movieID);
    }

    @Override
    public void setPresenter(MovieInfoContract.MovieInfoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null)
            presenter.unsubscribe();
    }
}
