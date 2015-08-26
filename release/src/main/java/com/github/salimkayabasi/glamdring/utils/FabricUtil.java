package com.github.salimkayabasi.glamdring.utils;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public final class FabricUtil {

  private static final String GIT_SHA = "GIT_SHA";
  private static final String BUILD_TIME = "BUILD_TIME";

  private FabricUtil() {
  }

  public static void init(Application app, String buildTime, String gitSha) {
    Fabric.with(app, new Crashlytics());
    Crashlytics.getInstance().core.setString(BUILD_TIME, buildTime);
    Crashlytics.getInstance().core.setString(GIT_SHA, gitSha);
  }
}