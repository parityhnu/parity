package com.lufan.parityproject;

import android.os.Handler;
import android.os.Looper;

public class MainLoopHandler {
    private static volatile MainLoopHandler mInstance;
    private Handler mHandler;

    private MainLoopHandler() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static MainLoopHandler getInstance() {
        if (mInstance == null) {
            synchronized (MainLoopHandler.class) {
                if (mInstance == null) {
                    mInstance = new MainLoopHandler();
                }
            }
        }
        return mInstance;
    }

    public void post(Runnable command) {
        mHandler.post(command);
    }

    public void postDelayed(Runnable command, long delayMillis) {
        mHandler.postDelayed(command, delayMillis);
    }

}
