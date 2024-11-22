package com.unip.safeeats.ui.details.menu.produtoFragments.ListaProdutos;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.CarrinhoItem;
import com.unip.safeeats.data.model.Produto;
import com.unip.safeeats.ui.details.menu.menuFragments.CarrinhoFragment;
import com.unip.safeeats.data.DTO.CarrinhoManager;

import java.text.NumberFormat;
import java.util.Locale;

public class DetalhesProduto extends Fragment {

    private static final String ARG_PRODUTO = "produto";
    private Produto produto;

    public DetalhesProduto() {
    }

    public static DetalhesProduto newInstance(Produto produto){
        DetalhesProduto fragment = new DetalhesProduto();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUTO, produto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            produto = getArguments().getParcelable(ARG_PRODUTO);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhes_produto, container, false);

        TextView nomeProduto = view.findViewById(R.id.nomeProdutoDetalhes);
        TextView precoProduto = view.findViewById(R.id.precoProdutoDetalhes);
        TextView descricaoProduto = view.findViewById(R.id.descricaoProdutoDetalhes);
        TextView disponibilidadeProduto = view.findViewById(R.id.disponibilidadeProdutoDetalhes);
        ImageView imagemProduto = view.findViewById(R.id.imagemProdutoDetalhes);
        Button botaoProduto = view.findViewById(R.id.adicionarCarrinhoButton);

        if (produto != null){

            NumberFormat formatador = NumberFormat.getInstance(new Locale("pt", "BR"));
            formatador.setMinimumFractionDigits(2);

            String valor = formatador.format(produto.getPreco());

            nomeProduto.setText(produto.getNome());
            precoProduto.setText("R$" + valor);
            descricaoProduto.setText(produto.getDescricao());
            imagemProduto.setImageResource(produto.getImgID());
            disponibilidadeProduto.setText("Em estoque: " + produto.getQuantidade().toString());

            botaoProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Cria o item para o carrinho
                    CarrinhoItem carrinhoItem = new CarrinhoItem(1, produto.getPreco(), 0, produto);

                    // Verifica se o fragmento do carrinho está na atividade e o adiciona ao carrinho
                    CarrinhoFragment carrinhoFragment = (CarrinhoFragment) getFragmentManager().findFragmentByTag("CarrinhoFragment");
                    if (carrinhoFragment != null) {
                        carrinhoFragment.adicionarAoCarrinho(carrinhoItem);
                    } else {
                        // Se o fragmento não estiver na mesma atividade, podemos carregar o carrinho ou mostrar erro
                        CarrinhoManager.addCarrinhoItem(getContext(), carrinhoItem);  // Salva o item no CarrinhoManager
                    }
                }
            });
        }
        return view;
    }
}
