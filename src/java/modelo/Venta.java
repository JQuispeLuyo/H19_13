/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Venta {

    int IDVENT;
    int IDEMP;
    Date FECVEN;

    public int getIDVENT() {
        return IDVENT;
    }

    public void setIDVENT(int IDVENT) {
        this.IDVENT = IDVENT;
    }

    public int getIDEMP() {
        return IDEMP;
    }

    public void setIDEMP(int IDEMP) {
        this.IDEMP = IDEMP;
    }

    public Date getFECVEN() {
        return FECVEN;
    }

    public void setFECVEN(Date FECVEN) {
        this.FECVEN = FECVEN;
    }
    
}
