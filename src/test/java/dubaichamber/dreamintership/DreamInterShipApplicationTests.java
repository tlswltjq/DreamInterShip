package dubaichamber.dreamintership;

import dubaichamber.dreamintership.supplierApplication.entity.Application;
import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.entity.Product;
import dubaichamber.dreamintership.supplierApplication.entity.ProductApplication;
import dubaichamber.dreamintership.supplierApplication.service.ApplicationService;
import dubaichamber.dreamintership.supplierApplication.service.CompanyService;
import dubaichamber.dreamintership.supplierApplication.service.ProductApplicationService;
import dubaichamber.dreamintership.supplierApplication.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DreamInterShipApplicationTests {

    @Autowired
    private ProductApplicationService productApplicationService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CompanyService companyService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateProductApplication() {
        // Create and save a Product
        Product product = productService.createProduct("TestProduct", "TestDescription");
        assertNotNull(product);
        Company company = companyService.createCompany("testCompany");
        // Create an Application
        Application application = applicationService.createApplication(company, "personA", "test@example.com", "123 Test St", "123-456-7890", "www.test.com", "Test Catalogue", "Test Comment");

        // Create and save a ProductApplication
        ProductApplication productApplication = productApplicationService.crateProductApplication(product, application);
        assertNotNull(productApplication);
    }
}