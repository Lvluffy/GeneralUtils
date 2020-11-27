package com.luffy.utils.bitmaplib.bitmapLoad.display;

public class BitmapDisplayFactory {

    public static IBitmapDisplayMode build(BitmapDisplayMode bitmapDisplayMode) {
        IBitmapDisplayMode iBitmapDisplayMode;
        if (bitmapDisplayMode == BitmapDisplayMode.ASYNC_TASK) {
            iBitmapDisplayMode = new BitmapDisplayAsyncTask();
        } else if (bitmapDisplayMode == BitmapDisplayMode.HANDLER) {
            iBitmapDisplayMode = BitmapDisplayHandler.getInstance();
        } else if (bitmapDisplayMode == BitmapDisplayMode.EXECUTOR) {
            iBitmapDisplayMode = BitmapDisplayExecutor.getInstance();
        } else {
            iBitmapDisplayMode = BitmapDisplayHandler.getInstance();
        }
        return iBitmapDisplayMode;
    }
}
