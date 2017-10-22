package nl.stefhost.testcollectie;

import android.app.Activity;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Gyroscoop extends Activity implements SensorEventListener {

    private SensorManager SensorManager;
    private Sensor Accelerometer;
    private int width;
    private int height;
    private int links;
    private String richting = "";
    private Handler Handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscoop);
        SensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Accelerometer = SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        Handler.postDelayed(rollen, 0);
    }

    protected void onResume() {
        super.onResume();
        SensorManager.registerListener(this, Accelerometer, android.hardware.SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        SensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        String x = Float.toString(event.values[2]);
        String y = Float.toString(event.values[1]);
        String z = Float.toString(event.values[0]);

        int links_rechts = (int) event.values[1];

        TextView TextView1 = (TextView) findViewById(R.id.TextView1);
        TextView1.setText("Breedte: "+width+"\nHoogte: "+height+"\nX: "+x+"\nY: "+y+"\nZ: "+z+"");

        TextView TextView2 = (TextView) findViewById(R.id.TextView2);

        if (links_rechts > 0){
            TextView2.setText("Rechts");
            richting = "rechts";
        }else if (links_rechts < 0){
            TextView2.setText("Links");
            richting = "links";
        }else{
            TextView2.setText("Neutraal");
            richting = "neutraal";
        }

    }

    private Runnable rollen = new Runnable() {

        public void run() {

            if (richting.equals("rechts")){
                links = links + 10;
                if (links > width-100){
                    links = width-100;
                }

            }else if (richting.equals("links")){
                links = links - 10;
                if (links < 0){
                    links = 0;
                }
            }

            ImageView ImageView = (ImageView) findViewById(R.id.imageView1);
            RelativeLayout.LayoutParams LayoutParams = new RelativeLayout.LayoutParams(ImageView.getLayoutParams());

            LayoutParams.setMargins(links, 980, 0, 0);
            ImageView.setLayoutParams(LayoutParams);

            TextView TextView3 = (TextView) findViewById(R.id.TextView3);
            TextView3.setText(""+links+"");

            Handler.postDelayed(this, 10);
        }

    };

}
