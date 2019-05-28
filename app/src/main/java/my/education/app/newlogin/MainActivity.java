package my.education.app.newlogin;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

class MyBounceInterpolator implements android.view.animation.Interpolator {
    private double mAmplitude = 1;
    private double mFrequency = 10;

    MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
public class MainActivity extends AppCompatActivity {
    //private Button modul, play;
    private ImageButton modul, play;
    private MediaPlayer player, welcome2;
    private static final String ACTION_PLAY = "com.example.action.PLAY";
    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_main);

        modul = findViewById(R.id.btn_modul);
        play = findViewById(R.id.btn_play);

        modul.setEnabled(false);
        play.setEnabled(false);

        //Intent svc=new Intent(MainActivity.this, BackgroundMusic.class);
        //startService(svc);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        player = MediaPlayer.create(MainActivity.this, R.raw.bs1);
        welcome2 = MediaPlayer.create(MainActivity.this, R.raw.welcomeapp);

        player.setLooping(true);
        player.start();
        welcome2.start();
        welcome2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                modul.setEnabled(true);
                play.setEnabled(true);
            }
        });

        modul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(MainActivity.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                modul.startAnimation(myAnim);
                Intent intent = new Intent(MainActivity.this, Modul.class);
                MainActivity.this.startActivity(intent);
                MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.bs1);
                player.stop();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(MainActivity.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                play.startAnimation(myAnim);
                Intent intent = new Intent(MainActivity.this, Play.class);
                MainActivity.this.startActivity(intent);
                MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.bs1);
                player.stop();
            }
        });
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(ACTION_PLAY)) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.bs1);
            mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);
            mediaPlayer.prepareAsync(); // prepare async to not block main thread
        }
        return flags;
    }

   /* public void onPrepared(MediaPlayer player, MediaPlayer welcome, MediaPlayer welcome2) {
        player.start();
        welcome.start();
        welcome2.start();
    }*/


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        finishAffinity();
//        player.stop();
        welcome2.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
        welcome2.release();
    }
}