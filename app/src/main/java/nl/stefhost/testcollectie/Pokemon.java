package nl.stefhost.testcollectie;

//import android.media.MediaPlayer;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.Activity;

public class Pokemon extends Activity {

    Handler handler = new Handler();
    int links;
    int boven;

    int deur_links = 575;
    int deur_boven = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MediaPlayer mediaPlayer = MediaPlayer.create(Pokemon.this, R.raw.pallet_town);
        mediaPlayer.start();

        setContentView(R.layout.activity_pokemon);

        ImageView image2 = (ImageView) findViewById(R.id.imageView2);
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(image2.getLayoutParams());
        lp2.setMargins(deur_links, deur_boven, 0, 0);
        image2.setLayoutParams(lp2);

        handler.postDelayed(rood, 2000);
    }

    Runnable rood = new Runnable(){
        @Override
        public void run(){
            links = links - 5;
            deur_links = deur_links - 5;

            ImageView image = (ImageView) findViewById(R.id.imageView1);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(image.getLayoutParams());
            lp.setMargins(links, 0, 0, 0);
            image.setLayoutParams(lp);

            ImageView image2 = (ImageView) findViewById(R.id.imageView2);
            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(image2.getLayoutParams());
            lp2.setMargins(deur_links, deur_boven, 0, 0);
            image2.setLayoutParams(lp2);

            if (links > -1220){
                handler.postDelayed(rood, 10);
            }
        }

    };

    public void deur(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Deur", Toast.LENGTH_SHORT);
        toast.show();
    }

}
