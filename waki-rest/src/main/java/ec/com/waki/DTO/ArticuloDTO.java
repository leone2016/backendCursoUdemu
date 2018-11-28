/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.DTO;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author leoz3
 */
public class ArticuloDTO {
    private Integer code;
    public String codeImport;
    private String nameProduct;
    private String smallDescription;
    private String longDescription;
    private Integer codeColor;
    private String colorHexadecimal;
    private String color;
    private String gender;
    private String type;
    private String urlFoto;
    private String Promo ;
    private BigDecimal rate;
    private BigDecimal ratePromo;
    private String category; 
    private Integer codeMaterial;
    private String nameMaterial;
    private String urlIconMaterial;
    private List<StockArticuloDTO> listStock;
    private List<FotosDTO> listPhoto; 
    

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StockArticuloDTO> getListStock() {
        return listStock;
    }

    public void setListStock(List<StockArticuloDTO> listStock) {
        this.listStock = listStock;
    }

    public String getPromo() {
        return Promo;
    }

    public void setPromo(String Promo) {
        this.Promo = Promo;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRatePromo() {
        return ratePromo;
    }

    public void setRatePromo(BigDecimal ratePromo) {
        this.ratePromo = ratePromo;
    }

    public List<FotosDTO> getListPhoto() {
        return listPhoto;
    }

    public void setListPhoto(List<FotosDTO> listPhoto) {
        this.listPhoto = listPhoto;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }
    public String getColorHexadecimal() {
        return colorHexadecimal;
    }

    public void setColorHexadecimal(String colorHexadecimal) {
        this.colorHexadecimal = colorHexadecimal;
    }

    public Integer getCodeColor() {
        return codeColor;
    }

    public void setCodeColor(Integer codeColor) {
        this.codeColor = codeColor;
    }

    public Integer getCodeMaterial() {
        return codeMaterial;
    }

    public void setCodeMaterial(Integer codeMaterial) {
        this.codeMaterial = codeMaterial;
    }

    public String getUrlIconMaterial() {
        return urlIconMaterial;
    }

    public void setUrlIconMaterial(String urlIconMaterial) {
        this.urlIconMaterial = urlIconMaterial;
    }

    public String getCodeImport() {
        return codeImport;
    }

    public void setCodeImport(String codeImport) {
        this.codeImport = codeImport;
    }

 
    
    
}
