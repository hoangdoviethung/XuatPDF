package com.springdemo.helllo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentSlipDTO {
    private String soGD;
    private String ngayGio;
    private String user;
    private String bangKe;
    private String logo;

    private String nguoiNhanTien;
    private String maKH;
    private String soGTTT;
    private String ngayCap;
    private String noiCap;
    private String diaChi;
    private String noiDung;
    private String soSo;
    private String kyHan;
    private String sanPham;
    private String tienThanhToan;
    private String tienTatToan;
    private String tongTienThanhToan;
    private String bangChu;
    private String nguoiNhanTien1;
    private String thuQuy;
    private String nguoiNhapLieu;
    private String nguoiPheDuyet;
    private List<Table> tableBangKe;
    private List<Table1> tableBangKe1;

}
