package com.xu.matrixdemo;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * matrix的demo
 *
 * @author chenjunxu
 * @date 16/9/13
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 移动
     */
    private Button btn_translate;
    /**
     * 缩放
     */
    private Button btn_scale;
    /**
     * 倾斜
     */
    private Button btn_skew;
    /**
     * 旋转
     */
    private Button btn_rotate;
    /**
     * 效果图
     */
    private ImageView img_after;
    /**
     * 参照图
     */
    private ImageView img_base;
    /**
     * 参照图位图
     */
    private Bitmap base_bitmap;
    /**
     * 画笔
     */
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_skew = (Button) findViewById(R.id.btn_skew);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        img_after = (ImageView) findViewById(R.id.img_after);
        img_base = (ImageView) findViewById(R.id.img_base);

        btn_translate.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_skew.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);

        // 设置参照图
        base_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.miao);
        img_base.setImageBitmap(base_bitmap);

        // 画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_translate:   //位移
                translate(80.0f, 80.0f);
                break;
            case R.id.btn_scale:   //缩放
                scale(0.5f, 0.5f);
                break;
            case R.id.btn_skew:  //倾斜
                skew(0.5f, 0.5f);
                break;
            case R.id.btn_rotate:  //旋转
                rotate(180);
                break;
            default:
                break;
        }
    }

    /**
     * 位移
     *
     * @param dx x轴移动的位移大小
     * @param dy y轴移动的位移大小
     */
    public void translate(float dx, float dy) {
        Bitmap translate_bitmap = Bitmap.createBitmap(base_bitmap.getWidth(), base_bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(translate_bitmap);
        Matrix matrix = new Matrix();
        matrix.setTranslate(dx, dy);
        canvas.drawBitmap(base_bitmap, matrix, paint);

        img_after.setImageBitmap(translate_bitmap);
    }

    /**
     * 旋转
     *
     * @param degrees 旋转的角度
     */
    public void rotate(float degrees) {
        Bitmap rotate_bitmap = Bitmap.createBitmap(base_bitmap.getWidth(), base_bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(rotate_bitmap);
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, base_bitmap.getWidth() / 2.0f, base_bitmap.getHeight() / 2.0f);
        canvas.drawBitmap(base_bitmap, matrix, paint);

        img_after.setImageBitmap(rotate_bitmap);
    }

    /**
     * 缩放
     *
     * @param sx 缩放的比例
     * @param sy 缩放的比例
     */
    public void scale(float sx, float sy) {
        Bitmap scale_bitmap = Bitmap.createBitmap(base_bitmap.getWidth(), base_bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(scale_bitmap);
        Matrix matrix = new Matrix();
        matrix.setScale(sx, sy);
        matrix.postTranslate(scale_bitmap.getWidth() / 2.0f, scale_bitmap.getHeight() / 2.0f);
        matrix.preTranslate(-scale_bitmap.getWidth() / 2.0f, -scale_bitmap.getHeight() / 2.0f);
        canvas.drawBitmap(base_bitmap, matrix, paint);

        img_after.setImageBitmap(scale_bitmap);
    }

    /**
     * 倾斜
     *
     * @param kx x轴倾斜的角度
     * @param ky y轴倾斜的角度
     */
    public void skew(float kx, float ky) {
        Bitmap skew_bitmap = Bitmap.createBitmap(base_bitmap.getWidth(), base_bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(skew_bitmap);
        Matrix matrix = new Matrix();
        matrix.setSkew(kx, ky);
        canvas.drawBitmap(base_bitmap, matrix, paint);

        img_after.setImageBitmap(skew_bitmap);
    }
}
