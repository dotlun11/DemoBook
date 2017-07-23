package com.example.dotlun.demobook;

/**
 * Created by Dotlun on 11/07/2017.
 */

public class KhachHang {
    public int id;
    public String ten;
    public String email;
    public String sdt;
    public String ngaydatban;
    public String giodatban;
    public String songuoilon;
    public String sotreem;
    public String gichu;

    public KhachHang(int id, String ten, String email, String sdt, String ngaydatban, String giodatban, String songuoilon, String sotreem, String gichu) {
        this.id = id;
        this.ten = ten;
        this.email = email;
        this.sdt = sdt;
        this.ngaydatban = ngaydatban;
        this.giodatban = giodatban;
        this.songuoilon = songuoilon;
        this.sotreem = sotreem;
        this.gichu = gichu;
    }
}
