package com.luffy.generalutils.ui.bitmapLoad;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luffy.generalutils.R;
import com.luffy.generalutils.base.BaseFragment;
import com.luffy.generalutils.base.UrlConstantManager;
import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheClient;
import com.luffy.utils.bitmaplib.bitmapLoad.display.BitmapDisplayFactory;
import com.luffy.utils.bitmaplib.bitmapLoad.display.BitmapDisplayMode;
import com.luffy.utils.bitmaplib.bitmapLoad.display.IBitmapDisplayMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2020-08-14
 *
 * @name 图片加载
 */
public class BitmapLoadFragment extends BaseFragment {

    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.image4)
    ImageView image4;
    @BindView(R.id.image5)
    ImageView image5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bitmap_load, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_cache, R.id.btn_display})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cache:
                BitmapCacheClient.getInstance(getContext()).display(image1, UrlConstantManager.getInstance().getUrlList().get(1));
                BitmapCacheClient.getInstance(getContext()).display(image2, UrlConstantManager.getInstance().getUrlList().get(2));
                BitmapCacheClient.getInstance(getContext()).display(image3, UrlConstantManager.getInstance().getUrlList().get(3));
                BitmapCacheClient.getInstance(getContext()).display(image4, UrlConstantManager.getInstance().getUrlList().get(4));
                BitmapCacheClient.getInstance(getContext()).display(image5, UrlConstantManager.getInstance().getUrlList().get(5));
                break;
            case R.id.btn_display:
                IBitmapDisplayMode iBitmapDisplayMode = BitmapDisplayFactory.build(BitmapDisplayMode.ASYNC_TASK);
                iBitmapDisplayMode.display(image1, UrlConstantManager.getInstance().getUrlList().get(1));
                iBitmapDisplayMode.display(image2, UrlConstantManager.getInstance().getUrlList().get(2));
                iBitmapDisplayMode.display(image3, UrlConstantManager.getInstance().getUrlList().get(3));
                iBitmapDisplayMode.display(image4, UrlConstantManager.getInstance().getUrlList().get(4));
                iBitmapDisplayMode.display(image5, UrlConstantManager.getInstance().getUrlList().get(5));
                break;
        }
    }
}
