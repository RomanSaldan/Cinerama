package com.lynx.cinerama.presentation.screens;

import com.lynx.cinerama.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by Lynx on 10/26/2016.
 */

@EActivity(R.layout.activity_actors)
public class ActorsActivity extends NavigationActivity {

    @AfterViews
    protected void initUI() {
        navigationView.setCheckedItem(R.id.menuItemActors);
    }
}
