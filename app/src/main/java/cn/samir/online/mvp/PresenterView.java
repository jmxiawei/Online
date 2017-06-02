package cn.samir.online.mvp;

import android.content.Context;


public interface PresenterView {

    boolean onSuccess(Object... params);

    boolean onFail(Object... params);

    /**
     * Get a {@link Context}.
     */
    Context context();

}
