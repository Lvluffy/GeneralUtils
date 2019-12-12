package com.luffy.generalutilslib.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 身份证-辅助工具
 */
public class IdCardUtils {

    private IdCardUtils() {
    }

    public static IdCardUtils getInstance() {
        return IdCardUtilsHelper.mIdCardUtils;
    }

    private static class IdCardUtilsHelper {
        private static final IdCardUtils mIdCardUtils;

        static {
            mIdCardUtils = new IdCardUtils();
        }
    }

    /**
     * 是否成年
     *
     * @param idCard 身份证号
     * @return 是否成年
     */
    public boolean isGrownUp(String idCard) {
        if (!ValidUtils.getInstance().isValid(idCard)) {
            return false;
        }
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (idCard.length() == 18) {
            return (Double.parseDouble(today) - (Double.parseDouble(idCard.substring(6, 14)))) * 0.0001 >= 18;
        } else if (idCard.length() == 15) {
            return (Double.parseDouble(today) - (Double.parseDouble("19" + idCard.substring(6, 12)))) * 0.0001 >= 18;
        }
        return false;
    }

}
