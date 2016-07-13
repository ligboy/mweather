package org.ligboy.mweather.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class LocationSuggestionsAdapter extends ResourceCursorAdapter {

    public LocationSuggestionsAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
