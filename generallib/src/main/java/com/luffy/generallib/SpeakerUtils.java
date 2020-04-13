package com.luffy.generallib;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 扬声器-辅助工具
 */
public class SpeakerUtils {

    private int currVolume = 0;

    private SpeakerUtils() {
    }

    public static SpeakerUtils getInstance() {
        return SpeakerUtilsHelper.mSpeakerUtils;
    }

    private static class SpeakerUtilsHelper {
        private static final SpeakerUtils mSpeakerUtils = new SpeakerUtils();
    }

    /**
     * 打开扬声器
     *
     * @param context 上下文
     */
    public void openSpeaker(Context context) {
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            currVolume = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
            if (!audioManager.isSpeakerphoneOn()) {
                audioManager.setSpeakerphoneOn(true);
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                        audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL),
                        AudioManager.STREAM_VOICE_CALL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭扬声器
     *
     * @param context 上下文
     */
    public void closeSpeaker(Context context) {
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) {
                if (audioManager.isSpeakerphoneOn()) {
                    audioManager.setSpeakerphoneOn(false);
                    audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                            currVolume,
                            AudioManager.STREAM_VOICE_CALL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}