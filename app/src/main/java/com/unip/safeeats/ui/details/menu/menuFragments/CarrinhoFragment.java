package com.unip.safeeats.ui.details.menu.menuFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unip.safeeats.R;
import com.unip.safeeats.data.DTO.CarrinhoManager;
import com.unip.safeeats.data.model.CarrinhoItem;
import com.unip.safeeats.ui.details.menu.ResumoFragment.FinalizacaoPedidoFragment;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoFragment extends Fragment implements CarrinhoAdapter.OnQuantidadeAlteradaListener {
    private RecyclerView recyclerView;
    private CarrinhoAdapter adapter;
    private List<CarrinhoItem> carrinhoItems = new ArrayList<>();
    private TextView totalTextView;
    private Button botaoConfirmarPedido;

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCarrinho);
        botaoConfirmarPedido = view.findViewById(R.id.botaoConfirmarPedido);
        totalTextView = view.findViewById(R.id.textTotalFinal);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtém os itens do carrinho a partir do CarrinhoManager (presumindo que o método existe)
        carrinhoItems = CarrinhoManager.getCarrinhoItems(getContext());

        totalTextView.setText(String.format("Total Final: R$ %.2f", CarrinhoManager.getTotal()));

        // Configura o Adapter com os itens do carrinho e a interface de listener
        adapter = new CarrinhoAdapter(carrinhoItems, this);
        recyclerView.setAdapter(adapter);

        botaoConfirmarPedido.setOnClickListener(v -> {
            if(carrinhoItems.isEmpty()){
                Toast.makeText(getContext(), "Por favor, adicione produtos para prosseguir", Toast.LENGTH_SHORT).show();
                return;
            }

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, new FinalizacaoPedidoFragment());
            transaction.addToBackStack(null);
            transaction.commit();


        });

        return view;
    }

    @Override
    public void onQuantidadeAlterada(int position, int novaQuantidade) {
        atualizarTotal();
    }

    @Override
    public void onRemoverItem(int position) {
        atualizarTotal();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void atualizarTotal() {
        double total = 0;
        for (CarrinhoItem item : carrinhoItems) {
            total += item.getSubtotal();
        }
        totalTextView.setText(String.format("Total Final: R$ %.2f", total));
    }

    // Método para adicionar um item ao carrinho
    public void adicionarAoCarrinho(CarrinhoItem carrinhoItem) {
        carrinhoItems.add(carrinhoItem);
        CarrinhoManager.saveCarrinhoItems(getContext(), carrinhoItems);
        adapter.notifyDataSetChanged();
        atualizarTotal();
    }
}
