package com.example.myapplication.model;

public class KhachHang {
    private String Ten , Sdt, Diachi , Gioitinh ;
    private int Id;

    @Override
    public String toString() {
        return "KhachHang{" +
                "Ten='" + Ten + '\'' +
                ", Sdt='" + Sdt + '\'' +
                ", Diachi='" + Diachi + '\'' +
                ", Gioitinh='" + Gioitinh + '\'' +
                ", Id=" + Id +
                '}';
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        Gioitinh = gioitinh;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public KhachHang() {
    }

    public KhachHang(String ten, String sdt, String diachi, String gioitinh, int id) {
        Ten = ten;
        Sdt = sdt;
        Diachi = diachi;
        Gioitinh = gioitinh;
        Id = id;
    }
}
