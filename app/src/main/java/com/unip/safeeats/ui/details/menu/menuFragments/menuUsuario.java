package com.unip.safeeats.ui.details.menu.menuFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.Cliente;
import com.unip.safeeats.ui.details.menu.usuarioFragments.InformacoesUsuario.InformacoesUsuarioFragment;
import com.unip.safeeats.ui.details.menu.usuarioFragments.trocarSenha.TrocarSenhaUsuario;
import com.unip.safeeats.ui.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link menuUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class menuUsuario extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Cliente cliente;


    private TextView usernameTextView, emailTextView;
    private ListView optionsListView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public menuUsuario() {
        // Required empty public constructor
    }

    public menuUsuario(Cliente cliente){
        this.cliente = cliente;
    }


    // TODO: Rename and change types and number of parameters
    public static menuUsuario newInstance(String param1, String param2) {
        menuUsuario fragment = new menuUsuario();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu_usuario, container, false);

        usernameTextView = rootView.findViewById(R.id.username);
        emailTextView = rootView.findViewById(R.id.email);
        optionsListView = rootView.findViewById(R.id.options_list);

        setUserInfo(cliente.getUsuario().getNome(), cliente.getUsuario().getEmail());

        String[] options = {"Configurações", "Minhas informações", "Trocar Senha", "Sair"};
        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<>(getContext(), R.layout.item_lista, R.id.item_text, options);
        optionsListView.setAdapter(optionsAdapter);

        optionsListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedOption = (String) parent.getItemAtPosition(position);
            handleOptionClick(selectedOption);
        });

        return rootView;
    }

    private void setUserInfo(String nome, String email){
        usernameTextView.setText(nome);
        emailTextView.setText(email);
    }

    private void handleOptionClick(String options){
        Fragment fragment = null;
        switch (options) {
            case "Configurações":
                // Navegar para tela de configurações
                break;
            case "Minhas informações":
                // Navegar para tela de troca de senha
                fragment = new InformacoesUsuarioFragment(cliente);
                break;
            case "Trocar Senha":
                fragment = new TrocarSenhaUsuario(cliente);
                // Fazer logout ou finalizar sessão
                break;
            case "Sair":
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        
        if(fragment != null){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}