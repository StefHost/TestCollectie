package nl.stefhost.testcollectie;

import java.nio.charset.Charset;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Tags_schrijven extends Activity {

    NfcAdapter NfcAdapter;
    public int nummer = 1;

    PendingIntent NfcPendingIntent;
    IntentFilter[] WriteTagFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_schrijven);

        NfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(this);
        if (NfcAdapter != null){
            NfcPendingIntent = PendingIntent.getActivity(this, 0,new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (NfcAdapter != null){
            IntentFilter intentFilter = new IntentFilter(android.nfc.NfcAdapter.ACTION_TAG_DISCOVERED);
            WriteTagFilters = new IntentFilter[] {intentFilter};
            NfcAdapter.enableForegroundDispatch(this, NfcPendingIntent, WriteTagFilters, null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (android.nfc.NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag tag = intent.getParcelableExtra(android.nfc.NfcAdapter.EXTRA_TAG);

            String data = String.valueOf(nummer);
            NdefRecord mimeRecord = NdefRecord.createMime("application/nl.stefhost.testcollectie.tags_lezen",data.getBytes(Charset.forName("US-ASCII")));
            NdefMessage NdefMessage = new NdefMessage(new NdefRecord[] { mimeRecord });

            try {
                Ndef ndef = Ndef.get(tag);
                if (ndef != null) {
                    ndef.connect();
                    ndef.writeNdefMessage(NdefMessage);
                    Toast.makeText(this, "Tag geschreven!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void knop_1 (View view){
        nummer = 1;
    }

    public void knop_2 (View view){
        nummer = 2;
    }

}
