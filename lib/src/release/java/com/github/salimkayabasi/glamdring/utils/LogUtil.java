package com.github.salimkayabasi.glamdring.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

public final class LogUtil {

  private LogUtil() {
  }

  public static void init() {
    Timber.plant(new CrashReportingTree());
  }

  private static class CrashReportingTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
      if (t != null && priority >= Log.WARN) {
        Crashlytics.logException(t);
      }
    }
  }
}