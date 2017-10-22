package nl.stefhost.testcollectie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Spel_2_Canvas extends ImageView{

    int fps = 0;
    int fps_tellen = 0;

    public String debug1 = "";
    public String debug2 = "";
    public String debug3 = "";
    public String debug4 = "";

    public Handler handler;

    static int[] kamer = new int[] {
        1,2,3,4,4,5,4,5,
        6,7,8,9,9,9,9,10,
        9,9,9,9,9,9,9,9,
        9,9,9,9,9,9,9,9,
        9,9,9,11,9,9,9,9,
        9,9,9,12,9,9,9,9,
        13,9,9,9,9,9,14,9,
        15,9,9,9,9,9,16,9};

    static int[] overlay = new int[] {
        1,1,1,1,1,1,1,1,
        1,1,1,0,0,0,0,0,
        9,9,9,9,9,9,9,9,
        1,1,0,0,0,0,0,0,
        9,9,9,11,9,9,9,9,
        9,9,9,12,9,9,9,9,
        13,9,9,9,9,9,14,9,
        15,9,9,9,9,9,16,9};

    public Bitmap[] bitmap = new Bitmap[16];
    public Bitmap ash;
    public Matrix matrix = new Matrix();
    public Paint paint = new Paint();

    public Spel_2_Canvas(Context context, AttributeSet attrs)  {
        super(context, attrs);

        bitmap[0] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_1);
        bitmap[1] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_2);
        bitmap[2] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_3);
        bitmap[3] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_4);
        bitmap[4] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_5);
        bitmap[5] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_6);
        bitmap[6] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_7);
        bitmap[7] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_8);
        bitmap[8] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_9);
        bitmap[9] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_10);
        bitmap[10] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_11);
        bitmap[11] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_12);
        bitmap[12] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_13);
        bitmap[13] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_14);
        bitmap[14] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_15);
        bitmap[15] = BitmapFactory.decodeResource(getResources(),R.drawable.plaatje_16);
        ash = BitmapFactory.decodeResource(getResources(),R.drawable.ash_voor);

        new fps_tellen().execute();
    }

    int tellen_x = 0;
    int tellen_y = 0;
    int tellen = 0;

    int left = 0;
    int left_tellen = 0;
    int left_tot = 0;
    int top = 0;
    int top_tellen = 0;
    int top_tot = 0;

    int ash_kolom = 5;
    int ash_rij = 4;

    protected void onDraw(Canvas canvas) {

        debug3 = "Ash_kolom: "+ash_kolom+" Ash_rij: "+ash_rij;
        debug4 = "Left: "+left+ " Tot: "+left_tot;

        while (tellen_y < 8){

            while (tellen_x < 8){

                paint.setTextSize(60);
                paint.setColor(Color.BLUE);

                int plaatje = kamer[tellen] -1;

                matrix.setRotate(0, 0, 0);
                matrix.setScale(2,2);
                matrix.postTranslate(left, top);
                canvas.drawBitmap(bitmap[plaatje], matrix, paint);

                left = left + 128;
                tellen_x++;
                tellen++;
            }

            tellen_x = 0;

            left = left_tellen;
            top = top + 128;

            tellen_y = tellen_y+1;
        }

        tellen_x = 0;
        tellen_y = 0;
        tellen = 0;

        left = left_tellen;
        top = top_tellen;

        // ash
        matrix.setRotate(0, 0, 0);
        matrix.setScale(2, 2);
        matrix.postTranslate(512, 384);
        canvas.drawBitmap(ash, matrix, paint);

        // debug
        canvas.drawText(debug1, 0, 1500, paint);
        canvas.drawText(debug2, 0, 1600, paint);
        canvas.drawText(debug3, 0, 1700, paint);
        canvas.drawText(debug4, 0, 1800, paint);
        canvas.drawText("FPS: "+fps, 0, 1900, paint);

        invalidate();
        fps_tellen++;

    }

    private class fps_tellen extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params){

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            fps = fps_tellen;
            fps_tellen = 0;
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            new fps_tellen().execute();
        }

    }

    public static float xPos = 0;
    public static float yPos = 0;

    public boolean lopen = false;
    public int aantal_links;
    public int aantal_rechts;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent (MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                xPos = event.getX();
                yPos = event.getY();
                double kolom = Math.floor((xPos-left) / 128) +1;
                double rij = Math.floor((yPos-top) / 128) +1;
                debug1 = "Gekozen Kolom: "+kolom+" Gekozen Rij: "+rij;
                debug3 = "Ash_kolom: "+ash_kolom+" Ash_rij: "+ash_rij;

                int gekozen_kolom = (int) kolom;
                int gekozen_rij = (int) rij;
                int totaal = ((gekozen_rij -1) * 8) + gekozen_kolom;
                int leeg = overlay[totaal-1];

                debug2 = "Gekozen: "+totaal+" Leeg: "+leeg;

                if (!lopen){
                    if (!(kolom < 1) && kolom < ash_kolom){
                        aantal_links = ash_kolom - (int)kolom;
                    }else if (!(kolom > 8) && kolom > ash_kolom){
                        aantal_rechts = (int)kolom - ash_kolom;
                    }

                    /*if (!(rij < 1) && rij < ash_rij){
                        top_tot = top_tot + (ash_rij - (int)rij) * 128;
                        handler = new Handler();
                        ash_rij = (int)rij;
                    }else if (!(rij > 8) && rij > ash_rij){
                        top_tot = top_tot - ((int)rij - ash_rij) * 128;
                        handler = new Handler();
                        ash_rij = (int)rij;
                    }*/

                    handler = new Handler();
                    lopen_links();

                }


                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return true;

    }

    private void lopen_links() {
        if (aantal_links > 0){
            int naar_kolom = (ash_kolom -1);
            int totaal = ((ash_rij-1)*8) +naar_kolom;
            Log.d("Pokemon", "totaal:"+totaal);
            int leeg = overlay[totaal-1];
            if (leeg == 0) {
                left_tot = left_tot + 128;
                handler.postDelayed(links, 0);
            }
        }else{
            lopen_rechts();
        }
    }

    private Runnable links = new Runnable() {
        @Override
        public void run() {
            if (left_tellen < left_tot){
                left = left+8;
                left_tellen = left;
                handler.postDelayed(links, 0);
            }else{
                aantal_links--;
                ash_kolom--;
                lopen_links();
            }
        }
    };

    private void lopen_rechts() {
        if (aantal_rechts > 0){
            left_tot = left_tot - 128;
            handler.postDelayed(rechts, 0);
        }
    }

    private Runnable rechts = new Runnable() {
        @Override
        public void run() {
            if (left_tellen > left_tot){
                left = left-8;
                left_tellen = left;
                handler.postDelayed(rechts, 0);
            }else{
                aantal_rechts--;
                ash_kolom++;
                lopen_rechts();
            }
        }
    };

    private Runnable omlaag = new Runnable() {
        @Override
        public void run() {
            if (top_tellen < top_tot){
                top = top+8;
                top_tellen = top;
                handler.postDelayed(omlaag, 0);
            }else{
                handler.postDelayed(omhoog, 0);
            }
        }
    };

    private Runnable omhoog = new Runnable() {
        @Override
        public void run() {
            if (top_tellen > top_tot){
                top = top-8;
                top_tellen = top;
                handler.postDelayed(omhoog, 0);
            }else{
                lopen = false;
            }
        }
    };


}