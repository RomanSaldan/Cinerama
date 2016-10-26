package com.lynx.cinerama.presentation;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.lynx.cinerama.BuildConfig;
import com.squareup.leakcanary.LeakCanary;

import org.androidannotations.annotations.EApplication;

/**
 * Created by Lynx on 10/26/2016.
 */

@EApplication
public class CineramaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(!BuildConfig.PRODUCTION) {
            LeakCanary.install(this);
            Stetho.initializeWithDefaults(this);
        }
    }
}
