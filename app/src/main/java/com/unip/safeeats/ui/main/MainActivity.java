package com.unip.safeeats.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.unip.safeeats.R;
import com.unip.safeeats.data.remote.ApiService;
import com.unip.safeeats.data.remote.RetrofitClient;
import com.unip.safeeats.ui.details.cadastro.telaCadastro1;
import com.unip.safeeats.ui.details.telaLogin;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        abrirTelaCadastro(findViewById(R.id.mainBotaoRegistro));
        abrirTelaCadastro(findViewById(R.id.mainBotaoLogin));

    }
    public void abrirTelaCadastro(View v){
        ((Button) v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, telaCadastro1.class);
                startActivity(intent);
            }
        });
    }

    public void abrirTelaLogin(View v){
        ((Button) v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, telaLogin.class);
                startActivity(intent);
            }
        });

    }
}