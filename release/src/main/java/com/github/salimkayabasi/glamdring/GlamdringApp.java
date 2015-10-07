package com.github.salimkayabasi.glamdring;

import android.app.Application;

import com.github.salimkayabasi.glamdring.utils.LogUtil;

import butterknife.ButterKnife;

public class GlamdringApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    LogUtil.init();
    ButterKnife.setDebug(BuildConfig.DEBUG);
  }
}