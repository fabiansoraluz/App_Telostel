package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dawii.dto.UsuarioPrincipal;
import com.dawii.entity.Enlace;
import com.dawii.entity.Usuario;

@Service
public class UserDetailsImp implements UserDetailsService {

    @Autowired
    private UsuarioService SUsuario; 

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Paso 1: Cargar un usuario basado en el nombre de usuario proporcionado
        Usuario user = SUsuario.buscarXUsername(username);

        // Paso 2: Obtener los enlaces relacionados con el usuario a través de su primer rol
        List<Enlace> enlaces = user.getRoles().get(0).getEnlaces();

        // Paso 3: Construir y devolver un objeto UserDetails (en este caso, UsuarioPrincipal)
        return UsuarioPrincipal.build(user, enlaces);
    }
}


