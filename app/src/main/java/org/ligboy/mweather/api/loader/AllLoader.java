package org.ligboy.mweather.api.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class AllLoader extends AsyncTaskLoader {

    public AllLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
