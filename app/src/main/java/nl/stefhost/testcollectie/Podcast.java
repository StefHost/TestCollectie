package nl.stefhost.testcollectie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Podcast extends AppCompatActivity {

    TextView textViewArtiest;
    TextView textViewTitel;
    ImageView imageViewAlbum;
    ImageView imageViewPlay_pauze;
    ImageView imageViewStop;

    boolean spelen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);

        textViewArtiest = (TextView) findViewById(R.id.artiest);
        textViewTitel = (TextView) findViewById(R.id.titel);
        imageViewAlbum = (ImageView) findViewById(R.id.album);
        imageViewPlay_pauze = (ImageView) findViewById(R.id.play_pauze);
        imageViewStop = (ImageView) findViewById(R.id.stop);

    }

    public void afspelen (View view){
        String tag = view.getTag().toString();
        switch (tag){
            case "1":
                keuze("Ed Sheeran", "All Of The Stars");
                break;
            case "2":
                keuze("Milow", "Born In The Eighties");
                break;
            case "3":
                keuze("Michael Bublé", "Haven't Met You Yet");
                break;
            case "4":
                keuze("Marco Borsato", "Kleine Oneindigheid");
                break;
            case "5":
                keuze("James Blunt", "Goodbye My Lover");
                break;
        }
    }

    public void keuze(String artiest, String titel){

        textViewArtiest.setText(artiest);
        textViewTitel.setText(titel);
        imageViewPlay_pauze.setBackgroundResource(R.drawable.pauze);
        imageViewStop.setBackgroundResource(R.drawable.stop);

        switch (artiest){
            case "Ed Sheeran":
                imageViewAlbum.setBackgroundResource(R.mipmap.album_1);
                break;
            case "Milow":
                imageViewAlbum.setBackgroundResource(R.mipmap.album_2);
                break;
            case "Michael Bublé":
                imageViewAlbum.setBackgroundResource(R.mipmap.album_3);
                break;
            case "Marco Borsato":
                imageViewAlbum.setBackgroundResource(R.mipmap.album_4);
                break;
            case "James Blunt":
                imageViewAlbum.setBackgroundResource(R.mipmap.album_5);
                break;

        }

        artiest = artiest.replace(" ", "%20");
        titel = titel.replace(" ", "%20");
        String volledige_link = "http://muziek.radiostereo.nl/"+artiest+"%20-%20"+titel+".mp3";

        spelen = true;
        new Podcast_muziekspeler().execute(volledige_link);

    }

    public void start_pauze(View view){
        if (spelen){
            Podcast_muziekspeler.pauze();
            imageViewPlay_pauze.setBackgroundResource(R.drawable.play);
            spelen = false;
        }else{
            Podcast_muziekspeler.start();
            imageViewPlay_pauze.setBackgroundResource(R.drawable.pauze);
            spelen = true;
        }
    }

    public void stop(View view) {
        if (spelen){
            Podcast_muziekspeler.stop();
            imageViewPlay_pauze.setBackgroundResource(R.drawable.play);
            spelen = false;
        }
    }

}
