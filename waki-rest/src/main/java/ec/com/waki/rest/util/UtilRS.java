/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.rest.util;

import ec.com.waki.DTO.ArticuloDTO;
import ec.com.waki.DTO.FotosDTO;
import ec.com.waki.DTO.StockArticuloDTO;
import ec.com.waki.entidades.Articulo;
import ec.com.waki.entidades.FotoArticulo;
import ec.com.waki.entidades.StockArticulo;
import ec.com.waki.servicios.FotoArticuloService;
import ec.com.waki.util.Constantes;
import ec.com.waki.util.DateUtil;
import ec.com.waki.util.Tipo_bundle;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpHeaders;

/**
 *
 * @author leoz3
 */
public class UtilRS {
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    private static final String REALM = "example";
    
    private String codigosArticulos ="";
    
   @EJB(beanName = "FotoArticuloServiceImpl")
   private FotoArticuloService fotoArticuloService;
    
    public List<ArticuloDTO> cargarFotosArticulo(List<ArticuloDTO> retornoListado, List<FotoArticulo> listadoFoto){
         List<FotosDTO> listFotoDTO = new ArrayList<>();
         for(ArticuloDTO articuloDTO: retornoListado){
            listFotoDTO = new ArrayList<>();
            for(FotoArticulo fotoArticulo: listadoFoto){
                if(fotoArticulo.getArticulo().getCodigo().intValue() == articuloDTO.getCode().intValue()){
//                System.out.println("---ARTICULO "+articuloDTO.getCode());
                   if(fotoArticulo.getFotoPrincipal()){
                     articuloDTO.setUrlFoto(fotoArticulo.getUrl()+fotoArticulo.getArticulo().getCodigo()+'/'+fotoArticulo.getNombreImagen()+fotoArticulo.getTipoImagen());
                   }
                   listFotoDTO.add(new FotosDTO(fotoArticulo.getUrl()+fotoArticulo.getArticulo().getCodigo()+'/'+fotoArticulo.getNombreImagen()+fotoArticulo.getTipoImagen(),fotoArticulo.getArticulo().getCodigo()));
//                   System.out.println("--"+listFotoDTO.size());
                   articuloDTO.setListPhoto(listFotoDTO);
                }
             }
        }
        return retornoListado;
    }
    public List<ArticuloDTO> cargarArticulo(List<StockArticulo> stockArticulo){
        System.out.println("------LLEGA ------------------------"+stockArticulo.size());
      
        List<Articulo> tempArticulo= new ArrayList<>();
        List<ArticuloDTO> retornoArticulo= new ArrayList<>();
        for (StockArticulo stock : stockArticulo) {
            if(tempArticulo.isEmpty()){
                tempArticulo.add(stock.getArticulo());
            }else{
                boolean verifica = false;
                for (Articulo articulo : tempArticulo) {
                    if(articulo.getCodigo().intValue() == stock.getArticulo().getCodigo().intValue()){
                        verifica=true;
                        break;
                    }
                }
                if(!verifica){
                    tempArticulo.add(stock.getArticulo());
                }
            }
        }
        System.out.println("------SALE"+tempArticulo.size());
        for (Articulo articulo : tempArticulo) {
            ArticuloDTO articuloDTO = new ArticuloDTO();
            articuloDTO.setCode(articulo.getCodigo());
            articuloDTO.setCodeImport(articulo.getCodigoImportacion());
            articuloDTO.setNameProduct((articulo.getNombre()==null ||articulo.getNombre().equals(""))? articulo.getNombreImportacion():articulo.getNombre());
            articuloDTO.setCodeColor(articulo.getColor().getCodigo());
            articuloDTO.setColor(articulo.getColor().getEtiqueta());
            articuloDTO.setColorHexadecimal(articulo.getColor().getValor());
            articuloDTO.setGender(articulo.getGenero().getEtiqueta());
            articuloDTO.setSmallDescription(articulo.getDescripcionCorta());
            articuloDTO.setLongDescription(articulo.getDescripcionLarga());
            articuloDTO.setType(articulo.getTipo().getEtiqueta());
            articuloDTO.setPromo("NO PROMO");
            articuloDTO.setUrlFoto(Tipo_bundle.DEFAULT_IMG);
            articuloDTO.setRate(articulo.getVentaPublico());
            articuloDTO.setRatePromo(BigDecimal.ZERO);
//            System.out.println("------categoria "+(articulo.getCategoria()!= null && articulo.getCategoria().getEtiqueta()!=null));
            if(articulo.getCategoria()!= null && articulo.getCategoria().getEtiqueta()!=null){
            articuloDTO.setCategory(articulo.getCategoria().getEtiqueta());
            }
//            System.out.println("----material "+(articulo.getMaterial().getEtiqueta()));
            if(articulo.getMaterial()!= null && articulo.getMaterial().getEtiqueta()!=null){
            articuloDTO.setCodeMaterial(articulo.getMaterial().getCodigo());
            articuloDTO.setNameMaterial(articulo.getMaterial().getEtiqueta());
            articuloDTO.setUrlIconMaterial(articulo.getMaterial().getDescripcion());
            }
            /*
            LISTADO STOCK
            */
            List<StockArticuloDTO> liststockDTO = new ArrayList<>();
//            System.out.println("articulo "+ articulo.getCodigo());
            for (StockArticulo stock : stockArticulo) {
                StockArticuloDTO stockDTO = new StockArticuloDTO();
                if(articulo.getCodigo().intValue() == stock.getArticulo().getCodigo().intValue()){
                    stockDTO.setArticle(articulo.getCodigo());
                    articuloDTO.setRatePromo(stock.getTarifaVenta());
                    if(stock.getPromo() !=null){
                        articuloDTO.setPromo(stock.getPromo().getEtiqueta());
                        BigDecimal promo = new BigDecimal(stock.getPromo().getValor());
//                        System.out.println("-----promo"+stock.getTarifaVenta().subtract(articulo.getVentaPublico().multiply(promo)));
                        BigDecimal newRatePromo = stock.getTarifaVenta().subtract(stock.getTarifaVenta().multiply(promo));
                        articuloDTO.setRatePromo(newRatePromo.setScale(2, BigDecimal.ROUND_UP));
                    }
                    stockDTO.setRate(stock.getTarifaVenta());
                    
                    liststockDTO.add(stockDTO);
                }
                
                
            }
             articuloDTO.setListStock(liststockDTO);
            /*
            LISTADO FOTOS
            */
             this.codigosArticulos +=articulo.getCodigo()+",";;
//            if(!articulo.getFotoArticuloList().isEmpty()){
//                        List<FotosDTO> listFotoDTO = new ArrayList<>();
//                        for (FotoArticulo fotoArticulo : articulo.getFotoArticuloList()) {
//                            if(fotoArticulo.getFotoPrincipal()){
//                                articuloDTO.setUrlFoto(fotoArticulo.getUrl()+fotoArticulo.getNombreImagen()+fotoArticulo.getTipoImagen());
//                            }
//                            listFotoDTO.add(new FotosDTO(fotoArticulo.getUrl()+fotoArticulo.getNombreImagen()+fotoArticulo.getTipoImagen(),articulo.getCodigo()));
//                        }
//              articuloDTO.setListPhoto(listFotoDTO);
//            }
            retornoArticulo.add(articuloDTO);
        }
//       
//        etornoProdutco.setUrlFoto("https://firebasestorage.googleapis.com/v0/b/bancoimagenes-ccc97.appspot.com/o/1.png?alt=media&token=37ea1c62-ad91-4827-8c82-c0dc07965a17");
        return retornoArticulo;
    }
    private boolean isTokenBasedAuthentication(String authorizationHeader) {
       
        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader == null &&  authorizationHeader.isEmpty() &&  authorizationHeader.split(" ").length != 2 ;
    }
    public Boolean validarToken2(String authorizationHeader){
//    System.out.println("LLEGA UTILRS"+authorizationHeader);
    Boolean retornoValida = true;// authorizationHeader  valid
    String email =Constantes.CAMPO_BLANCO;
    Long tiempo = DateUtil.tomarFechaActual().getTime() ;
    if (isTokenBasedAuthentication(authorizationHeader)) {
        
            return false;
     }
       try {
           String undecodedToken = JWTUtil.decodificarToken(authorizationHeader);
             String[] todo  = undecodedToken.split("[\\\"||\\\"]");
             String[] time = todo[10].split("[\\:||\\,]");
            tiempo = new Long(time[1]);
            email= todo[13];
//            System.out.println("-----"+email);
            } catch (Exception e) {
               return false;
            }
          if (!email.equals("lmedinae@est.ups.edu.ec")){
           return false;
          }
    return retornoValida;
    }

    public String getCodigosArticulos() {
        return codigosArticulos;
    }

    public void setCodigosArticulos(String codigosArticulos) {
        this.codigosArticulos = codigosArticulos;
    }

    public FotoArticuloService getFotoArticuloService() {
        return fotoArticuloService;
    }

    public void setFotoArticuloService(FotoArticuloService fotoArticuloService) {
        this.fotoArticuloService = fotoArticuloService;
    }
    
   
  

}
