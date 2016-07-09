package org.ligboy.mweather.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import org.ligboy.mweather.R;

import static android.content.Context.ACCOUNT_SERVICE;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public final class AccountUtil {
    // The account name
    private static final String ACCOUNT = "dummyaccount";

    private AccountUtil() {
        throw new IllegalAccessError();
    }

    /**
     * Create a new dummy account for the sync adapter
     *
     * @param context The application context
     */
    public static Account createSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(
                ACCOUNT, context.getString(R.string.account_type));
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            return newAccount;
        } else {
            return null;
        }
    }
}
