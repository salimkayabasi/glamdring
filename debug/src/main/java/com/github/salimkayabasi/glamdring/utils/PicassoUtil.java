package com.github.salimkayabasi.glamdring.utils;

import android.content.Context;
import android.net.Uri;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import timber.log.Timber;

public final class PicassoUtil {

  private static final String PICASSO_CACHE = "picasso-cache";
  private static final long PICASSO_CACHE_SIZE = 100 * 1024 * 1024;

  private PicassoUtil() {
  }

  public static void init(Context context) {
    OkHttpClient client = new OkHttpClient();
    StethoUtil.addNetworkInterceptor(client);
    File cache = new File(context.getCacheDir(), PICASSO_CACHE);
    if (!cache.exists()) {
      //noinspection ResultOfMethodCallIgnored
      cache.mkdirs();
    }
    try {
      client.setCache(new Cache(cache, PICASSO_CACHE_SIZE));
    } catch (Exception e) {
      Timber.e("Cache creation error", e);
    }
    Picasso picasso = new Picasso.Builder(context)
        .downloader(new OkHttpDownloader(client))
        .listener(new Picasso.Listener() {
          @Override
          public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
            Timber.i(e, uri.toString());
          }
        })
        .build();

    Picasso.setSingletonInstance(picasso);
  }
}