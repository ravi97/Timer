package com.example.ravikiran.timer;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class TimerActivity extends AppCompatActivity {


    private int seconds=0;
    private boolean running=false;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        if(savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(wasRunning)
        {
            running=true;
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        wasRunning=running;
        running=false;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(wasRunning)
        {
            running=true;
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        wasRunning=running;
        running=false;
    }

    //to start timer
    public void onClickStart(View view)
    {
        running=true;
    }

    //to stop the timer
    public void onClickStop(View view)
    {
        running=false;
    }

    //to reset timer
    public void onClickReset(View view)
    {
        running=false;
        seconds=0;
    }

    private void runTimer()
    {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
                        @Override
                    public void run()
                        {
                            int hours = seconds/3600;
                            int minutes = (seconds%3600)/60;
                            int secs = seconds%60;
                            String time=String.format("%d:%02d:%02d",hours, minutes,secs);
                            timeView.setText(time);

                            if(running)
                            {
                                seconds++;
                            }
                            handler.postDelayed(this,1000);

                        }


       });
   }


}
