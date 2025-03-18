/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_logic;

import ENTITY.*;
import GUI.FormAddGiay;
import GUI.data;
import Process_Data.FileDAO;
import Process_Data.SQL_Giay;
import Process_Data.SQL_NhanHang;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import library.Swing.ButtonMenu;
import library.Swing.ScrollBarCustom;
import library.Table.EventAction;
import library.Table.ModelAction;

/**
 *
 * @author lengo
 */
public class Logic_Data {

    private data ui;
    private JPanel uiContain;
    private Logic_Admin_Home lgHome;
    private SQL_Giay data;
    private SQL_NhanHang datan;
    private String queryCategory = "";
    private String queryColor = "";
    private String querySize = "";
    private String queryTextSearch = "";

    public Logic_Data(data ui) {
        this.ui = ui;
    }

    public void setlgHome(Logic_Admin_Home lg) {
        this.lgHome = lg;
        adEvent();
    }

    public void loadDefault() {
        List<ENTITY.Entity_Giay> list = data.instance().getLists();
        initData(list);
    }

    public void loadCustom() {
            List<Entity_Giay> list = data.instance().getListsBYDK(queryCategory, queryColor, querySize, queryTextSearch);
            initData(list);
    }

    public void initData(List<ENTITY.Entity_Giay> list) {
        ((DefaultTableModel) ui.table.getModel()).setRowCount(0);
        ui.table.fixTable(ui.jScrollPane1);
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(Object object) {
                loadDelete();
            }

            @Override
            public void update(Object object) {
                int id = (int)ui.table.getValueAt(ui.table.getSelectedRow(), 0);
                lgHome.loadDataChiTiet(data.instance().getByID(id));
            }
        };
        if (list == null) return;
        for (Entity_Giay x : list) {
            Entity_NhanHang n = datan.instance().getByID(x.getIdNhan());
            ui.table.addRow(new Object[]{x.getId(), x.getName(), n.getName(), new ModelAction(this, eventAction)});
        }
        ui.table.setRowSelectionInterval(0, 0);
    }

    public void adEvent() {
        this.ui.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormAddGiay f = new FormAddGiay();
                f.lg.setlgHome(lgHome);
                f.show();
            }
        });
        this.ui.btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)ui.table.getValueAt(ui.table.getSelectedRow(), 0);
                lgHome.loadDataChiTiet(data.instance().getByID(id));
            }
        });

        this.ui.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDelete();
            }
        });
        this.ui.btnAddFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAddFile();
            }
        });
        this.ui.btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadXuatFile();
            }
        });

        this.ui.txSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                loadSearch(ui.txSearch.getText());
            }
        });
        
        this.ui.table.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                int id = (int)ui.table.getValueAt(ui.table.getSelectedRow(), 0);
                lgHome.loadDataChiTiet(data.instance().getByID(id));
            }
        });

    }

//    public void loadUI(int i) {
//        this.ui.pnContaint.removeAll();
//        if (i == 1) {
//            uiContain = new dataList();
//        } else {
//            uiContain = new dataInfo();
//        }
//        Dimension d = new Dimension(1118, 574);
//        if (ui.pnContaint.size().width == 0) {
//            uiContain.setSize(d);
//        } else {
//            System.out.println(ui.pnContaint.size().width + " " + ui.pnContaint.size().height);
//            uiContain.setSize(ui.pnContaint.size().width, ui.pnContaint.size().height);
//        }
//        this.ui.pnContaint.add(uiContain);
//        this.ui.pnContaint.revalidate();
//        this.ui.pnContaint.repaint();
//    }
    
    public void loadSearch(String valueSearch) {
        System.out.println(valueSearch);
        if (valueSearch.equals("")) {
            queryTextSearch = "";
        }else{
            queryTextSearch = " and namee like N'%"+ valueSearch + "%' ";
        }
        loadCustom();
    }

    public void loadUpdate() {
        System.out.println("Viet code Delete o day");
    }

    public void loadDelete() {
        int id = (int)ui.table.getValueAt(ui.table.getSelectedRow(), 0);
        if (JOptionPane.showConfirmDialog(uiContain, "Bạn thực sự muốn xóa!!", "Xác nhận", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
            if (!data.instance().delete(id)) {
                JOptionPane.showMessageDialog(uiContain, "Không thể xóa dữ liệu");
            }
        }
        loadCustom();
    }

    public void loadAddFile() {
        System.out.println("Viet code add file o day");
    }

    public void loadXuatFile() {
        String nameFile = JOptionPane.showInputDialog("Nhập vào tên file cần lưu");
        String pathFile = System.getProperty("user.home") + "\\Downloads\\" + nameFile + ".xlsx";
        if (FileDAO.instance().file_Write(ui.table, pathFile, nameFile)) {
            JOptionPane.showMessageDialog(ui, "Xuất file thành công\nTruy cập đường dẫn để xem file : \n\"" + pathFile + "\"");
        }
    }
    
    
    
    

    public void loadQueryCategory(ButtonMenu btn) {
        queryCategory = "";
        if (btn.getText().equals("Tất cả")) {
            queryCategory = "";
        } else {
            String[] parts = btn.getText().split("\\ | ");
            int id = Integer.parseInt(parts[0].trim());
            queryCategory = " and ID_TRADEMARK = " + Integer.toString(id);
        }

    }

    public void loadQueryColor() {
        queryColor = "";
        String  query = "	and id in " +
"		(select DISTINCT  DETAIL_SHOES.ID_SHOE from COLORS, DETAIL_COLORS, DETAIL_SHOES" +
"		where COLORS.ID = DETAIL_COLORS.ID_COLOR and DETAIL_SHOES.ID = DETAIL_COLORS.ID_DETAIL_SHOE ";
        if (ui.jrbAll.isSelected()) {
            queryColor = "";
            return;
        } else if (ui.jrbBlue.isSelected()) {
            query = query + " and COLORS.NAMEE like N'%Blue%') ";
        } else if (ui.jrbBrown.isSelected()) {
            query = query + " and COLORS.NAMEE like N'%Brown%') ";
        } else if (ui.jrbOrange.isSelected()) {
            query = query + " and COLORS.NAMEE like N'%Orange%') ";
        } else if (ui.jrbRed.isSelected()) {
            query = query + " and COLORS.NAMEE like N'%Red%') ";
        } else if (ui.jrbWhite.isSelected()) {
            query = query + " and COLORS.NAMEE like N'%White%') ";
        }
        queryColor = query;
    }

    public void loadQuerySize() {
        querySize = "";
     
        String query = ""
                + " and id in " +                
"		(select DISTINCT  DETAIL_SHOES.ID_SHOE from SIZES, DETAIL_SIZES, DETAIL_SHOES" +
"		where SIZEs.ID = DETAIL_SIZES.ID_SIZE and DETAIL_SIZES.ID_DETAIL_SHOE = DETAIL_SHOES.ID " ;
        if (ui.jrbAll1.isSelected()) {
            querySize = "";
            return;
        } else if (ui.jrb2.isSelected()) {
           query = query +  " and SIZES.SIZE between 20 and 30) ";
        } else if (ui.jrb2.isSelected()) {
           query = query +  " and SIZES.SIZE between 30 and 40) ";
        } else if (ui.jrb2.isSelected()) {
           query = query +  " and SIZES.SIZE between 40 and 50) ";
        }
        querySize = query;
    }

    public void initOptionCategory() {
        ui.btnCateAll.setSelected(true);
        List<Entity_NhanHang> listn = datan.instance().getLists();
        ui.pnOptionCategory.setLayout(new GridLayout(listn.size() + 1, 1));
        ui.spCategory.setVerticalScrollBar(new ScrollBarCustom());
        for (Entity_NhanHang x : listn) {
            library.Swing.ButtonMenu btn = new ButtonMenu();
            btn.setText(x.getId() + " | " + x.getName());
            ui.pnOptionCategory.add(btn);
        }
        for (Component x : ui.pnOptionCategory.getComponents()) {
            JButton btn = (JButton) x;
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setDefaultBackground(ui.pnOptionCategory);
                    btn.setSelected(true);
                    loadQueryCategory((ButtonMenu) btn);
                    loadCustom();
                }
            });
        }
    }

    public void initOptionColor() {
        for (Object x : ui.jpnColor.getComponents()) {
            if (x instanceof JPanel) {
                ((JPanel) x).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        for (Object k : ui.jpnColor.getComponents()) {
                            if (k instanceof JPanel) {
                                ((JPanel) k).setBorder(BorderFactory.createEmptyBorder());
                            }
                        }
                        ((JPanel) x).setBorder(BorderFactory.createLineBorder(Color.white));
                        if (((JPanel) x).getBackground() == Color.WHITE) {
                            ui.jrbAll.setSelected(true);
                        } else if (((JPanel) x).getBackground() == Color.RED) {
                            ui.jrbRed.setSelected(true);
                        } else if (((JPanel) x).getBackground() == Color.BLUE) {
                            ui.jrbBlue.setSelected(true);
                        } else if (((JPanel) x).getBackground() == Color.orange) {
                            ui.jrbOrange.setSelected(true);
                        } else if (((JPanel) x).getBackground() == Color.gray) {
                            ui.jrbBrown.setSelected(true);
                        } else {
                            ui.jrbWhite.setSelected(true);
                        }
                        loadQueryColor();
                        loadCustom();
                    }
                });
            }
        }
    }

    public void initOptionSize() {
        ui.btnSizeAll.setSelected(true);
        for (Object x : ui.jpnSize.getComponents()) {
            if (x instanceof ButtonMenu) {
                ((ButtonMenu) x).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        setDefaultBackground(ui.jpnSize);
                        ((ButtonMenu) x).setSelected(true);
                        if (((ButtonMenu) x).getText().equals("20 - 30")) {
                            ui.jrb2.setSelected(true);
                        } else if (((ButtonMenu) x).getText().equals("30 - 40")) {
                            ui.jrb3.setSelected(true);
                        } else if (((ButtonMenu) x).getText().equals("40 - 50")) {
                            ui.jrb4.setSelected(true);
                        } else {
                            ui.jrbAll1.setSelected(true);
                        }
                        loadQuerySize();
                        loadCustom();
                    }
                });
            }
        }
    }

    public void setDefaultBackground(JPanel pn) {
        for (Component x : pn.getComponents()) {
            if (x instanceof ButtonMenu) {
                JButton btn = (JButton) x;
                btn.setSelected(false);
            }
        }
    }
}
