package com.github.salimkayabasi.glamdring.utils;

import android.content.Context;

import timber.log.Timber;

public final class LogUtil {

  private LogUtil() {
  }

  public static void init(Context context) {
    Timber.plant(new Timber.DebugTree());
    Timber.plant(new StethoUtil.StethoTree());
    StethoUtil.init(context);
  }

}