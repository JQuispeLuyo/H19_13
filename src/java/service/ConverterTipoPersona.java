/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jq_00
 */
@FacesConverter("ConverterTipoPersona")
public class ConverterTipoPersona implements Converter{

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
                case "A":
                    TIPPER = "Administrador";
                    break;
                case "J":
                    TIPPER = "Jefe";
                    break;
                case "V":
                    TIPPER = "Vendedor";
                    break;
            }
        }
        return TIPPER;

    }
    
}
