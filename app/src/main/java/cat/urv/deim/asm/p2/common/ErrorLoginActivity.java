package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ErrorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_login);

    }

    public void try_again(View view){
        Intent intent = new Intent(this, LoginActivity.class);//Lo tenemos que enviar al main anonimo
        startActivity(intent);
        finish();
    }
}
