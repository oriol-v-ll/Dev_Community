package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usr, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        usr = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.password);
    }

    /*------Validación de lo que introduce el usuario---------*/
    //Se crea método para poder
    public void register(View view){

        String user = usr.getText().toString();
        String password = pass.getText().toString();

        //Si el usuario no pone usuario  o contraseña se va al error.
        if ((user.length() == 0)||(password.length()==0)){
            Intent intent = new Intent(LoginActivity.this, ErrorLoginActivity.class);
            startActivity(intent);
            finish();

        }else{ //De momento no se valida con la base de datos si el usuario y contraseña son correctos.

            //Se lleva al usuario a la Activity principal.
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

    }
    /*------Final validación de lo que introduce el usuario---------*/

    /*--------Creamos método para el uso anónimo de la app-------*/
    public void anonymous_use(View view){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);//Lo tenemos que enviar al main anonimo
        startActivity(intent);
        finish();
    }
    /*--------Final de creamos método para el uso anónimo de la app-------*/
}
