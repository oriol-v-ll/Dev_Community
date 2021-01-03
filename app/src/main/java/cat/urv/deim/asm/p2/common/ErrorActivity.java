package cat.urv.deim.asm.p2.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
    }

    public void tryAgain(View view) {
        Intent intent = new Intent(this, MainActivity.class);//Lo tenemos que enviar al main anonimo
        startActivity(intent);
        finish();
    }
}


