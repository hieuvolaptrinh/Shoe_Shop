/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_logic;




import ENTITY.Entity_TaiKhoan;
import GUI.Login;
import GUI.adminHome;
import Process_Data.SQL_TaiKhoan;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.html.parser.Entity;

/**
 *
 * @author anreal
 */
public class Logic_DangNhap {

    Entity_TaiKhoan account;
    Login UIlg;
    SQL_TaiKhoan data;
    public Logic_DangNhap(Login lg) {
        this.UIlg = lg;
    }

    public void loginBtnDn() {
        String user = UIlg.name3.getText();
        String password = UIlg.pass.getText();

        if (!checkInFo(user) || !checkInFo(password)) {
            JOptionPane.showMessageDialog(UIlg, "Tài khoản hoặc mật khẩu nhập sai định dạng");
            return;
        }
        if (login(user, password)) {
            UIlg.dispose();
            new adminHome().show();
        } else {
            JOptionPane.showMessageDialog(UIlg, "Bạn nhập sai mật khẩu hoặc tên đăng nhập", "Thông báo lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void toggleSeePass() {
        char seePass = UIlg.pass.getEchoChar();
        if (seePass == 0) {
            UIlg.pass.setEchoChar('\u2022');
        } else {
            UIlg.pass.setEchoChar((char) 0);
        }
    }

    public boolean checkInFo(String input) {
        if (input.equals("")) {
            return false;
        }
        if (!Pattern.matches("\\w+", input)) {
            return false;
        }
        return true;
    }

    private boolean login(String user, String password) {
        Entity_TaiKhoan tk = data.instance().getByNamePass(user, password);
        if(tk != null){
            return true;
        }return false;
    }
}
