package com.lynx.cinerama.presentation.screens.actor_item.scenes;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.presentation.adapters.ScenesAdapter;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.holders.data.SceneDH;
import com.lynx.cinerama.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.lynx.cinerama.presentation.screens.actor_item.ActorItemActivity;
import com.lynx.cinerama.presentation.screens.gallery.FullscreenImageActivity_;
import com.lynx.cinerama.presentation.utils.Constants;

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

@EFragment(R.layout.fragment_actor_scenes)
public class ActorScenesFragment extends BaseFragment<ActorItemActivity> implements ActorScenesContract.ActorScenesView {

    private ActorScenesContract.ActorScenesPresenter presenter;
    private GridLayoutManager glm;

    @FragmentArg
    protected int actorID;

    @ViewById
    protected RecyclerView rvActorScenes_FAS;

    @Bean
    protected ScenesAdapter scenesAdapter;
    @Bean
    protected ActorRepository actorRepository;

    @AfterInject
    protected void initPresenter() {
        new ActorScenesPresenter(this, actorRepository, actorID);
    }

    @AfterViews
    protected void initUI() {
        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
        glm = new GridLayoutManager(getActivity(), spanCount, LinearLayoutManager.VERTICAL, false);
        rvActorScenes_FAS.setLayoutManager(glm);
        rvActorScenes_FAS.setAdapter(scenesAdapter);
        rvActorScenes_FAS.addOnScrollListener(new EndlessRecyclerViewScrollListener(glm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadMoreActorScenes(page);
            }
        });
        scenesAdapter.setOnCardClickListener((view, position, viewType) -> {
            clickActorScene(view, position);
        });

        presenter.subscribe();
    }

    @Override
    public void displayActorScenes(ArrayList<SceneDH> sceneDHs) {
        scenesAdapter.addListDH(sceneDHs);
    }

    @Override
    public void clickActorScene(View v, int pos) {
        presenter.startSceneGallery(v, pos);
    }

    @Override
    public void displaySceneGallery(View v, int pos, int actorID, ArrayList<ImageModel> cachedImages, int page, int totalImages) {
        View vv = glm.findContainingItemView(v).findViewById(R.id.ivMovieScene_LIS);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vv.setTransitionName("gallery" + pos);
        }

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), vv, "gallery" + pos);

        FullscreenImageActivity_.intent(getActivity())
                .actorID(actorID)
                .currentPosition(pos)
                .cachedGallery(cachedImages)
                .page(page)
                .totalItems(totalImages)
                .galleryType(Constants.GALLERY_TYPE_ACTOR_SCENES)
                .withOptions(options.toBundle())
                .start();
    }

    @Override
    public void setPresenter(ActorScenesContract.ActorScenesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int spanCount = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
        glm.setSpanCount(spanCount);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null)
            presenter.unsubscribe();
    }
}
