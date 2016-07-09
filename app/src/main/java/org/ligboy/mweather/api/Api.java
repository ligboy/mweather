package org.ligboy.mweather.api;

import android.content.Context;
import android.support.annotation.NonNull;

import org.ligboy.mweather.R;
import org.ligboy.mweather.core.RetrofitManager;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public final class Api {

    private static CaiyunV1Api CAIYUN_V_1;
    private static CaiyunV2Api CAIYUN_V_2;

    private Api() {
        throw new IllegalAccessError("");
    }

    public static void setup(@NonNull Context context) {
        RetrofitManager retrofitManager1 = new RetrofitManager(context,
                context.getString(R.string.caiyun_base));
        CAIYUN_V_1 = retrofitManager1.create(CaiyunV1Api.class);
        RetrofitManager retrofitManager2 = new RetrofitManager(context,
                context.getString(R.string.caiyun_api_base));
        CAIYUN_V_2 = retrofitManager2.create(CaiyunV2Api.class);

    }

    public static CaiyunV1Api caiyunV1() {
        return CAIYUN_V_1;
    }

    public static CaiyunV2Api caiyunV2() {
        return CAIYUN_V_2;
    }
}
