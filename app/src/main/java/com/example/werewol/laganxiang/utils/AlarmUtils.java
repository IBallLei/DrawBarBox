package com.example.werewol.laganxiang.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import com.example.werewol.laganxiang.application.LaGanXiangApplication;

/**
 * Created by zhanglei
 * on 2017/10/27.
 */
public class AlarmUtils {

    private static Vibrator vibrator;
    private static MediaPlayer mMediaPlayer;

    public static void alarm() {
        vibrator = (Vibrator) LaGanXiangApplication.getInstance()
                .getSystemService(Context.VIBRATOR_SERVICE);
        startVibrate();
        playRing(LaGanXiangApplication.getInstance());
    }

    public static void stopAlarm() {
        stopVibrate();
        stopRing();
    }

    private static void startVibrate() {
        long[] pattern = {100, 400, 100, 400};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, 2);
    }

    private static void stopVibrate() {
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    //开始播放
    private static void playRing(final Context context) {
        try {
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);//用于获取手机默认铃声的Uri
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(context, alert);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);//告诉mediaPlayer播放的是铃声流
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //停止播放
    private static void stopRing() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
        }
    }
}
