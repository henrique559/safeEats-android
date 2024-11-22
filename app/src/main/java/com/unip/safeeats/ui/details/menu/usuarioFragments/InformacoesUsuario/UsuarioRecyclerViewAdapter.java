package com.unip.safeeats.ui.details.menu.usuarioFragments.InformacoesUsuario;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unip.safeeats.R;
import com.unip.safeeats.data.model.Cliente;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class UsuarioRecyclerViewAdapter extends RecyclerView.Adapter<UsuarioRecyclerViewAdapter.ViewHolder> {

    private final List<Cliente> mValues;

    public UsuarioRecyclerViewAdapter(List<Cliente> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_info_user, parent, false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Cliente cliente = mValues.get(position);
        holder.nomeTextView.setText("Nome: " + cliente.getUsuario().getNome());
        holder.emailTextView.setText("Email: " + cliente.getUsuario().getEmail());
        holder.telefoneTextView.setText("Telefone: " + cliente.getTelefone());
        holder.CPFTextView.setText("CPF: " + cliente.getCpf());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nomeTextView;
        public final TextView emailTextView;
        public final TextView telefoneTextView;
        public final TextView CPFTextView;
        public final TextView EnderecoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nomeTextView = itemView.findViewById(R.id.itemNome);
            this.emailTextView = itemView.findViewById(R.id.itemEmail);
            this.telefoneTextView = itemView.findViewById(R.id.itemTelefone);
            CPFTextView = itemView.findViewById(R.id.itemCPF);
            EnderecoTextView = itemView.findViewById(R.id.itemEndereco);
        }

    }
}