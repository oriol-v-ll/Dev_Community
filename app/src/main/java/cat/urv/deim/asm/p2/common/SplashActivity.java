package cat.urv.deim.asm.p2.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        //Declaramos bandera para que entre una sola vez en el tutorial
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        boolean tutorialDone = sharedPref.getBoolean("TUTORIAL_DONE", true);

        if (tutorialDone) {
            //Cambiamos a false despues de que entre por primera vez en el tutorial, ya no entrara mas
            editor.putBoolean("TUTORIAL_DONE", false);
            editor.commit();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, TutorialActivity.class);
                    startActivity(intent);
                    finish();
                }

                ;
            }, DURACION_SPLASH);
        }else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                }

                ;
            }, DURACION_SPLASH);
        }
    }
}
