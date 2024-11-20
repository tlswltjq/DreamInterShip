package dubaichamber.dreamintership.supplierApplication.service;

import com.opencsv.CSVWriter;
import dubaichamber.dreamintership.supplierApplication.entity.ApplicationDetails;
import dubaichamber.dreamintership.supplierApplication.repository.ApplicationDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportService {
    private final ApplicationDetailsRepository applicationDetailsRepository;
    private final String[] columns = {
            "company_name", "application_id", "contact_person", "email",
            "address", "phone_number", "website", "product_catalogue",
            "comment", "created_at", "product_id", "product_name", "description"
    };

    public byte[] exportToExcel() {
        List<ApplicationDetails> details = applicationDetailsRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Application Details");

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (ApplicationDetails detail : details) {
                Row dataRow = sheet.createRow(rowNum++);
                populateRow(dataRow, detail);
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to export Excel file", e);
        }
    }

    public String exportToCsv() {
        List<ApplicationDetails> details = applicationDetailsRepository.findAll();

        try (StringWriter stringWriter = new StringWriter();
             CSVWriter csvWriter = new CSVWriter(stringWriter)) {

            csvWriter.writeNext(columns);

            for (ApplicationDetails detail : details) {
                String[] data = new String[columns.length];
                populateData(data, detail);
                csvWriter.writeNext(data);
            }

            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to export CSV file", e);
        }
    }

    private void populateRow(Row row, ApplicationDetails detail) {
        row.createCell(0).setCellValue(detail.getCompanyName());
        row.createCell(1).setCellValue(detail.getApplicationId());
        row.createCell(2).setCellValue(detail.getContactPerson());
        row.createCell(3).setCellValue(detail.getEmail());
        row.createCell(4).setCellValue(detail.getAddress());
        row.createCell(5).setCellValue(detail.getPhoneNumber());
        row.createCell(6).setCellValue(detail.getWebsite());
        row.createCell(7).setCellValue(detail.getProductCatalogue());
        row.createCell(8).setCellValue(detail.getComment());
        row.createCell(9).setCellValue(detail.getCreatedAt().toString());
        row.createCell(10).setCellValue(detail.getProductId());
        row.createCell(11).setCellValue(detail.getProductName());
        row.createCell(12).setCellValue(detail.getDescription());
    }

    private void populateData(String[] data, ApplicationDetails detail) {
        data[0] = detail.getCompanyName();
        data[1] = String.valueOf(detail.getApplicationId());
        data[2] = detail.getContactPerson();
        data[3] = detail.getEmail();
        data[4] = detail.getAddress();
        data[5] = detail.getPhoneNumber();
        data[6] = detail.getWebsite();
        data[7] = detail.getProductCatalogue();
        data[8] = detail.getComment();
        data[9] = detail.getCreatedAt().toString();
        data[10] = String.valueOf(detail.getProductId());
        data[11] = detail.getProductName();
        data[12] = detail.getDescription();
    }
}