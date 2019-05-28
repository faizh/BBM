package my.education.app.newlogin;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

public class BackgroundMusic extends AppCompatActivity{
    public MediaPlayer ring = MediaPlayer.create(BackgroundMusic.this, my.education.app.newlogin.R.raw.bs1);
    @Override
    protected void onPause() {
        super.onPause();
       ring.pause();
// pause music
    }

    @Override
    protected void onResume() {
        super.onResume();
        ring.start();
// play music
    }
}