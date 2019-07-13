/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author PC
 */
public class DetalleVenta {

    int IDDETVENT;
    int IDVENT;
    int IDEQUI;
    int CANTEQUI;

    public int getIDDETVENT() {
        return IDDETVENT;
    }

    public void setIDDETVENT(int IDDETVENT) {
        this.IDDETVENT = IDDETVENT;
    }

    public int getIDVENT() {
        return IDVENT;
    }

    public void setIDVENT(int IDVENT) {
        this.IDVENT = IDVENT;
    }

    public int getIDEQUI() {
        return IDEQUI;
    }

    public void setIDEQUI(int IDEQUI) {
        this.IDEQUI = IDEQUI;
    }

    public int getCAN() {
        return CANTEQUI;
    }

    public void setCAN(int CAN) {
        this.CANTEQUI = CAN;
    }

}
