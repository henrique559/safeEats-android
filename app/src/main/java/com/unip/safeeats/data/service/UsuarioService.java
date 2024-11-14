package com.unip.safeeats.data.service;

/*
* Classe para manipulação de dados
* */

import com.unip.safeeats.data.model.Usuario;
import com.unip.safeeats.data.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {
    // Create (POST)
    @POST("Usuario")
    Call<Usuario> criarUsuario(@Body Usuario usuario);

    // Read (GET)
    @GET("Usuario/{id}")
    Call<Usuario> obterUsuario(@Path("id") int id);

    // Update (PUT)
    @PUT("Usuario/{id}")
    Call<Usuario> atualizarUsuario(@Path("id") int id, @Body Usuario usuario);

    // Delete (DELETE)
    @DELETE("Usuario/{id}")
    Call<Void> deletarUsuario(@Path("id") int id);

    // Read all (GET)
    @GET("Usuario")
    Call<List<Usuario>> obterTodosUsuarios();
}
