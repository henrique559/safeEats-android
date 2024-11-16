package com.unip.safeeats.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.unip.safeeats.R;
import com.unip.safeeats.data.DTO.LoginDTO;
import com.unip.safeeats.data.model.LoginResponse;
import com.unip.safeeats.data.remote.ApiService;
import com.unip.safeeats.data.remote.RetrofitClient;
import com.unip.safeeats.ui.details.cadastro.telaCadastro1;
import com.unip.safeeats.ui.details.menu.telaMenuPrincipal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private boolean estaLogado = false;

    EditText textEmail, textSenha;
    ApiService apiService;

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

        textEmail = ((EditText) findViewById(R.id.LoginInputEmail));
        textSenha = ((EditText) findViewById(R.id.LoginInputSenha));

        ((TextView) findViewById(R.id.textoCadastrar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, telaCadastro1.class);
                startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.mainBotaoLogar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail.getText().toString().trim();
                String senha = textSenha.getText().toString().trim();
                LoginDTO usuario = new LoginDTO(email, senha);
                fazerLogin(usuario);
            }
        });
    }
    public void fazerLogin(LoginDTO request){
        apiService = RetrofitClient.getApiService();

        // Chamada da API de login
        Call<LoginResponse> call = apiService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null){

                    // Cria um objeto recebendo a resposta da api, com um cliente e seu token
                    LoginResponse loginResponse = new LoginResponse(response.body().getToken(), response.body().getCliente());

                    if(loginResponse.getCliente() != null){
                        Toast.makeText(MainActivity.this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, telaMenuPrincipal.class);
                        intent.putExtra("usuarioLogado", loginResponse);

                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Ocorreu um problema com o banco de dados.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Login ou senha incorretos", Toast.LENGTH_SHORT).show();
                    Log.d("API_ERROR", "3- fazerLogin(): " + response.message());
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("API_ERROR", " 4 - fazerLogin(): " + t.getMessage());
            }
        });
    }
}