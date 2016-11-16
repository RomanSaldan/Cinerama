package com.lynx.cinerama.presentation.screens.actors;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.actors.ResponseActorInfo;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.presentation.screens.NavigationActivity;
import com.lynx.cinerama.presentation.utils.Constants;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.lynx.cinerama.R.id.svMovies_AM;

/**
 * Created by Lynx on 10/26/2016.
 */

@EActivity(R.layout.activity_actors)
@OptionsMenu(R.menu.menu_search)
public class ActorsActivity extends NavigationActivity implements ActorsContract.ActorsView {

    private ActorsContract.ActorsPresenter presenter;

    @Extra
    protected int actorID;

    @ViewById
    protected Toolbar toolbar_AA;
    @ViewById
    protected CircleImageView ivCirclePerson_AA;
    @ViewById
    protected TextView tvPersonTitle_AA;
    @ViewById
    protected ViewPager vpActor_AA;
    @ViewById
    protected MaterialSearchView svActors_AA;
    @ViewById
    protected TabLayout tabLayout_AA;

    @OptionsMenuItem(R.id.action_menu_search)
    protected MenuItem menuItemSearch;

    @Bean
    protected ActorRepository actorRepository;

    @AfterInject
    protected void initPresenter() {
        new ActorsPresenter(this, actorRepository, actorID == 0 ? Constants.TEST_ACTOR_ID : actorID);
    }

    @AfterViews
    protected void initUI() {
        navigationView.setCheckedItem(R.id.menuItemActors);
        setupToolbar();
        presenter.subscribe();
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar_AA);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, fullView, toolbar_AA,
                R.string.drawer_open, R.string.drawer_close);
        fullView.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void setActorName(String actorName) {
        tvPersonTitle_AA.setText(actorName);
    }

    @Override
    public void setActorImage(String path) {
        Glide.with(this)
                .load(Constants.BASE_LARGE_IMAGE_URL + path)
                .placeholder(R.drawable.placeholder_portrait)
                .error(R.drawable.placeholder_portrait)
                .centerCrop()
                .into(ivCirclePerson_AA);
    }

    @Override
    public void setupBottomBar() {

    }

    @Override
    public void setupActorInfo(ResponseActorInfo responseActorInfo) {

    }

    @Override
    public void refreshActorInfo(int actorID) {
        this.actorID = actorID;
        initPresenter();
        presenter.subscribe();
    }

    @Override
    public void setPresenter(ActorsContract.ActorsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        svActors_AA.setMenuItem(menuItemSearch);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null) presenter.unsubscribe();
    }
}
