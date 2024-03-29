package org.ligboy.mweather.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.ligboy.mweather.account.Authenticator;

public class AuthenticatorService extends Service {

    private Authenticator mAuthenticator;

    public AuthenticatorService() {
    }

    @Override
    public void onCreate() {
        mAuthenticator = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
