/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class AsignacionDetalle {
    int IDDETASIG, IDASIG, IDPERJEF, IDPEREMP,IDSUC;
    String NOMSUC, NOMCOMJEF, NOMCOMEMP, DNIEMP, ESTADETASIG;

    public int getIDDETASIG() {
        return IDDETASIG;
    }

    public void setIDDETASIG(int IDDETASIG) {
        this.IDDETASIG = IDDETASIG;
    }

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

    public int getIDPEREMP() {
        return IDPEREMP;
    }

    public void setIDPEREMP(int IDPEREMP) {
        this.IDPEREMP = IDPEREMP;
    }

    public int getIDSUC() {
        return IDSUC;
    }

    public void setIDSUC(int IDSUC) {
        this.IDSUC = IDSUC;
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

    public String getNOMCOMEMP() {
        return NOMCOMEMP;
    }

    public void setNOMCOMEMP(String NOMCOMEMP) {
        this.NOMCOMEMP = NOMCOMEMP;
    }

    public String getDNIEMP() {
        return DNIEMP;
    }

    public void setDNIEMP(String DNIEMP) {
        this.DNIEMP = DNIEMP;
    }

    public String getESTADETASIG() {
        return ESTADETASIG;
    }

    public void setESTADETASIG(String ESTADETASIG) {
        this.ESTADETASIG = ESTADETASIG;
    }
}
