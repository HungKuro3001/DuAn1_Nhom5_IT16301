/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.PhieuNhap;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuNhap_DAO extends Dao<PhieuNhap, String>{
      String insert ="INSERT PHIEUNHAP VALUES (?,?,?,?,?)";
      String update ="UPDATE PHIEUNHAP SET THANHTIEN =?, NGAYNHAP=?, NOINHAP=?,GHICHU=? WHERE MAPN=?";
      String selectAll= "SELECT * FROM PHIEUNHAP ";
      String selectByMaPN="select * from PHIEUNHAP WHERE MAPN=?";
    @Override
    public void insert(PhieuNhap entity) {
        jdbcHelper.Update(insert, entity.getMaPN(),entity.getThanhtien(),entity.getNgayNhap(),
                entity.getNoiNhap(),entity.getGhiChu());
    }

    @Override
    public void update(PhieuNhap entity) {
        jdbcHelper.Update(update, entity.getThanhtien(), entity.getNgayNhap(),entity.getNoiNhap(),entity.getGhiChu(),
                entity.getMaPN());
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PhieuNhap> selectAll() {
        List<PhieuNhap> list = this.selectBySql(selectAll);
        return list;
    }

    @Override
    public PhieuNhap selectById(String key) {
        List<PhieuNhap> list = this.selectBySql(selectByMaPN,key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public String maPDT_TuSinh() throws SQLException {
        String ma = null;
        String sql = "{CALL SP_MAPN}";
        ResultSet rs = jdbcHelper.query(sql);
        while (rs.next()) {
            ma = rs.getString(1);
        }
        return ma;
    }

    @Override
    protected List<PhieuNhap> selectBySql(String sql, Object... args) {
        try {
            List<PhieuNhap> list= new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {                
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getString(1));
                pn.setThanhtien(rs.getDouble(2));
                pn.setNgayNhap(rs.getDate(3));
                pn.setNoiNhap(rs.getString(4));
                pn.setGhiChu(rs.getString(5));
                list.add(pn);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
                
    }
    
}
