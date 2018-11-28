/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.servicios.impl;

import ec.com.waki.entidades.Articulo;
import ec.com.waki.entidades.StockArticulo;
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
public class StockArticuloServiceImpl implements StockArticuloService{
    @PersistenceContext(unitName = "waki_ejbPU")
    private EntityManager em;

    private final Logger LOG = Logger.getLogger(StockArticuloServiceImpl.class.getName());

    @Override
    public List<StockArticulo> buscarArticuloStock(String estadoArticulo) {
          List<StockArticulo> retorno = new ArrayList<>();
        try {
            String consulta = "SELECT s FROM StockArticulo s JOIN FETCH s.articulo.genero JOIN FETCH s.articulo.color WHERE s.estado = 1 and s.estadoArticulo.codigo in ("+estadoArticulo+") and  :fechaActual > s.visibleDesde AND s.visibleHasta > :fechaActual order by s.articulo.codigoImportacion asc";
            Query query = em.createQuery(consulta);
            query.setParameter("fechaActual", DateUtil.tomarFechaActual());
//and  :fechaActual > s.visibleDesde AND s.visibleDesde > :fechaActual
            retorno = query.getResultList();
        } catch (Exception e) {

            UtilEjb.imprimirMensajeError("Error en el servicio StockArticuloServiceImpl - buscarArticuloStock()  ", e, LOG);
        }
        return retorno;
    }
    
    @Override
    public List<StockArticulo> verificarArticuloStock(String articulos) {
          List<StockArticulo> retorno = new ArrayList<>();
        try {
            String consulta = "SELECT s FROM StockArticulo s JOIN FETCH s.articulo.genero JOIN FETCH s.articulo.color WHERE s.estado = 1 and s.articulo.codigo in ("+articulos+") order by s.articulo.codigoImportacion asc";
            Query query = em.createQuery(consulta);
            retorno = query.getResultList();
        } catch (Exception e) {
            UtilEjb.imprimirMensajeError("Error en el servicio StockArticuloServiceImpl - verificarArticuloStock()  ", e, LOG);
        }
        return retorno;
    }
}
