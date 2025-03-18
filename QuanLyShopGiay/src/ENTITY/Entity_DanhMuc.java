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
public class Entity_DanhMuc {

    private int id;
    private String name;

    @Override
    public String toString() {
        return name;
    }

    public Object[] toRowTable(EventAction event) {
        return new Object[]{id, name, new ModelAction(this, event)};
    }

    public Entity_DanhMuc() {
    }

    public Entity_DanhMuc(int id, String name) {
        this.id = id;
        this.name = name;
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

}
