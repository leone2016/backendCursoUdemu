/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leoz3
 */
@Entity
@Table(name = "foto_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FotoArticulo.findAll", query = "SELECT f FROM FotoArticulo f")
    , @NamedQuery(name = "FotoArticulo.findByCodigo", query = "SELECT f FROM FotoArticulo f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FotoArticulo.findByEstado", query = "SELECT f FROM FotoArticulo f WHERE f.estado = :estado")
    , @NamedQuery(name = "FotoArticulo.findByUrl", query = "SELECT f FROM FotoArticulo f WHERE f.url = :url")
    , @NamedQuery(name = "FotoArticulo.findByTipoImagen", query = "SELECT f FROM FotoArticulo f WHERE f.tipoImagen = :tipoImagen")
    , @NamedQuery(name = "FotoArticulo.findByNombreImagen", query = "SELECT f FROM FotoArticulo f WHERE f.nombreImagen = :nombreImagen")
    , @NamedQuery(name = "FotoArticulo.findByFotoPrincipal", query = "SELECT f FROM FotoArticulo f WHERE f.fotoPrincipal = :fotoPrincipal")
    , @NamedQuery(name = "FotoArticulo.findByFechaRegistro", query = "SELECT f FROM FotoArticulo f WHERE f.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "FotoArticulo.findByUsuarioLogeado", query = "SELECT f FROM FotoArticulo f WHERE f.usuarioLogeado = :usuarioLogeado")})
public class FotoArticulo implements Serializable {

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
    @Size(max = 2147483647)
    @Column(name = "url")
    private String url;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @Size(max = 2147483647)
    @Column(name = "tipo_imagen")
    private String tipoImagen;
    @Size(max = 2147483647)
    @Column(name = "nombre_imagen")
    private String nombreImagen;
    @Column(name = "foto_principal")
    private Boolean fotoPrincipal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "usuario_logeado")
    private Integer usuarioLogeado;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo articulo;

    public FotoArticulo() {
    }

    public FotoArticulo(Integer codigo) {
        this.codigo = codigo;
    }

    public FotoArticulo(Integer codigo, int estado, Date fechaRegistro) {
        this.codigo = codigo;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public Boolean getFotoPrincipal() {
        return fotoPrincipal;
    }

    public void setFotoPrincipal(Boolean fotoPrincipal) {
        this.fotoPrincipal = fotoPrincipal;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Integer usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
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
        if (!(object instanceof FotoArticulo)) {
            return false;
        }
        FotoArticulo other = (FotoArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.waki.entidades.FotoArticulo[ codigo=" + codigo + " ]";
    }
    
}
