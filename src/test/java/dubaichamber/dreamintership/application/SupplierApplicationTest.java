package dubaichamber.dreamintership.application;

import dubaichamber.dreamintership.supplierApplication.api.ApiController;
import dubaichamber.dreamintership.supplierApplication.entity.*;
import dubaichamber.dreamintership.supplierApplication.model.ProductAndDescription;
import dubaichamber.dreamintership.supplierApplication.model.SupplierForm;
import dubaichamber.dreamintership.supplierApplication.service.*;
import dubaichamber.dreamintership.util.FixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@DisplayName("Application Submitting Test")
@ExtendWith(MockitoExtension.class)
public class SupplierApplicationTest {
    private final FixtureFactory factory = new FixtureFactory();

    @Mock
    ApplicationService applicationService;
    @Mock
    CompanyService companyService;
    @Mock
    FileService fileService;
    @Mock
    ProductService productService;
    @Mock
    ProductApplicationService productApplicationService;
    @InjectMocks
    ApiController apiController;

    @DisplayName("Test handleSupplierForm")
    @Test
    void testHandleSupplierForm() {
        String companyName = "new_company";
        String contactPerson = "Mr.Shin";
        String email = "wltjq1203@icloud.com";
        String address = "30 Chuchill Ave";
        String phoneNumber = "0401377916";
        String website = "https://www.github.com/tlswltjq";
        List<ProductAndDescription> products = List.of(new ProductAndDescription("Apple", "sweet"), new ProductAndDescription("lemon", "sour"));
        MockMultipartFile productCatalogue = new MockMultipartFile("fruit", "Fruits.zip", "application/zip", "sample zip".getBytes());
        String comment = "our products are good";
        String fileUrl = "/Users/sinjiseop/Projects/DreamInterShip/uploads/" + companyName + "_" + contactPerson + "_" + productCatalogue.getOriginalFilename();

        SupplierForm supplierForm = factory.createSupplierForm(companyName, contactPerson, email, address, phoneNumber, website, products, productCatalogue, comment);
        CompanyId companyId = factory.createCompanyId(companyName, contactPerson);
        Company company = factory.createCompany(companyId);
        Application application = factory.createApplication(company, email, address, phoneNumber, website, fileUrl, comment);

        when(companyService.createCompany(companyName, contactPerson)).thenReturn(company);
        when(fileService.storeFile(productCatalogue, companyName, contactPerson)).thenReturn(fileUrl);
        when(applicationService.createApplication(company, email, address, phoneNumber, website, fileUrl, comment)).thenReturn(application);
        when(productService.createProduct(any(String.class), any(String.class))).thenReturn(mock(Product.class));
        when(productApplicationService.crateProductApplication(any(Product.class), any(Application.class))).thenReturn(mock(ProductApplication.class));

        apiController.handleFormSubmission(supplierForm);

        verify(companyService).createCompany(companyName, contactPerson);
        verify(fileService).storeFile(productCatalogue, companyName, contactPerson);
        verify(applicationService).createApplication(company, email, address, phoneNumber, website, fileUrl, comment);
        verify(productService, times(products.size())).createProduct(any(String.class), any(String.class));
        verify(productApplicationService, times(products.size())).crateProductApplication(any(Product.class), any(Application.class));
    }
}
