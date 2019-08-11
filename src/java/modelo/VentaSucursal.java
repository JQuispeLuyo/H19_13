/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author jq_00
 */
public class VentaSucursal {
    String NOMSUC;
    int CONT;
    Double TOTAL;
    String FEC;

    public String getNOMSUC() {
        return NOMSUC;
    }

    public void setNOMSUC(String NOMSUC) {
        this.NOMSUC = NOMSUC;
    }

    public int getCONT() {
        return CONT;
    }

    public void setCONT(int CONT) {
        this.CONT = CONT;
    }

    public Double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(Double TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getFEC() {
        return FEC;
    }

    public void setFEC(String FEC) {
        this.FEC = FEC;
    }
}
