package my.education.app.newlogin;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class SubMainAngka extends AppCompatActivity {
    private ImageButton puluhan, satuan, ratusan;
    private MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_sub_main_angka);

        puluhan = findViewById(R.id.imgpuluhan);
        satuan = findViewById(R.id.imgsatuan);
        ratusan = findViewById(R.id.imgratusan);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        backsound = MediaPlayer.create(SubMainAngka.this, R.raw.bs2);
        backsound.setLooping(true);
        backsound.start();


        puluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainAngka.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                puluhan.startAnimation(myAnim);
                Intent i = new Intent(SubMainAngka.this, angkapuluhan.class);
                SubMainAngka.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });

        satuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(SubMainAngka.this, R.raw.klik);
                ring.start();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                satuan.startAnimation(myAnim);
                Intent i = new Intent(SubMainAngka.this, angka.class);
                SubMainAngka.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });

        ratusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainAngka.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                ratusan.startAnimation(myAnim);
                Intent i = new Intent(SubMainAngka.this, angkaratusan.class);
                SubMainAngka.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });
    }

    public void onBackPressed(){
        Intent a = new Intent(SubMainAngka.this, Modul.class);
        startActivity(a);
        finish();
        backsound.stop();
    }

}
