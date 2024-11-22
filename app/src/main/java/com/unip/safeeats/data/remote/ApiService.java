package com.unip.safeeats.data.remote;



import com.unip.safeeats.data.DTO.LoginDTO;
import com.unip.safeeats.data.DTO.RegistroDTO;
import com.unip.safeeats.data.model.Cliente;
import com.unip.safeeats.data.model.Endereco;
import com.unip.safeeats.data.model.LoginResponse;
import com.unip.safeeats.data.model.Produto;
import com.unip.safeeats.data.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/*
Classe criada para declaração de interfaces que servirão como End-point para a API realizar a conexão e fazer operações CRUD
* */
public interface ApiService {


    @POST("Usuario")
    Call<Usuario> criarUsuario(@Body Usuario usuario);

    @POST("Endereco")
    Call<Endereco> criarEndereco(@Body Endereco endereco);

    @POST("Cliente")
    Call<Cliente> criarCliente(@Body Cliente cliente);

    @PUT("Cliente")
    Call<Cliente> modificarCliente(@Body Cliente cliente);

    @POST("Auth/login")
    Call<LoginResponse> login(@Body LoginDTO loginRequest);

    @POST("Auth/registrar")
    Call<Cliente> registrar(@Body RegistroDTO registro);

    @GET("Cliente/email/{email}")
    Call<Cliente> getClientePorEmail(@Path("email") String email);

    @GET("Produto")
    Call<List<Produto>> getProdutos();





}