package com.lynx.cinerama.data.model.search;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/29/2016.
 */

public class ResponseMultiSearch {
    public int page;
    public int total_pages;
    public int total_results;
    public ArrayList<SearchResultCommon> results;
}
