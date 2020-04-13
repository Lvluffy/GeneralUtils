package com.luffy.generallib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 头像裁剪-辅助工具
 */
public class PhotoProcessor {
    private PhotoProcessor() {
    }

    public static PhotoProcessor getInstance() {
        return PhotoProcessor.PhotoProcessorHelper.mPhotoProcessor;
    }

    private static class PhotoProcessorHelper {
        private static final PhotoProcessor mPhotoProcessor = new PhotoProcessor();
    }

    /**
     * 获取裁剪过的图片文件
     *
     * @return 文件
     */
    public File getTempImage(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File tempFile = new File(context.getFilesDir().getAbsolutePath(), "temp.jpg");
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempFile;
        }
        return null;
    }

    /**
     * 获取拍照后的图片文件
     *
     * @return 文件
     */
    public File getTempImageFromPhoto() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File tempFile = new File(Environment.getExternalStorageDirectory(), "tempPho.jpg");
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempFile;
        }
        return null;
    }

    /**
     * 将裁剪过得图压缩,转移后,返回处理过的图
     *
     * @param filePath 文件路径
     * @param context  上下文
     * @return 文件
     */
    public File getSmallImageFile(String filePath, Context context) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(filePath, options);
        if (bm == null) {
            return null;
        }
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 30, baos);//压缩
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String fileName = "upload_tmp.jpg";
        File file = context.getFileStreamPath(fileName);
        if (file.exists()) {
            file.delete();
        }
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        int options2 = 100;//不压缩
        bm.compress(Bitmap.CompressFormat.JPEG, options2, baos2);
        while (baos2.toByteArray().length / 1024 > 100) {
            baos2.reset();
            options2 -= 10;
            bm.compress(Bitmap.CompressFormat.JPEG, options2, baos2);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos2.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
        }
        return inSampleSize;
    }
}
