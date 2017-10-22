package nl.stefhost.testcollectie;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

public class Podcast_muziekspeler extends AsyncTask<String, Void, String> {
	private static MediaPlayer mediaPlayer = new MediaPlayer();

	protected String doInBackground(String... url) {
		
			String test = url[0];

			mediaPlayer.reset();
		    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		    try{
				mediaPlayer.setDataSource(test);
			}catch (IllegalArgumentException e){
				Log.d("Podcast", "IllegalArgumentException");
				e.printStackTrace();
			}catch (SecurityException e){
				Log.d("Podcast", "SecurityException");
				e.printStackTrace();
			}catch (IllegalStateException e){
				Log.d("Podcast", "IllegalStateException");
				e.printStackTrace();
			}catch (IOException e){
				Log.d("Podcast", "IOException");
				e.printStackTrace();
			}
			try{
				mediaPlayer.prepare();
			}catch (IllegalStateException e){
				Log.d("Podcast", "IllegalStateException");
				e.printStackTrace();
			}catch (IOException e){
				Log.d("Podcast", "IOException");
				e.printStackTrace();
			}
			
		return null;
	}
	
    @Override
    protected void onPostExecute(String result) {
		mediaPlayer.start();
    }
	
	public static void start() {
		mediaPlayer.start();
	}
	
	public static void pauze() {
		mediaPlayer.pause();
	}
	
	public static void stop() {
		mediaPlayer.pause();
		mediaPlayer.seekTo(0);
	}
	
}
