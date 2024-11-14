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
import com.unip.safeeats.data.model.Endereco;
import com.unip.safeeats.data.model.Usuario;
import com.unip.safeeats.data.remote.ApiService;

public class telaCadastro2 extends AppCompatActivity {
    EditText inputEndereco, inputNumero, inputComplemento, inputCEP;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent OBJ = getIntent();
        Usuario usuario = (Usuario) OBJ.getSerializableExtra("usuario");

        inputEndereco = ((EditText) findViewById(R.id.cadastroInputEndereco));
        inputNumero = ((EditText) findViewById(R.id.cadastroInputNumero));
        inputComplemento = ((EditText) findViewById(R.id.cadastroInputComplemento));
        inputCEP = ((EditText) findViewById(R.id.cadastroInputCEP));

        ((Button) findViewById(R.id.LoginButtonEnviar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enderecoStr = inputEndereco.getText().toString().trim();
                String numero = inputNumero.getText().toString().trim();
                String complemento = inputComplemento.getText().toString().trim();
                String cep = inputCEP.getText().toString().trim();

                if(enderecoStr.isEmpty() || numero.isEmpty() || complemento.isEmpty() || cep.isEmpty()){
                    Toast.makeText(telaCadastro2.this, "Por favor, insira seus dados.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Endereco endereco = new Endereco(enderecoStr, numero, complemento, cep);

                    Intent intent = new Intent(telaCadastro2.this, telaCadastro3.class);
                    intent.putExtra("endereco", endereco);
                    intent.putExtra("usuario", usuario);


                    assert usuario != null;

                    startActivity(intent);

                }
            }
        });



        Toast.makeText(this, String.format("%s", usuario.toString()), Toast.LENGTH_SHORT).show();
    }


}