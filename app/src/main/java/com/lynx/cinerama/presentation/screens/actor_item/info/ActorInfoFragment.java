package com.lynx.cinerama.presentation.screens.actor_item.info;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.screens.actor_item.ActorItemActivity;
import com.lynx.cinerama.presentation.screens.movie_item.info.fullscreen_poster.FullscreenPosterActivity_;
import com.lynx.cinerama.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 11/16/2016.
 */

@EFragment(R.layout.fragment_actor_info)
public class ActorInfoFragment extends BaseFragment<ActorItemActivity> implements ActorInfoContract.ActorInfoView {

    private ActorInfoContract.ActorInfoPresenter presenter;

    @FragmentArg
    protected int actorID;

    @ViewById
    protected TextView tvActorName_FAI;
    @ViewById
    protected TextView tvKnownAs_FAI;
    @ViewById
    protected ImageView ivActorImage_FAI;
    @ViewById
    protected ImageView btnImdb_FAI;
    @ViewById
    protected ImageView btnWeb_FAI;
    @ViewById
    protected TextView tvActorBirthplace_FAI;
    @ViewById
    protected TextView tvActorBirthday_FAI;
    @ViewById
    protected TextView tvActorBiography_FAI;

    @StringRes(R.string.key_fullscreen_poster)
    protected String keyFullscreenPoster;

    @Bean
    protected ActorRepository actorRepository;

    @AfterInject
    protected void initPresenter() {
        new ActorInfoPresenter(this, actorRepository, actorID);
    }

    @AfterViews
    protected void initUI() {
        RxView.clicks(btnWeb_FAI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(this::clickWeb);
        RxView.clicks(btnImdb_FAI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(this::clickIMDB);
        RxView.clicks(ivActorImage_FAI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(this::clickActorImage);

        presenter.subscribe();
    }

    @Override
    public void displayActorName(String name) {
        tvActorName_FAI.setText(name);
    }

    @Override
    public void displayKnownAs(String knownAs) {
        tvKnownAs_FAI.setText(knownAs);
    }

    @Override
    public void displayActorImage(String path) {
        Glide.with(getActivity())
                .load(Constants.BASE_IMAGE_URL + path)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        presenter.setFullscreenBitmap(resource);
                        ivActorImage_FAI.setImageBitmap(resource);
                        ivActorImage_FAI.setDrawingCacheEnabled(true);
                    }
                });
    }

    @Override
    public void enableWebButton(boolean isEnabled) {
        btnWeb_FAI.setVisibility(isEnabled ? View.VISIBLE : View.GONE);
    }

    @Override
    public void enableImdbButton(boolean isEnabled) {
        btnImdb_FAI.setVisibility(isEnabled ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayBirthplace(String birthplace) {
        tvActorBirthplace_FAI.setText(birthplace);
    }

    @Override
    public void displayBirthdate(String birthdate) {
        tvActorBirthday_FAI.setText(birthdate);
    }

    @Override
    public void displayBiography(String biography) {
        tvActorBiography_FAI.setText(biography);
    }

    @Override
    public void clickWeb(Void v) {
        presenter.startActorWebpage();
    }

    @Override
    public void clickIMDB(Void v) {
        presenter.startActorImdbPage();
    }

    @Override
    public void clickActorImage(Void v) {
        presenter.startFullscreenImage();
    }

    @Override
    public void displayActorWebpage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void displayActorImdbPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void displayFullscreenImage(Bitmap fullscreenImage) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), ivActorImage_FAI, keyFullscreenPoster);

        FullscreenPosterActivity_.intent(getActivity())
                .imageBitmap(fullscreenImage)
                .withOptions(options.toBundle())
                .start();
    }

    @Override
    public void setPresenter(ActorInfoContract.ActorInfoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null)
            presenter.unsubscribe();
    }
}
