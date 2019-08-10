
package service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("ConverterTipoInventario")
public class ConverterTipoInventario implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        String TIPPER = "";
        if (value != null) {
            TIPPER = (String) value;
            switch (TIPPER) {
                case "I":
                    TIPPER = "Ingresos";
                    break;
                case "S":
                    TIPPER = "Salida";
                    break;
            }
        }
        return TIPPER;

    }
 
}
