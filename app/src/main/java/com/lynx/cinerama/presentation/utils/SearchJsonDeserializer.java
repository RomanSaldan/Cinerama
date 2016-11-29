package com.lynx.cinerama.presentation.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lynx.cinerama.data.model.search.ResponseMultiSearch;
import com.lynx.cinerama.data.model.search.SearchResultMovie;
import com.lynx.cinerama.data.model.search.SearchResultPerson;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Lynx on 11/29/2016.
 */

public class SearchJsonDeserializer implements JsonDeserializer<ResponseMultiSearch> {
    @Override
    public ResponseMultiSearch deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ResponseMultiSearch result = new ResponseMultiSearch();
        Gson gson = new Gson();
        JsonObject jsonObject = json.getAsJsonObject();
        result.page = jsonObject.get("page").getAsInt();
        result.total_pages = jsonObject.get("total_pages").getAsInt();
        result.total_results = jsonObject.get("total_results").getAsInt();
        result.results = new ArrayList<>();
        JsonArray resultItems = jsonObject.getAsJsonArray("results");
        for(JsonElement element : resultItems) {
            String media_type = element.getAsJsonObject().get("media_type").getAsString();
            switch (media_type) {
                case "person":
                    result.results.add(gson.fromJson(element, SearchResultPerson.class));
                    break;
                case "movie":
                    result.results.add(gson.fromJson(element, SearchResultMovie.class));
                    break;
            }
        }
        return result;
    }
}
