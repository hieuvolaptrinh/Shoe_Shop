/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

/**
 *
 * @author lengo
 */
public class Entity_HoaDonChiTiet {

    private int id;
    private int idHoaDon;
    private int idGiay;
    private int soLuong;
    private double gia;

    public Entity_HoaDonChiTiet() {
    }

    public Entity_HoaDonChiTiet(int id, int idHoaDon, int idGiay, int soLuong, double gia) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idGiay = idGiay;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdGiay() {
        return idGiay;
    }

    public void setIdGiay(int idGiay) {
        this.idGiay = idGiay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
     

}
