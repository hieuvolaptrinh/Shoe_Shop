/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_logic;

import ENTITY.Entity_DanhMuc;
import ENTITY.Entity_Giay;
import ENTITY.Entity_NhanHang;
import GUI.FormAddGiay;
import GUI.data;
import Process_Data.SQL_DanhMuc;
import Process_Data.SQL_Giay;
import Process_Data.SQL_NhanHang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import library.Swing.ScrollBarCustom;

/**
 *
 * @author lengo
 */
public class Logic_Data2 {

    private FormAddGiay ui;
    private JPanel uiContain;
    private Logic_Admin_Home lgHome;
    private SQL_Giay data;
    private SQL_NhanHang datan;
    private SQL_DanhMuc dataDM;
    private List<Entity_DanhMuc> upDM = new ArrayList<>();

    public Logic_Data2(FormAddGiay ui) {
        this.ui = ui;
    }

    public void setlgHome(Logic_Admin_Home lg) {
        this.lgHome = lg;
        addEvent();
    }

    public void initDanhMuc() {
        ui.cbbDanhMuc.removeAllItems();
        List<Entity_DanhMuc> list = dataDM.instance().getLists();
        for (Entity_DanhMuc x : list) {
            ui.cbbDanhMuc.addItem(x);
        }
    }

    public void initNhanHang() {
        ui.cbbNhanHang.removeAllItems();
        List<Entity_NhanHang> list = datan.instance().getLists();
        for (Entity_NhanHang x : list) {
            ui.cbbNhanHang.addItem(x);
        }
    }
    
    public void addEvent() {
        this.ui.btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAdd();
            }
        });
        this.ui.btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lgHome.initUI(3);
            }
        });
        this.ui.btnAddDanhMuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAddDanhMuc();
            }
        });
    }
    
    public void loadAdd(){
        Entity_Giay g = new Entity_Giay(-1, ui.name.getText(), ui.mota.getText(), ((ENTITY.Entity_NhanHang)ui.cbbNhanHang.getSelectedItem()).getId());
        
        
        if(data.instance().insert(g)){
            if (upDM != null && upDM.size() > 0) {
                for (Entity_DanhMuc x : upDM) {
                    dataDM.instance().insert(x, data.instance().getIDNext());
                }
            }
            lgHome.initUI(3);
            ui.dispose();
            JOptionPane.showMessageDialog(uiContain, "Them moi thanh cong\n Chon bieu tuong chinh sua de them thong tin san pham");
        }else{
            JOptionPane.showMessageDialog(uiContain, "Khong the them du lieu");
        }
                
            
    }
    
    public void loadAddDanhMuc() {
        Entity_DanhMuc dm = (Entity_DanhMuc) ui.cbbDanhMuc.getSelectedItem();
        boolean exists = false;
        ListModel<Entity_DanhMuc> model = ui.lDanhMuc.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            Entity_DanhMuc x = model.getElementAt(i);
            if (x.getId() == dm.getId()) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            DefaultListModel<Entity_DanhMuc> defaultListModel;
            if (model instanceof DefaultListModel) {
                defaultListModel = (DefaultListModel<Entity_DanhMuc>) model;
            } else {
                defaultListModel = new DefaultListModel<>();
                for (int i = 0; i < model.getSize(); i++) {
                    defaultListModel.addElement(model.getElementAt(i));
                }
                ui.lDanhMuc.setModel(defaultListModel);

            }
            upDM.add(dm);
            defaultListModel.addElement(dm);
        }
    }
    
    public void initJscroll() {
        ui.jScrollPane4.setVerticalScrollBar(new ScrollBarCustom());
        ui.jScrollPane3.setVerticalScrollBar(new ScrollBarCustom());
    }
}
