/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.servicios.impl;

import ec.com.waki.entidades.Articulo;
import ec.com.waki.entidades.FotoArticulo;
import ec.com.waki.entidades.StockArticulo;
import ec.com.waki.servicios.FotoArticuloService;
import ec.com.waki.util.UtilEjb;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.com.waki.servicios.StockArticuloService;
import ec.com.waki.util.DateUtil;

/**
 *
 * @author leoz3
 */
@Stateless
@LocalBean
public class FotoArticuloServiceImpl implements FotoArticuloService{
    @PersistenceContext(unitName = "waki_ejbPU")
    private EntityManager em;

    private final Logger LOG = Logger.getLogger(FotoArticuloServiceImpl.class.getName());

//    @Override
//    public List<StockArticulo> buscarArticuloStock(String estadoArticulo) {
//          List<StockArticulo> retorno = new ArrayList<>();
//        try {
//            String consulta = "SELECT s FROM StockArticulo s JOIN FETCH s.articulo WHERE s.estado = 1 and s.estadoArticulo.codigo in ("+estadoArticulo+") and  :fechaActual > s.visibleDesde AND s.visibleHasta > :fechaActual order by s.articulo.codigoImportacion asc";
//            Query query = em.createQuery(consulta);
////            query.setParameter("estados", estadoArticulo);
//            query.setParameter("fechaActual", DateUtil.tomarFechaActual());
////and  :fechaActual > s.visibleDesde AND s.visibleDesde > :fechaActual
//            retorno = query.getResultList();
//        } catch (Exception e) {
//
//            UtilEjb.imprimirMensajeError("Error en el servicio StockArticuloServiceImpl - buscarArticuloStock()  ", e, LOG);
//        }
//        return retorno;
//    }

    @Override
    public List<FotoArticulo> buscarFotoArticulo(String articulos) {
       List<FotoArticulo> retorno = new ArrayList<>();
       try {
            String consulta = "SELECT f FROM FotoArticulo f JOIN FETCH f.articulo WHERE f.estado = 1 and f.articulo.codigo in ("+articulos+") order by f.articulo.codigo";
            Query query = em.createQuery(consulta);
            retorno = query.getResultList();
        } catch (Exception e) {
            UtilEjb.imprimirMensajeError("Error en el servicio FotoArticuloServiceImpl - buscarFotoArticulo(articulos)  ", e, LOG);
        }
       return retorno;
    }
}
