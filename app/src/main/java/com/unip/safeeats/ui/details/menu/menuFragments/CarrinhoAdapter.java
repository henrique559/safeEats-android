package com.unip.safeeats.ui.details.menu.menuFragments;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.unip.safeeats.R;
import com.unip.safeeats.data.DTO.CarrinhoManager;
import com.unip.safeeats.data.model.CarrinhoItem;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {
    private List<CarrinhoItem> carrinhoItems;
    private OnQuantidadeAlteradaListener onQuantidadeAlteradaListener;

    public interface OnQuantidadeAlteradaListener {
        void onQuantidadeAlterada(int position, int novaQuantidade);
        void onRemoverItem(int position);
    }

    public CarrinhoAdapter(List<CarrinhoItem> carrinhoItems, OnQuantidadeAlteradaListener listener) {
        this.carrinhoItems = carrinhoItems;
        this.onQuantidadeAlteradaListener = listener;
    }

    @Override
    public CarrinhoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_carrinho_list, parent, false);
        return new CarrinhoViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(CarrinhoViewHolder holder, int position) {
        CarrinhoItem item = carrinhoItems.get(position);
        holder.nomeProduto.setText(item.getProduto().getNome());
        holder.precoUnitario.setText(String.format("R$ %.2f", item.getPrecoUnitario()));
        holder.quantidade.setText(String.valueOf("Quantidade: " + item.getQuantidade()));
        holder.imagemProduto.setImageResource(item.getProduto().getImgID());
        holder.subtotal.setText(String.format("R$ %.2f", item.getSubtotal()));

        // Ação de adicionar quantidade
        holder.botaoAdicionar.setOnClickListener(v -> {
            int novaQuantidade = item.getQuantidade() + 1;
            item.setQuantidade(novaQuantidade);
            item.setSubtotal(item.getPrecoUnitario() * novaQuantidade);
            CarrinhoManager.saveCarrinhoItems(v.getContext(), carrinhoItems);  // Salva a lista atualizada
            notifyItemChanged(position);
            onQuantidadeAlteradaListener.onQuantidadeAlterada(position, novaQuantidade);
        });

        // Ação de remover quantidade
        holder.botaoRemover.setOnClickListener(v -> {
            if (item.getQuantidade() >= 0) {
                int novaQuantidade = item.getQuantidade() - 1;
                item.setQuantidade(novaQuantidade);
                item.setSubtotal(item.getPrecoUnitario() * novaQuantidade);
                CarrinhoManager.saveCarrinhoItems(v.getContext(), carrinhoItems);  // Salva a lista atualizada
                notifyItemChanged(position);
                onQuantidadeAlteradaListener.onQuantidadeAlterada(position, novaQuantidade);

                if(item.getQuantidade() == 0){
                    CarrinhoManager.removeCarrinhoItems(v.getContext(), item);
                }
            }
        });

        // Ação de remover item do carrinho
        holder.botaoRemoverItem.setOnClickListener(v -> {
            carrinhoItems.remove(position);
            CarrinhoManager.saveCarrinhoItems(v.getContext(), carrinhoItems);  // Salva a lista atualizada
            notifyItemRemoved(position);
            onQuantidadeAlteradaListener.onRemoverItem(position);
        });
    }

    @Override
    public int getItemCount() {
        return carrinhoItems.size();
    }

    public static class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto, precoUnitario, quantidade, subtotal;
        ImageView imagemProduto;
        Button botaoAdicionar, botaoRemover, botaoRemoverItem;

        public CarrinhoViewHolder(View itemView) {
            super(itemView);

            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            precoUnitario = itemView.findViewById(R.id.precoUnitario);
            quantidade = itemView.findViewById(R.id.quantidade);
            subtotal = itemView.findViewById(R.id.subtotal);
            imagemProduto = itemView.findViewById(R.id.imagemProduto);
            botaoAdicionar = itemView.findViewById(R.id.botaoAdicionar);
            botaoRemover = itemView.findViewById(R.id.botaoRemover);
            botaoRemoverItem = itemView.findViewById(R.id.botaoRemoverItem);
        }
    }
}
