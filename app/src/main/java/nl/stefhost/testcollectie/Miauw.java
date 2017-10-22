package nl.stefhost.testcollectie;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class Miauw extends Activity implements SensorEventListener {

    private android.hardware.SensorManager SensorManager;
    private Sensor Accelerometer;

    MediaPlayer mediaPlayer;

    boolean geluid = false;

    public int uren;
    public int minuten;

    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miauw);
        SensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Accelerometer = SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        PowerManager.WakeLock wakeLock;
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My wakelook");
        wakeLock.acquire();

        handler = new Handler();
        handler.post(timer);
    }

    @SuppressWarnings("deprecation")
    private Runnable timer = new Runnable() {
        @Override
        public void run() {

            Date date = new Date();
            uren = date.getHours();
            minuten = date.getMinutes();

            Log.d("Miauw", "uren:"+uren+" minuten:"+minuten);

            //if (uren > 19){
            if (uren > 0){
                if (minuten == 0 || minuten == 5 || minuten == 10 || minuten == 15 || minuten == 20 || minuten == 25 || minuten == 30 || minuten == 35 || minuten == 40 || minuten == 45 || minuten == 50 || minuten == 55){
                    random();
                }
            }

            handler.postDelayed(timer, 60000);
        }
    };

    protected void onResume() {
        SensorManager.registerListener(this, Accelerometer, android.hardware.SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        String x = Float.toString(event.values[0]);
        String y = Float.toString(event.values[1]);

        int x1 = (int) event.values[0];
        int y1 = (int) event.values[1];

        //TextView TextView1 = (TextView) findViewById(R.id.TextView1);
        //TextView1.setText(x+y);

        //if (uren > 19){
        if (uren > 0){

            if (x1 < 0 || x1 > 0){
                random();
            }else if (y1 < 0 || y1 > 0){
                random();
            }

        }

    }

    public void random(){
        Random random = new Random();
        int uitkomst = random.nextInt((3 - 1) + 1) + 1;
        geluid(uitkomst);
    }

    public void geluid(int keuze){
        if (!geluid) {
            geluid = true;
            if (keuze == 1){
                mediaPlayer = MediaPlayer.create(this, R.raw.kat_1);
            }else if(keuze == 2){
                mediaPlayer = MediaPlayer.create(this, R.raw.kat_2);
            }else{
                mediaPlayer = MediaPlayer.create(this, R.raw.kat_3);
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                    geluid = false;
                }
            });
            mediaPlayer.start();
        }
    }

}
