/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_logic;

import ENTITY.Entity_Giay;
import GUI.Category;
import GUI.adminHome;
import GUI.dashBoard;
import GUI.data;
import GUI.dataInfo;
import GUI.sell;
import GUI.ship;
import GUI.staff;
import GUI.trademark;
import javax.swing.JPanel;

/**
 *
 * @author lengo
 */
public class Logic_Admin_Home {

    private adminHome uiHome;
    private Logic_Menu lgMenu;
    private Logic_Staff lgStaff;
    private Logic_Data lgData;
    private Logic_Sale lgSelSale;
    private Logic_Ship lgShip;
    private Logic_TradeMark lgMark;
    private Logic_Category lgCategory;
    private Logic_DataChiTiet lgDD;
    private JPanel ui;
    private Entity_Giay giay = null;

    public Logic_Admin_Home(adminHome uiHome) {
        this.uiHome = uiHome;
        this.lgMenu = new Logic_Menu(uiHome.menu1);
        lgMenu.setlgHome(this);
    }
    
    public void loadDataChiTiet(Entity_Giay giay){
        this.giay = giay;
        initUI(8);
    }
    
    public int getIndex(){
        return lgMenu.getUI();
    }

    public void initUI(int index) {
        this.uiHome.pnContaint.removeAll();
        switch (index) {
            case 1:
                ui = new dashBoard();
                break;
            case 2:
                ui = new staff();
                this.lgStaff = new Logic_Staff((staff) ui);
                lgStaff.setlgHome(this);
                break;
            case 3:
                ui = new data();
                this.lgData = new Logic_Data((data) ui);
                lgData.setlgHome(this);
                break;
            case 4:
                ui = new sell();
                this.lgSelSale = new Logic_Sale( (sell) ui);
                lgSelSale.setlgHome(this);
                break;
            case 5:
                ui = new Category();
                this.lgCategory = new Logic_Category((Category) ui);
                lgCategory.setlgHome(this);
                break;
            case 6:
                ui = new trademark();
                this.lgMark = new Logic_TradeMark((trademark) ui);
                lgMark.setlgHome(this);
                break;
            case 7:
                ui = new ship();
                this.lgShip = new Logic_Ship( (ship) ui);
                lgShip.setlgHome(this);
                break;
            case 8:
                ui = new dataInfo();
                this.lgDD = new Logic_DataChiTiet( (dataInfo) ui);
                this.lgDD.LoadGiay(giay);
                lgDD.setlgHome(this);
                break;

        }
        ui.setSize(this.uiHome.pnContaint.size().width, this.uiHome.pnContaint.size().height);
        
        this.uiHome.pnContaint.add(ui);
        this.uiHome.pnContaint.revalidate();
        this.uiHome.pnContaint.repaint();
    }

}
