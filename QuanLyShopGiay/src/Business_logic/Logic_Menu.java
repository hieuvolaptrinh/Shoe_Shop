/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_logic;

import GUI.menu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author lengo
 */
public class Logic_Menu {

    private menu uiMenu;
    private Logic_Admin_Home lgHome;

    public Logic_Menu(menu uiMenu) {
        this.uiMenu = uiMenu;
    }

    public JButton getButtonSelected() {
        for (Component x : uiMenu.panelMenu.getComponents()) {
            JButton btn = (JButton) x;
            if (btn.isSelected()) {
                return btn;
            }
        }
        return new JButton();
    }

    public int getUI() {
        JButton btn = getButtonSelected();
        if (btn.getText().equals("Doanh thu")) {
            return 1;
        } else if (btn.getText().equals("Nhân viên")) {
            return 2;
        } else if (btn.getText().equals("Dữ liệu")) {
            return 3;
        } else if (btn.getText().equals("Mã giảm giá")) {
            return 4;
        } else if (btn.getText().equals("Danh mục")) {
            return 5;
        } else if (btn.getText().equals("Nhãn hàng")) {
            return 6;
        } else if (btn.getText().equals("Đơn vị vận chuyển")) {
            return 7;
        } else if (btn.getText().equals("Cài đặt")) {
            return 8;
        } else {
            return 0;
        }
    }

    public void setlgHome(Logic_Admin_Home lg){
        this.lgHome = lg;
        adEvent();
    }
    public void adEvent() {
        for (Component x : uiMenu.panelMenu.getComponents()) {
            if (x instanceof JButton) {
                final JButton button = (JButton) x; 
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setDefaultBackground();
                        button.setSelected(true);
                        lgHome.initUI(lgHome.getIndex());
                    }
                });
            }
        }
    }
    public void setDefaultBackground() {
        for (Component x : uiMenu.panelMenu.getComponents()) {
            JButton btn = (JButton) x;
            btn.setSelected(false);
        }
    }
    

}
