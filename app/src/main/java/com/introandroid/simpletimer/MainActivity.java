package com.introandroid.simpletimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;

  @Override
    protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

        startB = (Button) this.findViewById(R.id.buttonABC);
        startB.setOnClickListener(this);
        text = this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));
    }

    @Override
    public void onClick(View v) {
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            final String text = getString(R.string.time);
            startB.setText(text);
        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
            String reset = getString(R.string.reset);
            startB.setText(reset);
        }

    }
    class MyCountDownTimer extends CountDownTimer
    {
        MyCountDownTimer (long args1, long args2)
        {
            super (args1, args2);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            text.setText(getString(R.string.timeRemain) + millisUntilFinished);
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText(getString(R.string.timeElapsed) + String.valueOf(timeElapsed));

        }

        @Override
        public void onFinish() {

            text.setText(getString(R.string.timeUp));
            timeElapsedView.setText(getString(R.string.timeElapsed) + String.valueOf(startTime));

        }
    }


}
