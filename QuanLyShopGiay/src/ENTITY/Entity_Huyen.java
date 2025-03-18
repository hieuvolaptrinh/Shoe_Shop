/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

/**
 *
 * @author lengo
 */
public class Entity_Huyen {
    private int id;
    private String name;
    private int idParent;

    @Override
    public String toString() {
        return name;
    }

    
    public Entity_Huyen() {
    }

    public Entity_Huyen(int id, String name, int idParent) {
        this.id = id;
        this.name = name;
        this.idParent = idParent;
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

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    
    
}
