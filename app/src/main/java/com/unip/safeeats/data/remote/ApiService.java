package com.unip.safeeats.data.remote;

import com.unip.safeeats.data.DTO.LoginDTO;
import com.unip.safeeats.data.DTO.RegistroDTO;
import com.unip.safeeats.data.model.Cliente;
import com.unip.safeeats.data.model.Endereco;
import com.unip.safeeats.data.model.LoginResponse;
import com.unip.safeeats.data.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @POST("Auth/login")
    Call<LoginResponse> login(@Body LoginDTO loginRequest);

    @POST("Auth/registrar")
    Call<Cliente> registrar(@Body RegistroDTO registro);




}