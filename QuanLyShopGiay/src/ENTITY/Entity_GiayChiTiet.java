/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

/**
 *
 * @author lengo
 */
public class Entity_GiayChiTiet {
    private int id;
    private int idGiay;
    private double gia;
    private int soluong;

    public Entity_GiayChiTiet() {
    }

    public Entity_GiayChiTiet(int id, int idGiay, double gia, int soluong) {
        this.id = id;
        this.idGiay = idGiay;
        this.gia = gia;
        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGiay() {
        return idGiay;
    }

    public void setIdGiay(int idGiay) {
        this.idGiay = idGiay;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
}
