package com.example.myapplication.quanlydichvu;

public class DichVu {
    private int IdDichVu;
    private String TenDichVu;

    public DichVu(int idDichVu, String tenDichVu) {
        IdDichVu = idDichVu;
        TenDichVu = tenDichVu;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        TenDichVu = tenDichVu;
    }

    public int getIdDichVu() {
        return IdDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        IdDichVu = idDichVu;
    }
}
