/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

/**
 *
 * @author lengo
 */
public class Entity_KichThuoc {
    private int id;
    private int kichThuoc;
    private String moTa;

    @Override
    public String toString() {
        return Integer.toString(kichThuoc);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(int kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Entity_KichThuoc(int id, int kichThuoc, String moTa) {
        this.id = id;
        this.kichThuoc = kichThuoc;
        this.moTa = moTa;
    }

    public Entity_KichThuoc() {
    }

}
