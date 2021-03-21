package com.example.justchatting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText entradaEmail, entradaPass;
    String lEmail;
    String lPass;
    TextView forgot_password;
    private Button btnLogIn, btn_registro;
    private FirebaseAuth fautenticacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        fautenticacion = FirebaseAuth.getInstance();

        entradaEmail = (EditText) findViewById(R.id.entrada_email);
        entradaPass = (EditText) findViewById(R.id.entrada_pass);
        btnLogIn = (Button) findViewById(R.id.btn_login);
        btn_registro = (Button) findViewById(R.id.btn_registro);
        forgot_password = findViewById(R.id.forgot_password);




        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(MainActivity.this, forgotActivity.class));
                Toast.makeText(MainActivity.this, "Función no implementada todavía",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lEmail = entradaEmail.getText().toString().trim();
                lPass = entradaPass.getText().toString().trim();
                if (!TextUtils.isEmpty(lEmail) && !TextUtils.isEmpty(lPass)) {

                    logIn(lEmail, lPass);


                } else {
                    Toast.makeText(MainActivity.this, "Falta algún campo por rellenar, no ha sido posible iniciar sesión",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent regIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(regIntent);

            }
        });


    }


    private void logIn(String email, String password) {
        fautenticacion.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Login efectuado con éxito!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}