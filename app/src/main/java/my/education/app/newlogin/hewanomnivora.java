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

public class hewanomnivora extends AppCompatActivity {
    private ImageView imghewan,imgjudul;
    private ImageButton imgnext, imgback;
    private int i=0;
    private MediaPlayer hewansuara, backsound, welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_hewanomnivora);

        imghewan = findViewById(R.id.imghewan);
        imgjudul = findViewById(R.id.txtjdl);
        imgnext = findViewById(R.id.imgnext);
        imgback = findViewById(R.id.imgback);

        imgnext.setEnabled(false);
        imgback.setEnabled(false);

        imghewan.setImageResource(R.drawable.hewanayam);
        final int[] imageArray = {R.drawable.hewanayam, R.drawable.hewanbabi, R.drawable.hewanbebek, R.drawable.hewanberang, R.drawable.hewanberuang, R.drawable.hewandinosaurus, R.drawable.hewanikan, R.drawable.hewanmonyet, R.drawable.hewantikus, R.drawable.hewantupai};
        final int[] imageArray2 = {R.drawable.hurufhewanayam, R.drawable.hurufhewanbabi, R.drawable.hurufhewanbebek, R.drawable.hurufhewanberang, R.drawable.hurufhewanberuang, R.drawable.hurufhewandinosaurus, R.drawable.hurufhewanikan, R.drawable.hurufhewanmonyet, R.drawable.hurufhewantikus, R.drawable.hurufhewantupai};
        final int[] soundhewan = {R.raw.hewan_ayam, R.raw.hewan_babi, R.raw.hewan_bebek, R.raw.hewan_berangberang, R.raw.hewan_beruang, R.raw.hewan_dinosauris, R.raw.hewan_ikan, R.raw.hewan_monyet, R.raw.hewan_tikus, R.raw.hewan_tupai};

        imgback.setVisibility(View.INVISIBLE);

        hewansuara = MediaPlayer.create(hewanomnivora.this, R.raw.hewan_ayam);
        welcome = MediaPlayer.create(hewanomnivora.this, R.raw.hewanomnivora_opening);
        backsound = MediaPlayer.create(hewanomnivora.this, R.raw.bs2);
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
                final MediaPlayer ring= MediaPlayer.create(hewanomnivora.this, R.raw.klik);
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
                        Intent intent = new Intent(hewanomnivora.this, SubMainHewan.class);
                        startActivity(intent);
                        finish();
                        backsound.stop();
                        return;
                        //imgnext.setVisibility(View.INVISIBLE);
                    }else if(i>=0){
                        imgback.setVisibility(View.VISIBLE);
                    }
                    i++;
                    hewansuara = MediaPlayer.create(hewanomnivora.this, soundhewan[i]);
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
                final MediaPlayer ring= MediaPlayer.create(hewanomnivora.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                if (imgnext.isEnabled()==true){
                    imgback.startAnimation(myAnim);
                    i--;
                    imghewan.setImageResource(imageArray[i]);
                    imgjudul.setImageResource(imageArray2[i]);

                    hewansuara = MediaPlayer.create(hewanomnivora.this, soundhewan[i]);
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
                                final MediaPlayer ring= MediaPlayer.create(hewanomnivora.this, R.raw.klik);
                                ring.start();
                                Intent i = new Intent(hewanomnivora.this, SubMainHewan.class);
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
        Intent a = new Intent(hewanomnivora.this, SubMainHewan.class);
        startActivity(a);
        finish();
        backsound.stop();
        welcome.stop();
        hewansuara.stop();

    }
}
