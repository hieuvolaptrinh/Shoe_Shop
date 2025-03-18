/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

import library.Table.EventAction;
import library.Table.ModelAction;

/**
 *
 * @author lengo
 */
public class Entity_TaiKhoan {
    private int id;
    private String name;
    private String email;
    private String SDT;
    private String loai;
    private int tinh;
    private String matKhau;

    
    public Object[] toRowTable(EventAction event) {
        return new Object[]{id, name, email, SDT, loai,  new ModelAction(this, event)};
    }
    public Entity_TaiKhoan() {
    }

    public Entity_TaiKhoan(int id, String name, String email, String SDT, String loai, int tinh, String matKhau) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.SDT = SDT;
        this.loai = loai;
        this.tinh = tinh;
        this.matKhau = matKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getTinh() {
        return tinh;
    }

    public void setTinh(int tinh) {
        this.tinh = tinh;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    
}
