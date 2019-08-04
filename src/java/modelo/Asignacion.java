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
public class Asignacion {
    
    int IDASIG;
    int IDSUC;
    String NOMSUC;
    int IDPERJEF;
    String NOMCOMJEF;
    String DNIJEF;
    String ESTAASIGPER;

    public int getIDASIG() {
        return IDASIG;
    }

    public void setIDASIG(int IDASIG) {
        this.IDASIG = IDASIG;
    }

    public int getIDPERJEF() {
        return IDPERJEF;
    }

    public void setIDPERJEF(int IDPERJEF) {
        this.IDPERJEF = IDPERJEF;
    }

    public int getIDSUC() {
        return IDSUC;
    }

    public void setIDSUC(int IDSUC) {
        this.IDSUC = IDSUC;
    }

    public String getESTAASIGPER() {
        return ESTAASIGPER;
    }

    public void setESTAASIGPER(String ESTAASIGPER) {
        this.ESTAASIGPER = ESTAASIGPER;
    }

    public String getNOMSUC() {
        return NOMSUC;
    }

    public void setNOMSUC(String NOMSUC) {
        this.NOMSUC = NOMSUC;
    }

    public String getNOMCOMJEF() {
        return NOMCOMJEF;
    }

    public void setNOMCOMJEF(String NOMCOMJEF) {
        this.NOMCOMJEF = NOMCOMJEF;
    }

    public String getDNIJEF() {
        return DNIJEF;
    }

    public void setDNIJEF(String DNIJEF) {
        this.DNIJEF = DNIJEF;
    }
   
}
