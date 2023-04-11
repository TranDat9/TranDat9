package com.example.myapplication.DTO;

public class HoaDon  {
    public HoaDon(String maPhong, String tenKH, String tienDV, String note, int IDHD, boolean traPhong) {
        this.maPhong = maPhong;
        this.tenKH = tenKH;
        this.tienDV = tienDV;
        this.note = note;
        this.IDHD = IDHD;
        this.traPhong = traPhong;
    }

    private String maPhong, tenKH, tienDV, note;
    private int IDHD ;
    private boolean traPhong;

    public boolean isTraPhong() {
        return traPhong;
    }

    public void setTraPhong(boolean traPhong) {
        this.traPhong = traPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTienDV() {
        return tienDV;
    }

    public void setTienDV(String tienDV) {
        this.tienDV = tienDV;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIDHD() {
        return IDHD;
    }

    public void setIDHD(int IDHD) {
        this.IDHD = IDHD;
    }

    public HoaDon() {
    }


}
