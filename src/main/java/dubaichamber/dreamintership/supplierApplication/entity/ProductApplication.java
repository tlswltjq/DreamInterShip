package dubaichamber.dreamintership.supplierApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductApplicationId.class)
@Table(name = "product_application")
public class ProductApplication {
    @Id
    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "application_id")
    private Application applicationId;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product productId;
}