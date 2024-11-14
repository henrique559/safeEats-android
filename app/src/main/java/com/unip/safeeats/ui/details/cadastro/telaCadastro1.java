package com.unip.safeeats.ui.details.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.Usuario;
import com.unip.safeeats.data.remote.ApiService;

public class telaCadastro1 extends AppCompatActivity {
    EditText inputNome, inputEmail, inputSenha;
    ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inputs

        inputNome = ((EditText) findViewById(R.id.cadastroInputNome));
        inputEmail = ((EditText)findViewById(R.id.cadastroInputEmails));
        inputSenha = ((EditText) findViewById(R.id.cadastroInputSenhas));

        //Botões
        Button buttonEnviar = findViewById(R.id.LoginButtonEnviar);


        // Aciona evento do botão
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = inputNome.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String senha = inputSenha.getText().toString().trim();

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(telaCadastro1.this, "Por favor, preencha com seus dados.", Toast.LENGTH_LONG).show();
                }
                else{
                    Usuario usuario = new Usuario(nome, email,senha);

                    Intent intent = new Intent(telaCadastro1.this, telaCadastro2.class);
                    intent.putExtra("usuario",usuario);

                    startActivity(intent);

                }
            }
        });
    }


}