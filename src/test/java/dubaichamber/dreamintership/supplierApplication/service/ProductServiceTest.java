package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Product;
import dubaichamber.dreamintership.supplierApplication.repository.ProductRepository;
import dubaichamber.dreamintership.util.FixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private final FixtureFactory factory = new FixtureFactory();
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @DisplayName("Should create a product entity successfully")
    @Test
    void createProductTest() {
        String productName = "apple";
        String productDescription = "sweet";
        Product product = factory.createProduct(productName, productDescription);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product newProduct = productService.createProduct(productName, productDescription);

        verify(productRepository).save(any(Product.class));
        assertThat(product).isSameAs(newProduct);
    }

    @DisplayName("Should retrieve a product entity by name successfully")
    @Test
    void retrieveProductTest() {
        String productName = "apple";
        String productDescription = "sweet";
        Product product = factory.createProduct(productName, productDescription);

        when(productRepository.findById(productName)).thenReturn(Optional.ofNullable(product));

        Product retireveProduct = productService.retireveProduct(productName);

        verify(productRepository).findById(productName);
        assertThat(retireveProduct).isSameAs(product);
    }

    @DisplayName("Should throw NoSuchElementException when retrieving a non-existing product")
    @Test
    void failToRetrieveProductTest() {
        String productName = "nonExistingProduct";

        when(productRepository.findById(productName)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productService.retireveProduct(productName));

        verify(productRepository).findById(productName);
    }

    @DisplayName("Should delete a product entity by name successfully")
    @Test
    void deleteProductTest() {
        String productName = "apple";
        String productDescription = "sweet";
        Product product = factory.createProduct(productName, productDescription);

        when(productRepository.findById(productName)).thenReturn(Optional.ofNullable(product));

        Product deletedProduct = productService.deleteProduct(productName);

        verify(productRepository).findById(productName);
        verify(productRepository).delete(any(Product.class));
        assertThat(deletedProduct).isSameAs(product);
    }

    @DisplayName("Should throw NoSuchElementException when deleting a non-existing product")
    @Test
    void failToDeleteProductTest() {
        String productName = "nonExistingProduct";

        when(productRepository.findById(productName)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productService.deleteProduct(productName));

        verify(productRepository).findById(productName);
    }
}