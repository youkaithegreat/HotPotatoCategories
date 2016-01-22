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
//general imports as needed


public class FullscreenActivity extends AppCompatActivity {

    private View mControlsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //main method

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        //AdView Things
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Finding View from XML
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        final TextView mStartStopView = (TextView) findViewById(R.id.StartStop);
        final TextView mDifficultyView = (TextView) findViewById(R.id.Difficulty);
        final TextView mTimer = (TextView) findViewById(R.id.Timer);
        final TextView mInstructions = (TextView) findViewById(R.id.Instructions);
        final TextView mClock = (TextView) findViewById(R.id.Clock);
        final TextView mBackground = (TextView) findViewById(R.id.fullscreen_content);

        //instantiate object of game
        final Operation game = new Operation();
        final Categories cat = new Categories();

        //Start Button Methods
        mStartStopView.setOnClickListener(new View.OnClickListener() {

            CountDownTimer ticker;

            boolean started = false;

            public void onClick(View v) {
                //on click vibrates, starts timer, updates clock
                //ticker updates with the CountDownTimer
                //updates textviews
                //TextView Updates are kept here, because UI updates need to be run on main thread

                buttonVibrate();

                if (started == true) {
                    ticker.cancel();
                    game.resetTimer();
                    mClock.setText("" + game.getTimer() / 1000);
                    mStartStopView.setText("Start!");
                    mBackground.setText("Hot Potato Categories!");
                    started = false;
                } else {

                    ticker = new CountDownTimer(game.getTimer(), 1000) {

                        public void onTick(long millisUntilFinished) {
                            mClock.setText("" + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            mClock.setText("0");
                            mBackground.setText("Time's Up!");
                            game.resetTimer();
                        }
                    };
                    ticker.start();

                    mBackground.setText(cat.getCatString(game.getCategory()));
                    mStartStopView.setText("Stop/Reset!");
                    started = true;
                }
            }
        });

        //Clicker for Difficulty. Methods run on game object
        mDifficultyView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                buttonVibrate();
                mDifficultyView.setText(game.setCategory());

            }
        });

        //Time selection button
        mTimer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                buttonVibrate();
                game.pickTimer();
                mTimer.setText("Time! " + game.getTimer()/1000 + "s");
            }
        });

        //puts instructions on the screen. toggle will be added in future versions
        mInstructions.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                buttonVibrate();
                mBackground.setTextSize(10);
                mBackground.setText(game.instructions());
            }
        });

        //clock is updated, not really a button. This should be changed later on.
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
        //hides the top bar
        super.onPostCreate(savedInstanceState);
        mControlsView.setVisibility(View.GONE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    public void buttonVibrate(){
        //vibrate called by onclicklisteners
        final Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(150);
        //set at 150 for just enough haptic feedback, eventually will need to add disable sound/vibrate
    }

}
