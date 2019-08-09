/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author PC31
 */
public class Inventario {
    
    int IDINV;
    int IDEQUI;
    Date FECINV;
    int CANTINV;
    String TIPINV;

    public int getIDINV() {
        return IDINV;
    }

    public void setIDINV(int IDINV) {
        this.IDINV = IDINV;
    }

    public int getIDEQUI() {
        return IDEQUI;
    }

    public void setIDEQUI(int IDEQUI) {
        this.IDEQUI = IDEQUI;
    }

    public Date getFECINV() {
        return FECINV;
    }

    public void setFECINV(Date FECINV) {
        this.FECINV = FECINV;
    }

    public int getCANTINV() {
        return CANTINV;
    }

    public void setCANTINV(int CANTINV) {
        this.CANTINV = CANTINV;
    }

    public String getTIPINV() {
        return TIPINV;
    }

    public void setTIPINV(String TIPINV) {
        this.TIPINV = TIPINV;
    }
    
}
