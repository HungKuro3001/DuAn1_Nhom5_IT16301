/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author Admin
 */
public class ChiTietHD {
    private String maHD;
    private int soLuong;
    private double tienCong;
    private double donGia;
    private double giamGia;
    private double ThanhTien;
    private String maSp;

    public ChiTietHD() {
    }

    public ChiTietHD(String maHD, int soLuong, double tienCong, double donGia, double giamGia, double ThanhTien, String maSp) {
        this.maHD = maHD;
        this.soLuong = soLuong;
        this.tienCong = tienCong;
        this.donGia = donGia;
        this.giamGia = giamGia;
        this.ThanhTien = ThanhTien;
        this.maSp = maSp;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTienCong() {
        return tienCong;
    }

    public void setTienCong(double tienCong) {
        this.tienCong = tienCong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
}
