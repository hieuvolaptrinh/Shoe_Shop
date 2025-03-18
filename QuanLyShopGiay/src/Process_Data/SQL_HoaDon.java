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
public class SQL_HoaDon {

    public static SQL_HoaDon instance;

    public static SQL_HoaDon instance() {
        if (instance == null) {
            instance = new SQL_HoaDon();
        }
        return instance;
    }

    public List<ENTITY.Entity_HoaDon> runQuery(String Query, Object... paramenter) {
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_HoaDon> lists = new ArrayList<>();

            while (rs.next()) {
                ENTITY.Entity_HoaDon x = new ENTITY.Entity_HoaDon(
                        rs.getInt(1),
                        rs.getDate(2).toLocalDate(),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(11));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi hOADON dao");
        }
        return null;
    }

    public int getIDNext() {
        String query = "SELECT IDENT_CURRENT('BILLS')";
        return ((BigDecimal) DataProvider.instance().ExcuteScalaQuery(query)).intValue();
    }

//    public boolean insert(ENTITY.Entity_HoaDon item) {
//        String query = "INSERT INTO BILLS "
//                + "VALUES (?,?)";
//        return DataProvider.instance().ExcuteNoneQuery(query, item.getId(), item.getIdGiay()) > 0;
//    }
//    public boolean update(ENTITY.Entity_HoaDon item){
//        String query = "UPDATE BILLS SET ID = ?, ID_DETAIL_SHOE = ? where id = ?";
//        return DataProvider.instance().ExcuteNoneQuery(query, item.getName(), item.getId()) >0 ;        
//    }
//    public boolean delete(int id){
//        String query = "update BILLS set statuss = 0 where id = ?";
//        return DataProvider.instance().ExcuteNoneQuery(query, id)>0;
//    }
    public List<ENTITY.Entity_HoaDon> getLists(int idShoesDetailt) {
        String query = "select * from BILLS where ID_DETAIL_SHOE = ?";
        List<ENTITY.Entity_HoaDon> lists = runQuery(query, idShoesDetailt);
        return lists.size() > 0 ? lists : null;
    }

    public List<ENTITY.Entity_HoaDon> getLists() {
        String query = "select * from BILLS ";
        List<ENTITY.Entity_HoaDon> lists = runQuery(query);
        return lists.size() > 0 ? lists : null;
    }

    public ENTITY.Entity_HoaDon getByID(int id) {
        String query = "select * from BILLS where id =  ?";
        List<ENTITY.Entity_HoaDon> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0) : null;
    }

    public int getCoutByIDShoe(int idDetailShoes) {
        String query = "select count(DETAIL_BILLS.ID_DETAIL_SHOE) from DETAIL_BILLS, BILLS "
                + "where DETAIL_BILLS.ID_BILL = BILLS.ID and DATECHECKOUT is not null and DETAIL_BILLS.ID_DETAIL_SHOE = ? "
                + "group by DETAIL_BILLS.ID_DETAIL_SHOE";
        return (int) DataProvider.instance().ExcuteScalaQuery(query, idDetailShoes);
    }

    public int getCountAccept() {
        String query = "select count(id) from BILLS where ID_STATUS_BILL = 1";
        return (int) DataProvider.instance().ExcuteScalaQuery(query);
    }

    public double getDoanhThu() {
        String query = "select sum(countt * PRICE) from BILLS, DETAIL_BILLS "
                + "where BILLS.ID = DETAIL_BILLS.ID_BILL and BILLS.ID_STATUS_BILL = 1";
        return ((BigDecimal)DataProvider.instance().ExcuteScalaQuery(query)).intValue();
    }
    
    public int getCount() {
        String query ="select count(id) from bills";
        return (int) DataProvider.instance().ExcuteScalaQuery(query);
    }

}
