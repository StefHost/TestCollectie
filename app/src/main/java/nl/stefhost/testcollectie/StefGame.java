package nl.stefhost.testcollectie;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StefGame extends Activity {

    public String resultaat = "";
    public String id = "1";
    public String link_1 = "";
    public String link_2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stefgame);
        new spel_laden().execute();
    }

    private class spel_laden extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params)  {

            URL url = null;
            URLConnection urlConnection = null;
            InputStream inputStream = null;

            try {
                url = new URL("http://www.stefhost.nl/stefgame/index.php?id="+id);
            } catch (MalformedURLException e) {
                System.out.println("MalformedURLException");
            }

            if (url != null){
                try{
                    urlConnection = url.openConnection();
                }catch (java.io.IOException e){
                    System.out.println("java.io.IOException");
                }
            }

            if (urlConnection != null){
                try{
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }catch (java.io.IOException e) {
                    System.out.println("java.io.IOException");
                }
            }

            if (inputStream != null){
                resultaat = inputStream.toString();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                try{
                    resultaat = bufferedReader.readLine();
                }catch (java.io.IOException e) {
                    System.out.println("java.io.IOException");
                }

            }else{
                resultaat = "ERROR";
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            klaar();
        }

    }

    private void klaar() {

        StringTokenizer tokens = new StringTokenizer(resultaat, "|");
        String tekst = tokens.nextToken();
        String knop1 = tokens.nextToken();
        link_1 = tokens.nextToken();
        String knop2 = tokens.nextToken();
        link_2 = tokens.nextToken();

        TextView textview = (TextView) findViewById(R.id.textview);
        textview.setText(tekst);

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setText(knop1);

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setText(knop2);

    }

    public void laden_1(View view) {
        id = link_1;
        new spel_laden().execute();
    }

    public void laden_2(View view) {
        id = link_2;
        new spel_laden().execute();
    }

}