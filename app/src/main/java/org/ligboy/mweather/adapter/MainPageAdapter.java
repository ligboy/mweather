package org.ligboy.mweather.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.ligboy.mweather.R;
import org.ligboy.mweather.ui.NowFragment;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class MainPageAdapter extends FragmentPagerAdapter {

    private static final int[] PAGE_TITLES = new int[] {
            R.string.main_page_title_now,
            R.string.main_page_title_tomorrow,
            R.string.main_page_title_week
    };

    private static final int PAGE_COUNT = PAGE_TITLES.length;

    private final Context mContext;

    public MainPageAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = NowFragment.newInstance();
                break;
            case 1:
                fragment = NowFragment.newInstance();
                break;
            case 2:
                fragment = NowFragment.newInstance();
                break;
            default:
                throw new IllegalStateException("Illegal position of main page: " + position);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(PAGE_TITLES[position]);
    }
}
