package com.example.myapplication.DTO;

public class Phong {
    private String id, kieuPhong ;
    private boolean trangThai;

    public Phong(String id, String kieuPhong, boolean trangThai) {
        this.id = id;
        this.kieuPhong = kieuPhong;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKieuPhong() {
        return kieuPhong;
    }

    public void setKieuPhong(String kieuPhong) {
        this.kieuPhong = kieuPhong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Phong() {
    }
}
