package com.luffy.utils.bitmaplib.bitmapLoad.download;

import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheFile;
import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheMemory;
import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheNet;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 网络缓存-工厂
 */
public class BitmapDownloadFactory {

    private BitmapDownloadHandler bitmapDownloadHandler;
    private BitmapDownloadAsyncTask bitmapDownloadAsyncTask;
    private BitmapDownloadExecutor bitmapDownloadExecutor;
    private BitmapDownloadMode bitmapDownloadMode = BitmapDownloadMode.EXECUTOR;

    private BitmapDownloadFactory() {
        bitmapDownloadHandler = new BitmapDownloadHandler();
        bitmapDownloadAsyncTask = new BitmapDownloadAsyncTask();
        bitmapDownloadExecutor = new BitmapDownloadExecutor();
    }

    public static BitmapDownloadFactory getInstance() {
        return BitmapDownloadFactoryHolder.instance;
    }

    private static class BitmapDownloadFactoryHolder {
        private static final BitmapDownloadFactory instance = new BitmapDownloadFactory();
    }

    public BitmapDownloadFactory setBitmapCacheMemory(BitmapCacheMemory bitmapCacheMemory) {
        bitmapDownloadHandler.setBitmapCacheMemory(bitmapCacheMemory);
        bitmapDownloadAsyncTask.setBitmapCacheMemory(bitmapCacheMemory);
        bitmapDownloadExecutor.setBitmapCacheMemory(bitmapCacheMemory);
        return this;
    }

    public BitmapDownloadFactory setBitmapCacheFile(BitmapCacheFile bitmapCacheFile) {
        bitmapDownloadHandler.setBitmapCacheFile(bitmapCacheFile);
        bitmapDownloadAsyncTask.setBitmapCacheFile(bitmapCacheFile);
        bitmapDownloadExecutor.setBitmapCacheFile(bitmapCacheFile);
        return this;
    }

    public BitmapDownloadFactory setBitmapDownloadMode(BitmapDownloadMode bitmapDownloadMode) {
        this.bitmapDownloadMode = bitmapDownloadMode;
        return this;
    }

    public BitmapCacheNet build() {
        if (bitmapDownloadMode == BitmapDownloadMode.HANDLER) {
            return bitmapDownloadHandler;
        }
        if (bitmapDownloadMode == BitmapDownloadMode.ASYNC_TASK) {
            return bitmapDownloadAsyncTask;
        }
        if (bitmapDownloadMode == BitmapDownloadMode.EXECUTOR) {
            return bitmapDownloadExecutor;
        }
        return bitmapDownloadExecutor;
    }
}
