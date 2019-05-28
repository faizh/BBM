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

public class hewankarnivora extends AppCompatActivity {
    private ImageView imghewan,imgjudul;
    private ImageButton imgnext, imgback;
    private int i=0;
    MediaPlayer hewansuara, backsound, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_hewankarnivora);


        imghewan = findViewById(R.id.imghewan);
        imgjudul = findViewById(R.id.txtjdl);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imghewan.setImageResource(R.drawable.hewananjing);
        final int[] imageArray = {R.drawable.hewananjing, R.drawable.hewanbuaya, R.drawable.hewanelang, R.drawable.hewanflamingo, R.drawable.hewanharimau, R.drawable.hewankucing, R.drawable.hewanmacantutul, R.drawable.hewanpenguin, R.drawable.hewanrubah, R.drawable.hewanserigala};
        final int[] imageArray2 = {R.drawable.hurufhewananjing, R.drawable.hurufhewanbuaya, R.drawable.hurufhewanelang, R.drawable.hurufhewanflamingo, R.drawable.hurufhewanharimau, R.drawable.hurufhewankucing, R.drawable.hurufhewanmacantutul, R.drawable.hururfhewanpenguin, R.drawable.hurufhewanrubah, R.drawable.hurufhewanserigala};
        final int[] soundhewan = {R.raw.hewan_anjing, R.raw.hewan_buaya, R.raw.hewan_elang, R.raw.hewan_flamingo, R.raw.hewan_harimau, R.raw.hewan_kucing, R.raw.hewan_macantutul, R.raw.hewan_pinguin, R.raw.hewan_rubah, R.raw.hewan_serigala};

        imgback.setVisibility(View.INVISIBLE);

        hewansuara = MediaPlayer.create(hewankarnivora.this, R.raw.hewan_anjing);
        welcome = MediaPlayer.create(hewankarnivora.this, R.raw.hewankarnivora_opening);
        backsound = MediaPlayer.create(hewankarnivora.this, R.raw.bs2);
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
                imgback.setEnabled(true);
                imgnext.setEnabled(true);
            }
        });

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(hewankarnivora.this, R.raw.klik);
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
                        Intent intent = new Intent(hewankarnivora.this, SubMainHewan.class);
                        startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    hewansuara = MediaPlayer.create(hewankarnivora.this, soundhewan[i]);
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
                final MediaPlayer ring= MediaPlayer.create(hewankarnivora.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imghewan.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);

                    hewansuara = MediaPlayer.create(hewankarnivora.this, soundhewan[i]);
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
                                final MediaPlayer ring= MediaPlayer.create(hewankarnivora.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(hewankarnivora.this, SubMainHewan.class);
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
        Intent a = new Intent(hewankarnivora.this, SubMainHewan.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        hewansuara.stop();
    }
}
