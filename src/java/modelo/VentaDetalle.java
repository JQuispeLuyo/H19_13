/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author PC23
 */
public class VentaDetalle {
    int IDDETVENT;
    int IDVENT;
    int IDEQUI;
    int IDSUC;
    String DESEQUI; 
    Double PREEQUI;
    int CANTEQUI;
    Double IMPORT;

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

    public String getDESEQUI() {
        return DESEQUI;
    }

    public void setDESEQUI(String DESEQUI) {
        this.DESEQUI = DESEQUI;
    }

    public Double getPREEQUI() {
        return PREEQUI;
    }

    public void setPREEQUI(Double PREEQUI) {
        this.PREEQUI = PREEQUI;
    }

    public int getCANTEQUI() {
        return CANTEQUI;
    }

    public void setCANTEQUI(int CANTEQUI) {
        this.CANTEQUI = CANTEQUI;
    }

    public Double getIMPORT() {
        return IMPORT;
    }

    public void setIMPORT(Double IMPORT) {
        this.IMPORT = IMPORT;
    }

    public int getIDSUC() {
        return IDSUC;
    }

    public void setIDSUC(int IDSUC) {
        this.IDSUC = IDSUC;
    }
    
}
