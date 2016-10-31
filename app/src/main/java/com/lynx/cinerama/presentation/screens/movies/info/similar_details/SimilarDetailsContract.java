package com.lynx.cinerama.presentation.screens.movies.info.similar_details;

import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/28/2016.
 */

public interface SimilarDetailsContract {
    interface SimilarDetailsView extends BaseView<SimilarDetailsPresenter> {
        void setupToolbar(String moveiTitle);
        void displayMoreSimilars(ArrayList<SimilarDH> similarDHs);
    }
    interface SimilarDetailsPresenter extends BasePresenter {
        void loadMoreSimilars(int page);
    }
}
