package my.education.app.newlogin;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    private ImageButton btnimg1, btnimg2, btnimg3, btnimg4;
    private TextView txtjdl;
    private MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_game);

        btnimg1 = findViewById(R.id.img1);
        btnimg2 = findViewById(R.id.img2);
        btnimg3 = findViewById(R.id.img3);
        btnimg4 = findViewById(R.id.img4);
        txtjdl = findViewById(R.id.txtjdl);

        final int[] fruitArray = {R.drawable.buahanggur, R.drawable.buahapel, R.drawable.buahdurian, R.drawable.buahjeruk, R.drawable.buahmangga, R.drawable.buahpisang, R.drawable.buahstrawberry, R.drawable.buahtomat};
        final int[] shapeArray = {R.drawable.blingkaran, R.drawable.bkotak, R.drawable.bppanjang, R.drawable.bsegitiga, R.drawable.bslima};
        final int[] animalsArray = {R.drawable.hewananjing, R.drawable.hewanburung, R.drawable.hewandinosaurus, R.drawable.hewanflamingo, R.drawable.hewankucing, R.drawable.hewanlebah, R.drawable.hewanpanda, R.drawable.hewanserigala};
        final String[] judul = {"Mana Buah Pisang","Mana Buah Durian","Mana Hewan Burung","Mana Hewan Lebah", "Mana Bentuk Segitiga", "Mana Bentuk Lingkaran","Mana Warna Hijau", "Mana Warna Biru"};

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        backsound = MediaPlayer.create(Game.this, R.raw.bs3);
        backsound.start();
        backsound.setLooping(true);


        final MediaPlayer soal = MediaPlayer.create(Game.this, R.raw.game_mana_pisang);
        soal.start();
        soal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                soal.release();
            }
        });

        btnimg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                final MediaPlayer ring= MediaPlayer.create(Game.this, R.raw.correct);
                myAnim.setInterpolator(interpolator);
                btnimg3.startAnimation(myAnim);
                btnimg2.clearAnimation();
                btnimg4.clearAnimation();
                btnimg1.clearAnimation();
                ring.start();

                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });

                Intent i = new Intent(Game.this, Game2.class);
                Game.this.startActivity(i);
                backsound.stop();
                finish();
            }
        });

        btnimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(Game.this, R.raw.wrong);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                btnimg1.startAnimation(myAnim);
                btnimg2.clearAnimation();
                btnimg4.clearAnimation();
                btnimg3.clearAnimation();
                ring.start();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
            }
        });

        btnimg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(Game.this, R.raw.wrong);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                btnimg2.startAnimation(myAnim);
                btnimg1.clearAnimation();
                btnimg4.clearAnimation();
                btnimg3.clearAnimation();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
            }
        });

        btnimg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(Game.this, R.raw.wrong);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                btnimg4.startAnimation(myAnim);
                btnimg2.clearAnimation();
                btnimg1.clearAnimation();
                btnimg3.clearAnimation();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
            }
        });

    }
    public void onBackPressed(){
        Intent a = new Intent(Game.this, Play.class);
        startActivity(a);
        finish();
        backsound.stop();
    }
}
