package com.adamin90.adamlee.disklrucacheexample.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
/**
 * LruCache缓存数据工具类
 */

public class Util {
	/**
	 * 缓存json数据
	 */
	private LruCache<Integer, String> mJsonCache;
	/**
	 * 缓存图片信息
	 */
	private LruCache<Integer, Bitmap> mBitmapCache;

	public Util() {
		mJsonCache = new LruCache<Integer, String>(1 * 1024 * 1024);
		mBitmapCache = new LruCache<Integer, Bitmap>(2 * 1024 * 1024);
	}

	/**
	 * 添加进入缓存列表
	 * 
	 * @param key
	 * @param value
	 */
	public void addJsonLruCache(Integer key, String value) {
		mJsonCache.put(key, value);
	}

	public void addBitmapLruCache(Integer key, Bitmap value) {
		mBitmapCache.put(key, value);
	}

	/**
	 * 从缓存列表中拿出来
	 * 
	 * @param key
	 * @return
	 */
	public String getJsonLruCache(Integer key) {
		return mJsonCache.get(key);
	}

	public Bitmap getBitmapLruCache(Integer key) {
		return mBitmapCache.get(key);
	}

}

