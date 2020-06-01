/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.beans;

import ec.com.waki.entidades.Catalogo;
import ec.com.waki.util.Constantes;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leo Medina
 */

@ManagedBean(name = "catalogoBean")
@ViewScoped
public class CatalogoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CatalogoBean.class.getName());
//     @EJB(beanName = "CatalogoServiceImpl")
//    private CatalogoService catalogoService;
//     
    private Catalogo registro;
    private List<Catalogo> listadoCatalogo;
    private List<String> listadoTipos;
    
       @PostConstruct
    public void inicializar() {
       
    }
     public String inicializarNuevo(ActionEvent actionEvent) {
        registro = new Catalogo();
        return null;
    }
     public String buscarCatalogo(javax.faces.event.ActionEvent actionEvent) {
//        listadoCatalogo = catalogoService.buscarCatalogo(catalogoBusqueda);
        return null;
    }
     
       public String guardarRegistro() {
           System.out.println(this.registro);
//        try {
//            this.registro.setEstado(Constantes.ESTADO_ACTIVO);
//        
//            if (registro.getCodigo() == null) {
//                commonService.crearRegistro(auditoriaUtil.auditarCreacion(null, Catalogo.class, loginBean, jsonObjetoNuevo, CatalogoBean.class, this.registro));
//                this.commonService.crearRegistro(this.registro);
//                this.registro = new Catalogo();
//                UtilMessages.addInfoMessage("Info", "El registro ha sido creado exitosamente.");
//            } else {
//                commonService.crearRegistro(auditoriaUtil.auditarActualizacion(null, Catalogo.class, loginBean, jsonObjetoAnterior, jsonObjetoNuevo, CatalogoBean.class, registro));
//                UtilMessages.addInfoMessage("Info", "El registro ha sido actualizado exitosamente.");
//                commonService.actualizar(registro);
//            }
//            listadoCatalogo = catalogoService.buscarCatalogo(catalogoBusqueda);
//            inicializarNuevo(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "catalogo";
    } 

    public Catalogo getRegistro() {
        return registro;
    }

    public void setRegistro(Catalogo registro) {
        this.registro = registro;
    }

    public List<Catalogo> getListadoCatalogo() {
        return listadoCatalogo;
    }

    public void setListadoCatalogo(List<Catalogo> listadoCatalogo) {
        this.listadoCatalogo = listadoCatalogo;
    }

    public List<String> getListadoTipos() {
        return listadoTipos;
    }

    public void setListadoTipos(List<String> listadoTipos) {
        this.listadoTipos = listadoTipos;
    }
    
}
