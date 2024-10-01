package com.curso.curso.dao;

import com.curso.curso.Models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuario();

    void eliminar(int id);

    Usuario getUsuarioById(int id);

    void crearUsuario(Usuario usuario);

    Usuario ObtenerUsuarioLogin(Usuario usuario);
}
