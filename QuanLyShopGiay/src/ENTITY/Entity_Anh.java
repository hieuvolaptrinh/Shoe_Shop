/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

/**
 *
 * @author lengo
 */
public class Entity_Anh {
    private String id;
    private int idGiay;

    public Entity_Anh() {
    }

    public Entity_Anh(String id, int idGiay) {
        this.id = id;
        this.idGiay = idGiay;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdGiay() {
        return idGiay;
    }

    public void setIdGiay(int idGiay) {
        this.idGiay = idGiay;
    }
    
    
}
