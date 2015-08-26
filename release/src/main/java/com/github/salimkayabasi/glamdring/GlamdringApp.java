package com.github.salimkayabasi.glamdring;

import android.app.Application;
import android.content.Context;

import com.github.salimkayabasi.glamdring.utils.LogUtil;
import com.github.salimkayabasi.glamdring.utils.PicassoUtil;

import java.util.concurrent.atomic.AtomicReference;

import butterknife.ButterKnife;

public class GlamdringApp extends Application {

  private static final AtomicReference<GlamdringApp> INSTANCE = new AtomicReference<>();

  public static Context getContext() {
    return INSTANCE.get();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    INSTANCE.set(this);
    LogUtil.init();
    PicassoUtil.init(this);
    ButterKnife.setDebug(BuildConfig.DEBUG);
  }
}