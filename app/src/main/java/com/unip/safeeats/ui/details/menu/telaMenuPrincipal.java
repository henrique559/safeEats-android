package com.unip.safeeats.ui.details.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.LoginResponse;
import com.unip.safeeats.data.remote.ApiService;

public class telaMenuPrincipal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_menu_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        LoginResponse usuarioLogado = (LoginResponse) intent.getSerializableExtra("usuarioLogado");

        assert usuarioLogado != null;
        Toast.makeText(this, usuarioLogado.getCliente().toString() + "\n" + usuarioLogado.getToken(), Toast.LENGTH_SHORT).show();
    }

}