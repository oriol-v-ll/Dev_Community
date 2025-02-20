package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TutorialActivity extends AppCompatActivity {
    private final int DURACION_FOTO = 4000; // 4 segundos
    private final int DURACION_FOTO_2 = 6000; // 8 segundos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        final ProgressBar tutorialbar = this.findViewById(R.id.progressBar);

        //Linea para borrar
        /*----------Definición de cambio de imágenes y texto -----------------------*/
        //Definimos la imagen con android para poder variarla
        final ImageView image = (ImageView) findViewById(R.id.help_2);
        image.setImageResource(R.drawable.help_2);
        //Definimos el texto de la imagen para que quede concorde con las especificaciones
        final TextView text = (TextView) findViewById(R.id.Texto_tutorial);
        text.setText(R.string.be_in_touch);
        tutorialbar.setProgress(33);

        //El cambio a la primera imagen
        new Handler().postDelayed(new Runnable(){
            public void run(){
                image.setImageResource(R.drawable.help_3);
                //Que cambie el texto que nos interesa
                text.setText(R.string.networking);
                tutorialbar.setProgress(66);

            };
        }, DURACION_FOTO);

        //El cambio a la segunda imagen
        new Handler().postDelayed(new Runnable(){
            public void run(){
                image.setImageResource(R.drawable.help_1);
                //Que cambie el texto que nos interesa
                text.setText(R.string.learn);
                tutorialbar.setProgress(100);

            };
        }, DURACION_FOTO_2);

        /*----------Final definición de cambio de imágenes y texto -----------------------*/

        /*----------Hacer que el botón funcione correctamente-----------------------------*/
        ImageView skipTutorial = (ImageView) findViewById(R.id.skip_tutorial);
        skipTutorial.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(TutorialActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        });
        /*----------Final hacer que el botón funcione correctamente-----------------------------*/

        new Handler().postDelayed(new Runnable(){
            public void run(){

                Intent intent = new Intent(TutorialActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            };
        }, 10000);




    }

}
