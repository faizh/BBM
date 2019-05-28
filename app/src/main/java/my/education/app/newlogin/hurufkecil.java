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

public class hurufkecil extends AppCompatActivity {
    private ImageView imghuruf;
    private ImageButton btnnext, btnback;
    private int i = 0;
    private MediaPlayer hurufsuara, backsound, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_hurufkecil);

        imghuruf = findViewById(R.id.imghuruf);
        btnnext = findViewById(R.id.imgnext);
        btnback = findViewById(R.id.imgback);

        btnnext.setEnabled(false);
        btnback.setEnabled(false);

        imghuruf.setImageResource(R.drawable.hurufakecil);
        final int[] imageArray = {R.drawable.hurufakecil, R.drawable.hurufbkecil, R.drawable.hurufckecil, R.drawable.hurufdkecil, R.drawable.hurufekecil, R.drawable.huruffkecil, R.drawable.hurufgkecil, R.drawable.hurufhkecil, R.drawable.hurufikecil, R.drawable.hurufjkecil, R.drawable.hurufkkecil, R.drawable.huruflkecil, R.drawable.hurufmkecil, R.drawable.hurufnkecil, R.drawable.hurufokecil, R.drawable.hurufpkecil, R.drawable.hurufqkecil, R.drawable.hurufrkecil, R.drawable.hurufskecil, R.drawable.huruftkecil, R.drawable.hurufukecil, R.drawable.hurufvkecil, R.drawable.hurufwkecil, R.drawable.hurufxkecil, R.drawable.hurufykecil, R.drawable.hurufzkecil};
        final int[] soundhuruf = {R.raw.hurufa, R.raw.hurufb, R.raw.hurufc, R.raw.hurufd, R.raw.hurufe, R.raw.huruff, R.raw.hurufg, R.raw.hurufh, R.raw.hurufi, R.raw.hurufj, R.raw.hurufk, R.raw.hurufl, R.raw.hurufm, R.raw.hurufn, R.raw.hurufo, R.raw.hurufp, R.raw.hurufq, R.raw.hurufr, R.raw.hurufs, R.raw.huruft, R.raw.hurufu, R.raw.hurufv, R.raw.hurufw, R.raw.hurufx, R.raw.hurufy, R.raw.hurufz};

        btnback.setVisibility(View.INVISIBLE);

        hurufsuara = MediaPlayer.create(hurufkecil.this, R.raw.hurufa);
        welcome = MediaPlayer.create(hurufkecil.this, R.raw.hurufkecil_opening);
        backsound = MediaPlayer.create(hurufkecil.this, R.raw.bs2);
        backsound.start();
        backsound.setLooping(true);

        welcome.start();
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
                final MediaPlayer ring= MediaPlayer.create(hurufkecil.this, R.raw.klik);
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
                        },5000);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnnext.setEnabled(true);
                            btnback.setEnabled(true);
                        }
                    },3200);

                    if(i>=imageArray.length-1) {
                        Intent intent = new Intent(hurufkecil.this, SubMainHuruf.class);
                        startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        btnback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    hurufsuara = MediaPlayer.create(hurufkecil.this, soundhuruf[i]);
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
                final MediaPlayer ring= MediaPlayer.create(hurufkecil.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (btnnext.isEnabled()==true){
                    btnback.startAnimation(myAnim);
                    i--;
                    imghuruf.setImageResource(imageArray[i]);

                    hurufsuara = MediaPlayer.create(hurufkecil.this, soundhuruf[i]);
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
                                final MediaPlayer ring= MediaPlayer.create(hurufkecil.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(hurufkecil.this, SubMainHuruf.class);
                                startActivity(i);
                                backsound.stop();
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
        Intent a = new Intent(hurufkecil.this, SubMainHuruf.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        hurufsuara.stop();
    }
}
