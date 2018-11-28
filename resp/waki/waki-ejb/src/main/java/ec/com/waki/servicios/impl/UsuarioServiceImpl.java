/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.servicios.impl;

import ec.com.waki.entidades.Usuario;
import ec.com.waki.servicios.UsuarioService;
import ec.com.waki.util.Constantes;
import ec.com.waki.util.UtilEjb;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author leoz3
 */
@Stateless
@LocalBean
public class UsuarioServiceImpl implements UsuarioService{
    @PersistenceContext(unitName = "waki_ejbPU")
    private EntityManager em;

    private final Logger LOG = Logger.getLogger(UsuarioServiceImpl.class.getName());
    
    @Override
    public Usuario buscarUsuario(String email, String contrasenia) {
          Usuario retorno = new Usuario();
        try {
            String consulta = "SELECT f FROM Usuario f WHERE f.mail LIKE :correo AND f.contrasenia LIKE '" + contrasenia + "' AND f.estado =" + Constantes.ESTADO_ACTIVO;
            Query query = em.createQuery(consulta);
            query.setParameter("correo", email);
            if( !query.getResultList().isEmpty()){
             retorno = (Usuario) query.getSingleResult();
            }
           
        } catch (Exception e) {
            UtilEjb.imprimirMensajeError("Error en el servicio UsuarioServiceImpl - buscarUsuario(String email, String contrasenia)  ", e, LOG);
        }
        return retorno;
    }
}
