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

public class SubMainHuruf extends AppCompatActivity {
    private ImageButton besar, kecil;
    private MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_sub_main_huruf);

        besar = findViewById(R.id.imgbesar);
        kecil = findViewById(R.id.imgkecil);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        backsound = MediaPlayer.create(SubMainHuruf.this, R.raw.bs2);
        backsound.setLooping(true);
        backsound.start();

        besar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(SubMainHuruf.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                besar.startAnimation(myAnim);
                Intent i = new Intent(SubMainHuruf.this, huruf.class);
                SubMainHuruf.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });

        kecil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(SubMainHuruf.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                kecil.startAnimation(myAnim);
                Intent i = new Intent(SubMainHuruf.this, hurufkecil.class);
                SubMainHuruf.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });

    }

    public void onBackPressed(){
        Intent intent = new Intent(SubMainHuruf.this, Modul.class);
        startActivity(intent);
        finish();
        backsound.stop();
    }
}
