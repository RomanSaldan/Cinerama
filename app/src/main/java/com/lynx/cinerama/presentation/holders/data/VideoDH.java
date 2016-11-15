package com.lynx.cinerama.presentation.holders.data;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.lynx.cinerama.data.model.movies.videos.MovieVideo;
import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 11/10/2016.
 */

public class VideoDH extends RecyclerDH {
    private MovieVideo movieVideo;

    public VideoDH(MovieVideo movieVideo) {
        this.movieVideo = movieVideo;
    }

    public MovieVideo getMovieVideo() {
        return movieVideo;
    }
}
