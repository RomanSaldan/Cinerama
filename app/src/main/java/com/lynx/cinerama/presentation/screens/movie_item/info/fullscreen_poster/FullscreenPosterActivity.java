package com.lynx.cinerama.presentation.screens.movie_item.info.fullscreen_poster;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lynx.cinerama.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Lynx on 10/31/2016.
 */

@Fullscreen
@EActivity(R.layout.activity_fullscreen_poster)
public class FullscreenPosterActivity extends AppCompatActivity implements FullscreenPosterContract.FullscreenPosterView {

    private FullscreenPosterContract.FullscreenPosterPresenter presenter;
    private PhotoViewAttacher photoViewAttacher;

    @Extra
    protected Bitmap imageBitmap;

    @ViewById
    protected Toolbar toolbarTransparent_AFP;
    @ViewById
    protected ImageView ivFullscreenPoster_AFP;
    @ViewById
    protected ProgressBar pbLoading_AFP;

    @AfterInject
    protected void initPresenter() {
        new FullscreenPosterPresenter(this, imageBitmap);
    }

    @AfterViews
    protected void initUI() {
        setSupportActionBar(toolbarTransparent_AFP);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        presenter.subscribe();
    }

    @Override
    public void initImage(Bitmap imageBitmap) {
        photoViewAttacher = new PhotoViewAttacher(ivFullscreenPoster_AFP, true);
        ivFullscreenPoster_AFP.setImageBitmap(imageBitmap);
    }

    @Override
    public void displayProgress(boolean isShown) {
        pbLoading_AFP.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPresenter(FullscreenPosterContract.FullscreenPosterPresenter presenter) {
        this.presenter = presenter;
    }

    @OptionsItem(android.R.id.home)
    protected void homeSelected() {
        supportFinishAfterTransition();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.unsubscribe();
    }
}
