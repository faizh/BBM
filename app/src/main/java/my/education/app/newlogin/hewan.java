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

public class hewan extends AppCompatActivity {
    private ImageView imghewan,imgjudul;
    private ImageButton imgnext, imgback;
    private int i=0;
    private MediaPlayer hewansuara, backsound, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_hewan);

        imghewan = findViewById(R.id.imghewan);
        imgjudul = findViewById(R.id.txtjdl);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imghewan.setImageResource(R.drawable.hewanburung);
        final int[] imageArray = {R.drawable.hewanburung, R.drawable.hewangajah, R.drawable.hewangorilla, R.drawable.hewanjerapah, R.drawable.hewankoala, R.drawable.hewanlebah, R.drawable.hewanpanda, R.drawable.hewanrusa, R.drawable.hewansapi, R.drawable.hewanzebra};
        final int[] imageArray2 = {R.drawable.hurufhewanburung, R.drawable.hurufhewangajah, R.drawable.hurufhewangorilla, R.drawable.hurufhewanjerapah, R.drawable.hurufhewankoala, R.drawable.hurufhewanlebah, R.drawable.hurufhewanpanda, R.drawable.hurufhewanrusa, R.drawable.hurufhewansapi, R.drawable.hurufhewanzebra};
        final int[] soundhewan = {R.raw.hewan_burung, R.raw.hewan_gajah, R.raw.hewan_gorila, R.raw.hewan_jerapah, R.raw.hewan_koala, R.raw.hewan_lebah, R.raw.hewan_panda, R.raw.hewan_rusa, R.raw.hewan_sapi, R.raw.hewan_zebra};

        imgback.setVisibility(View.INVISIBLE);

        hewansuara = MediaPlayer.create(hewan.this, R.raw.hewan_burung);
        welcome = MediaPlayer.create(hewan.this, R.raw.hewanherbivora_opening);
        backsound = MediaPlayer.create(hewan.this, R.raw.bs2);
        backsound.start();
        backsound.setLooping(true);

        welcome.start();
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                hewansuara.start();
            }
        });
        hewansuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
                final MediaPlayer ring= MediaPlayer.create(hewan.this, R.raw.klik);
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
                        Intent intent = new Intent(hewan.this, SubMainHewan.class);
                        startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    hewansuara = MediaPlayer.create(hewan.this, soundhewan[i]);
                    hewansuara.start();
                    hewansuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            hewansuara.release();
                            ring.release();
                        }
                    });
                    imghewan.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);
                    return;
                }
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(hewan.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imghewan.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);

                    hewansuara = MediaPlayer.create(hewan.this, soundhewan[i]);
                    hewansuara.start();
                    hewansuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            hewansuara.release();
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
                                final MediaPlayer ring= MediaPlayer.create(hewan.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(hewan.this, SubMainHewan.class);
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
        Intent a = new Intent(hewan.this, SubMainHewan.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        hewansuara.stop();
    }
}
