package com.trnqb.cafe.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.BillDTO;
import com.trnqb.cafe.entity.Bill;
import com.trnqb.cafe.jwt.JwtFilter;
import com.trnqb.cafe.repository.BillRepository;
import com.trnqb.cafe.service.BillService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        try {
            String fileName;
            if (validateRequestMap(requestMap)) {
                if (requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate")) {
                    fileName = (String) requestMap.get("uuid");
                } else {
                    fileName = CafeUtils.getUUID();
                    requestMap.put("uuid", fileName);
                    insertBill(requestMap);
                }

                String data = "Name: " + requestMap.get("name") + "\n"
                        + "Phone Number: " + requestMap.get("phoneNumber") + "\n"
                        + "Email: " + requestMap.get("email") + "\n"
                        + "Payment Method: " + requestMap.get("paymentMethod");

//                Map<String, String> customerInfo = new HashMap<>();
//                customerInfo.put("name", (String) requestMap.get("name"));
//                customerInfo.put("email", (String) requestMap.get("email"));
//                customerInfo.put("phoneNumber", (String) requestMap.get("phoneNumber"));
//                customerService.addCustomer(customerInfo);

                Document document = new Document();
                PdfWriter.getInstance(document,
                        new FileOutputStream(CafeConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));

                document.open();
                setRectangleInPdf(document);

                Paragraph chunk = new Paragraph("Cafe Management System", getFont("Header"));
                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);

                Paragraph paragraph = new Paragraph(data + "\n \n", getFont("Data"));
                document.add(paragraph);

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                addTableHeader(table);

                JSONArray jsonArray = CafeUtils.getJsonArrayFromString((String) requestMap.get("productDetails"));
                for (int i = 0; i < jsonArray.length(); i++) {
                    addRows(table, CafeUtils.getMapFromJson(jsonArray.getString(i)));
                }

                document.add(table);

                Paragraph footer = new Paragraph("Total: " +
                        requestMap.get("totalAmount") + "\n" +
                        "Thank you for visiting. Please visit again!", getFont("Data"));
                document.add(footer);
                document.close();
                return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("Required data not found.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<BillDTO>> getBills() {
        List<BillDTO> bills;


        if (jwtFilter.isAdmin()) {
            bills = billRepository.findAll().stream().map(bill -> mapToDTO(bill, new BillDTO())).toList();
        } else {
            bills = billRepository.findAllByCreateBy(jwtFilter.getCurrentUser()).stream().map(bill -> mapToDTO(bill, new BillDTO())).toList();;

        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteBill(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Bill> optional = billRepository.findById(id);
                if (optional.isPresent()) {
                    billRepository.deleteById(id);
                    return CafeUtils.getResponseEntity("Bill has been deleted.", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity("Bill ID does not exist.", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        try {
            byte[] bytes = new byte[0];
            if (!requestMap.containsKey("uuid") && validateRequestMap(requestMap)) {
                return new ResponseEntity<>(bytes, HttpStatus.BAD_REQUEST);
            }
            String filePath = CafeConstants.STORE_LOCATION + "\\" + (String) requestMap.get("uuid") + ".pdf";
            if (CafeUtils.isFileExist(filePath)) {
                bytes = getByteArray(filePath);
                return new ResponseEntity<>(bytes, HttpStatus.OK);
            } else {
                requestMap.put("isGenerate", false);
                generateReport(requestMap);
                bytes = getByteArray(filePath);
                return new ResponseEntity<>(bytes, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<BillDTO>> getBillsFrom(LocalDate date) {
        try {
            List<BillDTO> billDTOS = billRepository.findAllByDate(date).stream().map(bill -> mapToDTO(bill, new BillDTO())).toList();
            return new ResponseEntity<>(billDTOS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<BillDTO>> getBillsByPayment(String payment) {
        try {
            List<BillDTO> billDTOS = billRepository.findAllByPaymentMethod(payment).stream().map(bill -> mapToDTO(bill, new BillDTO())).toList();
            return new ResponseEntity<>(billDTOS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> getWeeklyOrderDistribution(LocalDate date) {
        try {
            List<BillDTO> billDTOs;
            if (date == null) {
                billDTOs = billRepository.findAllByDateRanges(LocalDate.now().minusDays(7)).stream().map(bill -> mapToDTO(bill, new BillDTO())).toList();
            } else {
                billDTOs = billRepository.findAllByDateRanges(LocalDate.now()).stream().map(bill -> mapToDTO(bill, new BillDTO())).toList();
            }
      
            return new ResponseEntity<>(this.getOrderTimeData(billDTOs), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> getTotalOrdersByDay(LocalDate date) {
        try {
            if (date == null) {
                return new ResponseEntity<>(this.getTotalOrdersLast2Weeks(LocalDate.now()), HttpStatus.OK);
            }
            return new ResponseEntity<>(this.getTotalOrdersLast2Weeks(date), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<Map<String, Object>> getTotalOrdersLast2Weeks(LocalDate date) {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 13; i >= 0; i--) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", date.minusDays(i));
            map.put("total", this.getDailyTotalOrder(date.minusDays(i)));
            maps.add(map);
        }
        return maps;
    }

    private Integer getDailyTotalOrder(LocalDate date) {
        List<BillDTO> billDTOS = billRepository.findAllByDate(date).stream().map(bill -> mapToDTO(bill, new BillDTO())).toList();
        return billDTOS.size();
    }

    private List<Map<String, Object>> getOrderTimeData(List<BillDTO> bills) {
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Integer> shiftCounts = new HashMap<>();
        shiftCounts.put("Morning", 0);
        shiftCounts.put("Afternoon", 0);
        shiftCounts.put("Evening", 0);

        for (BillDTO billDTO : bills) {
            shiftCounts.compute(billDTO.getShiftTime(), (shift, count) -> count == null ? 1 : count + 1);
        }

        int morning = shiftCounts.get("Morning");
        int afternoon = shiftCounts.get("Afternoon");
        int evening = shiftCounts.get("Evening");

        Map<String, Object> morningMap = new HashMap<>();
        morningMap.put("shift", "Morning");
        morningMap.put("orders", morning);

        Map<String, Object> afternoonMap = new HashMap<>();
        afternoonMap.put("shift", "Afternoon");
        afternoonMap.put("orders", afternoon);

        Map<String, Object> eveningMap = new HashMap<>();
        eveningMap.put("shift", "Evening");
        eveningMap.put("orders", evening);

        maps.add(morningMap);
        maps.add(afternoonMap);
        maps.add(eveningMap);
        return maps;
    }

    private byte[] getByteArray(String filePath) throws Exception {
        File init = new File(filePath);
        InputStream targetStream = new FileInputStream(init);
        byte[] bytes = IOUtils.toByteArray(targetStream);
        targetStream.close();
        return bytes;
    }

    private void addRows(PdfPTable table, Map<String, Object> data) {
        table.addCell((String) data.get("name"));
        table.addCell((String) data.get("category"));
        table.addCell(String.valueOf(data.get("quantity")));
        table.addCell(String.valueOf(data.get("price")));
        table.addCell(String.valueOf(data.get("total")));
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Name", "Category", "Quantity", "Price", "Sub Total")
                .forEach(columnTittle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTittle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private String classifyTimeOfDay(LocalTime time) {
        if (time.isAfter(LocalTime.of(0, 0)) && time.isBefore(LocalTime.of(12, 0))) {
            return "Morning";
        } else if (time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(17, 0))) {
            return "Afternoon";
        } else {
            return "Evening";
        }
    }

    private Font getFont(String type) {
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;

            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;

            default:
                return new Font();
        }
    }

    private void setRectangleInPdf(Document document) throws DocumentException {
        Rectangle rectangle = new Rectangle(577, 825, 18, 15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBorderColor(BaseColor.BLACK);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }

    private void insertBill(Map<String, Object> requestMap) {
        try {
            Bill bill = new Bill();
            bill.setUuid((String) requestMap.get("uuid"));
            bill.setName((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setPhoneNumber((String) requestMap.get("phoneNumber"));
            bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
            bill.setTotal((Integer) requestMap.get("totalAmount"));
            bill.setProductDetails((String) requestMap.get("productDetails"));
            bill.setCreateBy(jwtFilter.getCurrentUser());
            bill.setDate(LocalDate.now());
            bill.setTime(LocalTime.now());
            bill.setShiftTime(classifyTimeOfDay(bill.getTime()));
            billRepository.save(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("paymentMethod") &&
                requestMap.containsKey("productDetails") &&
                requestMap.containsKey("totalAmount");
    }

    private BillDTO mapToDTO(final Bill bill, final BillDTO billDTO) {
        billDTO.setId(bill.getId());
        billDTO.setUuid(bill.getUuid());
        billDTO.setName(bill.getName());
        billDTO.setEmail(bill.getEmail());
        billDTO.setPhoneNumber(bill.getPhoneNumber());
        billDTO.setPaymentMethod(bill.getPaymentMethod());
        billDTO.setTotal(bill.getTotal());
        billDTO.setProductDetails(bill.getProductDetails());
        billDTO.setCreateBy(bill.getCreateBy());
//        billDTO.setDateTime(bill.getDateTime());
        billDTO.setDate(bill.getDate());
        billDTO.setTime(bill.getTime());;
        billDTO.setShiftTime(bill.getShiftTime());
        return billDTO;
    }

    private Bill mapToEntity(final BillDTO billDTO, final Bill bill) {
        bill.setId(billDTO.getId());
        bill.setUuid(billDTO.getUuid());
        bill.setName(billDTO.getName());
        bill.setEmail(billDTO.getEmail());
        bill.setPhoneNumber(billDTO.getPhoneNumber());
        bill.setPaymentMethod(billDTO.getPaymentMethod());
        bill.setTotal(billDTO.getTotal());
        bill.setProductDetails(billDTO.getProductDetails());
        bill.setCreateBy(billDTO.getCreateBy());
//        bill.setDateTime(billDTO.getDateTime());
        bill.setDate(billDTO.getDate());
        bill.setTime(billDTO.getTime());
        bill.setShiftTime(billDTO.getShiftTime());
        return bill;
    }
}
