package com.lynx.cinerama.presentation.adapters;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.SceneDH;
import com.lynx.cinerama.presentation.holders.view.SceneVH;
import com.michenko.simpleadapter.SimpleRecyclerAdapter;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 11/2/2016.
 */

@EBean
public class ScenesAdapter extends SimpleRecyclerAdapter<SceneDH, SceneVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.list_item_scene;
    }
}
