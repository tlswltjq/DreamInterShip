package dubaichamber.dreamintership.util;

import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.entity.CompanyId;
import dubaichamber.dreamintership.supplierApplication.entity.Product;

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
}
