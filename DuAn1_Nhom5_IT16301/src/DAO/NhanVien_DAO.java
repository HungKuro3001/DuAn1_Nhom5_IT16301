/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.NhanVien;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Administrator
 */
public class NhanVien_DAO extends Dao<NhanVien, String> {
    String isnert ="Insert nhanvien values (?,?,?,?,?,?,?,?,?,1,?)";
    String selectAll   = "Select *from nhanvien";
    String updateSql = "UPDATE NHANVIEN SET HOTEN =?,NGAYSINH =?,CCCD =?,SDT =?,GIOITINH =?, GhiChu=? where MANV=?";
    String updatePassWord = "UPDATE NHANVIEN SET PASSWORD=? where MANV =?";
    String selectById = "SELECT*FROM NHANVIEN WHERE USERNAME=?";
    String block="UPDATE NHANVIEN SET TRANGTHAI=? where MANV =?";
    String selectByMaNV="SELECT*FROM NHANVIEN WHERE MANV=?";
    

    @Override
    public void insert(NhanVien entity) {
        jdbcHelper.Update(isnert, entity.getMaNV(),entity.getHoTen(),entity.getNgaySinh(),entity.getCCCD(),
        entity.getSDT(),entity.getGioiTinh(),entity.getUserName(),entity.getPassWord(),entity.isRole(),entity.getGhiChu());
    }

    @Override
    public void update(NhanVien entity) {
        jdbcHelper.Update(updateSql, entity.getHoTen(), entity.getNgaySinh(),
                entity.getCCCD(), entity.getSDT(),entity.getGioiTinh(),entity.getGhiChu(),entity.getMaNV());
    }
    public void updatePass(String pass,String manv) {
        jdbcHelper.Update(updatePassWord,pass,manv);
    }
    public void khoaTk(NhanVien entity){
        jdbcHelper.Update(block,entity.isTrangThai(),entity.getMaNV());
    }
    @Override
    public void delete(String key) {
        
    }

    @Override
    public List<NhanVien> selectAll() {
       List<NhanVien> list= this.selectBySql(selectAll);
       return list;
    }

    @Override
    public NhanVien selectById(String manv) {
        List<NhanVien> list = this.selectBySql(selectById, manv);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public NhanVien selectByManv(String manv) {
        List<NhanVien> list = this.selectBySql(selectByMaNV, manv);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        try {
            List<NhanVien> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setHoTen(rs.getString(2));
                nv.setNgaySinh(rs.getDate(3));
                nv.setCCCD(rs.getString(4));
                nv.setSDT(rs.getString(5));
                nv.setGioiTinh(rs.getBoolean(6));
                nv.setUserName(rs.getString(7));
                nv.setPassWord(rs.getString(8));
                nv.setRole(rs.getBoolean(9));
                nv.setTrangThai(rs.getBoolean(10));
                nv.setGhiChu(rs.getString(11));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    
}
