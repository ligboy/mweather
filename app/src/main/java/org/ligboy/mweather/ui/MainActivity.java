package org.ligboy.mweather.ui;

import android.animation.ArgbEvaluator;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.ligboy.mweather.R;
import org.ligboy.mweather.adapter.MainPageAdapter;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks;
import timber.log.Timber;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends BaseActivity
        implements OnNavigationItemSelectedListener, PermissionCallbacks {

    private static final int REQUEST_CODE_LOCATION = 124;

    private CoordinatorLayout mContentLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    public AMapLocationClient mLocationClient;

    private final ArgbEvaluator mColorEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mContentLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        setupSearchView();
        setupDrawer();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mViewPager.setAdapter(new MainPageAdapter(this, getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager, true);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
        });
        initLocation();
        startLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            if (mLocationClient.isStarted()) {
                mLocationClient.stopLocation();
            }
            mLocationClient.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            int currentNightMode = getResources().getConfiguration().uiMode
                    & Configuration.UI_MODE_NIGHT_MASK;
            switch (currentNightMode) {
                case Configuration.UI_MODE_NIGHT_NO:
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case Configuration.UI_MODE_NIGHT_YES:
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case Configuration.UI_MODE_NIGHT_UNDEFINED:
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateBackgroundColor(int frontColor, int backColor, float positionOffset) {
        int color = (Integer) mColorEvaluator.evaluate(positionOffset, frontColor, backColor);
        mContentLayout.setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }

    private void initLocation() {
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationCacheEnable(true);
//        option.setNeedAddress(true);
        option.setOnceLocation(true);
        option.setOnceLocationLatest(true);
        option.setKillProcess(true);
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    Timber.d(aMapLocation.toStr());
                    if (aMapLocation.getErrorCode() == 0) {
                        mSearchView.setQuery(aMapLocation.getDistrict() + aMapLocation.getStreet()
                                + aMapLocation.getStreetNum());
                    }

                }
            }
        });
    }

    private void startLocation() {
        if (EasyPermissions.hasPermissions(this, ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)) {
            mLocationClient.startLocation();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.location_rationale),
                    REQUEST_CODE_LOCATION, ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        startLocation();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
