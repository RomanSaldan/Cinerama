package com.lynx.cinerama.presentation.screens.actors.images;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lynx.cinerama.R;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.presentation.adapters.PostersAdapter;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.holders.data.PosterDH;
import com.lynx.cinerama.presentation.screens.actors.ActorsActivity;
import com.lynx.cinerama.presentation.screens.gallery.FullscreenImageActivity_;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.RecyclerVH;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/16/2016.
 */

@EFragment(R.layout.fragment_actor_images)
public class ActorImagesFragment extends BaseFragment<ActorsActivity> implements ActorImagesContract.ActorImagesView {

    private ActorImagesContract.ActorImagesPresenter presenter;
    private GridLayoutManager glm;

    @FragmentArg
    protected int actorID;

    @ViewById
    protected RecyclerView rvActorImages_FAI;

    @Bean
    protected ActorRepository actorRepository;
    @Bean
    protected PostersAdapter postersAdapter;

    @AfterInject
    protected void initPresenter() {
        new ActorImagesPresenter(this, actorRepository, actorID);
    }

    @AfterViews
    protected void initUI() {
        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;
        glm = new GridLayoutManager(getActivity(), spanCount, LinearLayoutManager.VERTICAL, false);
        rvActorImages_FAI.setLayoutManager(glm);
        rvActorImages_FAI.setAdapter(postersAdapter);
        postersAdapter.setOnCardClickListener((view, position, viewType) -> clickImage(view, position));

        presenter.subscribe();
    }

    @Override
    public void displayActorImages(ArrayList<PosterDH> imagesDHs) {
        postersAdapter.setListDH(imagesDHs);
    }

    @Override
    public void clickImage(View v, int position) {
        presenter.startImageGallery(v, position);
    }

    @Override
    public void startImageGalleryScreen(View v, int pos, int actorID) {
        View vv = glm.findContainingItemView(v).findViewById(R.id.ivPoster_LIP);

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            vv.setTransitionName("gallery" + pos);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), vv, "gallery" + pos);

        FullscreenImageActivity_.intent(getActivity())
                .actorID(actorID)
                .currentPosition(pos)
                .galleryType(Constants.GALLERY_TYPE_IMAGES)
                .withOptions(options.toBundle())
                .start();

    }

    @Override
    public void setPresenter(ActorImagesContract.ActorImagesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int spanCount = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;
        glm.setSpanCount(spanCount);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null)
            presenter.unsubscribe();
    }
}
