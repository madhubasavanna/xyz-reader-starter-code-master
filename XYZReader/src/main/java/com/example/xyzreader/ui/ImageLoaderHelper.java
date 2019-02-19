package com.example.xyzreader.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class ImageLoaderHelper {
    private static ImageLoaderHelper sInstance;
    private int dispWidth = 3;
    private int dispHeight = 6;

    public static ImageLoaderHelper getInstance(Context context, int width, int height) {
        if (sInstance == null) {
            sInstance = new ImageLoaderHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public static ImageLoaderHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ImageLoaderHelper(context.getApplicationContext());
        }

        return sInstance;
    }

    private final LruCache<String, Bitmap> mImageCache = new LruCache<String, Bitmap>(20);
    private ImageLoader mImageLoader;

    private ImageLoaderHelper(Context applicationContext) {
        RequestQueue queue = Volley.newRequestQueue(applicationContext);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String key, Bitmap value) {
                mImageCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
//                Bitmap bitmap = mImageCache.get(key);
//
//                int imageWidth = bitmap.getWidth();
//                int imageHeight = bitmap.getHeight();
//
//                int newWidth = dispWidth; //this method should return the width of device screen.
//                float scaleFactor = (float)newWidth/(float)imageWidth;
//                int newHeight = (int)(imageHeight * scaleFactor);
//
//                bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
                return mImageCache.get(key);
            }
        };
        mImageLoader = new ImageLoader(queue, imageCache);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
