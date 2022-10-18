package com.springdemo.helllo.controller;

import com.springdemo.helllo.dto.PaymentSlipDTO;
import com.springdemo.helllo.dto.ShowModelDTO;
import com.springdemo.helllo.service.JasperResponseService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/transfer-reverse")
public class JasperReport {
    @Resource
    JasperResponseService jasperResponseService;


    @PostMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf(@RequestBody List<ShowModelDTO> reqDTO) throws JRException {
        List<ShowModelDTO> exportPdfDTO = reqDTO;
        byte[] byteArr = jasperResponseService.exportPdf(exportPdfDTO);
        HttpHeaders headers = new HttpHeaders();
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename("Reverse").build();
        headers.setContentDisposition(contentDisposition);
        MediaType mediaType = MediaType.parseMediaType("application/pdf;charset=utf-8");
        headers.setContentType(mediaType);
        return ResponseEntity.ok().headers(headers).body(byteArr);
    }
    @PostMapping("/export-pdf/payment-slip")
    public ResponseEntity<byte[]> exportPdfPaymentSlip(@RequestBody PaymentSlipDTO reqDTO) throws JRException {
        byte[] byteArr = jasperResponseService.exportPdfPaymentSlip(reqDTO);
        HttpHeaders headers = new HttpHeaders();
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename("Reverse").build();
        headers.setContentDisposition(contentDisposition);
        MediaType mediaType = MediaType.parseMediaType("application/pdf;charset=utf-8");
        headers.setContentType(mediaType);
        return ResponseEntity.ok().headers(headers).body(byteArr);
    }

    /*
      [
    {
        "soDG": "SEATELLER20_FEERE_14102022_1668414338",
        "ngayGio": "14-10-2022 15:31:09",
        "user": "SEATELLER20",
        "bangKe": "",
        "tenKhachHang": "NGUYEN THI BANG TAM",
        "gttt": "C011642272",
        "noiDung": "note",
        "tongSoTienChi": "1000000000.12",
        "bangChu": "",
        "ngayCap": "23/05/2000",
        "maKH": "1",
        "noiCap": "HA NOI",
        "sdt": "045727387",
        "diaChi": "89 HANG BAC",
        "nguoiNhanTien": "NGUYEN THI BANG TAM",
        "nguoiNhapLieu": "SEATELLER20",
        "nguoiKiemSoat": "SEATELLER20",
        "nguoiPheDuyet": "SEATELLER20",
        "coCode": "Chi nhánh Sở giao dịch (VN0010002)",
        "type": "USD"
    },
    {
        "soDG": "SEATELLER20_FEERE_14102022_1668414338",
        "ngayGio": "14-10-2022 15:31:09",
        "user": "SEATELLER20",
        "bangKe": "",
        "tenKhachHang": "NGUYEN THI BANG TAM",
        "gttt": "C011642272",
        "noiDung": "đâsd",
        "tongSoTienChi": "10000000000.1",
        "bangChu": "",
        "ngayCap": "23/05/2000",
        "maKH": "1",
        "noiCap": "HA NOI",
        "sdt": "045727387",
        "diaChi": "89 HANG BAC",
        "nguoiNhanTien": "NGUYEN THI BANG TAM",
        "nguoiNhapLieu": "SEATELLER20",
        "nguoiKiemSoat": "SEATELLER20",
        "nguoiPheDuyet": "SEATELLER20",
        "coCode": "Chi nhánh Sở giao dịch (VN0010002)",
        "loaiBangKe": "Bảng kê các loại tiền lĩnh (USD)",
        "type": "USD",
        "tongCong": "1000000000.01",
        "fields": [
            {
                "menhGia": 100,
                "soTo": 10000,
                "thanhTien": 1000000
            },
            {
                "menhGia": 0.01,
                "soTo": 10,
                "thanhTien": 0.1
            }
        ]
    }
]
     */


    /*
    *
    * {
    "soGD": "000056402519",
    "ngayGio": "01/06/2022    18:33",
    "user": "SBOFS",
    "bangKe": "",
    "nguoiNhanTien": "LE THI THIEP",
    "maKH": "15356095",
    "soGTTT": "052143000052",
    "ngayCap": "12/11/2019",
    "noiCap": "CS QLHC V TTXH",
    "diaChi": "27A TRAN HUNG DAO, P PHAN CHU TRINH QUAN HOAN KIEM HA NO",
    "noiDung": "Tất toán Sổ tiết kiệm",
    "soSo": "SC2208787",
    "kyHan": "6 thang",
    "sanPham": "TIET KIEM LINH LAI CUOI KY",
    "tienThanhToan": "0 VND",
    "tienTatToan": "0 VND",
    "tongTienThanhToan": "8.695.299 VND",
    "bangChu": "Tám triệu, sáu trăm chín mươi lăm nghìn, hai trăm chín mươi chín đồng",
    "nguoiNhanTien1": "SEATELLER1",
    "thuQuy": "SEATELLER1",
    "nguoiNhapLieu": "SEATELLER1",
    "nguoiPheDuyet": "SEATELLER1",
    "tong": "695.299",
    "tableBangKe": [
        {
            "tuNgay": "20-01-1998",
            "denNgay": "28/12/2020",
            "soNgay": "183",
            "soDu": "8.000.000",
            "laiXuat": "5,8%",
            "tienLai": "232.636"
        },
        {
            "tuNgay": "20-01-1998",
            "denNgay": "28/12/2020",
            "soNgay": "183",
            "soDu": "8.000.000",
            "laiXuat": "5,8%",
            "tienLai": "232.636"
        },
        {
            "tuNgay": "20-01-1998",
            "denNgay": "28/12/2020",
            "soNgay": "183",
            "soDu": "8.000.000",
            "laiXuat": "5,8%",
            "tienLai": "232.636"
        },
        {
            "tuNgay": "20-01-1998",
            "denNgay": "28/12/2020",
            "soNgay": "183",
            "soDu": "8.000.000",
            "laiXuat": "5,8%",
            "tienLai": "232.636"
        },
        {
            "tuNgay": "20-01-1998",
            "denNgay": "28/12/2020",
            "soNgay": "183",
            "soDu": "8.000.000",
            "laiXuat": "5,8%",
            "tienLai": "232.636"
        }
    ],
    "tableBangKe1": [
        {
            "ht": "TK NO",
            "soTK": "000056402519",
            "tenTK": "LE THI THIEP 6T CK",
            "soTien": "8.695.299 VND"
        },
        {
            "ht": "TK NO",
            "soTK": "000056402519",
            "tenTK": "LE THI THIEP 6T CK",
            "soTien": "8.695.299 VND"
        },
        {
            "ht": "TK NO",
            "soTK": "000056402519",
            "tenTK": "LE THI THIEP 6T CK",
            "soTien": "8.695.299 VND"
        },
        {
            "ht": "TK Co",
            "soTK": "VND1000199990002",
            "tenTK": "TIEN MAT TAI QUY CHINH CN SGD - VND",
            "soTien": "8.695.299 VND"
        }
    ]
}
    * */
}
