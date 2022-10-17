package com.springdemo.helllo.dto;


import utils.NumToVietnameseWordUtils;

public class Field {
    private String menhGia;
    private String soTo;
    private String thanhTien;

    public String getMenhGia() {
        if (this.menhGia.contains(".")) return this.menhGia;
        else return NumToVietnameseWordUtils.convertToCommas(menhGia);
    }

    public String getSoTo() {
        return soTo;
    }

    public String getThanhTien() {
        if (this.thanhTien.contains(".")) return this.thanhTien;
        else return NumToVietnameseWordUtils.convertToCommas(thanhTien);
    }

    public void setMenhGia(String menhGia) {
        this.menhGia = menhGia;
    }

    public void setSoTo(String soTo) {
        this.soTo = soTo;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }
}

