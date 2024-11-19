package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Application;
import dubaichamber.dreamintership.supplierApplication.entity.Product;
import dubaichamber.dreamintership.supplierApplication.entity.ProductApplication;
import dubaichamber.dreamintership.supplierApplication.entity.ProductApplicationId;
import dubaichamber.dreamintership.supplierApplication.repository.ProductApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {
    private final ProductApplicationRepository productApplicationRepository;

    public ProductApplication crateProductApplication(Product product, Application application) {
        return productApplicationRepository.save(
                ProductApplication.builder()
                        .productId(product)
                        .applicationId(application)
                        .build()
        );
    }

    public ProductApplication retrieveApplication(ProductApplicationId id) {
        ProductApplication productApplication = productApplicationRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return productApplication;
    }

    public List<ProductApplication> retrieveApplicationList(Application application) {
        return productApplicationRepository.findByApplicationId(application);
    }

    public List<ProductApplication> retrieveApplicationList(Product product) {
        return productApplicationRepository.findByProductId(product);
    }
}
