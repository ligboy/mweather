package org.ligboy.mweather.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.ligboy.android.searchview.SearchAdapter;
import org.ligboy.android.searchview.SearchHistoryTable;
import org.ligboy.android.searchview.SearchItem;
import org.ligboy.android.searchview.SearchView;

import org.ligboy.mweather.R;
import org.ligboy.mweather.adapter.LocationSuggestionsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected SearchView mSearchView;
    protected FloatingActionButton mFab;
    protected SearchHistoryTable mHistoryDatabase;
    protected DrawerLayout mDrawerLayout;
    protected Toolbar mToolbar;

    protected void setupSearchView() {
        mHistoryDatabase = new SearchHistoryTable(this);

        mSearchView = (SearchView) findViewById(R.id.searchView);
        if (mSearchView != null) {
            mSearchView.setVersion(SearchView.VERSION_TOOLBAR);
            mSearchView.setVersionMargins(SearchView.VERSION_MARGINS_TOOLBAR_BIG);
            mSearchView.setHint(R.string.search_hint);
            mSearchView.setTextSize(16);
            mSearchView.setDivider(true);
            mSearchView.setVoice(true);
//            mSearchView.setVoiceText("Set permission on Android 6+ !");
            mSearchView.setAnimationDuration(SearchView.ANIMATION_DURATION);
            mSearchView.setShadowColor(ContextCompat.getColor(this, R.color.search_shadow_layout));
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    getData(query, 0);
                     mSearchView.close(false);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            mSearchView.setOnOpenCloseListener(new SearchView.OnOpenCloseListener() {
                @Override
                public void onOpen() {
                    if (mFab != null) {
                        mFab.hide();
                    }
                }

                @Override
                public void onClose() {
                    if (mFab != null) {
                        mFab.show();
                    }
                }
            });

            List<SearchItem> suggestionsList = new ArrayList<>();
            suggestionsList.add(new SearchItem("search1"));
            suggestionsList.add(new SearchItem("search2"));
            suggestionsList.add(new SearchItem("search3"));

            LocationSuggestionsAdapter searchAdapter = new LocationSuggestionsAdapter(this, new ArrayList<SearchItem>());
            searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    TextView textView = (TextView) view.findViewById(R.id.textView_item_text);
                    String query = textView.getText().toString();
                    getData(query, position);
                     mSearchView.close(false);
                }
            });
            mSearchView.setAdapter(searchAdapter);
        }
    }

    protected void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout != null) {
            mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    invalidateOptionsMenu();
                    if (mSearchView != null && mSearchView.isSearchOpen()) {
                        mSearchView.close(true);
                    }
                    if (mFab != null) {
                        mFab.hide();
                    }
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    invalidateOptionsMenu();
                    if (mFab != null) {
                        mFab.show();
                    }
                }
            });
            if (mSearchView != null) {
                mSearchView.setOnMenuClickListener(new SearchView.OnMenuClickListener() {
                    @Override
                    public void onMenuClick() {
                        mDrawerLayout.openDrawer(GravityCompat.START);
                    }
                });
            }
        }
    }


    protected void setupToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                mToolbar.setNavigationContentDescription(getResources().getString(R.string.app_name));
                setSupportActionBar(mToolbar);
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (mSearchView != null && mSearchView.isSearchOpen()) {
             mSearchView.close(true);
        } else {
            super.onBackPressed();
        }
    }

    protected void setupFab(View.OnClickListener listener) {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        if (mFab != null) {
            mFab.setOnClickListener(listener);
        }
    }

    private void getData(String text, int position) {
        mHistoryDatabase.addItem(new SearchItem(text));

//        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
//        intent.putExtra(EXTRA_KEY_VERSION, SearchView.VERSION_TOOLBAR_ICON);
//        intent.putExtra(EXTRA_KEY_VERSION_MARGINS, SearchView.VERSION_MARGINS_TOOLBAR_SMALL);
//        intent.putExtra(EXTRA_KEY_THEME, SearchView.THEME_LIGHT);
//        intent.putExtra(EXTRA_KEY_TEXT, text);
//        startActivity(intent);
//
//        Toast.makeText(getApplicationContext(), text + ", position: " + position, Toast.LENGTH_SHORT).show();
    }

    protected void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);
        recreate();
    }

}
