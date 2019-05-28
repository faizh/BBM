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

public class Modul extends AppCompatActivity {
    //private Button angka, huruf, buah, hewan, warna, bentuk;
    private ImageButton angka, huruf, buah, hewan, warna, bentuk;
    private MediaPlayer backsound=null, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.education.app.newlogin.R.layout.activity_modul);

        angka = findViewById(R.id.btn_angka);
        huruf = findViewById(R.id.btn_huruf);
        buah = findViewById(R.id.btn_buah);
        hewan = findViewById(R.id.btn_hewan);
        warna = findViewById(R.id.btn_warna);
        bentuk = findViewById(R.id.btn_bentuk);

        angka.setEnabled(false);
        huruf.setEnabled(false);
        buah.setEnabled(false);
        hewan.setEnabled(false);
        warna.setEnabled(false);
        bentuk.setEnabled(false);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        welcome = MediaPlayer.create(Modul.this, R.raw.modul_opening);
        welcome.start();
        backsound = MediaPlayer.create(Modul.this, R.raw.bs2);
        backsound.setLooping(true);
        backsound.start();
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                welcome.release();
                angka.setEnabled(true);
                huruf.setEnabled(true);
                buah.setEnabled(true);
                hewan.setEnabled(true);
                warna.setEnabled(true);
                bentuk.setEnabled(true);
            }
        });
        backsound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                backsound.release();
            }
        });

        angka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(Modul.this, R.raw.klik);
                final MediaPlayer sound= MediaPlayer.create(Modul.this, R.raw.angka_opening);
                backsound.stop();
                ring.start();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
                sound.start();
                sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        sound.release();
                    }
                });
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                angka.startAnimation(myAnim);
                Intent intent = new Intent(Modul.this, SubMainAngka.class);
                Modul.this.startActivity(intent);
                finish();
            }
        });

        huruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(Modul.this, R.raw.klik);
                final MediaPlayer sound= MediaPlayer.create(Modul.this, R.raw.huruf_opening);
                sound.start();
                ring.start();
                backsound.stop();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
                sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        sound.release();
                    }
                });
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                huruf.startAnimation(myAnim);
                Intent intent = new Intent(Modul.this, SubMainHuruf.class);
                Modul.this.startActivity(intent);
                finish();
            }
        });

        buah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(Modul.this, R.raw.klik);
                final MediaPlayer sound= MediaPlayer.create(Modul.this, R.raw.buah_opening);
                sound.start();
                ring.start();
                backsound.stop();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
                sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        sound.release();
                    }
                });
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                buah.startAnimation(myAnim);
                Intent intent = new Intent(Modul.this, SubMainBuah.class);
                Modul.this.startActivity(intent);
                finish();
            }
        });

        hewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(Modul.this, R.raw.klik);
                final MediaPlayer sound= MediaPlayer.create(Modul.this, R.raw.hewan_opening);
                sound.start();
                ring.start();
                backsound.stop();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
                sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        sound.release();
                    }
                });
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                hewan.startAnimation(myAnim);
                Intent intent = new Intent(Modul.this, SubMainHewan.class);
                Modul.this.startActivity(intent);
                finish();
            }
        });

        warna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(Modul.this, R.raw.klik);
                final MediaPlayer sound= MediaPlayer.create(Modul.this, R.raw.warna_opening);
                sound.start();
                ring.start();
                backsound.stop();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
                sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        sound.release();
                    }
                });
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                warna.startAnimation(myAnim);
                Intent intent = new Intent(Modul.this, SubMainWarna.class);
                Modul.this.startActivity(intent);
                finish();
            }
        });

        bentuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer ring= MediaPlayer.create(Modul.this, R.raw.klik);
                final MediaPlayer sound= MediaPlayer.create(Modul.this, R.raw.bentuk_opening);
                sound.start();
                ring.start();
                backsound.stop();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
                sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        sound.release();
                    }
                });
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                bentuk.startAnimation(myAnim);
                Intent intent = new Intent(Modul.this, SubMainBentuk.class);
                Modul.this.startActivity(intent);
            }
        });

    }
    public void onBackPressed(){
        welcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent a = new Intent(Modul.this, MainActivity.class);
                startActivity(a);
                finish();
                backsound.stop();
            }
        });
    }
}