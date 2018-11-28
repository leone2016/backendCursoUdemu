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
public class UsuarioDTO {
    
    private String name;
    private String email;
    private String password;

    public UsuarioDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public UsuarioDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "name=" + name + ", email=" + email + ", password=" + password + '}';
    }
    
    
}
