/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.entidades;

import ec.com.waki.util.Constantes;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leoz3
 */
@Entity
@Table(name = "articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findByCodigo", query = "SELECT a FROM Articulo a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Articulo.findByEstado", query = "SELECT a FROM Articulo a WHERE a.estado = :estado")
    , @NamedQuery(name = "Articulo.findByNombre", query = "SELECT a FROM Articulo a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Articulo.findByDescripcionCorta", query = "SELECT a FROM Articulo a WHERE a.descripcionCorta = :descripcionCorta")
    , @NamedQuery(name = "Articulo.findByDescripcionLarga", query = "SELECT a FROM Articulo a WHERE a.descripcionLarga = :descripcionLarga")
    , @NamedQuery(name = "Articulo.findByColor", query = "SELECT a FROM Articulo a WHERE a.color = :color")
    , @NamedQuery(name = "Articulo.findByCodigoImportacion", query = "SELECT a FROM Articulo a WHERE a.codigoImportacion = :codigoImportacion")
    , @NamedQuery(name = "Articulo.findByEmpresa", query = "SELECT a FROM Articulo a WHERE a.empresa = :empresa")
    , @NamedQuery(name = "Articulo.findByFechaRegistro", query = "SELECT a FROM Articulo a WHERE a.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Articulo.findByUsuarioRegistro", query = "SELECT a FROM Articulo a WHERE a.usuarioRegistro = :usuarioRegistro")})
public class Articulo implements Serializable {

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
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion_corta")
    private String descripcionCorta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion_larga")
    private String descripcionLarga;
    @JoinColumn(name = "color", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Catalogo color;
    @Size(max = 2147483647)
    @Column(name = "codigo_importacion")
    private String codigoImportacion;
    @Column(name = "empresa")
    private Integer empresa;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "usuario_registro")
    private Integer usuarioRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<StockArticulo> stockArticuloList;
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<DescripcionArticulo> descripcionArticuloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<Pedido> pedidoList;
    @JoinColumn(name = "categoria", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Catalogo categoria;
    @JoinColumn(name = "genero", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Catalogo genero;
    @JoinColumn(name = "tipo", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Catalogo tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<FotoArticulo> fotoArticuloList;
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<Inventario> inventarioList;
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_importacion")
    private String nombreImportacion;
    @Basic(optional = false)
    @Column(name = "venta_publico")
    private BigDecimal ventaPublico;
    @Basic(optional = false)
    @Column(name = "tarifa_importacion")
    private BigDecimal tarifaImportacion;
    @JoinColumn(name = "material", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Catalogo material;
    
    public Articulo() {
    }

    public Articulo(Integer codigo) {
        this.codigo = codigo;
    }

    public Articulo(Integer codigo, int estado, String nombre, String descripcionCorta, String descripcionLarga) {
        this.codigo = codigo;
        this.estado = estado;
        this.nombre = nombre;
        this.descripcionCorta = descripcionCorta;
        this.descripcionLarga = descripcionLarga;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public String getCodigoImportacion() {
        return codigoImportacion;
    }

    public void setCodigoImportacion(String codigoImportacion) {
        this.codigoImportacion = codigoImportacion;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(Integer usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    @XmlTransient
    public List<StockArticulo> getStockArticuloList() {
        return stockArticuloList;
    }

    public void setStockArticuloList(List<StockArticulo> stockArticuloList) {
        this.stockArticuloList = stockArticuloList;
    }

    @XmlTransient
    public List<DescripcionArticulo> getDescripcionArticuloList() {
        return descripcionArticuloList;
    }

    public void setDescripcionArticuloList(List<DescripcionArticulo> descripcionArticuloList) {
        this.descripcionArticuloList = descripcionArticuloList;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public Catalogo getCategoria() {
        return categoria;
    }

    public void setCategoria(Catalogo categoria) {
        this.categoria = categoria;
    }

    public Catalogo getGenero() {
        return genero;
    }

    public void setGenero(Catalogo genero) {
        this.genero = genero;
    }

    public Catalogo getTipo() {
        return tipo;
    }

    public void setTipo(Catalogo tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<FotoArticulo> getFotoArticuloList() {
        List<FotoArticulo> retornoFoto = new ArrayList<>();
        if(!this.fotoArticuloList.isEmpty()){
                for (FotoArticulo fotoArticulo : this.fotoArticuloList) {
                     if ( fotoArticulo.getEstado()==Constantes.ESTADO_ACTIVO){
                         retornoFoto.add(fotoArticulo);
                     }
                }
        }
        return retornoFoto;
    }

    public void setFotoArticuloList(List<FotoArticulo> fotoArticuloList) {
        this.fotoArticuloList = fotoArticuloList;
    }

    @XmlTransient
    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
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
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.waki.entidades.Articulo[ codigo=" + codigo + " ]";
    }

    public String getNombreImportacion() {
        return nombreImportacion;
    }

    public void setNombreImportacion(String nombreImportacion) {
        this.nombreImportacion = nombreImportacion;
    }

    public BigDecimal getVentaPublico() {
        return ventaPublico;
    }

    public void setVentaPublico(BigDecimal ventaPublico) {
        this.ventaPublico = ventaPublico;
    }

    public BigDecimal getTarifaImportacion() {
        return tarifaImportacion;
    }

    public void setTarifaImportacion(BigDecimal tarifaImportacion) {
        this.tarifaImportacion = tarifaImportacion;
    }

    public Catalogo getColor() {
        return color;
    }

    public void setColor(Catalogo color) {
        this.color = color;
    }

    public Catalogo getMaterial() {
        return material;
    }

    public void setMaterial(Catalogo material) {
        this.material = material;
    }

   
    
}
