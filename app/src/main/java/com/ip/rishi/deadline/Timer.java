package com.ip.rishi.deadline;

import android.os.CountDownTimer;
import android.widget.ViewFlipper;

import com.ip.rishi.deadline.CustomUIClasses.TimerView;

/**
 * Created by Apple on 02/03/16.
 */
public class Timer extends CountDownTimer{
    private String TAG = "Timer";
    TimerView timerView;
    ViewFlipper viewFlipper;
    long millisInFuture;
    public static boolean timerFinished = false;
    public Timer(long millisInFuture, long countDownInterval,TimerView timerView, ViewFlipper viewFlipper)
    {
        super(millisInFuture, countDownInterval);
        this.timerView = timerView;
        this.millisInFuture = millisInFuture;
        this.viewFlipper = viewFlipper;
    }

    @Override
    public void onTick(long millisUntilFinished)
    {
        float timeLeft = (float) (millisInFuture - millisUntilFinished);
        float deno = (float) millisInFuture;
        timerView.setProgress(timeLeft/deno * 100);
        timerView.setProgressValue(millisUntilFinished);

    }
    @Override
    public void onFinish()
    {
        viewFlipper.showPrevious();
    }
}
