package com.curso.curso.Controllers;

import com.curso.curso.Models.Usuario;
import com.curso.curso.Utils.JwtUtil;
import com.curso.curso.dao.UsuarioDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Usuario_Cotroller {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "usuario/{id}", method = RequestMethod.GET)
    public Usuario get(@PathVariable  int id, @RequestHeader(value = "Authorization")String token){
        if(!validarToken(token)){return null;}
       return  usuarioDao.getUsuarioById(id);
    }

    @RequestMapping(value = "usuario")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization")String token){

        if(!validarToken(token)){return null;}
        return  usuarioDao.getUsuario();
    }


    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return  usuarioId != null;

    }

    @RequestMapping(value = "usuario", method = RequestMethod.POST)
    public void createUsuarios(@RequestBody  Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        String hash = argon2.hash(1,1024,1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.crearUsuario(usuario);
    }


    @RequestMapping(value = "usuario/editar")
    public Usuario editar(@RequestHeader(value = "Authorization")String token){
        if(!validarToken(token)){return null;}
        Usuario usuario = new Usuario();
        usuario.setNombres("Pedro");
        usuario.setApellidos("Apellido");
        usuario.setTelefono("310385124");
        usuario.setEmail("ginzu200@gmail.com");
        usuario.setPassword("123");

        return usuario;
    }
    @RequestMapping(value = "usuario/delete/{id}", method = RequestMethod.DELETE)
    public String eliminar(@PathVariable int id,@RequestHeader(value = "Authorization")String token){
        if(!validarToken(token)){return null;}
        usuarioDao.eliminar(id);
        return "Usuario eliminado con exito";
    }
    
}
