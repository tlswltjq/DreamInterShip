package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Product;
import dubaichamber.dreamintership.supplierApplication.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    public Product retrieveProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        return product;
    }

    public Product retireveProductByProductName(String productName) {
        Product product = productRepository.findByProductName(productName).orElseThrow(NoSuchElementException::new);
        return product;
    }

    public Product deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        productRepository.delete(product);
        return product;
    }
}
