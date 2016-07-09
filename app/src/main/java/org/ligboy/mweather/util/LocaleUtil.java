package org.ligboy.mweather.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Locale;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public final class LocaleUtil {

    private LocaleUtil() {
        throw new IllegalAccessError("");
    }

    /**
     * Get Language String, like en_US.
     * @param locale The {@link Locale}.
     * @return Language.
     */
    @Nullable
    public static String getLanguage(Locale locale) {
        if (locale != null) {
            String country = locale.getCountry();
            if (TextUtils.isEmpty(country)) {
                return locale.getLanguage();
            } else {
                return locale.getLanguage() + "_" + country;
            }
        }
        return null;
    }
}
