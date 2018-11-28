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
public class StockArticuloDTO {
    
    private Integer code;
    private Integer article;
    private Integer promo;
    private BigDecimal rate;
    private Integer ratepromo;
    

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public Integer getPromo() {
        return promo;
    }

    public void setPromo(Integer promo) {
        this.promo = promo;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    
    public Integer getRatepromo() {
        return ratepromo;
    }

    public void setRatepromo(Integer ratepromo) {
        this.ratepromo = ratepromo;
    }

    
    
    
    
}
