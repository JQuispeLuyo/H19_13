
package service;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


public class Rutas {
//    public static void template() throws IOException{  
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(ec.getRequestContextPath() + "/faces/vistas/index/index.xhtml");     
//    }
    
    public static void redirectVenta() throws IOException{  
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/vistas/venta/venta.xhtml");     
    }
}
