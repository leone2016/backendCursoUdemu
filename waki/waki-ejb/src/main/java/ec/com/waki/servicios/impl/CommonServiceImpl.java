/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.servicios.impl;

import ec.com.waki.servicios.CommonService;
import ec.com.waki.util.UtilEjb;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author leoz3
 */
@Stateless
@LocalBean
public class CommonServiceImpl implements CommonService {

    @PersistenceContext(unitName = "waki_ejbPU")
    private EntityManager em;

    private final Logger LOG = Logger.getLogger(CommonServiceImpl.class.getName());

    public <T> List<T> findAll(Class<T> clazz) {
        return (List<T>) em.createQuery("SELECT p FROM " + clazz.getSimpleName() + " p", clazz).getResultList();
    }

    public <T> List<T> findAllACT(Class<T> clazz) {
        return (List<T>) em.createQuery("SELECT p FROM " + clazz.getSimpleName() + " p WHERE p.estado = 1", clazz).getResultList();
    }

    public <T> T findById(Class<T> clazz, Serializable id) {
        return em.find(clazz, id);
    }

    public boolean crearRegistro(Object object) {
        boolean retorno = false;
        try {
            em.persist(object);
            retorno = true;
        } catch (Exception e) {
            UtilEjb.imprimirMensajeError("Error en el servicio crear Registro", e, LOG);
        }
        return retorno;
    }

    public boolean actualizar(Object object) {
        boolean retorno = false;
        try {
            em.merge(object);
            retorno = true;
        } catch (Exception e) {
            UtilEjb.imprimirMensajeError("Error en el servicio actualizar Registro", e, LOG);
        }
        return retorno;
    }

    public boolean eliminar(Object object) {
        boolean retorno = false;
        try {
            object = em.merge(object);
            em.remove(object);
            retorno = true;
        } catch (Exception e) {
            UtilEjb.imprimirMensajeError("Error en el servicio eliminar Registro", e, LOG);
        }
        return retorno;
    }

}
