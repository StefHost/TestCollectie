package nl.stefhost.testcollectie;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class Video_toevoegen extends Activity {

    public String toevoegen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_toevoegen);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent);
            }
        }
    }

    void handleSendText(Intent intent) {
        toevoegen = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (toevoegen != null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Video_toevoegen.this);
            builder.setTitle("Intent Data")
                    .setMessage(toevoegen);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.show();

        }
    }

}
