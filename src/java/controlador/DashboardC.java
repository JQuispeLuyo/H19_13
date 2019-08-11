/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DashboardImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import modelo.VentaSucursal;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author jq_00
 */
@Named(value = "dashboardC")
@ViewScoped
public class DashboardC implements Serializable {

    private List<VentaSucursal> ventaSucursal = new ArrayList();
    private final DashboardImpl dao = new DashboardImpl();

    public DashboardC() {
    }

    @PostConstruct
    public void init() {
        createBarModels();
    }

    private BarChartModel barModel;

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        try {
            this.ventaSucursal = this.dao.listarVentaSucursal();
            ChartSeries modeloChar;

            for (VentaSucursal ventaSuc : this.ventaSucursal) {
                modeloChar = new ChartSeries();
                modeloChar.setLabel(ventaSuc.getNOMSUC());
                modeloChar.set(ventaSuc.getFEC(), ventaSuc.getTOTAL());
                model.addSeries(modeloChar);
            }

        } catch (Exception ex) {
            Logger.getLogger(DashboardC.class.getName()).log(Level.SEVERE, null, ex);
        }

//        ChartSeries boys = new ChartSeries();
//        boys.setLabel("Boys");
//        boys.set("2004", 120);
//        boys.set("2005", 100);
//        boys.set("2006", 44);
//        boys.set("2007", 150);
//        boys.set("2008", 25);
//        model.addSeries(boys);
        return model;
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        try {
            barModel = initBarModel();
            barModel.setTitle("Bar Chart");
            barModel.setLegendPosition("ne");

            Axis xAxis = barModel.getAxis(AxisType.X);
            xAxis.setLabel("Ventas");

            Axis yAxis = barModel.getAxis(AxisType.Y);
            yAxis.setLabel("Soles");
            yAxis.setMin(0);
            
            int max = this.dao.getMaxVentaSucursal();
            max = max + ((max * 10)/100);
            yAxis.setMax(max);
            
        } catch (Exception ex) {
            Logger.getLogger(DashboardC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
