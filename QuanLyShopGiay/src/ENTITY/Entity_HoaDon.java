/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

import java.time.LocalDate;

/**
 *
 * @author lengo
 */
public class Entity_HoaDon {
    private int id;
    private LocalDate DateIn;
    private LocalDate DateOu;
    private String ghiChu;
    private int thanhToan;
    private int taiKhoan;
    private int diaChi;
    private int vanChuyen;
    private int giamGia;
    private int trangThai;

    public Entity_HoaDon() {
    }

    public Entity_HoaDon(int id, LocalDate DateIn, LocalDate DateOu, String ghiChu, int thanhToan, int taiKhoan, int diaChi, int vanChuyen, int giamGia, int trangThai) {
        this.id = id;
        this.DateIn = DateIn;
        this.DateOu = DateOu;
        this.ghiChu = ghiChu;
        this.thanhToan = thanhToan;
        this.taiKhoan = taiKhoan;
        this.diaChi = diaChi;
        this.vanChuyen = vanChuyen;
        this.giamGia = giamGia;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateIn() {
        return DateIn;
    }

    public void setDateIn(LocalDate DateIn) {
        this.DateIn = DateIn;
    }

    public LocalDate getDateOu() {
        return DateOu;
    }

    public void setDateOu(LocalDate DateOu) {
        this.DateOu = DateOu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(int thanhToan) {
        this.thanhToan = thanhToan;
    }

    public int getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(int taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public int getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(int diaChi) {
        this.diaChi = diaChi;
    }

    public int getVanChuyen() {
        return vanChuyen;
    }

    public void setVanChuyen(int vanChuyen) {
        this.vanChuyen = vanChuyen;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
}
