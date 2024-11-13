package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Product;
import dubaichamber.dreamintership.supplierApplication.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(String productName, String description) {
        return productRepository.save(Product.builder()
                .productName(productName)
                .description(description)
                .build());
    }
}
