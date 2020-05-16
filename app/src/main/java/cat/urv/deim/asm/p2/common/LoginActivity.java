package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    private EditText usr, pass;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        usr = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.password);
        pref = getApplicationContext().getSharedPreferences("MyPref",
                0); // 0 - for private mode

        boolean anonym = pref.getBoolean("anonym", false);
        final Button button_anonym = (Button) findViewById(R.id.anonymous);

        button_anonym.setText(R.string.anonymous);

        //cuando es true es que es anonimo
        if (anonym){
            button_anonym.setText(R.string.continue_anonymous);
        }

    }

    /*------Validación de lo que introduce el usuario---------*/
    //Se crea método para poder
    public void register(View view){

        String user = usr.getText().toString();
        String password = pass.getText().toString();
        // Variable para comprobar la entrada anonima o logeada

        //Si el usuario no pone usuario  o contraseña se va al error.
        if ((user.length() == 0)||(password.length()==0)){
            Intent intent = new Intent(LoginActivity.this, ErrorLoginActivity.class);
            startActivity(intent);
            finish();

        }else{ //De momento no se valida con la base de datos si el usuario y contraseña son correctos.
            // Entramos de manera registrada
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("anonym", false); // Storing boolean - true/false
            editor.commit(); // commit changes

            //Se lleva al usuario a la Activity principal.
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

    }
    /*------Final validación de lo que introduce el usuario---------*/

    /*--------Creamos método para el uso anónimo de la app-------*/
    public void anonymous_use(View view){
        // Entramos de manera anonima
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("anonym", true); // Storing boolean - true/false
        editor.commit(); // commit changes

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);//Lo tenemos que enviar al main anonimo
        startActivity(intent);
        finish();
    }
    /*--------Final de creamos método para el uso anónimo de la app-------*/



}
