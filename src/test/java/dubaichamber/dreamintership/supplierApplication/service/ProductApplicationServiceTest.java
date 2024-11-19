package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Application;
import dubaichamber.dreamintership.supplierApplication.entity.Product;
import dubaichamber.dreamintership.supplierApplication.entity.ProductApplication;
import dubaichamber.dreamintership.supplierApplication.entity.ProductApplicationId;
import dubaichamber.dreamintership.supplierApplication.repository.ProductApplicationRepository;
import dubaichamber.dreamintership.util.FixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductApplicationServiceTest {
    private final FixtureFactory factory = new FixtureFactory();

    @Mock
    private ProductApplicationRepository productApplicationRepository;
    @InjectMocks
    private ProductApplicationService productApplicationService;

    @DisplayName("Should create productApplication entities using product and application entities")
    @Test
    void createProductApplicationTest() {
        Product product = mock(Product.class);
        Application application = mock(Application.class);
        ProductApplication productApplication = factory.createProductApplication(product, application);
        when(productApplicationRepository.save(any(ProductApplication.class))).thenReturn(productApplication);

        ProductApplication createdProductApplication = productApplicationService.crateProductApplication(product, application);

        verify(productApplicationRepository).save(any(ProductApplication.class));
        assertThat(createdProductApplication).isNotNull();
        assertThat(productApplication).isSameAs(createdProductApplication);
    }

    @DisplayName("Should find productApplication entities using application_id")
    @Test
    void retrieveProductApplicationUsingApplicationId() {
        ProductApplicationId id = new ProductApplicationId(1L, 1L);

        Product product = mock(Product.class);
        Application application = mock(Application.class);
        ProductApplication productApplication = new ProductApplication(application, product);

        when(productApplicationRepository.findById(id)).thenReturn(Optional.of(productApplication));

        ProductApplication retrievedProductApplication = productApplicationService.retrieveApplication(id);

        verify(productApplicationRepository).findById(id);
        assertThat(retrievedProductApplication).isNotNull();
        assertThat(retrievedProductApplication).isSameAs(productApplication);
    }

    @DisplayName("Should retrieve productApplication list using application")
    @Test
    void retrieveApplicationListByApplicationTest() {
        Application application = mock(Application.class);
        List<ProductApplication> productApplicationList = List.of(
                factory.createProductApplication(mock(Product.class), application),
                factory.createProductApplication(mock(Product.class), application)
        );

        when(productApplicationRepository.findByApplicationId(application)).thenReturn(productApplicationList);

        List<ProductApplication> retrievedList = productApplicationService.retrieveApplicationList(application);

        verify(productApplicationRepository).findByApplicationId(application);
        assertThat(retrievedList).isNotNull();
        assertThat(retrievedList).isSameAs(productApplicationList);
    }

    @DisplayName("Should retrieve productApplication list using product")
    @Test
    void retrieveApplicationListByProductTest() {
        Product product = mock(Product.class);
        List<ProductApplication> productApplicationList = List.of(
                factory.createProductApplication(product, mock(Application.class)),
                factory.createProductApplication(product, mock(Application.class))
        );

        when(productApplicationRepository.findByProductId(product)).thenReturn(productApplicationList);

        List<ProductApplication> retrievedList = productApplicationService.retrieveApplicationList(product);

        verify(productApplicationRepository).findByProductId(product);
        assertThat(retrievedList).isNotNull();
        assertThat(retrievedList).isSameAs(productApplicationList);
    }
}