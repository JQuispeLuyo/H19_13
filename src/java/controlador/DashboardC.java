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
import modelo.RankingVendedor;
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
    private List<RankingVendedor> rankingVendedor = new ArrayList();
    private final DashboardImpl dao = new DashboardImpl();

    public DashboardC() {
    }

    @PostConstruct
    public void init() {
        createBarModels();
    }

    private BarChartModel barModel;
    private BarChartModel barModelRanking;

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
        return model;
    }

    private BarChartModel initBarModelRanking() {
        BarChartModel model = new BarChartModel();
        try {
            this.rankingVendedor = this.dao.listarRankingVendedor();
            ChartSeries modeloChar;

            for (RankingVendedor ranking : this.rankingVendedor) {
                modeloChar = new ChartSeries();
                modeloChar.setLabel(ranking.getNOMPERCOM());
                modeloChar.set("", ranking.getTOTAL());
                model.addSeries(modeloChar);
            }

        } catch (Exception ex) {
            Logger.getLogger(DashboardC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    private void createBarModels() {
        this.createBarModel();
        this.createBarModelRanking();
    }

    private void createBarModel() {
        try {
            barModel = initBarModel();
            barModel.setTitle("Ventas por sucursal");
            barModel.setLegendPosition("ne");

            Axis xAxis = barModel.getAxis(AxisType.X);
            xAxis.setLabel("");

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

    
    private void createBarModelRanking() {
        try {
            barModelRanking = initBarModelRanking();
            barModelRanking.setTitle("Ranking de vendedores");
            barModelRanking.setLegendPosition("ne");

            Axis xAxis = barModelRanking.getAxis(AxisType.X);
            xAxis.setLabel("");

            Axis yAxis = barModelRanking.getAxis(AxisType.Y);
            yAxis.setLabel("Cantidad");
            yAxis.setMin(0);
            
            int max = this.dao.getMaxRankingVendedor();
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

    public BarChartModel getBarModelRanking() {
        return barModelRanking;
    }

    public void setBarModelRanking(BarChartModel barModelRanking) {
        this.barModelRanking = barModelRanking;
    }

}
