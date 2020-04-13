package com.luffy.generalutilslib.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.Rect;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by lvlufei on 2019/2/18
 *
 * @desc 截屏-辅助工具
 */
public class ScreenShotUtils {
    private ScreenShotUtils() {
    }

    public static ScreenShotUtils getInstance() {
        return ScreenShotUtilsHelper.mScreenShotUtils;
    }

    private static class ScreenShotUtilsHelper {
        private static final ScreenShotUtils mScreenShotUtils = new ScreenShotUtils();
    }

    /**
     * 截取-除了导航栏之外的屏幕
     *
     * @param mContext 上下文
     * @return Bitmap
     */
    public Bitmap getScreenBitmap(Activity mContext) {
        Bitmap bitmap;
        View view = mContext.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        bitmap = Bitmap.createBitmap(view.getDrawingCache());
        return bitmap;
    }

    /**
     * 截取-某个控件或者区域的屏幕
     *
     * @param view View
     * @return Bitmap
     */
    public Bitmap getWidgetBitmap(View view) {
        Bitmap bitmap;
        view.setBackgroundColor(Color.parseColor("#ffffff"));
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        bitmap = Bitmap.createBitmap(view.getDrawingCache());
        return bitmap;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity Activity
     * @return Bitmap
     */
    public Bitmap getScreenShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = ScreenUtils.getInstance().getScreenWidthHeightDensity(activity)[0];
        int height = ScreenUtils.getInstance().getScreenWidthHeightDensity(activity)[1];
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity Activity
     * @return Bitmap
     */
    public Bitmap getScreenShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = ScreenUtils.getInstance().getScreenWidthHeightDensity(activity)[0];
        int height = ScreenUtils.getInstance().getScreenWidthHeightDensity(activity)[1];
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();

        Matrix matrix = new Matrix();
        matrix.setScale(0.3f, 0.3f);
        bp = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
        return bp;
    }

    /**
     * 截取-ScrollView的屏幕
     *
     * @param scrollView ScrollView
     * @return Bitmap
     */
    public Bitmap getScrollViewBitmap(ScrollView scrollView) {
        Bitmap bitmap;
        // 获取ScrollView实际高度
        int h = 0;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

    /**
     * 截取-ListView的屏幕
     *
     * @param listView ListView
     * @return Bitmap
     */
    public Bitmap getListViewBitmap(ListView listView) {
        Bitmap bitmap;
        int h = 0;
        // 获取ListView实际高度
        for (int i = 0; i < listView.getChildCount(); i++) {
            h += listView.getChildAt(i).getHeight();
            listView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(listView.getWidth(), h, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        listView.draw(canvas);
        return bitmap;
    }

    /**
     * 截取-WebView的屏幕
     *
     * @param webView WebView
     * @return Bitmap
     */
    public Bitmap getWebViewBitmap(WebView webView) {
        Bitmap bitmap;
        webView.setBackgroundColor(Color.parseColor("#ffffff"));
        Picture mPicture = webView.capturePicture();
        bitmap = Bitmap.createBitmap(mPicture.getWidth(), mPicture.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mPicture.draw(canvas);
        return bitmap;
    }
}
