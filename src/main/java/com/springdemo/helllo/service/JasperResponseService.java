package com.springdemo.helllo.service;

import com.springdemo.helllo.dto.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
                String type = showModelDTOS.get(0).getType();
                String name = convertNameMoney(type);

                for (int i = 0; i < showModelDTOS.size() - 1; i++) {
                    JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/ReverseReport.jrxml").getInputStream());
                    parameters.put("customerId", showModelDTOS.get(i).getMaKH());
                    parameters.put("fullName", showModelDTOS.get(i).getTenKhachHang());
                    parameters.put("phone", showModelDTOS.get(i).getSdt());
                    parameters.put("address", showModelDTOS.get(i).getDiaChi());
                    parameters.put("identifierNumber", showModelDTOS.get(i).getGttt());
                    parameters.put("identifierDateOfIssue", showModelDTOS.get(i).getNgayCap());
                    parameters.put("identifierPlaceOfIssue", showModelDTOS.get(i).getNoiCap());
                    parameters.put("coCode", showModelDTOS.get(i).getCoCode());

                    String cur = showModelDTOS.get(i).getType();
                    String total = showModelDTOS.get(i).getTongSoTienChi();
                    if ("VND".equals(cur)) {
                        String totalView = NumToVietnameseWordUtils.convertToCommas(total);
                        Long totalD = Long.parseLong(total);
                        String totalWords = NumToVietnameseWordUtils.num2String(totalD);
                        parameters.put("total", totalView + " " + cur);
                        parameters.put("totalWords", totalWords + " đồng");
                    } else {
                        parameters.put("total", NumToVietnameseWordUtils.convertToCommasUSD(total) + " " + cur);
                        parameters.put("totalWords", NumToVietnameseWordUtils.num2StringEURO(total, cur));
                    }
                    parameters.put("detailOfPayment", showModelDTOS.get(i).getNoiDung());
                    //parameters.put("logo", ClassLoader.getSystemResourceAsStream("images/logo.png"));
                    parameters.put("logo", new ClassPathResource("images/logo.png").getInputStream());
                    parameters.put("transactionT24", showModelDTOS.get(i).getSoDG());
                    parameters.put("createDate", showModelDTOS.get(i).getNgayGio());
                    parameters.put("user", showModelDTOS.get(i).getUser());
                    parameters.put("cashier", showModelDTOS.get(i).getNguoiPheDuyet());
                    parameters.put("teller", showModelDTOS.get(i).getNguoiNhapLieu());
                    parameters.put("authorier", showModelDTOS.get(i).getNguoiPheDuyet());
                    parameters.put("bangke", showModelDTOS.get(i).getBangKe());
                    parameters.put("nguoiNhan", showModelDTOS.get(i).getNguoiNhanTien());
                    if ("VND".equals(cur)) {
                        parameters.put("bangChuCai", NumToVietnameseWordUtils.num2String(Long.valueOf(showModelDTOS.get(i).getTongCong())) + " đồng");
                    } else {
                        parameters.put("bangChuCai", NumToVietnameseWordUtils.num2StringEURO(showModelDTOS.get(i).getTongCong(), cur));
                    }
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
                    jasperPrintList.add(jasperPrint);
                }
                // check trường hợp có bảng kê
                ShowModelDTO showModelDTO = showModelDTOS.get(showModelDTOS.size() - 1);
                if (showModelDTO.getFields() != null && showModelDTO.getFields().size() > 0) {
                    JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/InventoryReverseReport.jrxml").getInputStream());
                    parameters.put("customerId", showModelDTO.getMaKH());
                    parameters.put("fullName", showModelDTO.getTenKhachHang());
                    parameters.put("phone", showModelDTO.getSdt());
                    parameters.put("address", showModelDTO.getDiaChi());
                    parameters.put("identifierNumber", showModelDTO.getGttt());
                    parameters.put("identifierDateOfIssue", showModelDTO.getNgayCap());
                    parameters.put("identifierPlaceOfIssue", showModelDTO.getNoiCap());
                    parameters.put("coCode", showModelDTO.getCoCode());

                    String cur = showModelDTO.getType();
                    String total = showModelDTO.getTongSoTienChi();
                    if ("VND".equals(cur)) {
                        String totalView = NumToVietnameseWordUtils.convertToCommas(total);
                        Long totalD = Long.parseLong(total);
                        String totalWords = NumToVietnameseWordUtils.num2String(totalD);
                        parameters.put("total", totalView + " " + cur);
                        parameters.put("totalWords", totalWords + " đồng");
                    } else {
                        parameters.put("total", NumToVietnameseWordUtils.convertToCommasUSD(total) + " " + cur);
                        parameters.put("totalWords", NumToVietnameseWordUtils.num2StringEURO(total, cur));
                    }
                    parameters.put("detailOfPayment", showModelDTO.getNoiDung());
                    //parameters.put("logo", ClassLoader.getSystemResourceAsStream("images/logo.png"));
                    parameters.put("logo", new ClassPathResource("images/logo.png").getInputStream());
                    parameters.put("transactionT24", showModelDTO.getSoDG());
                    parameters.put("createDate", showModelDTO.getNgayGio());
                    parameters.put("user", showModelDTO.getUser());
                    parameters.put("cashier", showModelDTO.getNguoiPheDuyet());
                    parameters.put("teller", showModelDTO.getNguoiNhapLieu());
                    parameters.put("authorier", showModelDTO.getNguoiPheDuyet());
                    parameters.put("bangke", showModelDTO.getBangKe());
                    parameters.put("nguoiNhan", showModelDTO.getNguoiNhanTien());
                    parameters.put("tongCong", showModelDTO.getTongCong());
                    if ("VND".equals(cur)) {
                        parameters.put("bangChuCai", NumToVietnameseWordUtils.num2String(Long.valueOf(showModelDTO.getTongCong())) + " đồng");
                    } else {
                        parameters.put("bangChuCai", NumToVietnameseWordUtils.num2StringEURO(showModelDTO.getTongCong(), cur));
                    }
                    parameters.put("loaiBangKe", showModelDTO.getLoaiBangKe());

                    List<Field> listItems = showModelDTO.getFields();
                    if (listItems != null && listItems.size() > 0) {
                        // khởi tạo data source
                        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listItems);
                        parameters.put("listInventory", dataSource);
                    }


                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
                    jasperPrintList.add(jasperPrint);
                } else {
                    JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/ReverseReport.jrxml").getInputStream());
                    parameters.put("customerId", showModelDTO.getMaKH());
                    parameters.put("fullName", showModelDTO.getTenKhachHang());
                    parameters.put("phone", showModelDTO.getSdt());
                    parameters.put("address", showModelDTO.getDiaChi());
                    parameters.put("identifierNumber", showModelDTO.getGttt());
                    parameters.put("identifierDateOfIssue", showModelDTO.getNgayCap());
                    parameters.put("identifierPlaceOfIssue", showModelDTO.getNoiCap());
                    parameters.put("coCode", showModelDTO.getCoCode());

                    String cur = showModelDTO.getType();
                    String total = showModelDTO.getTongSoTienChi();
                    if ("VND".equals(cur)) {
                        String totalView = NumToVietnameseWordUtils.convertToCommas(total);
                        Long totalD = Long.parseLong(total);
                        String totalWords = NumToVietnameseWordUtils.num2String(totalD);
                        parameters.put("total", totalView + " " + cur);
                        parameters.put("totalWords", totalWords + " đồng");
                    } else {
                        parameters.put("total", NumToVietnameseWordUtils.convertToCommasUSD(total) + " " + cur);
                        parameters.put("totalWords", NumToVietnameseWordUtils.num2StringEURO(total, cur));
                    }
                    parameters.put("detailOfPayment", showModelDTO.getNoiDung());
                    //parameters.put("logo", ClassLoader.getSystemResourceAsStream("images/logo.png"));
                    parameters.put("logo", new ClassPathResource("images/logo.png").getInputStream());
                    parameters.put("transactionT24", showModelDTO.getSoDG());
                    parameters.put("createDate", showModelDTO.getNgayGio());
                    parameters.put("user", showModelDTO.getUser());
                    parameters.put("cashier", showModelDTO.getNguoiPheDuyet());
                    parameters.put("teller", showModelDTO.getNguoiNhapLieu());
                    parameters.put("authorier", showModelDTO.getNguoiPheDuyet());
                    parameters.put("bangke", showModelDTO.getBangKe());
                    parameters.put("nguoiNhan", showModelDTO.getNguoiNhanTien());
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

    public byte[] exportPdfPaymentSlip(PaymentSlipDTO paymentSlipDTO) throws JRException {
        log.debug("Start exportPdf transfer_reserve info {}", paymentSlipDTO);

        if (paymentSlipDTO != null) {
            try {
                List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
                // khai báo các parameter
                Map<String, Object> parameters = new HashMap<>();


                JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/PaymentSlip.jrxml").getInputStream());
                parameters.put("soGD", paymentSlipDTO.getSoGD());
                parameters.put("ngayGio", paymentSlipDTO.getNgayGio());
                parameters.put("user", paymentSlipDTO.getUser());
                parameters.put("bangKe", paymentSlipDTO.getBangKe());
                parameters.put("logo",  new ClassPathResource("images/logo.png").getInputStream());

                parameters.put("nguoiNhanTien", paymentSlipDTO.getNguoiNhanTien());
                parameters.put("maKH", paymentSlipDTO.getMaKH());
                parameters.put("soGTTT", paymentSlipDTO.getSoGTTT());
                parameters.put("ngayCap", paymentSlipDTO.getNgayCap());
                parameters.put("noiCap", paymentSlipDTO.getNoiCap());
                parameters.put("diaChi", paymentSlipDTO.getDiaChi());
                parameters.put("noiDung", paymentSlipDTO.getNoiDung());
                parameters.put("soSo", paymentSlipDTO.getSoSo());
                parameters.put("kyHan", paymentSlipDTO.getKyHan());
                parameters.put("sanPham", paymentSlipDTO.getSanPham());
                parameters.put("tienThanhToan", paymentSlipDTO.getTienThanhToan());
                parameters.put("tongTienThanhToan", paymentSlipDTO.getTongTienThanhToan());
                parameters.put("bangChu", paymentSlipDTO.getBangChu());
                parameters.put("nguoiNhanTien1", paymentSlipDTO.getNguoiNhanTien1());
                parameters.put("thuQuy", paymentSlipDTO.getThuQuy());
                parameters.put("nguoiNhapLieu", paymentSlipDTO.getNguoiNhapLieu());
                parameters.put("nguoiPheDuyet", paymentSlipDTO.getNguoiPheDuyet());




                List<Table> listItems = paymentSlipDTO.getTableBangKe();
                if (listItems != null && listItems.size() > 0) {
                    // khởi tạo data source
                    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listItems);
                    parameters.put("tableBangKe", dataSource);
                }

                List<Table1> listItems1 = paymentSlipDTO.getTableBangKe1();
                if (listItems1 != null && listItems1.size() > 0) {
                    // khởi tạo data source1
                    JRBeanCollectionDataSource dataSource1 = new JRBeanCollectionDataSource(listItems1);
                    parameters.put("table1", dataSource1);
                }

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
                jasperPrintList.add(jasperPrint);
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


    private String convertNameMoney(String name) {
        if (name.equals("USD")) return " Đô la Mỹ";
        else if (name.equals("VND")) return " đồng";
        else return " euro";
    }

}
