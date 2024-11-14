package com.unip.safeeats.data.service;

/*
* Classe para manipulação de dados
* */

import com.unip.safeeats.data.model.Endereco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EnderecoService {
    // Create (POST)
    @POST("Endereco")
    Call<Endereco> criarEndereco(@Body Endereco endereco);

    // Read (GET)
    @GET("Endereco/{id}")
    Call<Endereco> obterEndereco(@Path("id") int id);

    // Update (PUT)
    @PUT("Endereco/{id}")
    Call<Endereco> atualizarEndereco(@Path("id") int id, @Body Endereco endereco);

    // Delete (DELETE)
    @DELETE("Endereco/{id}")
    Call<Void> deletarEndereco(@Path("id") int id);

    // Read all (GET)
    @GET("Endereco")
    Call<List<Endereco>> obterTodosEnderecos();
}
