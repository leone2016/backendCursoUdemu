/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leoz3
 */
@Entity
@Table(name = "stock_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockArticulo.findAll", query = "SELECT s FROM StockArticulo s")
    , @NamedQuery(name = "StockArticulo.findByCodigo", query = "SELECT s FROM StockArticulo s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "StockArticulo.findByEstado", query = "SELECT s FROM StockArticulo s WHERE s.estado = :estado")
    , @NamedQuery(name = "StockArticulo.findByTarifa", query = "SELECT s FROM StockArticulo s WHERE s.tarifa = :tarifa")
    , @NamedQuery(name = "StockArticulo.findByTarifaImportacion", query = "SELECT s FROM StockArticulo s WHERE s.tarifaImportacion = :tarifaImportacion")
    , @NamedQuery(name = "StockArticulo.findByFechaRegisto", query = "SELECT s FROM StockArticulo s WHERE s.fechaRegisto = :fechaRegisto")
    , @NamedQuery(name = "StockArticulo.findByUsuarioLogeado", query = "SELECT s FROM StockArticulo s WHERE s.usuarioLogeado = :usuarioLogeado")
    , @NamedQuery(name = "StockArticulo.findByVisibleDesde", query = "SELECT s FROM StockArticulo s WHERE s.visibleDesde = :visibleDesde")
    , @NamedQuery(name = "StockArticulo.findByTarifaVenta", query = "SELECT s FROM StockArticulo s WHERE s.tarifaVenta = :tarifaVenta")
    , @NamedQuery(name = "StockArticulo.findByVisibleHasta", query = "SELECT s FROM StockArticulo s WHERE s.visibleHasta = :visibleHasta")})
public class StockArticulo implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarifa")
    private BigDecimal tarifa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarifa_importacion")
    private BigDecimal tarifaImportacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_registo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegisto;
    @Column(name = "usuario_logeado")
    private Integer usuarioLogeado;
    @Column(name = "visible_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date visibleDesde;
    @Column(name = "tarifa_venta")
    private BigDecimal tarifaVenta;
    @Column(name = "visible_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date visibleHasta;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo articulo;
    @JoinColumn(name = "estado_articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Catalogo estadoArticulo;
    @JoinColumn(name = "promo", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Catalogo promo;

    public StockArticulo() {
    }

    public StockArticulo(Integer codigo) {
        this.codigo = codigo;
    }

    public StockArticulo(Integer codigo, int estado, BigDecimal tarifa, BigDecimal tarifaImportacion, Date fechaRegisto) {
        this.codigo = codigo;
        this.estado = estado;
        this.tarifa = tarifa;
        this.tarifaImportacion = tarifaImportacion;
        this.fechaRegisto = fechaRegisto;
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

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public BigDecimal getTarifaImportacion() {
        return tarifaImportacion;
    }

    public void setTarifaImportacion(BigDecimal tarifaImportacion) {
        this.tarifaImportacion = tarifaImportacion;
    }

    public Date getFechaRegisto() {
        return fechaRegisto;
    }

    public void setFechaRegisto(Date fechaRegisto) {
        this.fechaRegisto = fechaRegisto;
    }

    public Integer getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Integer usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public Date getVisibleDesde() {
        return visibleDesde;
    }

    public void setVisibleDesde(Date visibleDesde) {
        this.visibleDesde = visibleDesde;
    }

    public BigDecimal getTarifaVenta() {
        return tarifaVenta;
    }

    public void setTarifaVenta(BigDecimal tarifaVenta) {
        this.tarifaVenta = tarifaVenta;
    }

    public Date getVisibleHasta() {
        return visibleHasta;
    }

    public void setVisibleHasta(Date visibleHasta) {
        this.visibleHasta = visibleHasta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Catalogo getEstadoArticulo() {
        return estadoArticulo;
    }

    public void setEstadoArticulo(Catalogo estadoArticulo) {
        this.estadoArticulo = estadoArticulo;
    }

    public Catalogo getPromo() {
        return promo;
    }

    public void setPromo(Catalogo promo) {
        this.promo = promo;
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
        if (!(object instanceof StockArticulo)) {
            return false;
        }
        StockArticulo other = (StockArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.waki.entidades.StockArticulo[ codigo=" + codigo + " ]";
    }
    
}
