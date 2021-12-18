/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.DoiTra;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DoiTra_Dao extends Dao<DoiTra, String> {

    String insert = "INSERT DOITRA values (?,?,?,?,?,?,?)";
    String update = "UPDATE DOITRA SET MAHD=?,MASP=?,HINHTHUC=?,LYDO=?,GHICHU=? WHERE MAPDT=?";
    String selectAll = "SELECT * FROM DOITRA ORDER BY THOIGIAN DESC";

    @Override
    public void insert(DoiTra entity) {
        jdbcHelper.Update(insert, entity.getMaPDT(), entity.getMaHD(), entity.getMaSP(),
                entity.isHinhThuc(), entity.getLiDo(), entity.getGhiChu(),entity.getNgayDT());
    }

    @Override
    public void update(DoiTra entity) {
        jdbcHelper.Update(update, entity.getMaHD(), entity.getMaSP(), entity.isHinhThuc(),
                entity.getLiDo(), entity.getGhiChu(), entity.getMaPDT());
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DoiTra> selectAll() {
        List<DoiTra> list = this.selectBySql(selectAll);
        return list;
    }

    @Override
    public DoiTra selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String maPDT_TuSinh() throws SQLException {
        String ma = null;
        String sql = "{CALL SP_MAPDT}";
        ResultSet rs = jdbcHelper.query(sql);
        while (rs.next()) {
            ma = rs.getString(1);
        }
        return ma;
    }

    @Override
    protected List<DoiTra> selectBySql(String sql, Object... args) {
        try {
            List<DoiTra> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                DoiTra dt = new DoiTra();
                dt.setMaPDT(rs.getString(1));
                dt.setMaHD(rs.getString(2));
                dt.setMaSP(rs.getString(3));
                dt.setHinhThuc(rs.getBoolean(4));
                dt.setLiDo(rs.getString(5));
                dt.setGhiChu(rs.getString(6));
                dt.setNgayDT(rs.getDate(7));
                list.add(dt);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
