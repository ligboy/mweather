package org.ligboy.mweather.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Inputtips.InputtipsListener;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.lapism.searchview.SearchAdapter;
import com.lapism.searchview.SearchItem;

import org.ligboy.mweather.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class LocationSuggestionsAdapter extends SearchAdapter
        implements InputtipsListener {

    private Inputtips mInputtips;
    private final Object mLock = new Object();
    private Filter mFilter;

    public LocationSuggestionsAdapter(Context context) {
        super(context);
        init(context);
    }

    public LocationSuggestionsAdapter(Context context, List<SearchItem> suggestionsList) {
        super(context, suggestionsList);
        init(context);
    }

    private void init(Context context) {
        mInputtips = new Inputtips(context, this);
        mFilter = new Filter() {

            private final FilterResults NON_NULL_RESULT = new FilterResults();

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                key = charSequence.toString();
                if (!TextUtils.isEmpty(charSequence)) {
                    mInputtips.setQuery(new InputtipsQuery(charSequence.toString(), null));
                    mInputtips.requestInputtipsAsyn();
                    try {
                        synchronized(mLock) {
                            mLock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return NON_NULL_RESULT;
                } else {
                    return null;
                }
            }

            @Override
            public void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults == null) {
                    mResultList.clear();
                    notifyDataSetChanged();
                } else {
                    notifyDataSetChanged();
                }
            }

        };
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder viewHolder, int position) {
        if (viewHolder instanceof LocationResultViewHolder) {

        } else {
            super.onBindViewHolder(viewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        super.setOnItemClickListener(mItemClickListener);
    }

    @Override
    public void onGetInputtips(List<Tip> list, int resultID) {
        mResultList.clear();
        if (resultID == 1000 && list != null && list.size() > 0) {
            ArrayList<SearchItem> searchItems = new ArrayList<>(list.size());
            for (int i = 0; i < list.size(); i++) {
                Tip tip = list.get(i);
                if (!TextUtils.isEmpty(tip.getName())) {
                    SearchItem item = new SearchItem(R.drawable.ic_place_color_control_normal_24dp,
                            tip.getDistrict() + tip.getName());
                    searchItems.add(item);
                }
            }
            mResultList.addAll(searchItems);
        }

        synchronized(mLock) {
            mLock.notify();
        }
    }

    public class LocationResultViewHolder extends ResultViewHolder  {

        public LocationResultViewHolder(View view) {
            super(view);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
        }
    }
}
