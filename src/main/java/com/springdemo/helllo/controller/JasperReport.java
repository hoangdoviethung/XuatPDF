package com.springdemo.helllo.controller;

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

    /*
      [{
    "soDG": "SEATELLER20_FEERE_30092022_1667112484",
    "ngayGio": "30-09-2022 13:51:51",
    "user": "SEATELLER20",
    "bangKe": "",
    "tenKhachHang": "TRAN THI YEN",
    "gttt": "163042021",
    "noiDung": "note",
    "tongSoTienChi": "9999",
    "bangChu": "",
    "ngayCap": "27/10/2020",
    "maKH": "14892607",
    "noiCap": "CA NAM DINH dfdfdfdf",
    "sdt": "0855254514",
    "diaChi": "22 ngo 210, doi can KHI KI VAM KIM1 LU HAI BAN TRUNG HF",
    "nguoiNhanTien": "TRAN THI YEN",
    "nguoiNhapLieu": "SEATELLER20",
    "nguoiKiemSoat": "SEATELLER20",
    "nguoiPheDuyet": "SEATELLER20",
    "coCode": "Chi nhánh Sở giao dịch (VN0010002)",
    "type": "EURO"
},
{
    "soDG": "SEATELLER20_FEERE_30092022_1667112484",
    "ngayGio": "30-09-2022 13:51:51",
    "user": "SEATELLER20",
    "bangKe": "",
    "tenKhachHang": "TRAN THI YEN",
    "gttt": "163042021",
    "noiDung": "note",
    "tongSoTienChi": "9999",
    "bangChu": "",
    "ngayCap": "27/10/2020",
    "maKH": "14892607",
    "noiCap": "CA NAM DINH dfdfdfdf",
    "sdt": "0855254514",
    "diaChi": "22 ngo 210, doi can KHI KI VAM KIM1 LU HAI BAN TRUNG HF",
    "nguoiNhanTien": "TRAN THI YEN",
    "nguoiNhapLieu": "SEATELLER20",
    "nguoiKiemSoat": "SEATELLER20",
    "nguoiPheDuyet": "SEATELLER20",
    "coCode": "Chi nhánh Sở giao dịch (VN0010002)",
    "type": "EURO"
},
{
    "soDG": "SEATELLER20_FEERE_30092022_1667112484",
    "ngayGio": "30-09-2022 13:51:51",
    "user": "SEATELLER20",
    "bangKe": "",
    "tenKhachHang": "TRAN THI YEN",
    "gttt": "163042021",
    "noiDung": "note",
    "tongSoTienChi": "9999",
    "bangChu": "",
    "ngayCap": "27/10/2020",
    "maKH": "14892607",
    "noiCap": "CA NAM DINH dfdfdfdf",
    "sdt": "0855254514",
    "diaChi": "22 ngo 210, doi can KHI KI VAM KIM1 LU HAI BAN TRUNG HF",
    "coCode": "Chi nhánh Sở giao dịch (VN0010002)",
    "loaiBangKe": "Bảng kê các loại tiền lĩnh (VND)",
    "type": "VND",
    "fields": [
        {
            "menhGia": "500000",
            "soTo": "5"
        },
        {
            "menhGia": "200000",
            "soTo": null
        },
        {
            "menhGia": "100000",
            "soTo": null
        },
        {
            "menhGia": "50000",
            "soTo": "5"
        },
        {
            "menhGia": "20000",
            "soTo": "5277"
        },
        {
            "menhGia": "10000",
            "soTo": "1"
        },
        {
            "menhGia": "5000",
            "soTo": "15"
        },
        {
            "menhGia": "2000",
            "soTo": "3"
        },
        {
            "menhGia": "1000",
            "soTo": "6"
        },
        {
            "menhGia": "500",
            "soTo": "3"
        }
    ],
    "nguoiNhanTien": "TRAN THI YEN",
    "nguoiNhapLieu": "SEATELLER20",
    "nguoiKiemSoat": "SEATELLER20",
    "nguoiPheDuyet": "SEATELLER20"
}
]
     */
}
