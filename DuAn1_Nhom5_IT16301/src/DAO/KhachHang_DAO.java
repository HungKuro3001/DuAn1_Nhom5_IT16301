/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.KhachHang;
import Entity.NhanVien;
import Helper.jdbcHelper;
import static java.nio.file.Files.list;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Administrator
 */
public class KhachHang_DAO extends Dao<KhachHang , String>{
    String selectAll = "SELECT * FROM KHACHHANG";
    String insert = "Insert into KHACHHANG values(?,?,?,?,?,?,0)";
    String update = "Update KHACHHANG set HOTEN=?, SDT=?,GIOITINH=?,DIACHI=?,GHICHU=? where MAKH=?";
    String updateTT = "Update KHACHHANG set TRANGTHAI = ? where MAKH=?";
    String selectBySDT = "Select * from KHACHHANG where SDT like %?%";
    
    @Override
    public void insert(KhachHang entity) {
        jdbcHelper.Update(insert, entity.getMaKh(), entity.getHoTen(),entity.getSdt(),entity.isGioiTinh(),entity.getDiaChi(),entity.getGhiChu());
    }

    @Override
    public void update(KhachHang entity) {
        jdbcHelper.Update(update, entity.getHoTen(),entity.getSdt(),entity.isGioiTinh(),entity.getDiaChi(),entity.getGhiChu(),entity.getMaKh());
    }
    public void updateTT(KhachHang entity){
        jdbcHelper.Update(updateTT, entity.isTrangThai(),entity.getMaKh());
    }
    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<KhachHang> selectAllKH() {//select những khach hàng không bị khóa
        String selectAllKH ="SELECT * FROM KHACHHANG where TRANGTHAI=0";
        List<KhachHang> list = this.selectBySql(selectAllKH);
        return list;
    }
    
    @Override
    public List<KhachHang> selectAll() {
        List<KhachHang> list = this.selectBySql(selectAll);
        return list;
    }

    @Override
    public KhachHang selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<KhachHang> search(String SDT){
         String search ="Select * from KHACHHANG where SDT like '%"+SDT+"%'";
        List<KhachHang> listKH =this.selectBySql(search);
        return listKH;
    }
   
    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        try {
            List<KhachHang> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKh(rs.getString(1));
                kh.setHoTen(rs.getString(2));
                kh.setSdt(rs.getString(3));
                kh.setGioiTinh(rs.getBoolean(4));
                kh.setDiaChi(rs.getString(5));
                kh.setGhiChu(rs.getString(6));
                kh.setTrangThai(rs.getBoolean(7));
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    
}
