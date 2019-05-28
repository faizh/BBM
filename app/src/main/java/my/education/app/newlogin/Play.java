package my.education.app.newlogin;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class Play extends AppCompatActivity {
    private ImageButton start, exit;
    public MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_play);

        start = findViewById(R.id.btn_start);
        exit = findViewById(R.id.btn_exit);

        backsound = MediaPlayer.create(Play.this, R.raw.bs3);
        backsound.start();
        backsound.setLooping(true);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(Play.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                exit.startAnimation(myAnim);
                backsound.stop();

                Intent intent = new Intent(Play.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer ring= MediaPlayer.create(Play.this, R.raw.klik);
                ring.start();
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                start.startAnimation(myAnim);
                backsound.stop();

                Intent intent = new Intent(Play.this, Game.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed(){
        Intent a = new Intent(Play.this, MainActivity.class);
        startActivity(a);
        finish();
        backsound.stop();
        backsound.release();
    }
}
