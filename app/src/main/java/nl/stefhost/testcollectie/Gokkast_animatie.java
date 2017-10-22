package nl.stefhost.testcollectie;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Gokkast_animatie extends Activity {

    AnimationDrawable animatie1;
    AnimationDrawable animatie2;
    AnimationDrawable animatie3;

    String tekst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gokkast_animatie);

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);

        imageView1.setBackgroundResource(R.drawable.animatie1);
        imageView2.setBackgroundResource(R.drawable.animatie2);
        imageView3.setBackgroundResource(R.drawable.animatie3);

        animatie1 = (AnimationDrawable) imageView1.getBackground();
        animatie2 = (AnimationDrawable) imageView2.getBackground();
        animatie3 = (AnimationDrawable) imageView3.getBackground();

        animatie1.start();
        animatie2.start();
        animatie3.start();
    }

    public void stop1 (View view){
        animatie1.stop();

        int framenummer = 0;
        Drawable frame1 = animatie1.getCurrent();

        for (int i = 0; i < animatie1.getNumberOfFrames(); i++) {
            Drawable frame2 = animatie1.getFrame(i);
            if (frame1 == frame2) {
                framenummer = i +1;
                break;
            }
        }

        if (framenummer == 1){
            tekst = "hartje";
        }else if (framenummer == 2){
            tekst = "wereld";
        }else if (framenummer == 3){
            tekst = "zak";
        }else if (framenummer == 4){
            tekst = "geld";
        }else if (framenummer == 5){
            tekst = "familie";
        }else{
            tekst = "man";
        }

        Toast toast = Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT);
        toast.show();

    }

    public void stop2 (View view){
        animatie2.stop();

        int framenummer = 0;
        Drawable frame1 = animatie2.getCurrent();

        for (int i = 0; i < animatie2.getNumberOfFrames(); i++) {
            Drawable frame2 = animatie2.getFrame(i);
            if (frame1 == frame2) {
                framenummer = i +1;
                break;
            }
        }

        if (framenummer == 1){
            tekst = "wereld";
        }else if (framenummer == 2){
            tekst = "man";
        }else if (framenummer == 3){
            tekst = "hartje";
        }else if (framenummer == 4){
            tekst = "geld";
        }else if (framenummer == 5){
            tekst = "familie";
        }else{
            tekst = "zak";
        }

        Toast toast = Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT);
        toast.show();

    }

    public void stop3 (View view){
        animatie3.stop();

        int framenummer = 0;
        Drawable frame1 = animatie3.getCurrent();

        for (int i = 0; i < animatie3.getNumberOfFrames(); i++) {
            Drawable frame2 = animatie3.getFrame(i);
            if (frame1 == frame2) {
                framenummer = i +1;
                break;
            }
        }

        if (framenummer == 1){
            tekst = "man";
        }else if (framenummer == 2){
            tekst = "familie";
        }else if (framenummer == 3){
            tekst = "geld";
        }else if (framenummer == 4){
            tekst = "zak";
        }else if (framenummer == 5){
            tekst = "wereld";
        }else{
            tekst = "hartje";
        }

        Toast toast = Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT);
        toast.show();

    }

    public void start (View view){
        animatie1.start();
        animatie2.start();
        animatie3.start();
    }

}
