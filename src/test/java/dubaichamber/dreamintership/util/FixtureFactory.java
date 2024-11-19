package dubaichamber.dreamintership.util;

import dubaichamber.dreamintership.supplierApplication.entity.Application;
import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.entity.Product;
import dubaichamber.dreamintership.supplierApplication.entity.ProductApplication;
import dubaichamber.dreamintership.supplierApplication.model.ProductAndDescription;
import dubaichamber.dreamintership.supplierApplication.model.SupplierForm;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class FixtureFactory {
    public Product createProduct(String productName, String description) {
        Product entity = Product.builder().
                productName(productName)
                .description(description)
                .build();
        return entity;
    }


    public Company createCompany(String companyName) {
        return Company.builder()
                .companyName(companyName)
                .build();
    }

    public Application createApplication(Company company, String contactPerson, String email, String address, String phoneNumber, String webSite, String fileUrl, String comment) {
        return Application.builder()
                .applicationId(1L)
                .company(company)
                .email(email)
                .address(address)
                .phoneNumber(phoneNumber)
                .website(webSite)
                .productCatalogue(fileUrl)
                .comment(comment)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public ProductApplication createProductApplication(Product product, Application application) {
        return ProductApplication.builder()
                .productName(product)
                .applicationId(application)
                .build();
    }

    public SupplierForm createSupplierForm(String companyName, String contactPerson, String email, String address, String phoneNumber, String website, List<ProductAndDescription> products, MultipartFile productCatalogue, String comment) {
        SupplierForm supplierForm = new SupplierForm();
        supplierForm.setCompanyName(companyName);
        supplierForm.setContactPerson(contactPerson);
        supplierForm.setEmail(email);
        supplierForm.setAddress(address);
        supplierForm.setPhoneNumber(phoneNumber);
        supplierForm.setWebsite(website);
        supplierForm.setProducts(products);
        supplierForm.setProductCatalogue(productCatalogue);
        supplierForm.setComment(comment);
        return supplierForm;
    }
}
