/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_logic;

import ENTITY.Entity_Sale;
import GUI.sell;
import Process_Data.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import library.Table.EventAction;

/**
 *
 * @author lengo
 */
public class Logic_Sale {

    private sell ui;
    private JPanel uiContain;
    private Logic_Admin_Home lgHome;
    private SQL_Sale data;
    private String textSearch = "";
    private String dateSearch = "";

    public Logic_Sale(sell ui) {
        this.ui = ui;
    }

    public void setlgHome(Logic_Admin_Home lg) {
        this.lgHome = lg;
        adEvent();
    }

    public void loadDefault() {
        List<Entity_Sale> list = data.instance().getLists();
        initTable(list);
    }

    public void loadHetHan() {
        List<Entity_Sale> list = data.instance().getListsHetHan();
        initTable(list);
    }

    public void adEvent() {
        this.ui.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAdd();
            }
        });
        this.ui.btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUpdate();
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

        this.ui.btnDeleteFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateSearch = "EXPIRATION_TIME < getdate()";
                loadSearch();
            }
        });
        this.ui.btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetInfo();
                String dateB = ui.lDateB.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                String dateE = ui.lDateE.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                dateSearch = " EXPIRATION_TIME > '" + dateB + "' and EXPIRATION_TIME <= '" + dateE + "' ";
                loadSearch();
            }
        });
        this.ui.txSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                resetInfo();
                textSearch = ui.txSearch.getText();
                loadSearch();
            }
        });
        this.ui.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadInfo();
            }
        });
        this.ui.btnAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadDefault();
                ui.btnHetHan.setSelected(false);
                ui.btnAll.setSelected(true);
            }
        });
        this.ui.btnHetHan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadHetHan();
                ui.btnAll.setSelected(false);
                ui.btnHetHan.setSelected(true);
            }
        });
    }

    public void initTable(List<Entity_Sale> list) {
        ((DefaultTableModel) ui.table.getModel()).setRowCount(0);
        ui.table.fixTable(ui.jScrollPane1);
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(Object object) {
                loadDelete();
            }

            @Override
            public void update(Object object) {
                loadUpdate();
            }
        };
        for (Entity_Sale x : list) {
            ui.table.addRow(x.toRowTable(eventAction));
        }
        ui.table.setRowSelectionInterval(0, 0);
    }

    public void loadAdd() {
        Entity_Sale item = getItemSelect();
        if (!data.instance().insert(item)) {
            JOptionPane.showMessageDialog(uiContain, "Không thể thêm dữ liệu");
        } else {
            JOptionPane.showMessageDialog(uiContain, "Them thanh cong");
        }
        loadDefault();
        System.out.println("Thanh cong");
    }

    public void loadUpdate() {
        Entity_Sale item = getItemSelect();
        if (!data.instance().update(item)) {
            JOptionPane.showMessageDialog(uiContain, "Không thể cập nhật dữ liệu");
        }
        loadDefault();
    }

    public void loadSearch() {
        List<Entity_Sale> list;
        System.out.println("'"+textSearch+"'");
        if (textSearch == null || textSearch.equals("")) {
            textSearch = "";
            list = data.instance().getListsByQuery(" and " + dateSearch);
        } else {
            list = data.instance().getListsByQuery(" and namee like N'%" +textSearch +"%'" + " and " + dateSearch);
        }

        if (list == null) {
            loadDefault();
        } else {
            initTable(list);
        }
    }

    public void loadDelete() {
        Entity_Sale item = getItemSelect();
        if (JOptionPane.showConfirmDialog(uiContain, "Bạn thực sự muốn xóa!!", "Xác nhận", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
            if (!data.instance().delete(item.getId())) {
                JOptionPane.showMessageDialog(uiContain, "Không thể xóa dữ liệu");
            }
        }
        loadDefault();
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

    public void loadInfo() {
        int row = ui.table.getSelectedRow();
        System.out.println(ui.table.getValueAt(row, 0));
        Entity_Sale item = data.instance().getByID((int) ui.table.getValueAt(row, 0));
        ui.ID.setText(Integer.toString(item.getId()));
        ui.name.setText(item.getName());
        ui.count.setText(Integer.toString(item.getCount()));
        ui.gia.setText(Double.toString(item.getGiam()));
        ui.loai.setText(item.getType());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ui.dateB.setDate(sdf.parse(item.getDateB().toString()));
            ui.dateE.setDate(sdf.parse(item.getDateE().toString()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        ui.moTa.setText(item.getMoTa());
    }

    public void resetInfo() {
        ui.ID.setText("");
        ui.name.setText("");
        ui.count.setText("");
        ui.gia.setText("");
        ui.loai.setText("");
        ui.dateB.setDate(new Date());
        ui.dateE.setDate(new Date());
        ui.moTa.setText("");
    }

    public Entity_Sale getItemSelect() {
        return new Entity_Sale(
                Integer.parseInt(ui.ID.getText()),
                ui.name.getText(),
                Integer.parseInt(ui.count.getText()),
                Double.parseDouble(ui.gia.getText()),
                ui.dateB.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                ui.dateE.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                ui.loai.getText(),
                ui.moTa.getText()
        );
    }
}
