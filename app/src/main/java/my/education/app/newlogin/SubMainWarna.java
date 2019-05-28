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

public class SubMainWarna extends AppCompatActivity {
    private ImageButton dasar, campuran;
    private MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_sub_main_warna);

        dasar = findViewById(R.id.imgdasar);
        campuran = findViewById(R.id.imgcampuran);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        backsound = MediaPlayer.create(SubMainWarna.this, R.raw.bs2);
        backsound.setLooping(true);
        backsound.start();

        dasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainWarna.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                dasar.startAnimation(myAnim);
                Intent i = new Intent(SubMainWarna.this, warna.class);
                SubMainWarna.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });

        campuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainWarna.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                campuran.startAnimation(myAnim);
                Intent i = new Intent(SubMainWarna.this, warnadasar.class);
                SubMainWarna.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });
    }

    public void onBackPressed(){
        Intent a = new Intent(SubMainWarna.this, Modul.class);
        startActivity(a);
        finish();
        backsound.stop();
    }
}
