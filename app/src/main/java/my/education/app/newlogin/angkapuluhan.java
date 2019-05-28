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

public class angkapuluhan extends AppCompatActivity {
    private ImageView imgangka, imghuruf;
    private ImageButton imgnext, imgback;
    private int i = 0;
    private MediaPlayer puluhanangka, backsound, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_angkapuluhan);

        imgangka = findViewById(R.id.imgangka);
        imghuruf = findViewById(R.id.imghuruf);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imgangka.setImageResource(R.drawable.puluhan10);
        imghuruf.setImageResource(R.drawable.puluhanhuruf10);
        final int[] imageArray = {R.drawable.puluhan10, R.drawable.puluhan20, R.drawable.puluhan30, R.drawable.puluhan40, R.drawable.puluhan50, R.drawable.puluhan60, R.drawable.puluhan70, R.drawable.puluhan80, R.drawable.puluhan90};
        final int[] imageArray2 = {R.drawable.puluhanhuruf10, R.drawable.puluhanhuruf20, R.drawable.puluhanhuruf30, R.drawable.puluhanhuruf40, R.drawable.puluhanhuruf50, R.drawable.puluhanhuruf60, R.drawable.puluhanhuruf70, R.drawable.puluhanhuruf80, R.drawable.puluhanhuruf90};
        final int[] soundsatuan = {R.raw.puluhan10, R.raw.puluhan20, R.raw.puluhan30, R.raw.puluhan40, R.raw.puluhan50, R.raw.puluhan60, R.raw.puluhan70, R.raw.puluhan80, R.raw.puluhan90};

        imgback.setVisibility(View.INVISIBLE);
        welcome = MediaPlayer.create(angkapuluhan.this, R.raw.puluhan);
        backsound = MediaPlayer.create(angkapuluhan.this, R.raw.bs2);
        backsound.start();
        backsound.setLooping(true);
        puluhanangka = MediaPlayer.create(angkapuluhan.this, R.raw.puluhan10);
        welcome.start();
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                puluhanangka.start();
            }
        });
        puluhanangka.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
                final MediaPlayer ring= MediaPlayer.create(angkapuluhan.this, R.raw.klik);
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
                    },3000);

                    if(i>=imageArray.length-1) {
                        Intent intent = new Intent(angkapuluhan.this, SubMainAngka.class);
                        angkapuluhan.this.startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    puluhanangka = MediaPlayer.create(angkapuluhan.this, soundsatuan[i]);
                    puluhanangka.start();
                    puluhanangka.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            puluhanangka.release();
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
                final MediaPlayer ring= MediaPlayer.create(angkapuluhan.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imgangka.setImageResource(imageArray[i]);
                    imghuruf.setImageResource(imageArray2[i]);
                    puluhanangka = MediaPlayer.create(angkapuluhan.this, soundsatuan[i]);
                    puluhanangka.start();
                    puluhanangka.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            puluhanangka.release();
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
                                final MediaPlayer ring= MediaPlayer.create(angkapuluhan.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(angkapuluhan.this, SubMainAngka.class);
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

            }
        });
    }

    public void onBackPressed(){
        Intent a = new Intent(angkapuluhan.this, SubMainAngka.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        puluhanangka.stop();
    }
}
