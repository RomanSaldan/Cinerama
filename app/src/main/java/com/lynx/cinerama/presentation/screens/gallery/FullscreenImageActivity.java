package com.lynx.cinerama.presentation.screens.gallery;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.adapters.GalleryPagerAdapter;
import com.lynx.cinerama.presentation.custom.FixedZoomViewPager;
import com.lynx.cinerama.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 11/2/2016.
 */

@EActivity(R.layout.activity_fullscreen_image)
public class FullscreenImageActivity extends AppCompatActivity implements FullscreenImageContract.FullscreenImageView {

    private FullscreenImageContract.FullscreenImagePresenter presenter;

    @Extra
    protected int currentPosition;

    @Extra
    protected int movieID;

    @Extra
    protected String galleryType;

    @Bean
    protected MovieRepository movieRepository;

    @Bean
    protected GalleryPagerAdapter galleryPagerAdapter;

    @ViewById
    protected TextView tvPageIndicator_AFI;

    @ViewById
    protected ImageView ivBack_AFI;

    @ViewById
    protected LinearLayout llContainerTitle_AFI;

    @ViewById
    protected TextView tvTitle_AFI;

    @ViewById
    protected FixedZoomViewPager vpFullscreenImage_AFI;

    @AfterInject
    protected void initPresenter() {
        new FullscreenImagePresenter(this, movieID, galleryType, movieRepository);
    }

    @AfterViews
    protected void initUI() {
        vpFullscreenImage_AFI.setAdapter(galleryPagerAdapter);
        vpFullscreenImage_AFI.addOnPageChangeListener(pageChangeListener);
        galleryPagerAdapter.setTransitionrequisites(this, currentPosition);
        RxView.clicks(ivBack_AFI)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(this::clickBack);

        presenter.subscribe();
        supportPostponeEnterTransition();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        supportPostponeEnterTransition();
    }

    @Override
    public void displayGallery(List<ImageModel> imageModels) {
        galleryPagerAdapter.updateData(imageModels);
        vpFullscreenImage_AFI.setCurrentItem(currentPosition);
        displayIndicator(currentPosition + 1, vpFullscreenImage_AFI.getAdapter().getCount());
    }

    @Override
    public void displayIndicator(int current, int total) {
        tvPageIndicator_AFI.setText(String.format("%d/%d", current, total));
    }

    @Override
    public void displayTitle(String title) {
        tvTitle_AFI.setText(title);
    }

    @Override
    public void clickBack(Void v) {
        presenter.back();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void setPresenter(FullscreenImageContract.FullscreenImagePresenter presenter) {
        this.presenter = presenter;
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            displayIndicator(position + 1, vpFullscreenImage_AFI.getAdapter().getCount());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.unsubscribe();
    }
}
