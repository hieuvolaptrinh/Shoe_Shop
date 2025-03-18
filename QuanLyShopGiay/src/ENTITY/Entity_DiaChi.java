/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

/**
 *
 * @author lengo
 */
public class Entity_DiaChi {
    private String chiTiet;
    private int phuongXa;
    private int huyen;
    private int tinh;

    public Entity_DiaChi() {
    }

    public Entity_DiaChi(String chiTiet, int phuongXa, int huyen, int tinh) {
        this.chiTiet = chiTiet;
        this.phuongXa = phuongXa;
        this.huyen = huyen;
        this.tinh = tinh;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public int getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(int phuongXa) {
        this.phuongXa = phuongXa;
    }

    public int getHuyen() {
        return huyen;
    }

    public void setHuyen(int huyen) {
        this.huyen = huyen;
    }

    public int getTinh() {
        return tinh;
    }

    public void setTinh(int tinh) {
        this.tinh = tinh;
    }
    
    
}
