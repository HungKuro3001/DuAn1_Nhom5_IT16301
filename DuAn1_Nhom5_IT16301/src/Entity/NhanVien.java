/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private String maNV;
    private String HoTen;
    private Date ngaySinh ;
    private String CCCD ;
    private String SDT ;
    private boolean gioiTinh ;
    private String userName ;
    private String passWord ;
    private boolean role ;
    private boolean trangThai ;
    private String GhiChu;

    public NhanVien() {
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public NhanVien(String maNV, String HoTen, Date ngaySinh, String CCCD, String SDT, boolean gioiTinh, String userName, String passWord, boolean role, boolean trangThai, String GhiChu) {
        this.maNV = maNV;
        this.HoTen = HoTen;
        this.ngaySinh = ngaySinh;
        this.CCCD = CCCD;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.trangThai = trangThai;
        this.GhiChu = GhiChu;
    }
    

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }


    
}
