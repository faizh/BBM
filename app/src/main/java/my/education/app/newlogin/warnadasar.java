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

public class warnadasar extends AppCompatActivity {
    private ImageView imgwarna, imgjudul;
    private ImageButton imgnext, imgback;
    private int i=0;
    private MediaPlayer warnasuara, backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_warnadasar);

        imgwarna = findViewById(R.id.imgwarna);
        imgjudul = findViewById(R.id.txtjdl);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imgwarna.setImageResource(R.drawable.warnacoklat);
        imgjudul.setImageResource(R.drawable.hurufwarnacoklat);
        final int[] imageArray = {R.drawable.warnacoklat, R.drawable.warnacyan, R.drawable.warnahitam, R.drawable.warnakuning, R.drawable.warnaorange, R.drawable.warnapink, R.drawable.warnaputih, R.drawable.warnaungu};
        final int[] imageArray2 = {R.drawable.hurufwarnacoklat, R.drawable.hurufwarnacyan, R.drawable.hurufwarnahitam, R.drawable.hurufwarnakuning, R.drawable.hurufwarnaorange, R.drawable.hurufwarnapink, R.drawable.hurufwarnaputih, R.drawable.hurufwarnaungu};
        final int[] soundwarna = {R.raw.warna_coklat, R.raw.warnacyan, R.raw.warnahitam, R.raw.warnakuning, R.raw.warnaoren, R.raw.warnapink, R.raw.warnaputih, R.raw.warnaungu};

        imgback.setVisibility(View.INVISIBLE);

        warnasuara = MediaPlayer.create(warnadasar.this, R.raw.warna_coklat);
        MediaPlayer welcome = MediaPlayer.create(warnadasar.this, R.raw.warnacampuran_opening);
        backsound = MediaPlayer.create(warnadasar.this, R.raw.bs2);
        backsound.start();
        backsound.setLooping(true);

        welcome.start();
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                warnasuara.start();
            }
        });
        warnasuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imgback.setEnabled(true);
                imgnext.setEnabled(true);
            }
        });


        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(warnadasar.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgback.isEnabled()==true){
                    imgnext.startAnimation(myAnim);

                    imgnext.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgnext.setEnabled(true);
                            imgback.setEnabled(true);
                        }
                    },1200);

                    if(i>=imageArray.length-1) {
                        Intent intent = new Intent(warnadasar.this, SubMainWarna.class);
                        startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    warnasuara = MediaPlayer.create(warnadasar.this, soundwarna[i]);
                    warnasuara.start();
                    warnasuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            warnasuara.release();
                            ring.release();
                        }
                    });
                    imgwarna.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);
                    return;
                }
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(warnadasar.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imgwarna.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);

                    warnasuara = MediaPlayer.create(warnadasar.this, soundwarna[i]);
                    warnasuara.start();
                    warnasuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            warnasuara.release();
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
                                final MediaPlayer ring= MediaPlayer.create(warnadasar.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(warnadasar.this, SubMainWarna.class);
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
        Intent a = new Intent(warnadasar.this, SubMainWarna.class);
        startActivity(a);
        finish();
        backsound.stop();
    }
}
