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

public class SubMainBentuk extends AppCompatActivity {
    private ImageButton duaD, tigaD;
    private MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_sub_main_bentuk);

        duaD = findViewById(R.id.imgduaD);
        tigaD = findViewById(R.id.imgtigaD);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        backsound = MediaPlayer.create(SubMainBentuk.this, R.raw.bs2);
        backsound.setLooping(true);
        backsound.start();

        duaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainBentuk.this, R.raw.klik);
                MediaPlayer sdua= MediaPlayer.create(SubMainBentuk.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                duaD.startAnimation(myAnim);
                Intent i = new Intent(SubMainBentuk.this, bentuk.class);
                SubMainBentuk.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });

        tigaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(SubMainBentuk.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                tigaD.startAnimation(myAnim);
                Intent i = new Intent(SubMainBentuk.this, bentuk3d.class);
                SubMainBentuk.this.startActivity(i);
                finish();
                backsound.stop();
            }
        });
    }

    public void onBackPressed(){
        Intent a = new Intent(SubMainBentuk.this, Modul.class);
        startActivity(a);
        finish();
        backsound.stop();
    }
}
