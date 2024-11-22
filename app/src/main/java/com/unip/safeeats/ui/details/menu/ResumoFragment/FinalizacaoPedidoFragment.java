package com.unip.safeeats.ui.details.menu.ResumoFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unip.safeeats.R;
import com.unip.safeeats.data.DTO.CarrinhoManager;
import com.unip.safeeats.ui.details.menu.menuFragments.CarrinhoAdapter;
public class FinalizacaoPedidoFragment extends Fragment {

    private Spinner spinnerEndereco;
    private RadioGroup radioGroupPagamento;
    private Button btnFinalizarPedido;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla o layout do fragment
        View view = inflater.inflate(R.layout.fragment_finalizacao_pedido, container, false);

        // Inicializa os componentes do layout
        spinnerEndereco = view.findViewById(R.id.spinnerEndereco);
        ArrayAdapter<CharSequence> arr_adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.enderecos,
                android.R.layout.simple_spinner_item
        );
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEndereco.setAdapter(arr_adapter);

        spinnerEndereco.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String enderecoSelecionado = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), "Selecionado: " + enderecoSelecionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


        radioGroupPagamento = view.findViewById(R.id.radioGroupPagamento);
        btnFinalizarPedido = view.findViewById(R.id.btnFinalizarPedido);

        // Configura o botão de finalizar pedido
        btnFinalizarPedido.setOnClickListener(v -> finalizarPedido());

        // Inicializa o RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCarrinho);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ResumoAdapter adapter = new ResumoAdapter(CarrinhoManager.getCarrinhoItems(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    /**
     * Método chamado ao clicar no botão de finalizar pedido.
     */
    private void finalizarPedido() {
        // Verifica se um endereço foi selecionado
        if (spinnerEndereco.getSelectedItem() == null) {
            Toast.makeText(getContext(), "Por favor, selecione um endereço.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se uma forma de pagamento foi selecionada
        int selectedPagamentoId = radioGroupPagamento.getCheckedRadioButtonId();
        if (selectedPagamentoId == -1) {
            Toast.makeText(getContext(), "Por favor, selecione uma forma de pagamento.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obter a forma de pagamento selecionada
        RadioButton selectedPagamento = getView().findViewById(selectedPagamentoId);
        String formaPagamento = selectedPagamento.getText().toString();

        String enderecoSelecionado = spinnerEndereco.getSelectedItem().toString();
        Toast.makeText(
                getContext(),
                "Pedido finalizado!\nEndereço: " + enderecoSelecionado + "\nPagamento: " + formaPagamento,
                Toast.LENGTH_LONG
        ).show();

    }
}
