package com.si.uinam.mymoviecatalogueapi.view;

import android.content.Context;

public interface FragmentLifecycle {
    void onPauseFragment(Context context);
    void onResumeFragment(Context context);
}
