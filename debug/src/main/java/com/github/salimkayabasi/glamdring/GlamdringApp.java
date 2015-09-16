package com.github.salimkayabasi.glamdring;

import android.app.Application;
import android.content.Context;

import com.github.salimkayabasi.glamdring.utils.LogUtil;
import com.github.salimkayabasi.glamdring.utils.PicassoUtil;
import com.github.salimkayabasi.glamdring.utils.StethoUtil;

import java.util.concurrent.atomic.AtomicReference;

import butterknife.ButterKnife;

public class GlamdringApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    LogUtil.init();
    StethoUtil.init(this);
    PicassoUtil.init(this);
    ButterKnife.setDebug(BuildConfig.DEBUG);
  }
}