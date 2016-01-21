package com.kevintyang.androidapps.hotpotatocategories;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.w3c.dom.Text;

public class FullscreenActivity extends AppCompatActivity {
//?gfhgfh
    private View mContentView;

    private View mControlsView;

    private boolean mVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        final TextView mStartStopView = (TextView) findViewById(R.id.StartStop);
        final TextView mDifficultyView = (TextView) findViewById(R.id.Difficulty);
        final TextView mTimer = (TextView) findViewById(R.id.Timer);
        final TextView mInstructions = (TextView) findViewById(R.id.Instructions);
        final TextView mClock = (TextView) findViewById(R.id.Clock);
        final TextView mBackground = (TextView) findViewById(R.id.fullscreen_content);


        mStartStopView.setOnClickListener(new View.OnClickListener() {
            boolean started = false;
            CountDownTimer hello = new CountDownTimer(30000, 1000) {

                public void onTick(long millisUntilFinished) {
                    mClock.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    mClock.setText("0");
                    mBackground.setText("Time's Up!");
                }
            };

            public void onClick(View v) {
                if (started == true) {
                    hello.cancel();
                    mStartStopView.setText("Start!");
                    started = false;
                } else {

                    hello.start();
                    mStartStopView.setText("Stop/Reset!");
                    started = true;
                }

            }
        });

        mDifficultyView.setOnClickListener(new View.OnClickListener() {
            int selectCat = 0;
            String cat = "Easy!";

            public void onClick(View v) {


                selectCat++;
                switch (selectCat % 5) {
                    case 0:
                        cat = "Easy!";
                        break;
                    case 1:
                        cat = "Medium!";
                        break;
                    case 2:
                        cat = "Hard!";
                        break;
                    case 3:
                        cat = "Insane!";
                        break;
                    case 4:
                        cat = "Baby!";
                        break;
                }

                mDifficultyView.setText(cat);


            }
        });


        mTimer.setOnClickListener(new View.OnClickListener() {
            int selection = 0;
            int timer = 30;
            int timerSelection = 30;

            public void onClick(View v) {
                selection++;
                switch (selection % 5) {
                    case 0:
                        timer = 30;
                        break;
                    case 1:
                        timer = 45;
                        break;
                    case 2:
                        timer = 60;
                        break;
                    case 3:
                        timer = 75;
                        break;
                    case 4:
                        timer = 90;
                        break;
                }
                timerSelection = timer;
                mTimer.setText("Time! " + timer + "s");
            }
        });


        mInstructions.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mBackground.setTextSize(10);
                mBackground.setText("This game is a fun group game to play with friends or ESL students. \n This game is a hot potato style game where each person \n must say a word in the category on the screen \n before the time runs out! \n Pick your difficult, your time and push start!");
            }
        });


        mClock.setOnClickListener(new View.OnClickListener() {
            //unused
            public void onClick(View v) {
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


}
