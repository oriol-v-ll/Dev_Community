package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ProfileActivity extends MainActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getApplicationContext().getSharedPreferences("MyPref",
                0);

        boolean anonym = pref.getBoolean("anonym", true);

        //cuando es true es que es anonimo

        if (anonym){

            //Al ser anónimo se tiene que volver al menú de login.
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

        else{ //Metodo registrado.

            setContentView(R.layout.activity_profile);
            Toolbar toolbar = findViewById(R.id.toolbar_prof);
            setSupportActionBar(toolbar);
            //El metodo setDiplayHomeUpEnabled pone la flecha pero al hacer click lo quita de cualquier elemento UI.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //Ponemos un listener en el objeto de la flecha hacia atras para que vaya un paso atras
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // perform whatever you want on back arrow click
                    Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Falta inflater de menú.
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_no_search, menu);
        return true;
    }

}
