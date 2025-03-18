
package Business_logic;

import ENTITY.Entity_NhanHang;
import GUI.trademark;
import Process_Data.FileDAO;
import Process_Data.SQL_NhanHang;
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
import library.Table.EventAction;


/**
 *
 * @author lengo
 */
public class Logic_TradeMark {

    private trademark ui;
    private JPanel uiContain;
    private Logic_Admin_Home lgHome;
    private SQL_NhanHang data;

    public Logic_TradeMark(trademark ui) {
        this.ui = ui;
    }

    public void setlgHome(Logic_Admin_Home lg) {
        this.lgHome = lg;
        adEvent();
    }

    public void loadDefault() {
        List<Entity_NhanHang> list = data.instance().getLists();
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
    }

    public void initTable(List<Entity_NhanHang> list) {
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
        for (Entity_NhanHang x : list) {
            ui.table.addRow(x.toRowTable(eventAction));
        }
        ui.table.setRowSelectionInterval(0, 0);
    }

    public void loadAdd() {
        Entity_NhanHang item = getItemSelect();
        if (!data.instance().insert(item)) {
            JOptionPane.showMessageDialog(uiContain, "Không thể thêm dữ liệu");
        }else JOptionPane.showMessageDialog(uiContain, "Them thanh cong");
        loadDefault();
        System.out.println("Thanh cong");
    }

    public void loadUpdate() {
        Entity_NhanHang item = getItemSelect();
        if (!data.instance().update(item)) {
            JOptionPane.showMessageDialog(uiContain, "Không thể cập nhật dữ liệu");
        }
        loadDefault();
    }

    public void loadSearch(String valueSearch) {
        List<Entity_NhanHang> list = data.instance().getListsByName(valueSearch);
        if (list == null) {
            loadDefault();
        } else {
            initTable(list);
        }
    }

    public void loadDelete() {
        Entity_NhanHang item = getItemSelect();
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
        String pathFile = System.getProperty("user.home") + "\\Downloads\\"+nameFile+".xlsx";
        if(FileDAO.instance().file_Write(ui.table, pathFile, nameFile)){
            JOptionPane.showMessageDialog(ui, "Xuất file thành công\nTruy cập đường dẫn để xem file : \n\"" + pathFile +"\"");
        }
    }

    
    public void loadInfo(){
        int row = ui.table.getSelectedRow();
        System.out.println(ui.table.getValueAt(row, 0));
        Entity_NhanHang item = new Entity_NhanHang((int)ui.table.getValueAt(row, 0), (String) ui.table.getValueAt(row, 1));
        ui.ID.setText(Integer.toString(item.getId()));
        ui.name.setText(item.getName());
    }
    
    public void resetInfo(){
        ui.ID.setText("");
        ui.name.setText("");
    }
    public Entity_NhanHang getItemSelect() {
        return new Entity_NhanHang(Integer.parseInt(ui.ID.getText()), ui.name.getText());
    }
}
