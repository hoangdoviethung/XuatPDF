package com.springdemo.helllo.service;

import com.springdemo.helllo.dto.ShowModelDTO;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import utils.NumToVietnameseWordUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JasperResponseService {

    public byte[] exportPdf(List<ShowModelDTO> showModelDTOS) throws JRException {
        log.debug("Start exportPdf transfer_reserve info {}", showModelDTOS);

        if (showModelDTOS != null && showModelDTOS.size() > 0) {
            try {
                List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
                // khai báo các parameter
                Map<String, Object> parameters = new HashMap<>();
                for (ShowModelDTO item : showModelDTOS) {
                    JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/ReverseReport.jrxml").getInputStream());
                    parameters.put("customerId", item.getMaKH());
                    parameters.put("fullName", item.getTenKhachHang());
                    parameters.put("phone", item.getSdt());
                    parameters.put("address", item.getDiaChi());
                    parameters.put("identifierNumber", item.getGttt());
                    parameters.put("identifierDateOfIssue", item.getNgayCap());
                    parameters.put("identifierPlaceOfIssue", item.getNoiCap());
                    parameters.put("coCode", item.getCoCode());

                    String total = item.getTongSoTienChi();
                    String totalView = NumToVietnameseWordUtils.convertToCommas(total).replace(",", ".") + " VND";
                    Long totalD = Long.parseLong(total);
                    String totalWords = NumToVietnameseWordUtils.num2String(totalD) + " đồng";

                    parameters.put("total", totalView);
                    parameters.put("totalWords", totalWords);
                    parameters.put("detailOfPayment", item.getNoiDung());
                    //parameters.put("logo", ClassLoader.getSystemResourceAsStream("images/logo.png"));
                    parameters.put("logo", new ClassPathResource("images/logo.png").getInputStream());
                    parameters.put("transactionT24", item.getSoDG());
                    parameters.put("createDate", item.getNgayGio());
                    parameters.put("user", item.getUser());
                    parameters.put("cashier", item.getNguoiPheDuyet());
                    parameters.put("teller", item.getNguoiNhapLieu());
                    parameters.put("authorier", item.getNguoiPheDuyet());
                    parameters.put("bangke", item.getBangKe());
                    parameters.put("nguoiNhan", item.getNguoiNhanTien());
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
                    jasperPrintList.add(jasperPrint);
                }

                JRPdfExporter exporter = new JRPdfExporter();
                ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
                exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));

                SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
                reportConfig.setSizePageToContent(true);
                reportConfig.setForceLineBreakPolicy(false);
                exporter.exportReport();
                return pdfOutputStream.toByteArray();
            } catch (JRException | IOException e) {
                throw new JRException(e.getMessage());
            }
        } else {
            return null;
        }
    }

}
