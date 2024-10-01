package com.curso.curso.dao;

import com.curso.curso.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Usuario> getUsuario() {
       String query = "FROM Usuario";
       return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(int id) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public Usuario getUsuarioById(int id) {
       return entityManager.find(Usuario.class,id);
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario ObtenerUsuarioLogin(Usuario usuario) {


        String query = "FROM Usuario WHERE email = :email ";

         List<Usuario> lista = entityManager.createQuery(query).
                 setParameter("email", usuario.getEmail()).
                 getResultList();

         if(lista.isEmpty()){
             return null;
         }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);


         if( argon2.verify(passwordHashed,usuario.getPassword())){
             return lista.get(0);
         }
        return null;
    }


}
