/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

import java.time.LocalDate;
import library.Table.EventAction;
import library.Table.ModelAction;

/**
 *
 * @author lengo
 */
public class Entity_Sale {
    private int id;
    private String name;
    private int count;
    private double giam;
    private LocalDate DateB;
    private LocalDate DateE;
    private String type;
    private String moTa;

    
    @Override
    public String toString() {
        return name;
    }

    public Object[] toRowTable(EventAction event) {
        return new Object[]{id, name, count, giam, DateB, DateE, type, new ModelAction(this, event)};
    }
    public Entity_Sale() {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getGiam() {
        return giam;
    }

    public void setGiam(double giam) {
        this.giam = giam;
    }

    public LocalDate getDateB() {
        return DateB;
    }

    public void setDateB(LocalDate DateB) {
        this.DateB = DateB;
    }

    public LocalDate getDateE() {
        return DateE;
    }

    public void setDateE(LocalDate DateE) {
        this.DateE = DateE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Entity_Sale(int id, String name, int count, double giam, LocalDate DateB, LocalDate DateE, String type, String moTa) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.giam = giam;
        this.DateB = DateB;
        this.DateE = DateE;
        this.type = type;
        this.moTa = moTa;
    }

}
