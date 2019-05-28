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

public class buah extends AppCompatActivity {
    private ImageView imgbuah, imgjudul;
    private ImageButton imgnext, imgback;
    private  int i=0;
    private MediaPlayer buahsuara, backsound, welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_buah);

        imgbuah = findViewById(R.id.imgbuah);
        imgjudul = findViewById(R.id.txtjdl);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imgbuah.setImageResource(R.drawable.buahalpukat);
        imgjudul.setImageResource(R.drawable.hurufbuahalpukat);
        final int[] imageArray = {R.drawable.buahalpukat, R.drawable.buahapel, R.drawable.buahceri, R.drawable.buahdurian, R.drawable.buahjambu, R.drawable.buahmelon, R.drawable.buahnaga, R.drawable.buahpepaya, R.drawable.buahpisang, R.drawable.buahsemangka};
        final int[] imageArray2 = {R.drawable.hurufbuahalpukat, R.drawable.hurufbuahapel, R.drawable.hurufbuahceri, R.drawable.hurufbuahdurian, R.drawable.hurufbuahjambu, R.drawable.hurufbuahmelon, R.drawable.hurufbuahnaga, R.drawable.hurufbuahpepaya, R.drawable.hurufbuahpisang, R.drawable.hurufbuahsemangka};
        final int[] soundbuah = {R.raw.buah_alpukat, R.raw.buah_apel, R.raw.buah_ceri, R.raw.buah_durian, R.raw.buah_jambu, R.raw.buah_melon, R.raw.buah_naga, R.raw.buah_pepaya, R.raw.buah_pisang, R.raw.buah_semangka };

        imgback.setVisibility(View.INVISIBLE);

        buahsuara = MediaPlayer.create(buah.this, R.raw.buah_alpukat);
        welcome = MediaPlayer.create(buah.this, R.raw.buahmanis_opening);
        backsound = MediaPlayer.create(buah.this, R.raw.bs2);
        backsound.start();
        backsound.setLooping(true);

        welcome.start();
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                buahsuara.start();
            }
        });
        buahsuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
                final MediaPlayer ring= MediaPlayer.create(buah.this, R.raw.klik);
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
                        Intent intent = new Intent(buah.this, SubMainBuah.class);
                        buah.this.startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    buahsuara = MediaPlayer.create(buah.this, soundbuah[i]);
                    buahsuara.start();
                    buahsuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            buahsuara.release();
                            ring.release();
                        }
                    });
                    imgbuah.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);
                    return;
                }
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(buah.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imgbuah.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);

                    buahsuara = MediaPlayer.create(buah.this, soundbuah[i]);
                    buahsuara.start();
                    buahsuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            buahsuara.release();
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
                                final MediaPlayer ring= MediaPlayer.create(buah.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(buah.this, SubMainBuah.class);
                                startActivity(i);
                                backsound.stop();
                                finish();
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
        Intent a = new Intent(buah.this, SubMainBuah.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        buahsuara.stop();
    }
}
