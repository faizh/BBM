package my.education.app.newlogin;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;

public abstract class MusicService extends Service implements MediaPlayer.OnPreparedListener {
    private static final String ACTION_PLAY = "com.example.action.PLAY";
    MediaPlayer mediaPlayer = null;

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(ACTION_PLAY)) {
            mediaPlayer = MediaPlayer.create(MusicService.this, my.education.app.newlogin.R.raw.klik);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync(); // prepare async to not block main thread
        }
        return flags;
    }

    /** Called when MediaPlayer is ready */
    public void onPrepared(MediaPlayer player) {
        player.start();
    }
}