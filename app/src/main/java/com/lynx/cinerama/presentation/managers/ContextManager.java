package com.lynx.cinerama.presentation.managers;

import android.content.Context;

/**
 * Created by Lynx on 10/26/2016.
 */

public class ContextManager {

    private static Context instance;

    public static void setInstance(Context instance) {
        ContextManager.instance = instance;
    }

    public static Context getInstance() {
        return instance;
    }
}
