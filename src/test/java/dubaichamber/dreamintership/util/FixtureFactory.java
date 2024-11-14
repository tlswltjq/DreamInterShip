package dubaichamber.dreamintership.util;

import dubaichamber.dreamintership.supplierApplication.entity.Application;
import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.entity.CompanyId;
import dubaichamber.dreamintership.supplierApplication.entity.Product;

import java.time.LocalDateTime;

public class FixtureFactory {
    public Product createProduct(String productName, String description) {
        Product entity = Product.builder().
                productName(productName)
                .description(description)
                .build();
        return entity;
    }

    public CompanyId createCompanyId(String companyName, String contactPerson) {
        return new CompanyId(companyName, contactPerson);
    }

    public Company createCompany(CompanyId companyId) {
        return Company.builder()
                .id(companyId)
                .build();
    }

    public Application createApplication(Company company,  String email, String address, String phoneNumber, String webSite, String fileUrl, String comment) {
        return Application.builder()
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

}
