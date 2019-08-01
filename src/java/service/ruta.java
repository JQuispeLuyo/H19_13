
package service;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


public class ruta {
    public void template() throws IOException{  
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/Vistas/index/index.xhtml");     
    }
}
