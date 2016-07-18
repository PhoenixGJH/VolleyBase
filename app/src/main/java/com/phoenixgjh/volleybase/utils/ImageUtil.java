package com.phoenixgjh.volleybase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.TextUtils;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.phoenixgjh.volleybase.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.Hashtable;

/**
 * Created by Phoenix on 2016/7/18.
 */
public class ImageUtil {
    /**
     * 生成二维码
     *
     * @param string          二维码中包含的信息
     * @param mBitmap         logo图
     * @param format          编码格式
     * @param IMAGE_HALFWIDTH
     * @param picW
     * @return Bitmap 位图
     * @throws WriterException
     */
    public static Bitmap createCode(String string, Bitmap mBitmap, BarcodeFormat format, int IMAGE_HALFWIDTH, int picW) throws WriterException {
        Matrix m = new Matrix();
        float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
        float sy = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getHeight();
        m.setScale(sx, sy);//设置缩放信息
        //将logo图片按martix设置的信息缩放
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), m, false);
        MultiFormatWriter writer = new MultiFormatWriter();
        Hashtable hst = new Hashtable();
        hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");//设置字符编码
        BitMatrix matrix = writer.encode(string, format, picW, picW, hst);//生成二维码矩阵信息
        int width = matrix.getWidth();//矩阵高度
        int height = matrix.getHeight();//矩阵宽度
        int halfW = width / 2;
        int halfH = height / 2;
        int[] pixels = new int[width * height];//定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息
        for (int y = 0; y < height; y++) {//从行开始迭代矩阵
            for (int x = 0; x < width; x++) {//迭代列
                if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
                        && y > halfH - IMAGE_HALFWIDTH
                        && y < halfH + IMAGE_HALFWIDTH) {//该位置用于存放图片信息
                    //记录图片每个像素信息
                    pixels[y * width + x] = mBitmap.getPixel(x - halfW
                            + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);
                } else {
                    if (matrix.get(x, y)) {//如果有黑块点，记录信息
                        pixels[y * width + x] = 0xff000000;//记录黑块信息
                    }
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 加载圆形占位图
     *
     * @param context
     * @param w
     * @param h
     * @param imageView
     */
    public static void setRoundPlaceImg(Context context, int w, int h, ImageView imageView) {
        Picasso.with(context).load(R.mipmap.ic_launcher).resize(w, h).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int size = Math.min(source.getWidth(), source.getHeight());
                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;
                Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                if (squaredBitmap != source) {
                    source.recycle();
                }
                Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                BitmapShader shader = new BitmapShader(squaredBitmap,
                        BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                paint.setShader(shader);
                paint.setAntiAlias(true);
                float r = size / 2f;
                canvas.drawCircle(r, r, r, paint);
                squaredBitmap.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "circle";
            }
        }).centerCrop().into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param uri
     * @param w
     * @param h
     * @param imageView
     */
    public static void setRoundImg(Context context, String uri, int w, int h, ImageView imageView) {
        if (!TextUtils.isEmpty(uri)) {
            Picasso.with(context).load(uri).placeholder(R.mipmap.ic_launcher).resize(w, h).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    int size = Math.min(source.getWidth(), source.getHeight());
                    int x = (source.getWidth() - size) / 2;
                    int y = (source.getHeight() - size) / 2;
                    Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                    if (squaredBitmap != source) {
                        source.recycle();
                    }
                    Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();
                    BitmapShader shader = new BitmapShader(squaredBitmap,
                            BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                    paint.setShader(shader);
                    paint.setAntiAlias(true);
                    float r = size / 2f;
                    canvas.drawCircle(r, r, r, paint);
                    squaredBitmap.recycle();
                    return bitmap;
                }

                @Override
                public String key() {
                    return "circle";
                }
            }).centerCrop().into(imageView);
        } else {
            Picasso.with(context).load(R.mipmap.ic_launcher).resize(w, h).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    int size = Math.min(source.getWidth(), source.getHeight());
                    int x = (source.getWidth() - size) / 2;
                    int y = (source.getHeight() - size) / 2;
                    Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                    if (squaredBitmap != source) {
                        source.recycle();
                    }
                    Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();
                    BitmapShader shader = new BitmapShader(squaredBitmap,
                            BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                    paint.setShader(shader);
                    paint.setAntiAlias(true);
                    float r = size / 2f;
                    canvas.drawCircle(r, r, r, paint);
                    squaredBitmap.recycle();
                    return bitmap;
                }

                @Override
                public String key() {
                    return "circle";
                }
            }).centerCrop().into(imageView);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param uri
     * @param w
     * @param h
     * @param imageView
     */
    public static void setRoundImg(Context context, File uri, int w, int h, ImageView imageView) {
        if (uri != null)
            Picasso.with(context).load(uri).placeholder(R.mipmap.ic_launcher).resize(w, h).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    int size = Math.min(source.getWidth(), source.getHeight());
                    int x = (source.getWidth() - size) / 2;
                    int y = (source.getHeight() - size) / 2;
                    Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                    if (squaredBitmap != source) {
                        source.recycle();
                    }
                    Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();
                    BitmapShader shader = new BitmapShader(squaredBitmap,
                            BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                    paint.setShader(shader);
                    paint.setAntiAlias(true);
                    float r = size / 2f;
                    canvas.drawCircle(r, r, r, paint);
                    squaredBitmap.recycle();
                    return bitmap;
                }

                @Override
                public String key() {
                    return "circle";
                }
            }).centerCrop().into(imageView);
        else
            Picasso.with(context).load(R.mipmap.ic_launcher).resize(w, h).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    int size = Math.min(source.getWidth(), source.getHeight());
                    int x = (source.getWidth() - size) / 2;
                    int y = (source.getHeight() - size) / 2;
                    Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                    if (squaredBitmap != source) {
                        source.recycle();
                    }
                    Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();
                    BitmapShader shader = new BitmapShader(squaredBitmap,
                            BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                    paint.setShader(shader);
                    paint.setAntiAlias(true);
                    float r = size / 2f;
                    canvas.drawCircle(r, r, r, paint);
                    squaredBitmap.recycle();
                    return bitmap;
                }

                @Override
                public String key() {
                    return "circle";
                }
            }).centerCrop().into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param uri
     * @param w
     * @param h
     * @param imageView
     */
    public static void setRoundImg(final Context context, int uri, int w, int h, ImageView imageView) {
        if (uri != 0)
            Picasso.with(context).load(uri).placeholder(R.mipmap.ic_launcher).resize(w, h).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    int size = Math.min(source.getWidth(), source.getHeight());
                    int x = (source.getWidth() - size) / 2;
                    int y = (source.getHeight() - size) / 2;
                    Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                    if (squaredBitmap != source) {
                        source.recycle();
                    }
                    Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();
                    BitmapShader shader = new BitmapShader(squaredBitmap,
                            BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                    paint.setShader(shader);
                    paint.setAntiAlias(true);
                    float r = size / 2f;
                    canvas.drawCircle(r, r, r, paint);
                    squaredBitmap.recycle();
                    return bitmap;
                }

                @Override
                public String key() {
                    return "circle";
                }
            }).centerCrop().into(imageView);
        else
            Picasso.with(context).load(R.mipmap.ic_launcher).resize(w, h).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    int size = Math.min(source.getWidth(), source.getHeight());
                    int x = (source.getWidth() - size) / 2;
                    int y = (source.getHeight() - size) / 2;
                    Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                    if (squaredBitmap != source) {
                        source.recycle();
                    }
                    Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();
                    BitmapShader shader = new BitmapShader(squaredBitmap,
                            BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                    paint.setShader(shader);
                    paint.setAntiAlias(true);
                    float r = size / 2f;
                    canvas.drawCircle(r, r, r, paint);
                    squaredBitmap.recycle();
                    return bitmap;
                }

                @Override
                public String key() {
                    return "circle";
                }
            }).centerCrop().into(imageView);
    }

    /**
     * 获取圆形Bitmap
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getCroppedBitmap(Bitmap bitmap, int width) {
        Bitmap output_temp = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        int w_temp = output_temp.getWidth();
        int h_temp = output_temp.getHeight();
        //设置想要的大小
        float scaleW = ((float) width) / w_temp;
        float scaleH = ((float) width) / h_temp;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleW, scaleH);
        Bitmap output = Bitmap.createBitmap(output_temp, 0, 0, w_temp, h_temp, matrix, true);

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }

}
