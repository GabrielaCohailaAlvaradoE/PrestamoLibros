package com.pretamo.PrestamoLibro.service;

import com.pretamo.PrestamoLibro.model.Usuario;
import java.util.List;

public interface UsuarioService {

    List<Usuario> obtenerTodos();

    Usuario obtenerPorId(Long id);

    Usuario crear(Usuario usuario);

    Usuario actualizar(Long id, Usuario usuario);

    void eliminar(Long id);
}