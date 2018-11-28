/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.servicios.impl;

import ec.com.waki.entidades.Articulo;
import ec.com.waki.util.UtilEjb;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.com.waki.servicios.ArticuloService;

/**
 *
 * @author leoz3
 */
@Stateless
@LocalBean
public class ArticuloServiceImpl implements ArticuloService{
    @PersistenceContext(unitName = "waki_ejbPU")
    private EntityManager em;

    private final Logger LOG = Logger.getLogger(ArticuloServiceImpl.class.getName());

    @Override
    public List<Articulo> buscarArticulo() {
          List<Articulo> retorno = new ArrayList<>();
        try {
            String consulta = "SELECT p FROM Articulo p";
            Query query = em.createQuery(consulta);
//            query.setParameter("codigo", empresa.getCodigo());
            retorno = query.getResultList();
        } catch (Exception e) {

            UtilEjb.imprimirMensajeError("Error en el servicio ArticuloServiceImpl - buscarArticulo()  ", e, LOG);
        }
        return retorno;
    }
}
