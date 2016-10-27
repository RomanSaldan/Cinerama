package com.lynx.cinerama.presentation.custom.cast_view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.credits.PersonCast;
import com.lynx.cinerama.data.model.movies.credits.PersonCrew;
import com.lynx.cinerama.presentation.utils.Constants;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lynx on 10/27/2016.
 */

@EViewGroup(R.layout.layout_cast)
public class CastLayout extends LinearLayout {
    private Context mCtx;
    private ArrayList<PersonCrew> directors;
    private ArrayList<PersonCast> actors;

    private ICustomCastMoreListener moreListener;
    private ICustomCastItemListener itemListener;

    public CastLayout(Context context) {
        super(context);
        mCtx        = context;
        directors   = new ArrayList<>();
        actors      = new ArrayList<>();
    }

    public CastLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCtx        = context;
        directors   = new ArrayList<>();
        actors      = new ArrayList<>();
    }

    @ViewById
    protected FrameLayout flCircleCast_LCC;

    @ViewsById({R.id.civDirectorOne_LCC, R.id.civDirectorTwo_LCC})
    protected ArrayList<CircleImageView> directorViews;

    @ViewsById({R.id.civActorOne_LCC, R.id.civActorTwo_LCC, R.id.civActorThree_LCC})
    protected List<CircleImageView> actorViews;

    private void initListeners() {
        RxView.clicks(flCircleCast_LCC)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe((aVoid) -> moreListener.clickMore());
        if(directors.size() > 0) {
            for(int i = 0; i < directors.size(); i++) {
                final int p = i;
                RxView.clicks(directorViews.get(p))
                        .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                        .subscribe((aVoid) -> itemListener.clickItem(directors.get(p).id));
            }
        }
        if(actors.size() > 0) {
            for(int i = 0; i < actors.size(); i++) {
                final int p = i;
                RxView.clicks(actorViews.get(p))
                        .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                        .subscribe((aVoid) -> itemListener.clickItem(actors.get(p).id));
            }
        }
    }

    public void setCredits(MovieCredits data) {
        filterData(data);
        if(directors.size() > 0)
            for(int i = 0; i < directors.size(); i++) {
                directorViews.get(i).setVisibility(VISIBLE);
                Glide.with(mCtx)
                        .load(Constants.BASE_IMAGE_URL + directors.get(i).profile_path)
                        .centerCrop()
                        .into(directorViews.get(i));
            }
        if(actors.size() > 0)
            for(int i = 0; i < actors.size(); i++) {
                actorViews.get(i).setVisibility(VISIBLE);
                Glide.with(mCtx)
                        .load(Constants.BASE_IMAGE_URL + actors.get(i).profile_path)
                        .centerCrop()
                        .into(actorViews.get(i));
            }

    }

    private void filterData(MovieCredits credits) {
        if(credits.crew != null && credits.crew.size() > 0)
            for(PersonCrew personCrew : credits.crew) {
                if(personCrew.job.equalsIgnoreCase(Constants.KEY_DIRECTOR) && personCrew.profile_path != null) {
                    directors.add(personCrew);
                    if(directors.size() == 2) break;
                }
            }
        if(credits.cast != null && credits.cast.size() > 0) {
            for(PersonCast personCast : credits.cast) {
                if(personCast.profile_path != null) {
                    actors.add(personCast);
                    if(actors.size() == 3) break;
                }
            }
        }
        initListeners();
    }

    public void setItemListener(ICustomCastItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setMoreListener(ICustomCastMoreListener moreListener) {
        this.moreListener = moreListener;
    }
}
