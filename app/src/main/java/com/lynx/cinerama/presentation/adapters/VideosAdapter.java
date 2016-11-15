package com.lynx.cinerama.presentation.adapters;

import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.VideoDH;
import com.lynx.cinerama.presentation.holders.view.VideoVH;
import com.michenko.simpleadapter.SimpleRecyclerAdapter;

import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lynx on 11/10/2016.
 */

@EBean
public class VideosAdapter extends SimpleRecyclerAdapter<VideoDH, VideoVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.list_item_video;
    }

}
