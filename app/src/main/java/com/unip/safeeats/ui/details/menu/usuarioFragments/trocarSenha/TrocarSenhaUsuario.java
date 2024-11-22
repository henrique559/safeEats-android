package com.unip.safeeats.ui.details.menu.usuarioFragments.trocarSenha;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.Cliente;
import com.unip.safeeats.data.remote.ApiService;
import com.unip.safeeats.data.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrocarSenhaUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrocarSenhaUsuario extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Cliente cliente;
    ApiService apiService = RetrofitClient.getApiService();

    EditText senhaAnterior, novaSenha, confirmacaoSenha;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrocarSenhaUsuario() {
        // Required empty public constructor
    }

    public TrocarSenhaUsuario(Cliente cliente){
       this.cliente = cliente;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrocarSenhaUsuario.
     */
    // TODO: Rename and change types and number of parameters
    public static TrocarSenhaUsuario newInstance(String param1, String param2) {
        TrocarSenhaUsuario fragment = new TrocarSenhaUsuario();
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
        View rootView =  inflater.inflate(R.layout.fragment_trocar_senha_usuario, container, false);

        senhaAnterior = ((EditText) rootView.findViewById(R.id.senhaAnterior));
        novaSenha = ((EditText) rootView.findViewById(R.id.novaSenha));
        confirmacaoSenha = ((EditText) rootView.findViewById(R.id.confirmacaoSenha));

        if(senhaAnterior.getText().toString().equals(cliente.getUsuario().getSenha())){

        }


        return rootView;
    }
}