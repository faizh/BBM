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

public class SubMainHewan extends AppCompatActivity {
    private ImageButton herbivora, karnivora, omnivora;
    private MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_sub_main_hewan);

        herbivora = findViewById(R.id.imgherbivora);
        karnivora = findViewById(R.id.imgkarnivora);
        omnivora = findViewById(R.id.imgomnivora);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        backsound = MediaPlayer.create(SubMainHewan.this, R.raw.bs2);
        backsound.setLooping(true);
        backsound.start();


        herbivora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainHewan.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                herbivora.startAnimation(myAnim);
                Intent i = new Intent(SubMainHewan.this, hewan.class);
                SubMainHewan.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });

        karnivora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainHewan.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                karnivora.startAnimation(myAnim);
                Intent i = new Intent(SubMainHewan.this, hewankarnivora.class);
                SubMainHewan.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });

        omnivora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainHewan.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                omnivora.startAnimation(myAnim);
                Intent i = new Intent(SubMainHewan.this, hewanomnivora.class);
                SubMainHewan.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });
    }

    public void onBackPressed(){
        Intent a = new Intent(SubMainHewan.this, Modul.class);
        startActivity(a);
        finish();
        backsound.stop();
    }
}
