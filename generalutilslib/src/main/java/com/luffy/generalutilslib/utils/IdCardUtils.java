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
        private static IdCardUtils mIdCardUtils;

        static {
            mIdCardUtils = new IdCardUtils();
        }
    }

    /**
     * 是否成年
     *
     * @param idCard
     * @return
     */
    public boolean isGrownUp(String idCard) {
        if (!ValidUtils.getInstance().isValid(idCard)) {
            return false;
        }
        if (idCard.length() < 18) {
            return false;
        }
        long idCardDate = Long.valueOf(idCard.substring(6, 14));
        long today = Long.valueOf(TimeUtils.getInstance().getTime(new Date(), new SimpleDateFormat("yyyyMMdd")));
        if (today - idCardDate >= 180000) {
            return true;
        } else {
            return false;
        }
    }
}
