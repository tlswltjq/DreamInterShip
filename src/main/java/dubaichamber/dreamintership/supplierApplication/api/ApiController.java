package dubaichamber.dreamintership.supplierApplication.api;

import dubaichamber.dreamintership.supplierApplication.dto.*;
import dubaichamber.dreamintership.supplierApplication.entity.Application;
import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.entity.Product;
import dubaichamber.dreamintership.supplierApplication.entity.ProductApplication;
import dubaichamber.dreamintership.supplierApplication.model.ProductAndDescription;
import dubaichamber.dreamintership.supplierApplication.model.SupplierForm;
import dubaichamber.dreamintership.supplierApplication.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {
    private final ApplicationService applicationService;
    private final CompanyService companyService;
    private final FileService fileService;
    private final ProductService productService;
    private final ProductApplicationService productApplicationService;
    private final ExportService exportService;

    @PostMapping("/supplier/form")
    public ResponseEntity<String> handleFormSubmission(@ModelAttribute SupplierForm request) {
        Company company = companyService.createCompany(request.getCompanyName());

        String filePath = "";
        MultipartFile productCatalogue = request.getProductCatalogue();
        if (productCatalogue != null && !productCatalogue.isEmpty()) {
            filePath = fileService.storeFile(productCatalogue, request.getCompanyName(), request.getContactPerson());
        }
        Application application = applicationService.createApplication(company, request.getContactPerson(), request.getEmail(), request.getAddress(), request.getPhoneNumber(), request.getWebsite(), filePath, request.getComment());

        List<ProductAndDescription> productsDetail = request.getProducts();

        for (ProductAndDescription prod : productsDetail) {
            Product product = productService.createProduct(prod.getProduct(), prod.getDescription());
            ProductApplication productApplication = productApplicationService.crateProductApplication(product, application);
        }
        return ResponseEntity.ok("Form submitted successfully");
    }

    @GetMapping("/company")
    public ResponseEntity<CompanyResponse> getCompanyList() {
        List<Company> companyList = companyService.retrieveAllCompany();
        List<String> companyNames = companyList.stream()
                .map(Company::getCompanyName)
                .toList();
        CompanyResponse companyResponse = new CompanyResponse(companyNames);
        return ResponseEntity.ok(companyResponse);
    }

    @GetMapping("/application")
    public ResponseEntity<ApplicationResponse> getApplicationList(@RequestParam String companyName) {
        Company company = companyService.retrieveCompany(companyName);
        List<Application> applications = applicationService.retrieveApplicationList(company);
        List<ApplicationDetailResponse> list = applications.stream().map(ApplicationDetailResponse::new).toList();
        ApplicationResponse applicationResponse = new ApplicationResponse(companyName, list);
        return ResponseEntity.ok(applicationResponse);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProductList(@RequestParam Long applicationId) {
        Application application = applicationService.retrieveApplication(applicationId);
        List<ProductApplication> productApplications = productApplicationService.retrieveApplicationList(application);
        List<ProductDetailResponse> list = productApplications.stream().map(ProductDetailResponse::new).toList();
        ProductResponse productResponse = new ProductResponse(applicationId, list);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportToExcel() {
        byte[] excelFile = exportService.exportToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("application_details.xlsx")
                .build());

        return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }

    @GetMapping("/export/csv")
    public ResponseEntity<String> exportToCsv() {
        String csvContent = exportService.exportToCsv();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("application_details.csv")
                .build());

        return new ResponseEntity<>(csvContent, headers, HttpStatus.OK);
    }
}
