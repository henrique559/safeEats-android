package com.unip.safeeats.ui.details.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.unip.safeeats.data.DTO.RegistroDTO;
import com.unip.safeeats.data.model.Cliente;
import com.unip.safeeats.data.model.Endereco;
import com.unip.safeeats.data.model.Usuario;
import com.unip.safeeats.data.remote.ApiService;
import com.unip.safeeats.data.remote.RetrofitClient;
import com.unip.safeeats.ui.main.MainActivity;
import com.unip.safeeats.util.Regex.InputValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class telaCadastro3 extends AppCompatActivity {
    EditText inputTelefone, inputCPF, inputDataNascimento;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent OBJ = getIntent();
        Endereco endereco = (Endereco) OBJ.getSerializableExtra("endereco");
        Usuario usuario = (Usuario) OBJ.getSerializableExtra("usuario");

        inputTelefone = ((EditText) findViewById(R.id.cadastroInputTelefone));
        inputCPF = ((EditText) findViewById(R.id.cadastroInputCPF));
        inputDataNascimento = ((EditText) findViewById(R.id.cadastroInputData));

        ((Button) findViewById(R.id.LoginButtonEnviar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String telefone = inputTelefone.getText().toString().trim();
                String cpf = inputCPF.getText().toString().trim();
                String dataNascimento = inputDataNascimento.getText().toString().trim();

                if(!InputValidator.isInputValid(telefone, InputValidator.telefonePattern())){
                    Toast.makeText(telaCadastro3.this, "Por favor, insira um telefone valido.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!InputValidator.isInputValid(cpf, InputValidator.cpfPattern())){
                    Toast.makeText(telaCadastro3.this, "Por favor, insira um CPF valido.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(telefone.isEmpty() || cpf.isEmpty() || dataNascimento.isEmpty() ){
                    Toast.makeText(telaCadastro3.this, "Por favor, insira seus dados", Toast.LENGTH_SHORT).show();
                }
                else{
                    Cliente cliente = new Cliente(usuario, endereco, telefone,cpf);
                    Date date;

                    try{
                        date = format.parse(dataNascimento);
                    } catch (ParseException e) {
                        Toast.makeText(telaCadastro3.this, "Insira a data de nascimento nesse formato: dia/mês/ano", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    assert usuario != null;
                    assert endereco != null;
                    // Declarando um DTO para enviar dados
                    RegistroDTO clienteDados = new RegistroDTO(
                            usuario.getNome(),
                            usuario.getEmail(),
                            usuario.getSenha(),
                            cliente.getCpf(),
                            cliente.getTelefone(),
                            endereco.getRua(),
                            endereco.getNumero(),
                            endereco.getComplemento(),
                            endereco.getCep()
                );
                    Log.d("API_DEBUG", clienteDados.toString());
                    Toast.makeText(telaCadastro3.this, clienteDados.toString(), Toast.LENGTH_SHORT).show();

                    registrarCliente(clienteDados);

                }

            }
        });

    }
    // Metodos de POST
    public void registrarCliente(RegistroDTO registro){
        apiService = RetrofitClient.getApiService();

        Call<Cliente> call = apiService.registrar(registro);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()){
                    Toast.makeText(telaCadastro3.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(telaCadastro3.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(telaCadastro3.this, "Ocorreu um problema durante o cadastro", Toast.LENGTH_SHORT).show();
                    Log.d("API_ERROR", "registrarCliente(): " + response.message());
                }

            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.d("API_ERROR", "registrarCliente(): Erro de autenticação\nException: " + t.getMessage());
            }
        });
    }

}