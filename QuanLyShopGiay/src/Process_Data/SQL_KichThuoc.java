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
public class SQL_KichThuoc {
    public static SQL_KichThuoc instance ;
    
    public static SQL_KichThuoc instance(){
        if(instance == null) instance = new SQL_KichThuoc();
        return instance;
    }
    
    public List<ENTITY.Entity_KichThuoc> runQuery(String Query, Object...paramenter){
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_KichThuoc> lists = new ArrayList<>();
            
            while(rs.next()){
                ENTITY.Entity_KichThuoc x = new ENTITY.Entity_KichThuoc(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi SIZES dao");
        }
        return null;
    }
    
    public int getIDNext(){
        String query = "SELECT IDENT_CURRENT('SIZES')";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue() ;
    }
    
    public boolean insert(int idDetailShoe, int idSize){
        String query = "INSERT INTO DETAIL_SIZES (ID_DETAIL_SHOE, ID_SIZE) " +
                        "VALUES (?,?)";
        return DataProvider.instance().ExcuteNoneQuery(query, idDetailShoe, idSize) >0 ;
    }
    
//    public boolean update(ENTITY.Entity_KichThuoc item){
//        String query = "UPDATE SIZES SET ID = ?, ID_DETAIL_SHOE = ? where id = ?";
//        return DataProvider.instance().ExcuteNoneQuery(query, item.getName(), item.getId()) >0 ;        
//    }
    
//    public boolean delete(int id){
//        String query = "update SIZES set statuss = 0 where id = ?";
//        return DataProvider.instance().ExcuteNoneQuery(query, id)>0;
//    }
    
    public List<ENTITY.Entity_KichThuoc> getLists(int idShoesDetailt){
        String query = "select SIZES.* from DETAIL_SIZES, SIZES  where  DETAIL_SIZES.ID_SIZE = SIZES.ID and DETAIL_SIZES.ID_DETAIL_SHOE = ?";
        List<ENTITY.Entity_KichThuoc> lists = runQuery(query, idShoesDetailt);
        return lists.size() > 0 ? lists: null;
    }
    
    public List<ENTITY.Entity_KichThuoc> getLists(){
        String query = "select SIZES.* from  SIZES ";
        List<ENTITY.Entity_KichThuoc> lists = runQuery(query);
        return lists.size() > 0 ? lists: null;
    }
    
    public ENTITY.Entity_KichThuoc getByID(int id){
        String query = "select * from SIZES where id =  ?";
        List<ENTITY.Entity_KichThuoc> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0): null;
    }
    
    

}
