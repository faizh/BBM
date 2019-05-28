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

public class buahasam extends AppCompatActivity {
    private ImageView imgbuah, imgjudul;
    private ImageButton imgnext, imgback;
    private  int i=0;
    private MediaPlayer buahsuara, backsound, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_buahasam);

        imgbuah = findViewById(R.id.imgbuah);
        imgjudul = findViewById(R.id.txtjdl);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imgbuah.setImageResource(R.drawable.buahanggur);
        imgjudul.setImageResource(R.drawable.hurufbuahanggur);
        final int[] imageArray = {R.drawable.buahanggur, R.drawable.buahasam, R.drawable.buahjeruk, R.drawable.buahlemon, R.drawable.buahmangga, R.drawable.buahmanggis, R.drawable.buahmarkisa, R.drawable.buahnanas, R.drawable.buahstrawberry, R.drawable.buahtomat};
        final int[] imageArray2 = {R.drawable.hurufbuahanggur, R.drawable.hurufbuahasam, R.drawable.hurufbuahjeruk, R.drawable.hurufbuahlemon, R.drawable.hurufbuahmangga, R.drawable.hurufbuahmanggis, R.drawable.hurufbuahmarkisa, R.drawable.hurufbuahnanas, R.drawable.hurufbuahstrawberry, R.drawable.hurufbuahtomat};
        final int[] soundbuah = {R.raw.buah_anggur, R.raw.buah_asam, R.raw.buah_jeruk, R.raw.buah_lemon, R.raw.buah_mangga, R.raw.buah_manggis, R.raw.buah_markisa, R.raw.buah_nanas, R.raw.buah_stroberi, R.raw.buah_tomat};

        imgback.setVisibility(View.INVISIBLE);

        buahsuara = MediaPlayer.create(buahasam.this, R.raw.buah_anggur);
        welcome = MediaPlayer.create(buahasam.this, R.raw.buahmasam_opening);
        backsound = MediaPlayer.create(buahasam.this, R.raw.bs2);
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
                final MediaPlayer ring= MediaPlayer.create(buahasam.this, R.raw.klik);
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
                        Intent intent = new Intent(buahasam.this, SubMainBuah.class);
                        buahasam.this.startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    buahsuara = MediaPlayer.create(buahasam.this, soundbuah[i]);
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
                final MediaPlayer ring= MediaPlayer.create(buahasam.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                backsound.stop();
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imgbuah.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);

                    buahsuara = MediaPlayer.create(buahasam.this, soundbuah[i]);
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
                                final MediaPlayer ring= MediaPlayer.create(buahasam.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(buahasam.this, SubMainBuah.class);
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
        Intent a = new Intent(buahasam.this, SubMainBuah.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        buahsuara.stop();
    }
}
