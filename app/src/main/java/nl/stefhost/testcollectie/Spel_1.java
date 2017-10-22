package nl.stefhost.testcollectie;

import android.app.Activity;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Spel_1 extends Activity {

    ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_DTMF,ToneGenerator.MAX_VOLUME);

    public String kleur;
    public ImageView imageView;
    public boolean loop = true;
    public int snelheid = 500;
    public int patroon_lengte;
    public int tellen = 0;

    public String[] patroon;
    public String[] patroon_1 = {"r","r","r","g"};
    public String[] patroon_2 = {"r","g","r","g","r","r"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spel_1);
        imageView = (ImageView) findViewById(R.id.imageView);

        patroon = patroon_1;
        patroon_lengte = patroon_1.length;
        handler.postDelayed(rood_groen, 0);
    }

    Handler handler = new Handler();

    Runnable rood_groen = new Runnable(){
        @Override
        public void run() {

            if (tellen != patroon_lengte) {
                kleur = patroon[tellen];
                tellen++;
            }else{
                kleur = patroon[0];
                tellen = 1;
            }

            if (kleur.equals("r")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.spel_1_rood));
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_1, 100);
            }else{
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.spel_1_groen));
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_5, 100);
            }

            if (loop){
                handler.postDelayed(rood_groen, snelheid);
            }

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        loop = false;
    }

    public void test(View view) {
        if (kleur.equals("r")){
            Toast.makeText(this, "BOEM!", Toast.LENGTH_SHORT).show();
        }else{
            patroon = patroon_2;
            patroon_lengte = patroon.length;
            snelheid = snelheid-100;
        }
    }

}
