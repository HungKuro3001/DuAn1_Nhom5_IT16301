/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class ChatLieu {
    private String maCL;
    private String tenCL;
    private double giaBan;
    private double giaMua;
    private String moTa;

    public ChatLieu() {
    }

    public ChatLieu(String maCL, String tenCL, double giaBan, double giaMua, String moTa) {
        this.maCL = maCL;
        this.tenCL = tenCL;
        this.giaBan = giaBan;
        this.giaMua = giaMua;
        this.moTa = moTa;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(double giaMua) {
        this.giaMua = giaMua;
    }


    

    public String getMaCL() {
        return maCL;
    }

    public void setMaCL(String maCL) {
        this.maCL = maCL;
    }



    public String getTenCL() {
        return tenCL;
    }

    public void setTenCL(String tenCL) {
        this.tenCL = tenCL;
    }


    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    
}
