package com.springdemo.helllo.dto;


import utils.NumToVietnameseWordUtils;

public class Field {
    private String menhGia;
    private String soTo;
    private String thanhTien;

    public String getMenhGia() {
        return NumToVietnameseWordUtils.convertToCommas(menhGia);
    }

    public String getSoTo() {
        if (this.soTo == null) return null;

        return NumToVietnameseWordUtils.convertToCommas(soTo);
    }

    public String getThanhTien() {
        if (this.soTo == null) return null;
        return NumToVietnameseWordUtils.convertToCommas(String.valueOf((Integer.parseInt(this.menhGia) * Integer.parseInt(this.soTo))));
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
