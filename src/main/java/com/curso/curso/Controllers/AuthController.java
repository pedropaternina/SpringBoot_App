package com.curso.curso.Controllers;

import com.curso.curso.Models.Usuario;
import com.curso.curso.Utils.JwtUtil;
import com.curso.curso.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "usuario/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){



        Usuario usuario_obtenido = usuarioDao.ObtenerUsuarioLogin(usuario);

        if(usuario_obtenido != null){
           String tokenJwt = jwtUtil.create(String.valueOf(usuario_obtenido.getId()),usuario_obtenido.getEmail());
            return tokenJwt;
        }else{
            return "Error";
        }
    }
}
