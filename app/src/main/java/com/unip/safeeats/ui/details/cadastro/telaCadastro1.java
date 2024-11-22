package com.unip.safeeats.ui.details.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.Usuario;
import com.unip.safeeats.data.remote.ApiService;
import com.unip.safeeats.util.Regex.InputValidator;

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

        String[] listaTextos = getResources().getStringArray(R.array.requisitos_senha);
        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_lista, R.id.item_text, listaTextos);
        listView.setAdapter(adapter);


        // Aciona evento do botão
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = inputNome.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String senha = inputSenha.getText().toString().trim();

                if(nome.length() < 5){
                    Toast.makeText(telaCadastro1.this, "Por favor, insira um nome valido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!InputValidator.isInputValid(email, InputValidator.emailPattern())){
                    Toast.makeText(telaCadastro1.this, "Por favor, insira um email valido.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(senha.length() < 5){
                    Toast.makeText(telaCadastro1.this, "Por favor, insira uma senha valida.", Toast.LENGTH_SHORT).show();
                    return;

                }

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(telaCadastro1.this, "Por favor, preencha com seus dados.", Toast.LENGTH_SHORT).show();
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