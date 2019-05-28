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

public class angka extends AppCompatActivity {
    private ImageView imgangka, imghuruf;
    private ImageButton imgnext, imgback;
    private int i = 0;
    private MediaPlayer satuanangka, backsound, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_angka);

        imgangka = findViewById(R.id.imgangka);
        imghuruf = findViewById(R.id.imghuruf);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgangka.setImageResource(R.drawable.satuan1);
        imghuruf.setImageResource(R.drawable.satuanhuruf1);
        final int[] imageArray = {R.drawable.satuan1, R.drawable.satuan2, R.drawable.satuan3, R.drawable.satuan4, R.drawable.satuan5, R.drawable.satuan6, R.drawable.satuan7, R.drawable.satuan8, R.drawable.satuan9};
        final int[] imageArray2 = {R.drawable.satuanhuruf1, R.drawable.satuanhuruf2, R.drawable.satuanhuruf3, R.drawable.satuanhuruf4, R.drawable.satuanhuruf5, R.drawable.satuanhuruf6, R.drawable.satuanhuruf7, R.drawable.satuanhuruf8, R.drawable.satuanhuruf9};
        final int[] soundsatuan = {R.raw.satuan1, R.raw.satuan2, R.raw.satuan3, R.raw.satuan4, R.raw.satuan5, R.raw.satuan6, R.raw.satuan7, R.raw.satuan8, R.raw.satuan9};

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        satuanangka = MediaPlayer.create(angka.this, R.raw.satuan1);
        backsound = MediaPlayer.create(angka.this, R.raw.bs2);
        welcome = MediaPlayer.create(angka.this, R.raw.satuan);
        welcome.start();
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                satuanangka.start();
                welcome.release();
            }
        });
        satuanangka.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                satuanangka.release();
            }
        });
        backsound.start();
        backsound.setLooping(true);

        imgback.setVisibility(View.INVISIBLE);

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(angka.this, R.raw.klik);
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
                        Intent intent = new Intent(angka.this, SubMainAngka.class);
                        angka.this.startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    imgangka.setImageResource(imageArray[i]);
                    imghuruf.setImageResource(imageArray2[i]);
                    satuanangka = MediaPlayer.create(angka.this, soundsatuan[i]);
                    satuanangka.start();
                    satuanangka.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            satuanangka.release();
                            ring.release();
                        }
                    });

                    return;
                }


            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(angka.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);

                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imgangka.setImageResource(imageArray[i]);
                    imghuruf.setImageResource(imageArray2[i]);

                    satuanangka = MediaPlayer.create(angka.this, soundsatuan[i]);
                    satuanangka.start();
                    satuanangka.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            satuanangka.release();
                            ring.release();
                        }
                    });

                    imgback.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgback.setEnabled(true);
                        }
                    },2000);

                    if(i==0) {
                        imgback.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final MediaPlayer ring= MediaPlayer.create(angka.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(angka.this, SubMainAngka.class);
                                startActivity(i);
                                backsound.stop();
                            }
                        });
                        //imgback.setVisibility(View.INVISIBLE);
                        return;
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                }

               /* imgback.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgback.setEnabled(true);
                    }
                },1200);*/

                /*i--;
                imgangka.setImageResource(imageArray[i]);
                imghuruf.setImageResource(imageArray2[i]);*/
               /* if(i<=0) {
                    imgback.setVisibility(View.INVISIBLE);
                    return;
                }else if(i>=0){
                    imgback.setVisibility(View.VISIBLE);
                }*/
            }
        });
    }
    public void onBackPressed(){
        Intent a = new Intent(angka.this, SubMainAngka.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        satuanangka.stop();
    }

}