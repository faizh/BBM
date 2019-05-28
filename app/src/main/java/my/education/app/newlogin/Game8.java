package my.education.app.newlogin;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class Game8 extends AppCompatActivity {
    private ImageButton btnimg1, btnimg2, btnimg3, btnimg4;
    private MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_game8);
        btnimg1 = findViewById(R.id.img1);
        btnimg2 = findViewById(R.id.img2);
        btnimg3 = findViewById(R.id.img3);
        btnimg4 = findViewById(R.id.img4);

        btnimg1.setEnabled(false);
        btnimg2.setEnabled(false);
        btnimg3.setEnabled(false);
        btnimg4.setEnabled(false);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        final MediaPlayer soal = MediaPlayer.create(Game8.this, R.raw.game_mana_biru);
        soal.start();
        backsound = MediaPlayer.create(Game8.this, R.raw.bs3);
        backsound.start();
        backsound.setLooping(true);

        soal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                soal.release();
                btnimg1.setEnabled(true);
                btnimg2.setEnabled(true);
                btnimg3.setEnabled(true);
                btnimg4.setEnabled(true);
            }
        });

        btnimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                MediaPlayer ring= MediaPlayer.create(Game8.this, R.raw.correct);
                final MediaPlayer pintar = MediaPlayer.create(Game8.this, R.raw.game_pintar);
                myAnim.setInterpolator(interpolator);
                btnimg1.startAnimation(myAnim);
                btnimg2.clearAnimation();
                btnimg4.clearAnimation();
                btnimg3.clearAnimation();
                ring.start();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        pintar.start();
                    }
                });
                pintar.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        pintar.release();
                    }
                });

                Intent i = new Intent(Game8.this, Play.class);
                Game8.this.startActivity(i);
                backsound.stop();
                finish();
            }
        });

        btnimg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(Game8.this, R.raw.wrong);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                btnimg2.startAnimation(myAnim);
                btnimg1.clearAnimation();
                btnimg4.clearAnimation();
                btnimg3.clearAnimation();
            }
        });

        btnimg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(Game8.this, R.raw.wrong);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                btnimg3.startAnimation(myAnim);
                btnimg2.clearAnimation();
                btnimg4.clearAnimation();
                btnimg1.clearAnimation();
            }
        });

        btnimg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(Game8.this, R.raw.wrong);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                btnimg4.startAnimation(myAnim);
                btnimg2.clearAnimation();
                btnimg1.clearAnimation();
                btnimg3.clearAnimation();
            }
        });
    }
    public void onBackPressed(){
        Intent a = new Intent(Game8.this, Play.class);
        startActivity(a);
        finish();
        backsound.stop();
    }

}
