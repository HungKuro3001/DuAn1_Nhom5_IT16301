/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.ChiTietPhieuNhap;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class chiTietPhieuNhap_DAO extends Dao<ChiTietPhieuNhap, String>{
    String insert ="INSERT CHITIETPHIEUNHAP VALUES (?,?,?,?,?,?)";
    String update="UPDATE CHITIETPHIEUNHAP SET SL=?,DONGIA=?,CONG=?,THANHTIEN=? where MAPN=? and MASP=?";
    String selectByMAPN= "SELECT * FROM CHITIETPHIEUNHAP WHERE MAPN=?";
    String delete="DELETE FROM CHITIETPHIEUNHAP WHERE MAPN=? and MASP=?";
    
    
    public List<ChiTietPhieuNhap> selectByMAPN(String key) {
        List<ChiTietPhieuNhap> list = this.selectBySql(selectByMAPN, key);
        return list;
    }
    public void deletePNCT(ChiTietPhieuNhap entity) {
        jdbcHelper.Update(delete,entity.getMaPN(),entity.getMaSp());
    }
    @Override
    public void insert(ChiTietPhieuNhap entity) {
        jdbcHelper.Update(insert, entity.getMaPN(),entity.getMaSp(),entity.getSoLuong(),entity.getDonGia(),
                entity.getCong(),entity.getThanhTien());
    }

    @Override
    public void update(ChiTietPhieuNhap entity) {
        jdbcHelper.Update(update, entity.getSoLuong(),entity.getDonGia(),entity.getCong(),entity.getThanhTien(),
                entity.getMaPN(),entity.getMaSp());
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ChiTietPhieuNhap> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChiTietPhieuNhap selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<ChiTietPhieuNhap> selectBySql(String sql, Object... args) {
        try {
            List<ChiTietPhieuNhap> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {                
                ChiTietPhieuNhap ctPn= new ChiTietPhieuNhap();
                ctPn.setMaPN(rs.getString(1));
                ctPn.setMaSp(rs.getString(2));
                ctPn.setSoLuong(rs.getInt(3));
                ctPn.setDonGia(rs.getDouble(4));
                ctPn.setCong(rs.getDouble(5));
                ctPn.setThanhTien(rs.getDouble(6));
                list.add(ctPn);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
