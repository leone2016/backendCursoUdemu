/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.DTO;

/**
 *
 * @author leoz3
 */
public class FotosDTO {
    
    private String urlphoto;
    private Integer article;

    public FotosDTO(String urlphoto, Integer article) {
        this.urlphoto = urlphoto;
        this.article = article;
    }
    
    public String getUrlphoto() {
        return urlphoto;
    }

    public void setUrlphoto(String urlphoto) {
        this.urlphoto = urlphoto;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }
    
    
}
