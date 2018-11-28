/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.servicios;


import ec.com.waki.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author leoz3
 */
@Local
public interface UsuarioService {
  
     public Usuario buscarUsuario(String email, String contrasenia) ;
     
     
}
