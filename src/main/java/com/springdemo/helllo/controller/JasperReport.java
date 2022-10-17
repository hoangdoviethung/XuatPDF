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
}
