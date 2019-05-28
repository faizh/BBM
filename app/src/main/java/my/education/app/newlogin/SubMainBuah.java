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

public class SubMainBuah extends AppCompatActivity {
    private ImageButton manis, asam;
    private MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_sub_main_buah);

        manis = findViewById(R.id.imgmanis);
        asam = findViewById(R.id.imgasam);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        backsound = MediaPlayer.create(SubMainBuah.this, R.raw.bs2);
        backsound.setLooping(true);
        backsound.start();

        manis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainBuah.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                manis.startAnimation(myAnim);
                Intent i = new Intent(SubMainBuah.this, buah.class);
                SubMainBuah.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });

        asam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainBuah.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                asam.startAnimation(myAnim);
                Intent i = new Intent(SubMainBuah.this, buahasam.class);
                SubMainBuah.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });
    }

    public void onBackPressed(){
        Intent a = new Intent(SubMainBuah.this, Modul.class);
        startActivity(a);
        finish();
        backsound.stop();
    }
}
