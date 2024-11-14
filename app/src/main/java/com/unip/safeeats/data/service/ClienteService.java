package com.unip.safeeats.data.service;

/*
* Classe para manipulação de dados
* */

import com.unip.safeeats.data.model.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteService {
    // Create (POST)
    @POST("Cliente")
    Call<Cliente> criarCliente(@Body Cliente cliente);

    // Read (GET)
    @GET("Cliente/{id}")
    Call<Cliente> obterCliente(@Path("id") int id);

    // Update (PUT)
    @PUT("Cliente/{id}")
    Call<Cliente> atualizarCliente(@Path("id") int id, @Body Cliente cliente);

    // Delete (DELETE)
    @DELETE("Cliente/{id}")
    Call<Void> deletarCliente(@Path("id") int id);

    // Read all (GET)
    @GET("Cliente")
    Call<List<Cliente>> obterTodosClientes();
}
