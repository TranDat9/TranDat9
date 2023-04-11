package com.example.myapplication.quanlykhachhang;
public class KhachHang {
    private int IdKH;
    private String TenKH;
    private String SoCCCD;
    private String Diachi;
    private String SoDT;

    public KhachHang(int idKH, String tenKH, String soCCCD, String diachi, String soDT) {
        IdKH = idKH;
        TenKH = tenKH;
        SoCCCD = soCCCD;
        Diachi = diachi;
        SoDT = soDT;
    }

    public int getIdKH() {
        return IdKH;
    }

    public void setIdKH(int idKH) {
        IdKH = idKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getSoCCCD() {
        return SoCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        SoCCCD = soCCCD;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }
}
