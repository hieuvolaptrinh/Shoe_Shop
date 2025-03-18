/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_logic;

import ENTITY.Entity_Giay;
import ENTITY.Entity_GiayChiTiet;
import ENTITY.Entity_NhanHang;
import GUI.dashBoard;
import GUI.data;
import Process_Data.SQL_Giay;
import Process_Data.SQL_GiayChiTiet;
import Process_Data.SQL_HoaDon;
import Process_Data.SQL_NhanHang;
import Process_Data.SQL_TaiKhoan;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import library.Chart.ModelChart;
import library.Table.EventAction;
import library.Table.ModelAction;

/**
 *
 * @author lengo
 */
public class Logic_Dashboard {

    private dashBoard ui;
    private SQL_Giay dataG;
    private SQL_HoaDon data;
    private SQL_NhanHang datan;
    private SQL_GiayChiTiet dataGct;
    private SQL_TaiKhoan dataTK;

    public Logic_Dashboard(dashBoard ui) {
        this.ui = ui;
    }

    
    public void loadDataChart(){
        ui.chart.setTitle("Biểu đồ");
        ui.chart.addLegend("Tổng thu", Color.decode("#FFE000"), Color.decode("#799F0C"));
        ui.chart.addLegend("Thành công", Color.decode("#ffe259"), Color.decode("#ffa751"));
        ui.chart.addLegend("Thất bại", Color.decode("#3D7EAA"), Color.decode("#FFE47A"));
        initValueChart();
    }
    
    private void initValueChart() {
        ui.chart.clear();
        ui.chart.addData(new ModelChart("January", new double[]{500, 50, 100}));
        ui.chart.addData(new ModelChart("February", new double[]{600, 300, 150}));
        ui.chart.addData(new ModelChart("March", new double[]{200, 50, 900}));
        ui.chart.addData(new ModelChart("April", new double[]{480, 700, 100}));
        ui.chart.addData(new ModelChart("May", new double[]{350, 540, 500}));
        ui.chart.addData(new ModelChart("June", new double[]{450, 800, 100}));
        ui.chart.start();
    }
    
    public void initThongKe(){
        ui.tk1.setText(Integer.toString(dataTK.instance().getCount()));
        ui.tk2.setText(Integer.toString(dataGct.instance().getCount()));
        ui.tk3.setText(Integer.toString(data.instance().getCountAccept()));
    }
    
    public void initTyLe(){
        DecimalFormat df = new DecimalFormat("#,### VND");
        ui.tl1.setText(df.format(data.instance().getDoanhThu()));
        ui.tl2.setText(Integer.toString(data.instance().getCountAccept()) + "  Đơn");
        ui.tl3.setText(Integer.toString(data.instance().getCount()) + "  Đơn");
        
        ui.tllb1.setText("Tăng 2%");
        ui.tllb1.setForeground(Color.green);
//        ui.tllb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/up.png")));
        ui.tllb2.setText("Giảm 1%");
        ui.tllb2.setForeground(Color.red);
//        ui.tllb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); 
        ui.tllb3.setText("Tăng 3%");
        ui.tllb3.setForeground(Color.green);
//        ui.tllb3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/up.png")));
    }
    
    public void initTableTopProduct(){
        ((DefaultTableModel) ui.tableTopProduct.getModel()).setRowCount(0);
        ui.tableTopProduct.fixTable(ui.jScrollPane3);
        
        List<Entity_GiayChiTiet> list = dataGct.instance().getListsTop();
        
        if (list == null) return;
        for (Entity_GiayChiTiet x : list) {
            Entity_Giay g = dataG.instance().getByID(x.getIdGiay());
            Entity_NhanHang n = datan.instance().getByID(g.getIdNhan());
            ui.tableTopProduct.addRow(new Object[]{x.getId(), g.getName(), n.getName(), data.instance().getCoutByIDShoe(x.getId())});
        }
        ui.tableTopProduct.setRowSelectionInterval(0, 0);
    }
}
