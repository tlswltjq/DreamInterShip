package dubaichamber.dreamintership.supplierApplication.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductApplicationId implements Serializable {
    private Long applicationId;
    private String productName;
}