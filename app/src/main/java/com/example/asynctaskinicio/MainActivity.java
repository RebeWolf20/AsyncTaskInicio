package com.example.asynctaskinicio;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button Iniciar;
    private EditText user, password;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Iniciar=findViewById(R.id.btn_inicio);
        user=findViewById(R.id.et_usuario);
        password=findViewById(R.id.et_password);
        pb=findViewById(R.id.pb);

        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute(user.getText().toString());
            }
        });
    }

    public class Task extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return strings[0];
        }

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            Iniciar.setEnabled(false);
        }

        @Override
        protected void onPostExecute(String s) {
            pb.setVisibility(View.VISIBLE);
            Iniciar.setEnabled(true);
            Intent intent = new Intent(MainActivity.this, Resultado.class);
            intent.putExtra("Usuario", user.getText().toString());
            startActivity(intent);
        }
    }
}