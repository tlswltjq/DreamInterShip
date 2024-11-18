package dubaichamber.dreamintership.supplierApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAndDescription {
    private String product;
    private String description;
}