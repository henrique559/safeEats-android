package com.unip.safeeats.ui.details.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.unip.safeeats.R;
import com.unip.safeeats.data.model.LoginResponse;
import com.unip.safeeats.ui.details.menu.menuFragments.CarrinhoFragment;
import com.unip.safeeats.ui.details.menu.menuFragments.menuUsuario;
import com.unip.safeeats.ui.details.menu.produtoFragments.ListaProdutos.ListaProdutosFragment;

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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new ListaProdutosFragment())
                .commit();

        Intent intent = getIntent();
        LoginResponse usuarioLogado = (LoginResponse) intent.getSerializableExtra("usuarioLogado");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        Toast.makeText(this, usuarioLogado.getCliente().toString() + "\n" + usuarioLogado.getToken(), Toast.LENGTH_SHORT).show();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            int itemId = item.getItemId();


            if (itemId == R.id.nav_menu) {
                selected = new ListaProdutosFragment();
            } else if (itemId == R.id.nav_cart) {
                selected = new CarrinhoFragment();
            } else if (itemId == R.id.nav_user_data) {
                selected = new menuUsuario(usuarioLogado.getCliente());
            }

            if (selected != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, selected)
                        .commit();

            }
            return true;
        });
    }
}
