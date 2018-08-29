package com.shoaibnwar.facilitymanagement.Utilities;

/**
 * Created by gold on 8/28/2018.
 */

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


public class AppContext extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this); // add this
    }
}
