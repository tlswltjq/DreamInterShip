package dubaichamber.dreamintership.util;

import dubaichamber.dreamintership.supplierApplication.entity.Product;

public class FixtureFactory {
    public Product createProduct(String productName, String description) {
        Product entity = Product.builder().
                productName(productName)
                .description(description)
                .build();
        return entity;
    }
}
