/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.ChiTietHD;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiTietHD_DAO extends Dao<ChiTietHD, String> {

    String insert = "INSERT HOADONCHITIET VALUES (?,?,?,?,?,?,?)";
    String selectByMaHD = "SELECT * FROM HOADONCHITIET WHERE MAHD=? ";
    String deleteHDCT = "DELETE FROM HOADONCHITIET WHERE MaSP=? AND MAHD=?";
    String update= "UPDATE HOADONCHITIET SET SOLUONG=?, GIAMGIA=?, THANHTIEN=? WHERE MAHD=? AND MASP=? ";
    String delete= "DELETE FROM HOADONCHITIET WHERE MAHD=? AND MASP=?";
    @Override
    public void insert(ChiTietHD entity) {
        jdbcHelper.Update(insert, entity.getMaSp(), entity.getMaHD(), entity.getSoLuong(),
                entity.getTienCong(), entity.getDonGia(), entity.getGiamGia(), entity.getThanhTien()
        );

    }

    @Override
    public void update(ChiTietHD entity) {
       jdbcHelper.Update(update, entity.getSoLuong(),entity.getGiamGia(),entity.getThanhTien(),entity.getMaHD(),entity.getMaSp());
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void deleteHDCT(ChiTietHD entity) {
        jdbcHelper.Update(delete,entity.getMaHD(),entity.getMaSp());
    }
    public void delete(String key,String key2) {
        jdbcHelper.Update(deleteHDCT, key, key2);
    }
    
    @Override
    public List<ChiTietHD> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChiTietHD selectById(String key) {
        return null;
    }

    public List<ChiTietHD> selectByMAHD(String key) {
        List<ChiTietHD> list = this.selectBySql(selectByMaHD, key);
        return list;
    }

    @Override
    protected List<ChiTietHD> selectBySql(String sql, Object... args) {
        try {
            List<ChiTietHD> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                ChiTietHD ctHD = new ChiTietHD();
                ctHD.setMaSp(rs.getString(1));
                ctHD.setMaHD(rs.getString(2));
                ctHD.setSoLuong(rs.getInt(3));
                ctHD.setTienCong(rs.getDouble(4));
                ctHD.setDonGia(rs.getDouble(5));
                ctHD.setGiamGia(rs.getDouble(6));
                ctHD.setThanhTien(rs.getDouble(7));
                list.add(ctHD);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
         public int duLieu(String maDM,String ngayBatDau,String ngayKetThuc) throws SQLException { //lấy số lượng theo danh mục
        String query ="exec SP_BCTK2 @NGAYBATDAU = '"+ngayBatDau+"',@NGAYKETTHUC ='"+ngayKetThuc+" 23:59:59.999',@madm ="+maDM+"";
             //System.out.println(""+query);
        int SLSP = 0;
        ResultSet rs =jdbcHelper.query(query);
        while(rs.next()){
            SLSP =rs.getInt(1);
        }
        
        return SLSP;
    }
         

}
