/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leoz3
 */
@Entity
@Table(name = "catalogo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catalogo.findAll", query = "SELECT c FROM Catalogo c")
    , @NamedQuery(name = "Catalogo.findByCodigo", query = "SELECT c FROM Catalogo c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Catalogo.findByEstado", query = "SELECT c FROM Catalogo c WHERE c.estado = :estado")
    , @NamedQuery(name = "Catalogo.findByEtiqueta", query = "SELECT c FROM Catalogo c WHERE c.etiqueta = :etiqueta")
    , @NamedQuery(name = "Catalogo.findByValor", query = "SELECT c FROM Catalogo c WHERE c.valor = :valor")
    , @NamedQuery(name = "Catalogo.findByTipo", query = "SELECT c FROM Catalogo c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Catalogo.findByDescripcion", query = "SELECT c FROM Catalogo c WHERE c.descripcion = :descripcion")})
public class Catalogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "etiqueta")
    private String etiqueta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor")
    private String valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoArticulo", fetch = FetchType.LAZY)
    private List<StockArticulo> stockArticuloList;
    @OneToMany(mappedBy = "promo", fetch = FetchType.LAZY)
    private List<StockArticulo> stockArticuloList1;
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Articulo> articuloList;
    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    private List<Articulo> articuloList1;
    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
    private List<Articulo> articuloList2;

    public Catalogo() {
    }

    public Catalogo(Integer codigo) {
        this.codigo = codigo;
    }

    public Catalogo(Integer codigo, int estado, String etiqueta, String valor, String tipo) {
        this.codigo = codigo;
        this.estado = estado;
        this.etiqueta = etiqueta;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<StockArticulo> getStockArticuloList() {
        return stockArticuloList;
    }

    public void setStockArticuloList(List<StockArticulo> stockArticuloList) {
        this.stockArticuloList = stockArticuloList;
    }

    @XmlTransient
    public List<StockArticulo> getStockArticuloList1() {
        return stockArticuloList1;
    }

    public void setStockArticuloList1(List<StockArticulo> stockArticuloList1) {
        this.stockArticuloList1 = stockArticuloList1;
    }

    @XmlTransient
    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    @XmlTransient
    public List<Articulo> getArticuloList1() {
        return articuloList1;
    }

    public void setArticuloList1(List<Articulo> articuloList1) {
        this.articuloList1 = articuloList1;
    }

    @XmlTransient
    public List<Articulo> getArticuloList2() {
        return articuloList2;
    }

    public void setArticuloList2(List<Articulo> articuloList2) {
        this.articuloList2 = articuloList2;
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
        if (!(object instanceof Catalogo)) {
            return false;
        }
        Catalogo other = (Catalogo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.waki.entidades.Catalogo[ codigo=" + codigo + " ]";
    }
    
}
