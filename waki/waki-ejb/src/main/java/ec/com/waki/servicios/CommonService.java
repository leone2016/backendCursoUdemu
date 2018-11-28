/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.servicios;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author leoz3
 */
@Local
public interface CommonService {

    /**
     * Metodo que devielve todos registros tipo clazz
     *
     * @param clazz
     * @return listado de objetos
     */
    public <T> List<T> findAll(Class<T> clazz);

    /**
     * Metodo que devielve todos registros tipo clazz
     *
     * @param clazz
     * @return listado de objetos
     */
    public <T> List<T> findAllACT(Class<T> clazz);

    /**
     * Metodo para buscar un objeto por id
     *
     * @param <T>
     * @param clazz
     * @param id
     * @return
     */
    public <T> T findById(Class<T> clazz, Serializable id);

    /**
     * Metodo para crear un registro
     *
     * @param object
     * @return
     */
    public boolean crearRegistro(Object object);

    /**
     * Metodo para actualizar un registro
     *
     * @param object
     * @return
     */
    public boolean actualizar(Object object);

    /**
     * Metodo para eliminar un registro
     *
     * @param object
     * @return
     */
    public boolean eliminar(Object object);

}

