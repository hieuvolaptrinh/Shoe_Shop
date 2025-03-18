package Business_logic;

import ENTITY.Entity_Huyen;
import ENTITY.Entity_TaiKhoan;
import ENTITY.Entity_ThonXa;
import ENTITY.Entity_Tinh;
import ENTITY.Entity_XaPhuong;
import GUI.staff;
import Process_Data.FileDAO;
import Process_Data.SQL_DiaChi;
import Process_Data.SQL_NhanHang;
import Process_Data.SQL_TaiKhoan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.parser.Entity;
import library.Table.EventAction;
import org.apache.poi.ss.formula.ptg.TblPtg;

/**
 *
 * @author lengo
 */
public class Logic_Staff {

    private staff ui;
    private JPanel uiContain;
    private Logic_Admin_Home lgHome;
    private SQL_TaiKhoan data;
    private SQL_DiaChi dataDC;

    public Logic_Staff(staff ui) {
        this.ui = ui;
    }

    public void setlgHome(Logic_Admin_Home lg) {
        this.lgHome = lg;
        adEvent();
    }

    public void loadDefault() {
        List<Entity_TaiKhoan> list = data.instance().getLists();
        initTable(list);
    }

    public void loadTinh() {
        List<Entity_Tinh> list = dataDC.instance().getListTinh();
        ui.cbbTinh.removeAll();
        for (Entity_Tinh x : list) {
            ui.cbbTinh.addItem(x);
        }
        ui.cbbTinh.setSelectedIndex(0);
        Entity_Tinh t = (Entity_Tinh) ui.cbbTinh.getSelectedItem();
        loadHuyen(t.getId());
    }

    public void loadHuyen(int id) {
        List<ENTITY.Entity_Huyen> list = dataDC.instance().getListHuyen(id);
        ui.cbbHuyen.removeAllItems();
        for (Entity_Huyen x : list) {
            ui.cbbHuyen.addItem(x);
        }
        ui.cbbHuyen.setSelectedIndex(0);
        Entity_Huyen t = (Entity_Huyen) ui.cbbHuyen.getSelectedItem();
        loadXa(t.getId());
    }

    public void loadXa(int id) {
        List<ENTITY.Entity_XaPhuong> list = dataDC.instance().getListXaPhuong(id);
        ui.cbbXa.removeAllItems();
        for (Entity_XaPhuong x : list) {
            ui.cbbXa.addItem(x);
        }
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

        this.ui.txSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                resetInfo();
                loadSearch(ui.txSearch.getText());
            }
        });
        this.ui.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadInfo();
            }
        });

        ////cho tinh
        this.ui.cbbTinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHuyen(((Entity_Tinh) ui.cbbTinh.getSelectedItem()).getId());
            }
        });
        this.ui.cbbHuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadXa(((Entity_XaPhuong) ui.cbbXa.getSelectedItem()).getId());
            }
        });
    }

    public void initTable(List<Entity_TaiKhoan> list) {
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
        for (Entity_TaiKhoan x : list) {
            ui.table.addRow(x.toRowTable(eventAction));
        }
        ui.table.setRowSelectionInterval(0, 0);
    }

    public void loadAdd() {
        Entity_ThonXa tx = new Entity_ThonXa(-1, ui.tbDiaChi.getText(), ((Entity_XaPhuong) ui.cbbXa.getSelectedItem()).getId());
        dataDC.instance().insert(tx);
        tx.setId(dataDC.instance().getIDNext());
        Entity_TaiKhoan item = getItemSelect();
        item.setTinh(tx.getId());
        if (!data.instance().insert(item)) {
            JOptionPane.showMessageDialog(uiContain, "Không thể thêm dữ liệu");
        } else {
            JOptionPane.showMessageDialog(uiContain, "Them thanh cong");
        }
        loadDefault();
    }

    public void loadUpdate() {
        Entity_TaiKhoan item = getItemSelect();
        dataDC.instance().update(new Entity_ThonXa(item.getTinh(), ui.tbDiaChi.getText(), ((Entity_XaPhuong)ui.cbbXa.getSelectedItem()).getId()));
        if (!data.instance().update(item)) {
            JOptionPane.showMessageDialog(uiContain, "Không thể cập nhật dữ liệu");
        }
        loadDefault();
    }

    public void loadSearch(String valueSearch) {
        List<Entity_TaiKhoan> list = data.instance().getListsByName(valueSearch);
        if (list == null) {
            loadDefault();
        } else {
            initTable(list);
        }
    }

    public void loadDelete() {
        Entity_TaiKhoan item = getItemSelect();
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
        Entity_TaiKhoan item = data.instance().getByID((int) ui.table.getValueAt(row, 0));
        ui.ID.setText(Integer.toString(item.getId()));
        ui.name.setText(item.getName());
        ui.Email.setText(item.getEmail());
        ui.SDT.setText(item.getSDT());
        ui.cbbLoai.setSelectedItem(item.getLoai());
        Entity_Tinh t;
        Entity_Huyen h;
        Entity_XaPhuong x;
        Entity_ThonXa tx = dataDC.instance().getByID(item.getTinh());
        x = dataDC.instance().getXByID(tx.getIdParent());
        h = dataDC.instance().getHByID(x.getIdParent());
        t = dataDC.instance().getTByID(h.getIdParent());

        ui.cbbTinh.setSelectedItem(t);
        ui.cbbHuyen.setSelectedItem(h);
        ui.cbbXa.setSelectedItem(x);
        ui.tbDiaChi.setText(tx.getName());
    }

    public void resetInfo() {
        ui.ID.setText("");
        ui.name.setText("");
        ui.Email.setText("");
        ui.SDT.setText("");
    }

    public Entity_TaiKhoan getItemSelect() {
        int row = ui.table.getSelectedRow();
        System.out.println(ui.table.getValueAt(row, 0));
        Entity_TaiKhoan item = data.instance().getByID((int) ui.table.getValueAt(row, 0));
        item.setName(ui.name.getText());
        item.setEmail(ui.Email.getText());
        item.setSDT(ui.SDT.getText());
        item.setLoai(ui.cbbLoai.getSelectedItem().toString());
        return item;
    }
}
