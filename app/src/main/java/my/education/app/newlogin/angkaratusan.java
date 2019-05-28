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

public class angkaratusan extends AppCompatActivity {
    private ImageView imgangka, imghuruf;
    private ImageButton imgnext, imgback;
    private int i = 0;
    private MediaPlayer ratusanangka, backsound, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_angkaratusan);


        imgangka = findViewById(R.id.imgangka);
        imghuruf = findViewById(R.id.imghuruf);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imgangka.setImageResource(R.drawable.ratusan100);
        imghuruf.setImageResource(R.drawable.ratusanhuruf100);
        final int[] imageArray = {R.drawable.ratusan100, R.drawable.ratusan200, R.drawable.ratusan300, R.drawable.ratusan400, R.drawable.ratusan500, R.drawable.ratusan600, R.drawable.ratusan700, R.drawable.ratusan800, R.drawable.ratusan900};
        final int[] imageArray2 = {R.drawable.ratusanhuruf100, R.drawable.ratusanhuruf200, R.drawable.ratusanhuruf300, R.drawable.ratusanhuruf400, R.drawable.ratusanhuruf500, R.drawable.ratusanhuruf600, R.drawable.ratusanhuruf700, R.drawable.ratusanhuruf800, R.drawable.ratusanhuruf900};
        final int[] soundsatuan = {R.raw.ratusan100, R.raw.ratusan200, R.raw.ratusan300, R.raw.ratusan400, R.raw.ratusan500, R.raw.ratusan600, R.raw.ratusan700, R.raw.ratusan800, R.raw.ratusan900};

        imgback.setVisibility(View.INVISIBLE);

        ratusanangka = MediaPlayer.create(angkaratusan.this, R.raw.ratusan100);
        MediaPlayer welcome = MediaPlayer.create(angkaratusan.this, R.raw.ratusan);
        backsound = MediaPlayer.create(angkaratusan.this, R.raw.bs2);
        backsound.start();
        backsound.setLooping(true);
        welcome.start();
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                ratusanangka.start();
            }
        });
        ratusanangka.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imgnext.setEnabled(true);
                imgback.setEnabled(true);
            }
        });

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(angkaratusan.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgback.isEnabled()==true){
                    imgnext.startAnimation(myAnim);

                    imgnext.setEnabled(false);
                    if (i==1){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imgnext.setEnabled(true);
                                imgback.setEnabled(true);
                            }
                        },5000);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgnext.setEnabled(true);
                            imgback.setEnabled(true);
                        }
                    },2200);

                    if(i>=imageArray.length-1) {
                        Intent intent = new Intent(angkaratusan.this, SubMainAngka.class);
                        angkaratusan.this.startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    ratusanangka = MediaPlayer.create(angkaratusan.this, soundsatuan[i]);
                    ratusanangka.start();
                    ratusanangka.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            ratusanangka.release();
                            ring.release();
                        }
                    });
                    imgangka.setImageResource(imageArray[i]);
                    imghuruf.setImageResource(imageArray2[i]);
                    return;
                }
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(angkaratusan.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imgangka.setImageResource(imageArray[i]);
                    imghuruf.setImageResource(imageArray2[i]);
                    ratusanangka = MediaPlayer.create(angkaratusan.this, soundsatuan[i]);
                    ratusanangka.start();
                    ratusanangka.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            ratusanangka.release();
                            ring.release();
                        }
                    });

                    imgback.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgback.setEnabled(true);
                        }
                    },1200);

                    if(i==0) {
                        imgback.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final MediaPlayer ring= MediaPlayer.create(angkaratusan.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(angkaratusan.this, SubMainAngka.class);
                                startActivity(i);
                                backsound.stop();
                            }
                        });
                        return;
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
    public void onBackPressed(){
        Intent a = new Intent(angkaratusan.this, SubMainAngka.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        ratusanangka.stop();
    }
}
