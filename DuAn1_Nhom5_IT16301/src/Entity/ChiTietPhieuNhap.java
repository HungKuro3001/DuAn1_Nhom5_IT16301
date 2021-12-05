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
public class ChiTietPhieuNhap {

    private String maPN;
    private String maSp;
    private int soLuong;
    private double donGia;
    private double cong;
    private double thanhTien;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(String maPN, String maSp, int soLuong, double donGia, double cong, double thanhTien) {
        this.maPN = maPN;
        this.maSp = maSp;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.cong = cong;
        this.thanhTien = thanhTien;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getCong() {
        return cong;
    }

    public void setCong(double cong) {
        this.cong = cong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

}
