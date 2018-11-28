/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leoz3
 */
@Entity
@Table(name = "descripcion_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescripcionArticulo.findAll", query = "SELECT d FROM DescripcionArticulo d")
    , @NamedQuery(name = "DescripcionArticulo.findByCodigo", query = "SELECT d FROM DescripcionArticulo d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DescripcionArticulo.findByEstado", query = "SELECT d FROM DescripcionArticulo d WHERE d.estado = :estado")
    , @NamedQuery(name = "DescripcionArticulo.findByDescripcion", query = "SELECT d FROM DescripcionArticulo d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DescripcionArticulo.findByDetalle", query = "SELECT d FROM DescripcionArticulo d WHERE d.detalle = :detalle")})
public class DescripcionArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "estado")
    private Integer estado;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "detalle")
    private String detalle;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulo articulo;

    public DescripcionArticulo() {
    }

    public DescripcionArticulo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionArticulo)) {
            return false;
        }
        DescripcionArticulo other = (DescripcionArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.waki.entidades.DescripcionArticulo[ codigo=" + codigo + " ]";
    }
    
}
