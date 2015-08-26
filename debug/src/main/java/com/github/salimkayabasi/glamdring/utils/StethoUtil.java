package com.github.salimkayabasi.glamdring.utils;

import android.content.Context;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.console.ConsolePeerManager;
import com.facebook.stetho.inspector.protocol.module.Console;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

import timber.log.Timber;

public final class StethoUtil {

  private static final String TIMBER_FILE = "Timber.java";
  private static boolean init = false;

  private StethoUtil() {

  }

  public static void init(Context context) {
    Stetho.initialize(
        Stetho.newInitializerBuilder(context)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
            .build());
    init = true;
  }

  public static void addNetworkInterceptor(OkHttpClient client) {
    if (init) {
      client.networkInterceptors().add(new StethoInterceptor());
    }
  }

  private static String getTag() {
    boolean timberFound = false;
    for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
      if (!timberFound && !TIMBER_FILE.equals(ste.getFileName())) {
        continue;
      }
      timberFound = true;
      if (TIMBER_FILE.equals(ste.getFileName())) {
        continue;
      }
      return ste.getClassName() + ":" + ste.getMethodName()
          + ":" + ste.getLineNumber();
    }
    return "";
  }

  public static class StethoTree extends Timber.DebugTree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {

      ConsolePeerManager peerManager = ConsolePeerManager.getInstanceOrNull();
      if (peerManager == null) {
        return;
      }

      Console.MessageLevel logLevel;

      switch (priority) {
        case Log.VERBOSE:
        case Log.DEBUG:
          logLevel = Console.MessageLevel.DEBUG;
          break;
        case Log.INFO:
          logLevel = Console.MessageLevel.LOG;
          break;
        case Log.WARN:
          logLevel = Console.MessageLevel.WARNING;
          break;
        case Log.ERROR:
          logLevel = Console.MessageLevel.ERROR;
          break;
        default:
          logLevel = Console.MessageLevel.LOG;
      }

      CLog.writeToConsole(
          logLevel,
          Console.MessageSource.OTHER,
          getTag() + "\n" + message
      );
    }
  }
}