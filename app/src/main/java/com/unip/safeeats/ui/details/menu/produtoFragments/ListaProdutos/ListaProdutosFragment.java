package com.unip.safeeats.ui.details.menu.produtoFragments.ListaProdutos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.Produto;
import com.unip.safeeats.data.remote.ApiService;
import com.unip.safeeats.data.remote.RetrofitClient;
import com.unip.safeeats.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class ListaProdutosFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<Produto> produto = new ArrayList<>();
    private ApiService apiService;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListaProdutosFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListaProdutosFragment newInstance(int columnCount) {
        ListaProdutosFragment fragment = new ListaProdutosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list2, container, false);

        // Instanciação da API
        apiService = RetrofitClient.getApiService();
        super.onViewCreated(view, savedInstanceState);

        // Configuração do Recycler View
        RecyclerView recyclerView = view.findViewById(R.id.ProdutoList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);




        // Chamada da API
        Call<List<Produto>> call = apiService.getProdutos();
        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if(response.isSuccessful() && response.body() != null){

                    List<Produto> produtos = new ArrayList<>();
                    for(Produto produto_img : response.body()){
                        switch (produto_img.getNome()){
                            case "Alface":
                                produto_img.setImgID(R.drawable.alface);
                                break;
                            case "Tomate Cereja":
                                produto_img.setImgID(R.drawable.tomate);
                                break;
                            case "Manjericão":
                                produto_img.setImgID(R.drawable.manjericao);
                                break;
                            case "Morango":
                                produto_img.setImgID(R.drawable.morango);
                                break;
                            case "Laranja":
                                produto_img.setImgID(R.drawable.laranja);
                                break;
                            default:
                                break;
                        }
                        if(produto_img.getImgID() != null){
                            produtos.add(produto_img);
                        }
                    }
                    ListaProdutosAdapter adapter = new ListaProdutosAdapter(produtos, produto -> {
                        DetalhesProduto fragment = DetalhesProduto.newInstance(produto);
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, fragment)
                                .addToBackStack(null)
                                .commit();
                    });
                    recyclerView.setAdapter(adapter);
                }
                else {
                    Log.d("API_ERROR", "ListaProdutosFragment(): " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                Log.d("API_ERROR", "ListaProdutosFragment(): " + t.getMessage() + "\n" + t.getCause());
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}