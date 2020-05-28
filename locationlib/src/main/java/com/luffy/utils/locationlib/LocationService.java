package com.luffy.utils.locationlib;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by lvlufei on 2020-05-28
 *
 * @name 定位服务
 */
public class LocationService {
    private final static Location defaultLocation = new Location(LocationManager.NETWORK_PROVIDER);

    private LocationManager mLocationManager;
    private Context mContext;

    private LocationService(Context mContext) {
        this.mContext = mContext;
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    }

    public static LocationService getInstance(Context mContext) {
        return new LocationManagerHelper(mContext).instance;
    }

    private static class LocationManagerHelper {
        public LocationService instance;

        public LocationManagerHelper(Context context) {
            instance = new LocationService(context);
        }
    }

    /**
     * 获取最后一次的位置
     *
     * @return
     */
    public Location getLastLocation() {
        String provider = getBestProvider();
        if (provider == null) {
            return defaultLocation;
        }
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return defaultLocation;
        } else {
            Location location = mLocationManager.getLastKnownLocation(provider);
            if (location != null) {
                return location;
            }
            return defaultLocation;
        }
    }

    private String getBestProvider() {
        if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;
        }
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        return mLocationManager.getBestProvider(criteria, true);
    }

    /**
     * 获取地址
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return 地址:[0]=省，[1]=市，[2]=县，[3]=详情地址
     */
    public String[] getAddress(double longitude, double latitude) {
        String[] result = new String[4];
        Geocoder geocoder = new Geocoder(mContext);
        List<Address> addressList = null;
        try {
            addressList = geocoder.getFromLocation(latitude, longitude, 1);//得到的位置可能有多个当前只取其中一个
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addressList != null && addressList.size() > 0) {
            for (int i = 0; i < addressList.size(); i++) {
                Address address = addressList.get(i);
                result[0] = address.getAdminArea();
                result[1] = address.getLocality();
                result[2] = address.getSubLocality();
                result[3] = address.getFeatureName();
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("经度：");
        stringBuilder.append(longitude);
        stringBuilder.append(",纬度：");
        stringBuilder.append(latitude);
        stringBuilder.append(",地址：");
        stringBuilder.append(result[0]);
        stringBuilder.append(result[1]);
        stringBuilder.append(result[2]);
        stringBuilder.append(result[3]);
        Log.v(LocationService.class.getSimpleName(), stringBuilder.toString());
        return result;
    }
}
