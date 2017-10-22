package nl.stefhost.testcollectie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Beginscherm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginscherm);
    }

    public void gokkast_animatie(View view){
        Intent intent = new Intent(Beginscherm.this, Gokkast_animatie.class);
        startActivity(intent);
    }

    public void gyroscoop(View view){
        Intent intent = new Intent(Beginscherm.this, Gyroscoop.class);
        startActivity(intent);
    }

    public void miauw(View view){
        Intent intent = new Intent(Beginscherm.this, Miauw.class);
        startActivity(intent);
    }

    public void podcast(View view){
        Intent intent = new Intent(Beginscherm.this, Podcast.class);
        startActivity(intent);
    }

    public void pokemon(View view){
        Intent intent = new Intent(Beginscherm.this, Pokemon.class);
        startActivity(intent);
    }

    public void spel_1(View view){
        Intent intent = new Intent(Beginscherm.this, Spel_1.class);
        startActivity(intent);
    }

    public void spel_2(View view){
        Intent intent = new Intent(Beginscherm.this, Spel_2.class);
        startActivity(intent);
    }

    public void stefgame(View view){
        Intent intent = new Intent(Beginscherm.this, StefGame.class);
        startActivity(intent);
    }

    public void tags_schrijven(View view){
        Intent intent = new Intent(Beginscherm.this, Tags_schrijven.class);
        startActivity(intent);
    }

    public void voicerecognition(View view){
        Intent intent = new Intent(Beginscherm.this, VoiceRecognition.class);
        startActivity(intent);
    }

    public void qr_scanner(View view){
        Intent intent = new Intent(Beginscherm.this, QR_scanner.class);
        startActivity(intent);
    }

}
