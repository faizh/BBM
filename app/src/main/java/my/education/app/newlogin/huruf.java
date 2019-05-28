package my.education.app.newlogin;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class huruf extends AppCompatActivity {
    private ImageView imghuruf;
    private ImageButton btnnext, btnback;
    private int i = 0;
    private MediaPlayer hurufsuara, backsound, welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_huruf);

        imghuruf = findViewById(R.id.imghuruf);
        btnnext = findViewById(R.id.imgnext);
        btnback = findViewById(R.id.imgback);

        btnback.setEnabled(false);
        btnnext.setEnabled(false);

        imghuruf.setImageResource(R.drawable.hurufabesar);
        final int[] imageArray = {R.drawable.hurufabesar, R.drawable.hurufbbesar, R.drawable.hurufcbesar, R.drawable.hurufdbesar, R.drawable.hurufebesar, R.drawable.huruffbesar, R.drawable.hurufgbesar, R.drawable.hurufhbesar, R.drawable.hurufibesar, R.drawable.hurufjbesar, R.drawable.hurufkbesar, R.drawable.huruflbesar, R.drawable.hurufmbesar, R.drawable.hurufnbesar, R.drawable.hurufobesar, R.drawable.hurufpbesar, R.drawable.hurufqbesar, R.drawable.hurufrbesar, R.drawable.hurufsbesar, R.drawable.huruftbesar, R.drawable.hurufubesar, R.drawable.hurufvbesar, R.drawable.hurufwbesar, R.drawable.hurufxbesar, R.drawable.hurufybesar, R.drawable.hurufzbesar};
        final int[] soundhuruf = {R.raw.hurufa, R.raw.hurufb, R.raw.hurufc, R.raw.hurufd, R.raw.hurufe, R.raw.huruff, R.raw.hurufg, R.raw.hurufh, R.raw.hurufi, R.raw.hurufj, R.raw.hurufk, R.raw.hurufl, R.raw.hurufm, R.raw.hurufn, R.raw.hurufo, R.raw.hurufp, R.raw.hurufq, R.raw.hurufr, R.raw.hurufs, R.raw.huruft, R.raw.hurufu, R.raw.hurufv, R.raw.hurufw, R.raw.hurufx, R.raw.hurufy, R.raw.hurufz};

        btnback.setVisibility(View.INVISIBLE);

        welcome = MediaPlayer.create(huruf.this, R.raw.hurufkapita_opening);
        backsound = MediaPlayer.create(huruf.this, R.raw.bs2);
        backsound.start();
        backsound.setLooping(true);

        welcome.start();
        hurufsuara = MediaPlayer.create(huruf.this, R.raw.hurufa);
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                hurufsuara.start();
            }
        });
        hurufsuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnback.setEnabled(true);
                btnnext.setEnabled(true);
            }
        });


        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(huruf.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (btnback.isEnabled()==true){
                    btnnext.startAnimation(myAnim);

                    btnnext.setEnabled(false);
                    if (i==1){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btnnext.setEnabled(true);
                                btnback.setEnabled(true);
                            }
                        },7000);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnnext.setEnabled(true);
                            btnback.setEnabled(true);
                        }
                    },3200);

                    if(i>=imageArray.length-1) {
                        Intent intent = new Intent(huruf.this, SubMainHuruf.class);
                        startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        btnback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    hurufsuara = MediaPlayer.create(huruf.this, soundhuruf[i]);
                    hurufsuara.start();
                    hurufsuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            hurufsuara.release();
                            ring.release();
                        }
                    });

                    imghuruf.setImageResource(imageArray[i]);
                    return;
                }

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(huruf.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 30);
                backsound.stop();
                myAnim.setInterpolator(interpolator);
                if (btnnext.isEnabled()==true){
                    btnback.startAnimation(myAnim);
                    i--;
                    imghuruf.setImageResource(imageArray[i]);

                    hurufsuara = MediaPlayer.create(huruf.this, soundhuruf[i]);
                    hurufsuara.start();
                    hurufsuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            hurufsuara.release();
                            ring.release();
                        }
                    });

                    btnback.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnback.setEnabled(true);
                        }
                    },3000);

                    if(i==0) {
                        btnback.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final MediaPlayer ring= MediaPlayer.create(huruf.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(huruf.this, SubMainHuruf.class);
                                startActivity(i);
                            }
                        });
                        return;
                    }else if(i>=0){
                        btnback.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

    }
    public void onBackPressed(){
        Intent a = new Intent(huruf.this, SubMainHuruf.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        hurufsuara.stop();
    }
}
