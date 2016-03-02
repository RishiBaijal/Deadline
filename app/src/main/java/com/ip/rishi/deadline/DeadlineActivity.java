package com.ip.rishi.deadline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.ViewFlipper;

import com.ip.rishi.deadline.CustomUIClasses.TimerView;

public class DeadlineActivity extends AppCompatActivity {

    TimerView timerView;
    TimePicker timePicker;
    ViewFlipper viewFlipper;
    Timer timer;
    Button startButton, stopButton;
    public static final String TAG = "DeadlineActivity";
    public static final int TICK = 60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline);
        viewFlipper=(ViewFlipper) findViewById(R.id.viewFlipper);

        timerView= (TimerView) findViewById(R.id.timerView);
        timerView.setProgress(50);

        timePicker= (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(0);
        timePicker.setCurrentMinute(0);
        startButton = (Button) findViewById(R.id.startBtn);
        stopButton = (Button) findViewById(R.id.stopBtn);
        //stopButton.setVisibility(inflaterView.INVISIBLE);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long setMillis = (((timePicker.getCurrentHour() * 60 + timePicker.getCurrentMinute()) * 60) * 1000);
                long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
                if (setMillis != 0) {

//                    startButton.setVisibility(inflaterView.INVISIBLE);
//                    stopButton.setVisibility(inflaterView.VISIBLE);
                    Log.v(TAG, "Time: " + setMillis);
                    viewFlipper.showNext();
                    timer = new Timer(setMillis, TICK, timerView, viewFlipper);
                    timer.start();

                }
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                timer.cancel();
                viewFlipper.showNext();
                timePicker.setCurrentHour(0);
                timePicker.setCurrentMinute(0);
            }

        });
//
//        setButtons();

//        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflaterView=inflater.inflate(R.layout.activity_deadline, container, false);

//        MainTabActivity.zenVisited++;
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("zenModeVisited", Context.MODE_PRIVATE);
//        SharedPreferences.Editor startEditor = sharedPreferences.edit();
//        startEditor.putLong("visitZen", MainTabActivity.zenVisited);
//        System.out.println("The number of times zen has been visited is "+MainTabActivity.zenVisited);

        viewFlipper=(ViewFlipper)inflaterView.findViewById(R.id.viewFlipper);

        timerView= (TimerView) inflaterView.findViewById(R.id.timerView);
        timerView.setProgress(50);

        timePicker= (TimePicker) inflaterView.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(0);
        timePicker.setCurrentMinute(0);

        setButtons(inflaterView);

        return inflaterView;
    }
    private void setButtons(final View inflaterView)
    {
        startButton = (Button) inflaterView.findViewById(R.id.startBtn);
        stopButton = (Button) inflaterView.findViewById(R.id.stopBtn);
        stopButton.setVisibility(inflaterView.INVISIBLE);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                long setMillis=(((timePicker.getCurrentHour()*60+timePicker.getCurrentMinute())*60)*1000);
                long[] pattern = {500,500,500,500,500,500,500,500,500};
                if (setMillis != 0)
                {

                    startButton.setVisibility(inflaterView.INVISIBLE);
                    stopButton.setVisibility(inflaterView.VISIBLE);
                    Log.v(TAG, "Time: " + setMillis);
                    viewFlipper.showNext();
                    timer = new Timer(setMillis, TICK, timerView, viewFlipper);
                    timer.start();

                }
            }
        });
    }
}
