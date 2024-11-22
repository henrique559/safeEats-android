package com.unip.safeeats.ui.details.menu.produtoFragments.ListaProdutos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.Produto;
import com.unip.safeeats.ui.details.menu.produtoFragments.ListaProdutos.placeholder.PlaceholderContent.PlaceholderItem;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ListaProdutosAdapter extends RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder> {

    private final List<Produto> mValues;
    private OnProdutoClickListener listener;

    public interface OnProdutoClickListener{
        void onProdutoClick(Produto produto);
    }

    public ListaProdutosAdapter(List<Produto> items, OnProdutoClickListener listener) {
        mValues = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item2, parent, false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produto produto = mValues.get(position);
        holder.bind(produto, listener);
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nome;
        public final TextView descricao;
        public final TextView preco;
        public final ImageView produtoImagem;
        public final TextView quantidade;
        public TextView botao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.produtoNome);
            descricao = itemView.findViewById(R.id.produtoDescricao);
            preco = itemView.findViewById(R.id.produtoPreco);
            botao = itemView.findViewById(R.id.comprarButton);
            quantidade = itemView.findViewById(R.id.produtoEstoque);
            produtoImagem = itemView.findViewById(R.id.produtoImagem);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Produto produto, OnProdutoClickListener listener){
            NumberFormat formatador = NumberFormat.getInstance(new Locale("pt", "BR"));
            formatador.setMinimumFractionDigits(2);

            String valor = formatador.format(produto.getPreco());

            nome.setText(produto.getNome());
            preco.setText("R$" + valor);
            descricao.setText(produto.getDescricao());
            quantidade.setText("Em estoque: " + produto.getQuantidade().toString());
            produtoImagem.setImageResource(produto.getImgID());
            botao.setOnClickListener(v -> listener.onProdutoClick(produto));
        }

    }
}