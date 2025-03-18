/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process_Data;

import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import ENTITY.*;

/**
 *
 * @author lengo
 */
public class SQL_DiaChi {
    public static SQL_DiaChi instance ;
    
    public static SQL_DiaChi instance(){
        if(instance == null) instance = new SQL_DiaChi();
        return instance;
    }
    
    public List<ENTITY.Entity_Tinh> runQuery1(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_Tinh> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_Tinh x = new ENTITY.Entity_Tinh(
                        rs.getInt(1),
                        rs.getString(2));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi trademarks dao");
        }
        return null;
    }
    public List<ENTITY.Entity_Huyen> runQuery2(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_Huyen> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_Huyen x = new ENTITY.Entity_Huyen(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi trademarks dao");
        }
        return null;
    }
    public List<ENTITY.Entity_XaPhuong> runQuery3(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_XaPhuong> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_XaPhuong x = new ENTITY.Entity_XaPhuong(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi trademarks dao");
        }
        return null;
    }
        public List<ENTITY.Entity_ThonXa> runQuery4(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_ThonXa> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_ThonXa x = new ENTITY.Entity_ThonXa(
                        rs.getInt(1),
                        rs.getString(3),
                        rs.getInt(2));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi trademarks dao");
        }
        return null;
    }
    public List<Entity_Tinh> getListTinh(){
        String query = "select * from PROVINCES";
        List<ENTITY.Entity_Tinh> lists = runQuery1(query);
        return lists.size() > 0 ? lists: null;
    }
    public List<Entity_Huyen> getListHuyen(int id){
        String query = "select * from DISTRICTS where id_PROVINCE = ?";
        List<ENTITY.Entity_Huyen> lists = runQuery2(query, id);
        return lists.size() > 0 ? lists: null;
    }
    
    public List<Entity_XaPhuong> getListXaPhuong(int id){
        String query = "select * from COMMUNES where id_DISTRICT = ?";
        List<ENTITY.Entity_XaPhuong> lists = runQuery3(query, id);
        return lists.size() > 0 ? lists: null;
    }

    
    public String getAddress(int id){
        String query = "select  getAddress ?";
        return (String) DataProvider.instance().ExcuteScalaQuery(query);
    }
    
    public ENTITY.Entity_Tinh getTByID(int id){
        String query = "select * from PROVINCES where id =  ?";
        List<ENTITY.Entity_Tinh> lists = runQuery1(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public ENTITY.Entity_Huyen getHByID(int id){
        String query = "select * from DISTRICTS where id =  ?";
        List<ENTITY.Entity_Huyen> lists = runQuery2(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    public ENTITY.Entity_XaPhuong getXByID(int id){
        String query = "select * from COMMUNES where id =  ?";
        List<ENTITY.Entity_XaPhuong> lists = runQuery3(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public ENTITY.Entity_ThonXa getByID(int id){
        String query = "select * from ADDRESSS where id =  ?";
        List<ENTITY.Entity_ThonXa> lists = runQuery4(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public int getIDNext(){
        String query = "SELECT IDENT_CURRENT('ADDRESSS')";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue();
    }
    
    public boolean insert(ENTITY.Entity_ThonXa item){
        String query = "INSERT INTO ADDRESSS (ID_COMMUNES, DETAIL) " +
            "VALUES (?, ?)";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getIdParent(),item.getName()) >0 ;
    }
    public boolean update(ENTITY.Entity_ThonXa item){
        String query = "update addresss set  id_communes = ? ,  detail = ?  where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getIdParent(),item.getName(), item.getId()) >0 ;
    }
}
