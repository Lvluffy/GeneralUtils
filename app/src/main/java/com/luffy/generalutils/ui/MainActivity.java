package com.luffy.generalutils.ui;

import android.Manifest;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.luffy.generalutils.R;
import com.luffy.generalutils.base.BaseActivity;
import com.luffy.utils.bitmaplib.IconColourUtils;
import com.luffy.utils.filelib.FileConversionUtils;
import com.luffy.utils.filelib.FileStorageUtils;
import com.luffy.utils.generallib.DoubleClickExitUtils;
import com.luffy.utils.generallib.IntentUtils;
import com.luffy.utils.generallib.MoneyFormatUtils;
import com.luffy.utils.generallib.ScreenShotUtils;
import com.luffy.utils.locationlib.LocationService;
import com.luffy.utils.rxlib.PermissionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2019/10/11
 *
 * @name 主界面
 * @desc
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.item_1)
    LinearLayout item1;
    @BindView(R.id.item_2)
    LinearLayout item2;
    @BindView(R.id.item_3)
    LinearLayout item3;
    @BindView(R.id.item_4)
    LinearLayout item4;
    @BindView(R.id.imageView_4)
    ImageView imageView4;
    @BindView(R.id.item_5)
    LinearLayout item5;
    @BindView(R.id.item_6)
    LinearLayout item6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return DoubleClickExitUtils.getInstance().exit(this, null);
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.item_1,
            R.id.item_2,
            R.id.item_3,
            R.id.item_4,
            R.id.item_5,
            R.id.item_6,
            R.id.item_7})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.item_1) {
            Bitmap bitmap = ScreenShotUtils.getInstance().getScreenBitmap(this);
            FileConversionUtils.getInstance().bitmap2File(bitmap, FileStorageUtils.getInstance().getExternalCachePath(this), "屏幕截屏.png");

        } else if (i == R.id.item_2) {
            Bitmap bitmap = ScreenShotUtils.getInstance().getWidgetBitmap(item2);
            FileConversionUtils.getInstance().bitmap2File(bitmap, FileStorageUtils.getInstance().getExternalCachePath(this), "区域截屏.png");

        } else if (i == R.id.item_3) {
            Bitmap bitmap = ScreenShotUtils.getInstance().getScrollViewBitmap(scrollView);
            FileConversionUtils.getInstance().bitmap2File(bitmap, FileStorageUtils.getInstance().getExternalCachePath(this), "ScrollView截屏.png");

        } else if (i == R.id.item_4) {
            IconColourUtils.getInstance().setImageViewColor(imageView4, R.color.colorAccent);

        } else if (i == R.id.item_5) {
            Toast.makeText(this, MoneyFormatUtils.getInstance().doubleToStringRoundingNo(0.166), Toast.LENGTH_SHORT).show();

        } else if (i == R.id.item_6) {
            IntentUtils.getInstance().startActivity(this, AppActivity.class);

        } else if (i == R.id.item_7) {
            PermissionUtils.getInstance().meanWhileApplyMultiPermission(this, new PermissionUtils.MeanWhileApplyPermissionCallBack() {
                @Override
                public void onSucceed() {
                    Location location = LocationService.getInstance(MainActivity.this).getLastLocation();
                    String[] address = LocationService.getInstance(MainActivity.this).getAddress(location.getLongitude(), location.getLatitude());
                    Toast.makeText(MainActivity.this, address[0] + address[1] + address[2] + address[3], Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure() {

                }
            }, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }
}
