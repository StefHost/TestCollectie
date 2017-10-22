package nl.stefhost.testcollectie;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;

public class Spel_2 extends Activity {

    public MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spel_2);

        mPlayer = MediaPlayer.create(Spel_2.this, R.raw.pallet_town);
        mPlayer.start();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void onDestroy() {
        mPlayer.stop();
        super.onDestroy();
    }

}
