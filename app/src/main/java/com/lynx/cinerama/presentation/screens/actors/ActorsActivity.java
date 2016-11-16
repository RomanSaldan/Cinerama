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
import com.lynx.cinerama.presentation.adapters.ActorsTabAdapter;
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
import org.androidannotations.annotations.res.StringRes;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.lynx.cinerama.R.id.svMovies_AM;
import static com.lynx.cinerama.R.id.tabLayout_AM;
import static com.lynx.cinerama.R.id.viewpager_AM;

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

    @StringRes(R.string.tab_title_info)
    protected String tabTitleInfo;
    @StringRes(R.string.tab_title_credits)
    protected String tabTitleCredits;
    @StringRes(R.string.tab_title_images)
    protected String tabTitleImages;
    @StringRes(R.string.tab_title_scenes)
    protected String tabTitleScenes;


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
    public void displayActorName(String actorName) {
        tvPersonTitle_AA.setText(actorName);
    }

    @Override
    public void displayActorImage(String path) {
        Glide.with(this)
                .load(Constants.BASE_LARGE_IMAGE_URL + path)
                .centerCrop()
                .into(ivCirclePerson_AA);
    }

    @Override
    public void setupBottomBar() {
        HashMap<String, Integer> tabsMap = new HashMap<>();
        tabsMap.put(tabTitleInfo, R.drawable.selector_tab_actor_info);
        tabsMap.put(tabTitleCredits, R.drawable.selector_tab_actor_credits);
        tabsMap.put(tabTitleImages, R.drawable.selector_tab_actor_images);
        tabsMap.put(tabTitleScenes, R.drawable.selector_tab_actor_scenes);

        for (int i = 0; i < tabLayout_AA.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout_AA.getTabAt(i);
            if (tabsMap.keySet().contains(tab.getText().toString())) {
                tab.setIcon(tabsMap.get(tab.getText().toString()));
                tab.setText("");
            }
        }
    }

    @Override
    public void setupActorInfo(ResponseActorInfo responseActorInfo) {
        ActorsTabAdapter tabAdapter = new ActorsTabAdapter(this, getSupportFragmentManager(), responseActorInfo);
        vpActor_AA.setAdapter(tabAdapter);
        tabLayout_AA.setupWithViewPager(vpActor_AA);
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
