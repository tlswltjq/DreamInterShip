package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Product;
import dubaichamber.dreamintership.supplierApplication.repository.ProductRepository;
import dubaichamber.dreamintership.util.FixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private FixtureFactory factory = new FixtureFactory();
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    @Test
    void createProductTest() {
        String productName = "apple";
        String productDescription = "sweet";
        Product product = factory.createProduct(productName, productDescription);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product newProduct = productService.createProduct(productName, productDescription);

        verify(productRepository).save(any(Product.class));
        Assertions.assertThat(newProduct.getProductName()).isEqualTo(productName);
        Assertions.assertThat(newProduct.getDescription()).isEqualTo(productDescription);
    }
}