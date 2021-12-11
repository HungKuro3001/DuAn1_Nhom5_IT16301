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
public class DoiTra {
    private String maPDT;
    private String maHD;
    private String maSP;
    private  boolean hinhThuc;
    private String liDo;
    private String ghiChu;
    private Date NgayDT;

    public DoiTra() {
    }

    public DoiTra(String maPDT, String maHD, String maSP, boolean hinhThuc, String liDo, String ghiChu, Date NgayDT) {
        this.maPDT = maPDT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.hinhThuc = hinhThuc;
        this.liDo = liDo;
        this.ghiChu = ghiChu;
        this.NgayDT = NgayDT;
    }

    public Date getNgayDT() {
        return NgayDT;
    }

    public void setNgayDT(Date NgayDT) {
        this.NgayDT = NgayDT;
    }

    

    public String getMaPDT() {
        return maPDT;
    }

    public void setMaPDT(String maPDT) {
        this.maPDT = maPDT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public boolean isHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(boolean hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public String getLiDo() {
        return liDo;
    }

    public void setLiDo(String liDo) {
        this.liDo = liDo;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
