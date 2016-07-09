package org.ligboy.mweather.util;

import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public final class ConfigurationUtil {

    private ConfigurationUtil() {
        throw new IllegalAccessError("");
    }

    /**
     * Get the list of Locales.
     * @param configuration The {@link Configuration}.
     * @return list of Locales.
     */
    @SuppressWarnings("deprecation")
    public static Locale[] getLocales(@NonNull Configuration configuration) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return new Locale[] {configuration.locale};
        } else {
            LocaleList localeList = configuration.getLocales();
            int size = localeList.size();
            Locale[] locales = new Locale[size];
            for (int i = 0; i < size; i++) {
                locales[i] = localeList.get(i);
            }
            return locales;
        }
    }

}
