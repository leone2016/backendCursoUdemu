/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki;

import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author leoz3
 */
@javax.ws.rs.ApplicationPath("restfull")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        System.out.println("--------sale "+resources);
        return resources;
    }
    @Path("/hello")
    public class HelloResource {

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String getHello() {
            return "Hello!";
        }
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
       
          resources.add(ec.com.waki.filter.CrossOriginResourceSharingFilter.class);
          resources.add(ec.com.waki.rest.shopRS.class); 
          resources.add(ec.com.waki.rest.userRS.class); 
          resources.add(ec.com.waki.rest.auth.AuthRS.class);
          

        
        
    }
    
}
