package dubaichamber.dreamintership.supplierApplication.dto;

import dubaichamber.dreamintership.supplierApplication.entity.ProductApplication;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailResponse {
    private String productName;
    private String description;

    public ProductDetailResponse(ProductApplication productApplication) {
        this.productName = productApplication.getProductId().getProductName();
        this.description = productApplication.getProductId().getDescription();
    }
}
