package dubaichamber.dreamintership.supplierApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductResponse {
    private Long applicationId;
    private List<ProductDetailResponse> products;
}
