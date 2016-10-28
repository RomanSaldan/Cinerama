package com.lynx.cinerama.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;

/**
 * Created by Lynx on 10/28/2016.
 */

@EFragment
public abstract class BaseFragment<T extends Activity> extends Fragment {

    protected T mActivity;

    @SystemService
    protected InputMethodManager inputMethodManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mActivity = (T) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("This fragment should have activity instance");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(getView() != null) {
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }
}
