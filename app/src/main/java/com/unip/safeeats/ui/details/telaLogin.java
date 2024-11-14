package com.unip.safeeats.ui.details;

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
import com.unip.safeeats.data.model.LoginResponse;
import com.unip.safeeats.data.model.Usuario;
import com.unip.safeeats.data.remote.ApiService;
import com.unip.safeeats.data.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class telaLogin extends AppCompatActivity {
    EditText textEmail, textSenha;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textEmail = ((EditText) findViewById(R.id.LoginInputEmail));
        textSenha = ((EditText) findViewById(R.id.LoginInputSenha));

        ((Button) findViewById(R.id.LoginButtonEnviar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail.getText().toString().trim();
                String senha = textSenha.getText().toString().trim();
                Usuario usuario = new Usuario("", email, senha);


            }
        });
    }

    // TODO: Arrumar Request Body para enviar somente email e senha
    // TODO: Retornar um objeto cliente com o email do usuario

//    public LoginResponse fazerLogin(Usuario request){
//        apiService = RetrofitClient.getApiService();
//
//        Call<LoginResponse> call = apiService.login(request);
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                if(response.isSuccessful() && response.body() != null){
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//
//            }
//        });
//
//
//    }

}