package com.lynx.cinerama.presentation.base;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
