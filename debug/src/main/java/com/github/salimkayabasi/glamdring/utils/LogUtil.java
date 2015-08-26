package com.github.salimkayabasi.glamdring.utils;

import timber.log.Timber;

public final class LogUtil {

  private LogUtil() {
  }

  public static void init() {
    Timber.plant(new Timber.DebugTree());
    Timber.plant(new StethoUtil.StethoTree());
  }

}