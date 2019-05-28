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

public class bentuk extends AppCompatActivity {
    private ImageView imgbentuk, imgjudul;
    private ImageButton imgnext, imgback;
    private int i=0;
    private MediaPlayer bentuksuara, backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_bentuk);

        imgbentuk = findViewById(R.id.imgbentuk);
        imgjudul = findViewById(R.id.txtjdl);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imgbentuk.setImageResource(R.drawable.bentukbelahketupat);
        final int[] imageArray = {R.drawable.bentukbelahketupat, R.drawable.bentukjajargenjang, R.drawable.bentuklayang, R.drawable.bentuklingkaran, R.drawable.bentukoval, R.drawable.bentukpersegi, R.drawable.bentukpersegipanjang, R.drawable.bentuksegilima, R.drawable.bentuksegitiga, R.drawable.bentuktrapesium};
        final int[] imageArray2 = {R.drawable.hurufbentukbelahketupat, R.drawable.hurufbentukjajargenjang, R.drawable.hurufbentuklayang, R.drawable.hurufbentuklingkaran, R.drawable.hurufbentukoval, R.drawable.hurufbentukpersegi, R.drawable.hurufbentukpersegipanjang, R.drawable.hurufbentuksegilima, R.drawable.hurufbentuksegitiga, R.drawable.hurufbentuktrapesium};
        final int[] soundbentuk = {R.raw.bentuk_belahketupat, R.raw.bentuk_jajargenjang, R.raw.bentuk_layang2x, R.raw.bentuk_lingkaran, R.raw.bentuk_oval, R.raw.bentuk_persegi, R.raw.bentuk_persegipanjang, R.raw.bentuk_segilima, R.raw.bentuk_segitiga, R.raw.bentuk_trapesium};

        imgback.setVisibility(View.INVISIBLE);

        bentuksuara = MediaPlayer.create(bentuk.this, R.raw.bentuk_belahketupat);
        MediaPlayer welcome = MediaPlayer.create(bentuk.this, R.raw.opening_2d);
        backsound = MediaPlayer.create(bentuk.this, R.raw.bs2);
        backsound.start();
        backsound.setLooping(true);
        welcome.start();
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                bentuksuara.start();
            }
        });
        bentuksuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
                final MediaPlayer ring= MediaPlayer.create(bentuk.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                backsound.stop();
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
                        Intent intent = new Intent(bentuk.this, SubMainBentuk.class);
                        bentuk.this.startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    bentuksuara = MediaPlayer.create(bentuk.this, soundbentuk[i]);
                    bentuksuara.start();
                    bentuksuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            bentuksuara.release();
                            ring.release();
                        }
                    });
                    imgbentuk.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);
                    return;
                }
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(bentuk.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imgbentuk.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);

                    bentuksuara = MediaPlayer.create(bentuk.this, soundbentuk[i]);
                    bentuksuara.start();
                    bentuksuara.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            bentuksuara.release();
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
                                final MediaPlayer ring= MediaPlayer.create(bentuk.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(bentuk.this, SubMainBentuk.class);
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
        Intent a = new Intent(bentuk.this, SubMainBentuk.class);
        startActivity(a);
        finish();
        backsound.stop();
    }
}
