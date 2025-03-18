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
public class Entity_Giay {
    private int id;
    private String name;
    private String detail;
    private int idNhan;

//    public Object[] toRowTable(EventAction event) {
//        return new Object[]{id, name,   new ModelAction(this, event)};
//    }
    
    @Override
    public String toString() {
        return name;
    }

    
    public Entity_Giay() {
    }

    public Entity_Giay(int id, String name, String detail, int idNhan) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.idNhan = idNhan;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getIdNhan() {
        return idNhan;
    }

    public void setIdNhan(int idNhan) {
        this.idNhan = idNhan;
    }
    
    
}
