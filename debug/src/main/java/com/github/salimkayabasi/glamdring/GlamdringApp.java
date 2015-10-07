package com.github.salimkayabasi.glamdring;

import android.app.Application;

import com.github.salimkayabasi.glamdring.utils.LogUtil;
import com.github.salimkayabasi.glamdring.utils.StethoUtil;

import butterknife.ButterKnife;

public class GlamdringApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    LogUtil.init();
    StethoUtil.init(this);
    ButterKnife.setDebug(BuildConfig.DEBUG);
  }
}