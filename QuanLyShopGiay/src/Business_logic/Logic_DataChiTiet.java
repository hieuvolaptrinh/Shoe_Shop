/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_logic;

import ENTITY.Entity_Anh;
import ENTITY.Entity_DanhMuc;
import ENTITY.Entity_Giay;
import ENTITY.Entity_GiayChiTiet;
import ENTITY.Entity_KichThuoc;
import ENTITY.Entity_Mau;
import ENTITY.Entity_NhanHang;
import GUI.dataInfo;
import Process_Data.FileDAO;
import Process_Data.SQL_Anh;
import Process_Data.SQL_DanhMuc;
import Process_Data.SQL_Giay;
import Process_Data.SQL_GiayChiTiet;
import Process_Data.SQL_KichThuoc;
import Process_Data.SQL_Mau;
import Process_Data.SQL_NhanHang;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.parser.Entity;
import library.Swing.ButtonMenu;
import library.Swing.ScrollBarCustom;
import library.Swing.resizeImage;
import library.Table.EventAction;
import library.Table.ModelAction;
import library.Table.ModelProfile;

/**
 *
 * @author lengo
 */
public class Logic_DataChiTiet {

    private dataInfo ui;
    private JPanel uiContain;
    private Logic_Admin_Home lgHome;
    private SQL_GiayChiTiet data;
    private SQL_Giay dataG;
    private SQL_Anh dataA;
    private SQL_KichThuoc dataKT;
    private SQL_Mau dataM;
    private SQL_NhanHang datan;
    private SQL_DanhMuc dataDM;
    private List<Entity_DanhMuc> upDM = new ArrayList<>();
    private List<Entity_Mau> upM = new ArrayList<>();
    private List<Entity_KichThuoc> upKT = new ArrayList<>();
    private List<String> upAnh = new ArrayList<>();
    private Entity_Giay glocal = new Entity_Giay();

    public Logic_DataChiTiet(dataInfo ui) {
        this.ui = ui;
    }

    public void setlgHome(Logic_Admin_Home lg) {
        this.lgHome = lg;
        adEvent();
    }

    public void LoadGiay(Entity_Giay giay) {
        this.glocal = giay;
        ui.name.setText(glocal.getName());
        ui.mota.setText(glocal.getDetail());
        ui.cbbNhanHang.setSelectedItem(dataDM.instance().getByID(glocal.getIdNhan()));
        List<Entity_DanhMuc> ldm = dataDM.instance().getLists(glocal.getId());
        if (ldm != null) {
            DefaultListModel defaultListModel2 = new DefaultListModel<>();
            for (Entity_DanhMuc dm : ldm) {
                defaultListModel2.addElement(dm);
            }
            ui.lDanhMuc.setModel(defaultListModel2);
        }
        loadDefault();
    }

    public void loadDefault() {
        System.out.println(glocal.getId());
        List<ENTITY.Entity_GiayChiTiet> list = data.instance().getListsByID(glocal.getId());
        initData(list);
    }

    public void initData(List<ENTITY.Entity_GiayChiTiet> list) {
        ((DefaultTableModel) ui.table.getModel()).setRowCount(0);
        ui.table.fixTable(ui.jScrollPane6);
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(Object object) {
                loadDelete();
                loadDefault();
                loadResetInfo();
            }

            @Override
            public void update(Object object) {
                loadUpdate();
                loadDefault();
                loadResetInfo();
            }
        };
        if (list == null) {
            return;
        }

        for (Entity_GiayChiTiet x : list) {
            Entity_Giay g = dataG.instance().getByID(x.getIdGiay());
            List<Entity_Anh> la = dataA.instance().getLists(x.getId());
            List<Entity_KichThuoc> lk = dataKT.instance().getLists(x.getId());
            List<Entity_Mau> lm = dataM.instance().getLists(x.getId());
            Entity_NhanHang n = datan.instance().getByID(g.getIdNhan());
            String mau = "";
            String kt = "";

            if (la != null && lk != null) {
                for (Entity_Mau k : lm) {
                    mau += k.getName() + ", ";
                }
                for (Entity_KichThuoc s : lk) {
                    kt += s.getMoTa() + ", ";
                }
                ui.table.addRow(
                        new Object[]{
                            x.getId(),
                            new ModelProfile(new ImageIcon(getImage(la.get(0).getId())), g.getName()),
                            n.getName(),
                            x.getGia(),
                            x.getSoluong(),
                            mau,
                            kt,
                            new ModelAction(this, eventAction)});
            }

        }
        ui.table.setRowSelectionInterval(0, 0);
        ui.table.getColumnModel().getColumn(0).setPreferredWidth(20);
        ui.table.getColumnModel().getColumn(1).setPreferredWidth(250);
    }

    public void initValue() {
        Entity_GiayChiTiet gct = data.instance().getByID((int) ui.table.getValueAt(ui.table.getSelectedRow(), 0));

        List<Entity_Anh> la = dataA.instance().getLists(gct.getId());
        ui.pnImage.removeAll();
        ui.pnImage.setLayout(new GridLayout(la.size(), 1));
        if (la == null) {
            return;
        }
        ui.img.setIcon(new ImageIcon(getImage(la.get(0).getId())));
        for (Entity_Anh x : la) {
            resizeImage img = new resizeImage();
            img.setIcon(new ImageIcon(getImage(x.getId())));
            img.setPreferredSize(new java.awt.Dimension(25, 110));
            ui.pnImage.add(img);
            img.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ui.img.setIcon(img.getIcon());
                }
            });
        }
        ui.pnImage.revalidate();
        ui.pnImage.repaint();
    }

    public void initColor() {
        ui.pnColor.removeAll();
        List<Entity_Mau> list = dataM.instance().getLists();
        ui.pnColor.setLayout(new GridLayout((list.size() / 2) + 1, 2, 4, 4));

        if (list == null) {
            return;
        }
        for (Entity_Mau x : list) {
            ButtonMenu btn = new ButtonMenu();
            btn.setText(x.getCode());
            btn.colorDefault = Color.decode(x.getCode().trim());
            btn.setHorizontalAlignment(JButton.LEFT);
            ui.pnColor.add(btn);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setDefaultBackground(ui.pnColor);
                    btn.setSelected(true);
                }

            });
        }
        ui.pnColor.revalidate();
        ui.pnColor.repaint();
    }

    public void setDefaultBackground(JPanel pn) {
        for (Component x : pn.getComponents()) {
            if (x instanceof ButtonMenu) {
                JButton btn = (JButton) x;
                btn.setSelected(false);
            }
        }
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

    public void initKichThuoc() {
        ui.cbbKichThuoc.removeAll();
        List<Entity_KichThuoc> list = dataKT.instance().getLists();
        for (Entity_KichThuoc x : list) {
            ui.cbbKichThuoc.addItem(x);
        }
    }

    public void initJscroll() {
        ui.jScrollPane6.setVerticalScrollBar(new ScrollBarCustom());
        ui.jScrollPane5.setVerticalScrollBar(new ScrollBarCustom());
        ui.jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        ui.jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        ui.jScrollPane4.setVerticalScrollBar(new ScrollBarCustom());
        ui.jScrollPane3.setVerticalScrollBar(new ScrollBarCustom());
        ui.jScrollPane8.setVerticalScrollBar(new ScrollBarCustom());
        ui.jScrollPane9.setVerticalScrollBar(new ScrollBarCustom());
    }

    public void adEvent() {
        this.ui.btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lgHome.initUI(3);
            }
        });
        this.ui.btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUpdate();
                loadDefault();
                loadResetInfo();
            }
        });

        this.ui.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDelete();
                loadDefault();
                loadResetInfo();
            }
        });
        this.ui.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAdd();
                loadDefault();
                loadResetInfo();
            }
        });
        this.ui.btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadXuatFile();
            }
        });

        this.ui.btnAddKT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAddKT();
            }
        });
        this.ui.btnAddDanhMuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAddDanhMuc();
            }
        });
        this.ui.txSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSearch(ui.txSearch.getText());
            }
        });
        this.ui.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadInfo();
            }
        });

        this.ui.btnAddMau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadAddMau();
            }
        });

        this.ui.btnAddImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadAddAnh();
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
    public void loadUpdate() {
        int id = (int) ui.table.getValueAt(ui.table.getSelectedRow(), 0);
        Entity_GiayChiTiet gct = data.instance().getByID(id);
        Entity_Giay g = dataG.instance().getByID(gct.getIdGiay());
        try {
            if (upDM != null && upDM.size() > 0) {
                for (Entity_DanhMuc x : upDM) {
                    dataDM.instance().insert(x, gct.getIdGiay());
                }
            }
            if (upKT != null && upKT.size() > 0) {
                for (Entity_KichThuoc x : upKT) {
                    dataKT.instance().insert(x.getId(), id);
                }
            }
            if (upM != null && upM.size() > 0) {
                for (Entity_Mau x : upM) {
                    dataM.instance().insert(x.getId(), id);
                }
            }

            List<Entity_Anh> la = dataA.instance().getLists(id);
            int number = la.size();
            String foldAnh = "";
            String nameAnh = "";
            List<Entity_Mau> list = dataM.instance().getLists(id);
            for (int i = 0; i < list.size() - 1; i++) {
                foldAnh += list.get(i).getName() + " ";
                nameAnh += list.get(i).getName() + "_";
            }
            foldAnh += list.get(list.size() - 1).getName();
            nameAnh += list.get(list.size() - 1).getName();

            if (upAnh != null && upAnh.size() > 0) {
                String pathSave = "D:\\OneDrive - University of Technology and "
                        + "Education\\Desktop\\QuanLyShopGiay\\Images\\Shoe image";
                for (String x : upAnh) {
                    nameAnh += "_" + number + ".png";
                    number++;
                    downloadImage(x, pathSave + "\\" + g.getName() + "\\" + foldAnh, "\\" + nameAnh);
                    dataA.instance().insert(new Entity_Anh("Images\\Shoe image" + "\\" + g.getName() + "\\" + foldAnh + "\\" + nameAnh, id));
                }
            }

            gct.setGia(Double.parseDouble(ui.Gia.getText()));
            gct.setSoluong(Integer.parseInt(ui.Soluong.getText()));
            data.instance().update(gct);
            g.setName(ui.name.getText());
            g.setDetail(ui.mota.getText());
            g.setIdNhan(((Entity_NhanHang) ui.cbbNhanHang.getSelectedItem()).getId());
            dataG.instance().update(g);
            JOptionPane.showMessageDialog(uiContain, "cap nhat thanh cong");

            upAnh = null;
            upDM = null;
            upKT = null;
            upM = null;
            loadDefault();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(uiContain, "Cap nhật thất bại");
            ex.printStackTrace();
        }

    }

    public void loadDelete() {
        int id = (int) ui.table.getValueAt(ui.table.getSelectedRow(), 0);
        if (JOptionPane.showConfirmDialog(uiContain, "Bạn thực sự muốn xóa!!", "Xác nhận", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
            if (!data.instance().delete(id)) {
                JOptionPane.showMessageDialog(uiContain, "Không thể xóa dữ liệu");
            }
        }
    }

    public void loadSearch(String value) {
        if (value.equals("")) {
            loadDefault();
        } else {
            List<Entity_GiayChiTiet> list = data.getListsByName(value);
            initData(list);
        }
    }

    public void loadResetInfo(){
        ui.pnImage.removeAll();
        ui.img.setIcon(null);
        ui.lKichThuoc.removeAll();
        ui.lMau.removeAll();
        ui.Gia.setText("");
        ui.Soluong.setText("");
    }
    public void loadInfo() {
        Entity_GiayChiTiet x = data.instance().getByID((int) ui.table.getValueAt(ui.table.getSelectedRow(), 0));
        Entity_Giay g = dataG.instance().getByID(x.getIdGiay());
        List<Entity_Anh> la = dataA.instance().getLists(x.getId());
        List<Entity_KichThuoc> lk = dataKT.instance().getLists(x.getId());
        List<Entity_DanhMuc> ldm = dataDM.instance().getLists(g.getId());
        List<Entity_Mau> lm = dataM.instance().getLists(x.getId());
        Entity_NhanHang n = datan.instance().getByID(g.getIdNhan());

        ui.name.setText(g.getName());
        ui.mota.setText(g.getDetail());
        ui.Gia.setText(Double.toString(x.getGia()));
        ui.Soluong.setText(Integer.toString(x.getSoluong()));
        ui.cbbNhanHang.setSelectedItem(n);
        if (lk != null) {
            DefaultListModel defaultListModel = new DefaultListModel<>();
            for (Entity_KichThuoc kt : lk) {
                defaultListModel.addElement(kt);
            }
            ui.lKichThuoc.setModel(defaultListModel);
        }
        if (ldm != null) {
            DefaultListModel defaultListModel2 = new DefaultListModel<>();
            for (Entity_DanhMuc dm : ldm) {
                defaultListModel2.addElement(dm);
            }
            ui.lDanhMuc.setModel(defaultListModel2);
        }
        if (lm != null) {
            DefaultListModel defaultListModel3 = new DefaultListModel<>();
            for (Entity_Mau dm : lm) {
                defaultListModel3.addElement(dm);
            }
            ui.lMau.setModel(defaultListModel3);
        }
        initValue();
    }

    public void loadAdd() {

        Entity_GiayChiTiet gct = new Entity_GiayChiTiet();
        gct.setIdGiay(glocal.getId());
        gct.setGia(Double.parseDouble(ui.Gia.getText()));
        gct.setSoluong(Integer.parseInt(ui.Soluong.getText()));
//
//        List<Entity_Mau> upM1 = new ArrayList<>();
//        List<Entity_KichThuoc> upKT1 = new ArrayList<>();
//        List<String> upAnh1 = new ArrayList<>();
//        
//        
//        ListModel<Entity_KichThuoc> model = ui.lKichThuoc.getModel();
//        for (int i = 0; i < model.getSize(); i++) {
//            Entity_KichThuoc x = model.getElementAt(i);
//            upKT1.add(x);
//        }
//        ListModel<Entity_Mau> model1 = ui.lMau.getModel();
//        for (int i = 0; i < model1.getSize(); i++) {
//            Entity_Mau x = model1.getElementAt(i);
//            upM1.add(x);
//        }
//        for(Component x: ui.pnImage.getComponents()){
//            resizeImage img = (resizeImage) x;
//            ImageIcon imageIcon = (ImageIcon) img.getIcon();
//            upAnh1.add(imageIcon.getDescription());
//        }

        data.instance().insert(gct);
        gct.setId(data.instance().getIDNext());

        try {
            if (upKT != null && upKT.size() > 0) {
                for (Entity_KichThuoc x : upKT) {
                    dataKT.instance().insert(gct.getId(), x.getId());
                }
            }
            if (upM != null && upM.size() > 0) {
                for (Entity_Mau x : upM) {
                    dataM.instance().insert(x.getId(), gct.getId());
                }
            }

            int number = 1;
            String foldAnh = "";
            String nameAnh = "";
            List<Entity_Mau> list = dataM.instance().getLists(gct.getId());
            if (list == null) {
                JOptionPane.showMessageDialog(uiContain, "Ban chua them mau cho san pham");
            } else {
                for (int i = 0; i < list.size() - 1; i++) {
                    foldAnh += list.get(i).getName() + " ";
                    nameAnh += list.get(i).getName() + "_";
                }
                foldAnh += list.get(list.size() - 1).getName();
                nameAnh += list.get(list.size() - 1).getName();

                if (upAnh != null && upAnh.size() > 0) {
                    String pathSave = "D:\\OneDrive - University of Technology and "
                            + "Education\\Desktop\\QuanLyShopGiay\\Images\\Shoe image";
                    for (String x : upAnh) {
                        nameAnh += "_" + number + ".png";
                        number++;
                        downloadImage(x, pathSave + "\\" + glocal.getName() + "\\" + foldAnh, "\\" + nameAnh);
                        dataA.instance().insert(new Entity_Anh("Images\\Shoe image" + "\\" + glocal.getName() + "\\" + foldAnh + "\\" + nameAnh, gct.getId()));
                    }
                }
            }

            JOptionPane.showMessageDialog(uiContain, "Them thanh cong");

            upAnh = null;
            upDM = null;
            upKT = null;
            upM = null;
            loadDefault();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(uiContain, "Cap nhật thất bại");
            ex.printStackTrace();
        }
    }

    public void loadXuatFile() {
        String nameFile = JOptionPane.showInputDialog("Nhập vào tên file cần lưu");
        String pathFile = System.getProperty("user.home") + "\\Downloads\\" + nameFile + ".xlsx";
        if (FileDAO.instance().file_Write(ui.table, pathFile, nameFile)) {
            JOptionPane.showMessageDialog(ui, "Xuất file thành công\nTruy cập đường dẫn để xem file : \n\"" + pathFile + "\"");
        }
    }

    public void loadAddKT() {
        Entity_KichThuoc kt = (Entity_KichThuoc) ui.cbbKichThuoc.getSelectedItem();
        boolean exists = false;
        ListModel<Entity_KichThuoc> model = ui.lKichThuoc.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            Entity_KichThuoc x = model.getElementAt(i);
            if (x.getId() == kt.getId()) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            DefaultListModel<Entity_KichThuoc> defaultListModel;
            if (model instanceof DefaultListModel) {
                defaultListModel = (DefaultListModel<Entity_KichThuoc>) model;
            } else {
                defaultListModel = new DefaultListModel<>();
                for (int i = 0; i < model.getSize(); i++) {
                    defaultListModel.addElement(model.getElementAt(i));
                }
                ui.lKichThuoc.setModel(defaultListModel);
            }
            upKT.add(kt);
            defaultListModel.addElement(kt);
        }
    }

    public void loadAddMau() {
        Entity_Mau m = null;
        for (Component x : ui.pnColor.getComponents()) {
            if (x instanceof ButtonMenu) {
                JButton btn = (JButton) x;
                if (btn.isSelected()) {
                    m = dataM.instance().getByCode(btn.getText().trim());
                    break;
                }
            }
        }
        if (m == null) {
            JOptionPane.showMessageDialog(uiContain, "Vui lòng chọn màu!!");
            return;
        }
        boolean exists = false;
        ListModel<Entity_Mau> model = ui.lMau.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            Entity_Mau x = model.getElementAt(i);
            if (x.getId() == m.getId()) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            DefaultListModel<Entity_Mau> defaultListModel;
            if (model instanceof DefaultListModel) {
                defaultListModel = (DefaultListModel<Entity_Mau>) model;
            } else {
                defaultListModel = new DefaultListModel<>();
                for (int i = 0; i < model.getSize(); i++) {
                    defaultListModel.addElement(model.getElementAt(i));
                }
                ui.lMau.setModel(defaultListModel);

            }
            upM.add(m);
            defaultListModel.addElement(m);
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

    public void loadAddAnh() {
        int id = (int) ui.table.getValueAt(ui.table.getSelectedRow(), 0);
        List<Entity_Anh> list = dataA.instance().getLists(id);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Image File");
        int returnValue = fileChooser.showOpenDialog(ui);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            upAnh.add(selectedFile.toString());
            ui.pnImage.setLayout(new GridLayout(list.size() + upAnh.size(), 1));
            resizeImage img = new resizeImage();
            img.setIcon(new ImageIcon(selectedFile.toString()));
            img.setPreferredSize(new java.awt.Dimension(25, 110));
            ui.pnImage.add(img);
            img.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ui.img.setIcon(img.getIcon());
                }
            });
            ui.img.setIcon(new ImageIcon(selectedFile.toString()));
            ui.pnImage.repaint();
            ui.pnImage.revalidate();
        }
    }

    private void downloadImage(String files, String pathSave, String name) {
        try {
            File file = new File(files);
            byte[] imageData = Files.readAllBytes(file.toPath());
            File directory = new File(pathSave);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            Files.write(new File(pathSave + name).toPath(), imageData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getImage(String part) {
        return "D:\\OneDrive - University of Technology and Education\\Desktop\\QuanLyShopGiay\\" + part;
    }

//    public void initOptionColor() {
//        for (Object x : ui.jpnColor.getComponents()) {
//            if (x instanceof JPanel) {
//                ((JPanel) x).addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        super.mouseClicked(e);
//                        for (Object k : ui.jpnColor.getComponents()) {
//                            if (k instanceof JPanel) {
//                                ((JPanel) k).setBorder(BorderFactory.createEmptyBorder());
//                            }
//                        }
//                        ((JPanel) x).setBorder(BorderFactory.createLineBorder(Color.white));
//                        if (((JPanel) x).getBackground() == Color.WHITE) {
//                            ui.jrbAll.setSelected(true);
//                        } else if (((JPanel) x).getBackground() == Color.RED) {
//                            ui.jrbRed.setSelected(true);
//                        } else if (((JPanel) x).getBackground() == Color.BLUE) {
//                            ui.jrbBlue.setSelected(true);
//                        } else if (((JPanel) x).getBackground() == Color.orange) {
//                            ui.jrbOrange.setSelected(true);
//                        } else if (((JPanel) x).getBackground() == Color.gray) {
//                            ui.jrbBrown.setSelected(true);
//                        } else {
//                            ui.jrbWhite.setSelected(true);
//                        }
//                    }
//                });
//            }
//        }
//    }
}
