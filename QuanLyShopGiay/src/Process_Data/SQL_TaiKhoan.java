/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process_Data;

import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author lengo
 */
public class SQL_TaiKhoan {
    public static SQL_TaiKhoan instance ;
    
    public static SQL_TaiKhoan instance(){
        if(instance == null) instance = new SQL_TaiKhoan();
        return instance;
    }
    
    public List<ENTITY.Entity_TaiKhoan> runQuery(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_TaiKhoan> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_TaiKhoan x = new ENTITY.Entity_TaiKhoan(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(3)
                        
                );
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi ACCOUNTS dao");
        }
        return null;
    }
    
    
    
    public int getIDNext(){
        String query = "SELECT IDENT_CURRENT('ACCOUNTS')";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue() + 1;
    }
    
    public boolean insert(ENTITY.Entity_TaiKhoan item){
        String query = "INSERT INTO ACCOUNTS (NAMEE, PASSWORDD, EMAIL, PHONE,  POSITION, ID_ADDRESS) " +
                        "VALUES (?,'12345678',?, ?, ?, ? )";
        return DataProvider.instance().ExcuteNoneQuery(query, 
                item.getName(),
                item.getEmail(),
                item.getSDT(),
                item.getLoai(),
                item.getTinh()
                ) >0 ;
    }
    
    public boolean update(ENTITY.Entity_TaiKhoan item){
        String query = "UPDATE ACCOUNTS SET "
                + "NAMEe = ?,"
                + "EMAIL = ?,"
                + "PHONE = ?,"
                + "POSITION = ?,"
                + "ID_ADDRESS = ?,"
                + "PASSWORDD = ?"
                + " where id = ? ";
        return DataProvider.instance().ExcuteNoneQuery(query, 
                item.getName(), 
                 item.getEmail(),
                item.getSDT(),
                item.getLoai(),
                item.getTinh(),
                item.getMatKhau(),
                item.getId()) >0 ;        
    }
    
    public boolean delete(int id){
        String query = "update ACCOUNTS set statuss = 0 where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, id)>0;
    }
    
    public List<ENTITY.Entity_TaiKhoan> getLists(){
        String query = "select * from ACCOUNTS where statuss = 1";
        List<ENTITY.Entity_TaiKhoan> lists = runQuery(query);
        return lists.size() > 0 ? lists: null;
    }
    
    public ENTITY.Entity_TaiKhoan getByID(int id){
        String query = "select * from ACCOUNTS where statuss = 1 and id =  ?";
        List<ENTITY.Entity_TaiKhoan> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public List<ENTITY.Entity_TaiKhoan> getListsByName(String name){
        String query = "select * from ACCOUNTS where namee like ?";
        List<ENTITY.Entity_TaiKhoan> lists = runQuery(query,"%"+ name+"%");
        return lists.size() > 0 ? lists: null;
    }
    public ENTITY.Entity_TaiKhoan getByNamePass(String name, String pass){
        String query = "select * from ACCOUNTS where statuss = 1 and namee like ? and PASSWORDD = ?";
        List<ENTITY.Entity_TaiKhoan> lists = runQuery(query, name, pass);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    public int getCount() {
        String query = "select count(id) from ACCOUNTS where POSITION like N'User'";
        return (int) DataProvider.instance().ExcuteScalaQuery(query);
    }

}
