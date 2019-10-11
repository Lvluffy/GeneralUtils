package com.luffy.generalutilslib.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lvlufei on 2018/11/28
 *
 * @desc 文件转换-辅助工具
 */
public class FileConversionUtils {

    private FileConversionUtils() {
    }

    public static FileConversionUtils getInstance() {
        return FileConversionUtils.FileConversionUtilsHelper.mFileConversionUtils;
    }

    private static class FileConversionUtilsHelper {
        private static FileConversionUtils mFileConversionUtils;

        static {
            mFileConversionUtils = new FileConversionUtils();
        }
    }

    /**
     * URI转File
     *
     * @param uri
     * @return
     */
    public File uri2File(URI uri) {
        File file = null;
        if (uri != null) {
            file = new File(uri);
        }
        return file;
    }

    /**
     * File转URI
     *
     * @param file
     * @return
     */
    public URI file2URI(File file) {
        URI uri = null;
        if (file != null) {
            uri = file.toURI();
        }
        return uri;
    }

    /**
     * path转File
     *
     * @param path
     * @return
     */
    public File string2File(String path) {
        File file = null;
        if (path != null) {
            file = new File(path);
        }
        return file;
    }

    /**
     * File转path:
     *
     * @param file
     * @return
     */
    public String file2String(File file) {
        String path = null;
        if (file != null) {
            path = file.getPath();
        }
        return path;
    }

    /**
     * String转byte[]
     *
     * @param filePath
     * @return
     */
    public byte[] string2Byte(String filePath) {
        byte[] buffer = null;
        if (filePath != null) {
            File file = new File(filePath);
            buffer = file2Byte(file);
        }
        return buffer;
    }

    /**
     * File转byte[]
     *
     * @param file
     * @return
     */
    public byte[] file2Byte(File file) {
        byte[] bytes = null;
        if (file != null) {
            try {
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
                byte[] b = new byte[1000];
                int n;
                while ((n = fis.read(b)) != -1) {
                    bos.write(b, 0, n);
                }
                fis.close();
                bos.close();
                bytes = bos.toByteArray();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * byte[]转File
     *
     * @param bytes
     * @param filePath
     * @param fileName
     * @return
     */
    public File byte2File(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * Bitmap转byte[]
     *
     * @param bitmap
     * @return
     */
    public byte[] bitmap2Byte(Bitmap bitmap) {
        byte[] bytes = null;
        if (bitmap != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bytes = baos.toByteArray();
        }
        return bytes;
    }

    /**
     * byte[]转Bitmap
     *
     * @param bytes
     * @return
     */
    public Bitmap byte2Bitmap(byte[] bytes) {
        Bitmap bitmap = null;
        if (bytes != null && bytes.length > 0) {
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return bitmap;
    }


    /**
     * String转Bitmap
     *
     * @param path
     * @return
     */
    public Bitmap string2Bitmap(String path) {
        Bitmap bitmap = null;
        if (path != null) {
            try {
                URL url = new URL(path);
                URLConnection conn = url.openConnection();
                conn.connect();
                InputStream in;
                in = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * File转Bitmap
     *
     * @param file
     * @return
     */
    public Bitmap file2Bitmap(File file) throws IOException {
        Bitmap bitmap = null;
        if (file != null) {
            int IMAGE_MAX_SIZE = 1000;

            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            FileInputStream fis = new FileInputStream(file);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();

            int scale = 1;
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int) Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();
        }
        return bitmap;
    }

    /**
     * File路径转Bitmap
     *
     * @param filePath
     * @return
     */
    public Bitmap filePath2Bitmap(String filePath) throws IOException {
        Bitmap bitmap = null;
        if (filePath != null) {
            File file = new File(filePath);
            if (file == null) {
                return null;
            }
            bitmap = file2Bitmap(file);
        }
        return bitmap;
    }

    /**
     * Bitmap转File
     *
     * @param bitmap
     * @param filePath
     * @param fileName
     * @return
     */
    public File bitmap2File(Bitmap bitmap, String filePath, String fileName) {
        File file = null;
        if (bitmap != null && filePath != null && fileName != null) {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            BufferedOutputStream bos = null;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.flush();
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return file;
    }

    /**
     * assets文件夹下的json文件数据转String
     *
     * @param context  上下文对象
     * @param fileName 文件名称
     * @return
     */
    public String assetsFile2String(Context context, String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader mInputStreamReader = null;
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            mInputStreamReader = new InputStreamReader(assetManager.open(fileName));
            BufferedReader bf = new BufferedReader(mInputStreamReader);
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                mInputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}