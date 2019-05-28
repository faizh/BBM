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

public class bentuk3d extends AppCompatActivity {
    private ImageView imgbentuk, imgjudul;
    private ImageButton imgnext, imgback;
    private int i=0;
    MediaPlayer bentuksuara, backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_bentuk3d);

        imgbentuk = findViewById(R.id.imgbentuk);
        imgjudul = findViewById(R.id.txtjdl);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imgbentuk.setImageResource(R.drawable.bentukbalok);
        final int[] imageArray = {R.drawable.bentukbalok, R.drawable.bentukbola, R.drawable.bentukkerucut, R.drawable.bentukkubus, R.drawable.bentuklimassegi4, R.drawable.bentukprisma, R.drawable.bentuktabung};
        final int[] imageArray2 = {R.drawable.hurufbentukbalok, R.drawable.hurufbentukbola, R.drawable.hurufbentukkerucut, R.drawable.hurufbentukkubus, R.drawable.hurufbentuklimassegi4, R.drawable.hurufbentukprisma, R.drawable.hurufbentuktabung};
        final int[] soundbentuk = {R.raw.bentuk_balok, R.raw.bentuk_bola, R.raw.bentuk_kerucut, R.raw.bentuk_kubus, R.raw.bentuk_limassegiempat, R.raw.bentuk_prismasegittiga, R.raw.bentuk_tabung};

        imgback.setVisibility(View.INVISIBLE);

        bentuksuara = MediaPlayer.create(bentuk3d.this, R.raw.bentuk_balok);
        MediaPlayer welcome = MediaPlayer.create(bentuk3d.this, R.raw.opening_3d);
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
                imgnext.setEnabled(true);
                imgback.setEnabled(true);
            }
        });

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(bentuk3d.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                backsound = MediaPlayer.create(bentuk3d.this, R.raw.bs2);
                backsound.start();
                backsound.setLooping(true);

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
                        Intent intent = new Intent(bentuk3d.this, SubMainBentuk.class);
                        startActivity(intent);
                        backsound.stop();
                        finish();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    bentuksuara = MediaPlayer.create(bentuk3d.this, soundbentuk[i]);
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
                final MediaPlayer ring= MediaPlayer.create(bentuk3d.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imgbentuk.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);

                    bentuksuara = MediaPlayer.create(bentuk3d.this, soundbentuk[i]);
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
                                final MediaPlayer ring= MediaPlayer.create(bentuk3d.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(bentuk3d.this, SubMainBentuk.class);
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
        Intent a = new Intent(bentuk3d.this, SubMainBentuk.class);
        startActivity(a);
        finish();
        backsound.stop();
    }
}