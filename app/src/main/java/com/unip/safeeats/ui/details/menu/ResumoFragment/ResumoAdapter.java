package com.unip.safeeats.ui.details.menu.ResumoFragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.CarrinhoItem;

import java.util.List;

public class ResumoAdapter extends RecyclerView.Adapter<ResumoAdapter.CarrinhoViewHolder> {

    private List<CarrinhoItem> listaItens;

    public ResumoAdapter(List<CarrinhoItem> listaItens) {
        this.listaItens = listaItens;
    }

    @NonNull
    @Override
    public CarrinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_finalizacao, parent, false);
        return new CarrinhoViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull CarrinhoViewHolder holder, int position) {
        CarrinhoItem item = listaItens.get(position);
        holder.tvNomeProduto.setText(item.getProduto().getNome());
        holder.tvQuantidadeProduto.setText("Qtd: " + item.getQuantidade());
        holder.tvPrecoProduto.setText("R$ " + String.format("%.2f", item.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return listaItens.size();
    }

    static class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeProduto, tvQuantidadeProduto, tvPrecoProduto;

        public CarrinhoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeProduto = itemView.findViewById(R.id.tvNomeProduto);
            tvQuantidadeProduto = itemView.findViewById(R.id.tvQuantidadeProduto);
            tvPrecoProduto = itemView.findViewById(R.id.tvPrecoProduto);
        }
    }
}
