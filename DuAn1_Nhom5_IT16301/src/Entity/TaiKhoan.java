/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Administrator
 */
public class TaiKhoan {
    private int IDTK;
    private String userName;
    private String passWord;
    private boolean role;
    private String maNV;
    private boolean trangThai;

    public TaiKhoan() {
    }

    public TaiKhoan(int IDTK, String userName, String passWord, boolean role, String maNV, boolean trangThai) {
        this.IDTK = IDTK;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.maNV = maNV;
        this.trangThai = trangThai;
    }

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
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

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
}
