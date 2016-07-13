package org.ligboy.mweather.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ligboy.mweather.R;
import org.ligboy.mweather.api.loader.RealTimeLoader;
import org.ligboy.mweather.common.Weather;
import org.ligboy.mweather.common.WindDirection;
import org.ligboy.mweather.common.WindLevel;
import org.ligboy.mweather.model.RealTimeResult;
import org.ligboy.mweather.model.realtime.RealTime;
import org.ligboy.mweather.model.realtime.Wind;

import java.text.NumberFormat;

import retrofit2.Response;

/**
 *
 */
public class NowFragment extends Fragment {

    private static final int LOADER_ID_REAL_TIME = 1;

    private AppCompatTextView mUpdateTimeView;
    private AppCompatTextView mTemperatureView;
    private AppCompatTextView mTemperatureUnitView;
    private AppCompatTextView mHumidityView;
    private AppCompatTextView mWindSpeedView;
    private AppCompatTextView mWindLabelView;
    private AppCompatImageView mWindIndicatorView;
    private AppCompatImageView mWeatherIndicatorView;
    private AppCompatTextView mWeatherLabelView;

    private Loader<Response<RealTimeResult>> mRealTimeLoader;
    private RealTimeResult mRealTimeResult;

    public NowFragment() {
        // Required empty public constructor
    }

    public static NowFragment newInstance() {
        NowFragment fragment = new NowFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_now, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUpdateTimeView = (AppCompatTextView) view.findViewById(R.id.updateTime);
        mTemperatureView = (AppCompatTextView) view.findViewById(R.id.temperature);
        mTemperatureUnitView = (AppCompatTextView) view.findViewById(R.id.temperatureUnit);
        mHumidityView = (AppCompatTextView) view.findViewById(R.id.humidityLabel);
        mWindSpeedView = (AppCompatTextView) view.findViewById(R.id.windSpeedLabel);
        mWindLabelView = (AppCompatTextView) view.findViewById(R.id.windLabel);
        mWindIndicatorView = (AppCompatImageView) view.findViewById(R.id.windIndicator);
        mWeatherIndicatorView = (AppCompatImageView) view.findViewById(R.id.weatherIndicator);
        mWeatherLabelView = (AppCompatTextView) view.findViewById(R.id.weatherLabel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRealTimeLoader = getLoaderManager().initLoader(LOADER_ID_REAL_TIME, null,
                new LoaderManager.LoaderCallbacks<Response<RealTimeResult>>() {
                    @Override
                    public Loader<Response<RealTimeResult>> onCreateLoader(int id, Bundle args) {
                        return new RealTimeLoader(getContext(), "118.6175988,24.8771");
                    }

                    @Override
                    public void onLoadFinished(Loader<Response<RealTimeResult>> loader,
                                               Response<RealTimeResult> data) {
                        if (data != null && data.isSuccessful() && data.body() != null) {
                            RealTimeResult body = data.body();
                            mRealTimeResult = body;
                            update();
                        }
                    }

                    @Override
                    public void onLoaderReset(Loader<Response<RealTimeResult>> loader) {
//                mRealTimeLoader.reset();
                    }
                });
    }

    private void update() {
        if (mRealTimeResult != null) {
            mUpdateTimeView.setText(DateUtils.getRelativeTimeSpanString(getContext(),
                    mRealTimeResult.serverTime * 1000L, true));
            mTemperatureUnitView.setVisibility(View.VISIBLE);
            RealTime result = mRealTimeResult.result;
            if (result != null) {
                mTemperatureView.setText(String.valueOf(result.temperature));

                mHumidityView.setText(getString(R.string.now_humidity,
                        NumberFormat.getPercentInstance().format(result.humidity)));

                Weather weather = Weather.from(result.skyCondition);
                mWeatherIndicatorView.setImageResource(weather.getIconRes());
                mWeatherIndicatorView.setContentDescription(getString(weather.getNameRes()));
                mWeatherLabelView.setText(weather.getNameRes());

                Wind wind = result.wind;
                if (wind != null) {
                    mWindSpeedView.setVisibility(View.VISIBLE);
                    mWindLabelView.setVisibility(View.VISIBLE);
                    mWindIndicatorView.setVisibility(View.VISIBLE);

                    mWindSpeedView.setText(getString(R.string.now_wind_speed, wind.speed));
                    mWindIndicatorView.setRotation(wind.direction + 180);
                    WindLevel level = WindLevel.beaufortValueOf(wind.speed);
                    if (level != WindLevel.CALM) {
                        StringBuilder stringBuilder = new StringBuilder();
                        WindDirection windDirection = WindDirection.valueOf(wind.direction);
                        stringBuilder.append(getString(windDirection.getNameRes()));
                        stringBuilder.append(' ');
                        stringBuilder.append(getString(level.getNameRes())).append(' ');
                        mWindLabelView.setText(stringBuilder.toString());
                    } else {
                        mWindLabelView.setText(R.string.now_wind_level_calm);
                    }
                } else {
                    mWindSpeedView.setVisibility(View.INVISIBLE);
                    mWindLabelView.setVisibility(View.INVISIBLE);
                    mWindIndicatorView.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
}
