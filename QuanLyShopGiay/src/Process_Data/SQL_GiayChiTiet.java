/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process_Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import ENTITY.*;

/**
 *
 * @author lengo
 */
public class SQL_GiayChiTiet {

    public static SQL_GiayChiTiet instance;

    public static SQL_GiayChiTiet instance() {
        if (instance == null) {
            instance = new SQL_GiayChiTiet();
        }
        return instance;
    }

    public List<ENTITY.Entity_GiayChiTiet> runQuery(String Query, Object... paramenter) {
        try {
            ResultSet rs = DataProvider.instance().ExcuteQuery(Query, paramenter);
            List<ENTITY.Entity_GiayChiTiet> lists = new ArrayList<>();

            while (rs.next()) {
                ENTITY.Entity_GiayChiTiet x = new ENTITY.Entity_GiayChiTiet(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getInt(4));
                lists.add(x);
            }
            return lists;
        } catch (SQLException ex) {
            System.out.println("get list loi giayChiTiet dao");
        }
        return null;
    }

    public int getIDNext() {
        String query = "SELECT IDENT_CURRENT('DETAIL_SHOES')";
        return ((BigDecimal) DataProvider.instance().ExcuteScalaQuery(query)).intValue();
    }

    public boolean insert(ENTITY.Entity_GiayChiTiet item) {
        String query = "INSERT INTO DETAIL_SHOES (ID_SHOE, PRICE, COUNTT) "
                + "VALUES (?,?,?)";
        return DataProvider.instance().ExcuteNoneQuery(query, item.getIdGiay(), item.getGia(), item.getSoluong()) > 0;
    }

    public boolean update(ENTITY.Entity_GiayChiTiet item) {
        String query = "UPDATE DETAIL_SHOES SET"
                + " ID_SHOE = ?, PRICE = ?, COUNTT = ? where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query,
                item.getIdGiay(),
                item.getGia(),
                item.getSoluong(),
                item.getId()) > 0;
    }

    public boolean delete(int id) {
        String query = "update DETAIL_SHOES set statuss = 0 where id = ?";
        return DataProvider.instance().ExcuteNoneQuery(query, id) > 0;
    }

    public List<ENTITY.Entity_GiayChiTiet> getListsByID(int idDetailShoes) {
        String query = "select * from DETAIL_SHOES where statuss = 1 and ID_SHOE = ?";
        List<ENTITY.Entity_GiayChiTiet> lists = runQuery(query, idDetailShoes);
        return lists.size() > 0 ? lists : null;
    }

    public ENTITY.Entity_GiayChiTiet getByID(int id) {
        String query = "select * from DETAIL_SHOES where statuss = 1 and id =  ?";
        List<ENTITY.Entity_GiayChiTiet> lists = runQuery(query, id);
        return lists.size() > 0 ? lists.get(0) : null;
    }

    public List<ENTITY.Entity_GiayChiTiet> getListsByName(String name) {
        String query = "select * from DETAIL_SHOES where namee like ?";
        List<ENTITY.Entity_GiayChiTiet> lists = runQuery(query, "%" + name + "%");
        return lists.size() > 0 ? lists : null;
    }

    public List<ENTITY.Entity_GiayChiTiet> getListsTop() {
        String query = "SELECT DETAIL_SHOES.* FROM DETAIL_SHOES,"
                + "(select count(DETAIL_BILLS.ID_DETAIL_SHOE) 'soluon', DETAIL_BILLS.ID_DETAIL_SHOE AS 'ID' from DETAIL_BILLS, BILLS "
                + "where DETAIL_BILLS.ID_BILL = BILLS.ID and DATECHECKOUT is not null "
                + "group by DETAIL_BILLS.ID_DETAIL_SHOE) AS BANG2 "
                + "WHERE BANG2.ID = DETAIL_SHOES.ID ";
        List<ENTITY.Entity_GiayChiTiet> lists = runQuery(query);
        return lists.size() > 0 ? lists : null;
    }
    
    
    public int getCount() {
        String query = "select count(id) from DETAIL_SHOES";
        return (int) DataProvider.instance().ExcuteScalaQuery(query);
    }
}
