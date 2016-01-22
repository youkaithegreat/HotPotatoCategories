package com.kevintyang.androidapps.hotpotatocategories;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



public class FullscreenActivity extends AppCompatActivity {

    private View mControlsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mControlsView = findViewById(R.id.fullscreen_content_controls);

        final TextView mStartStopView = (TextView) findViewById(R.id.StartStop);
        final TextView mDifficultyView = (TextView) findViewById(R.id.Difficulty);
        final TextView mTimer = (TextView) findViewById(R.id.Timer);
        final TextView mInstructions = (TextView) findViewById(R.id.Instructions);
        final TextView mClock = (TextView) findViewById(R.id.Clock);
        final TextView mBackground = (TextView) findViewById(R.id.fullscreen_content);

        final Operation game = new Operation();

        mStartStopView.setOnClickListener(new View.OnClickListener() {
            boolean started = false;

            CountDownTimer hello;

            public void onClick(View v) {
                buttonVibrate();
                if (started == true) {
                    hello.cancel();
                    game.resetTimer();
                    mClock.setText("" + game.getTimer() / 1000);
                    mStartStopView.setText("Start!");
                    started = false;
                } else {

                    hello = new CountDownTimer(game.getTimer(), 1000) {

                        public void onTick(long millisUntilFinished) {
                            mClock.setText("" + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            mClock.setText("0");
                            mBackground.setText("Time's Up!");
                            game.resetTimer();
                        }
                    };
                    hello.start();

                    mStartStopView.setText("Stop/Reset!");
                    started = true;
                }

            }
        });

        mDifficultyView.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                buttonVibrate();
                mDifficultyView.setText(game.setCategory());


            }
        });


        mTimer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                buttonVibrate();
                game.pickTimer();
                mTimer.setText("Time! " + game.getTimer() + "s");
            }
        });


        mInstructions.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                buttonVibrate();
                mBackground.setTextSize(10);
                mBackground.setText(game.instructions());
            }
        });


        mClock.setOnClickListener(new View.OnClickListener() {
            //unused
            public void onClick(View v) {
                buttonVibrate();
                //animate clock eventually
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mControlsView.setVisibility(View.GONE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    public void buttonVibrate(){
        final Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(150);
    }


}
